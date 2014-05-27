package com.codenvy.api.factory.gwt.client;

import com.codenvy.api.factory.dto.Factory;
import com.codenvy.ide.MimeType;
import com.codenvy.ide.dto.DtoFactory;
import com.codenvy.ide.rest.AsyncRequestCallback;
import com.codenvy.ide.rest.AsyncRequestFactory;
import com.codenvy.ide.rest.HTTPHeader;
import com.codenvy.ide.ui.loader.Loader;
import com.codenvy.ide.util.Config;
import com.codenvy.ide.websocket.Message;
import com.codenvy.ide.websocket.MessageBuilder;
import com.codenvy.ide.websocket.MessageBus;
import com.codenvy.ide.websocket.WebSocketException;
import com.codenvy.ide.websocket.rest.RequestCallback;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.user.client.Window;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.google.inject.name.Named;

import javax.validation.constraints.NotNull;

import static com.codenvy.ide.MimeType.APPLICATION_JSON;
import static com.codenvy.ide.MimeType.TEXT_PLAIN;
import static com.codenvy.ide.rest.HTTPHeader.ACCEPT;
import static com.codenvy.ide.rest.HTTPHeader.CONTENTTYPE;

/**
 * Implementation of {@link com.codenvy.api.factory.gwt.client.FactoryServiceClient} service.
 * 
 * @author Vladyslav Zhukovskii
 */
@Singleton
public class FactoryServiceClientImpl implements FactoryServiceClient {
    private final DtoFactory          dtoFactory;
    private final MessageBus          messageBus;
    private final AsyncRequestFactory asyncRequestFactory;
    private final String              baseHttpUrl;
    private final Loader              loader;

    @Inject
    public FactoryServiceClientImpl(DtoFactory dtoFactory,
                                    MessageBus messageBus,
                                    AsyncRequestFactory asyncRequestFactory,
                                    @Named("restContext") String restContext,
                                    Loader loader) {
        this.dtoFactory = dtoFactory;
        this.loader = loader;
        this.messageBus = messageBus;
        this.asyncRequestFactory = asyncRequestFactory;
        this.baseHttpUrl = restContext + "/factory/";
    }

    /** {@inheritDoc} */
    @Override
    public void getFactory(@NotNull String raw, boolean encoded, @NotNull RequestCallback<Factory> callback) throws WebSocketException {
        StringBuilder url = new StringBuilder("/factory");

        if (encoded) {
            url.append("/").append(raw).append("?");
        } else {
            url.append("/nonencoded?").append(raw).append("&");
        }

        url.append("legacy=true");

        Message message =
                          new MessageBuilder(RequestBuilder.GET, url.toString()).header(HTTPHeader.ACCEPT, MimeType.APPLICATION_JSON)
                                                                                .build();
        messageBus.send(message, callback);
    }

    /** {@inheritDoc} */
    @Override
    public void acceptFactory(@NotNull Factory factory, @NotNull RequestCallback<Factory> callback) throws WebSocketException {
        Message message =
                          new MessageBuilder(RequestBuilder.POST, "/factory-handler/" + Config.getWorkspaceId() + "/accept")
                                                                                                                            .header(HTTPHeader.CONTENT_TYPE,
                                                                                                                                    MimeType.APPLICATION_JSON)
                                                                                                                            .header(HTTPHeader.ACCEPT,
                                                                                                                                    MimeType.APPLICATION_JSON)
                                                                                                                            .data(dtoFactory.toJson(factory))
                                                                                                                            .build();

        messageBus.send(message, callback);
    }

    /** {@inheritDoc} */
    @Override
    public void getFactorySnippet(String raw, boolean isEncoded, String type, @NotNull AsyncRequestCallback<String> callback) {
        StringBuilder url = new StringBuilder(baseHttpUrl);
        url.append(isEncoded ? raw : "nonencoded");
        url.append("/snippet?");
        if (!isEncoded) {
            url.append(raw).append("&");
        }
        url.append("type=").append(type);

        asyncRequestFactory.createGetRequest(url.toString())
                           .loader(loader)
                           .header(ACCEPT, TEXT_PLAIN)
                           .send(callback);

    }

}
