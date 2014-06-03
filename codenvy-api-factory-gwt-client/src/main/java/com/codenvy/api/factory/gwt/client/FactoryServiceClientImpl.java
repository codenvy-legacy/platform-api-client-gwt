/*******************************************************************************
 * Copyright (c) 2012-2014 Codenvy, S.A.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Codenvy, S.A. - initial API and implementation
 *******************************************************************************/
package com.codenvy.api.factory.gwt.client;

import com.codenvy.api.factory.dto.Factory;
import com.codenvy.ide.MimeType;
import com.codenvy.ide.dto.DtoFactory;
import com.codenvy.ide.rest.AsyncRequest;
import com.codenvy.ide.rest.AsyncRequestCallback;
import com.codenvy.ide.rest.AsyncRequestFactory;
import com.codenvy.ide.rest.HTTPHeader;
import com.codenvy.ide.util.Config;
import com.codenvy.ide.websocket.Message;
import com.codenvy.ide.websocket.MessageBuilder;
import com.codenvy.ide.websocket.MessageBus;
import com.codenvy.ide.websocket.WebSocketException;
import com.codenvy.ide.websocket.rest.RequestCallback;
import com.google.gwt.http.client.RequestBuilder;
import com.google.inject.Inject;
import com.google.inject.Singleton;

import javax.validation.constraints.NotNull;

/**
* Implementation of {@link com.codenvy.api.factory.gwt.client.FactoryServiceClient} service.
*
* @author Vladyslav Zhukovskii
*/
@Singleton
public class FactoryServiceClientImpl implements FactoryServiceClient {
    private final DtoFactory dtoFactory;
    private AsyncRequestFactory asyncRequestFactory;
    private final MessageBus messageBus;

    @Inject
    public FactoryServiceClientImpl(DtoFactory dtoFactory,
                                    AsyncRequestFactory asyncRequestFactory,
                                    MessageBus messageBus) {
        this.dtoFactory = dtoFactory;
        this.asyncRequestFactory = asyncRequestFactory;
        this.messageBus = messageBus;
    }

    /** {@inheritDoc} */
    @Override
    public void getFactory(@NotNull String raw, boolean encoded, @NotNull AsyncRequestCallback<Factory> callback) {
        StringBuilder url = new StringBuilder("/api/factory");

        if (encoded) {
            url.append("/").append(raw).append("?");
        } else {
            url.append("/nonencoded?").append(raw).append("&");
        }

        url.append("legacy=true");

        asyncRequestFactory.createGetRequest(url.toString()).header(HTTPHeader.ACCEPT, MimeType.APPLICATION_JSON)
                .send(callback);
    }

    /** {@inheritDoc} */
    @Override
    public void acceptFactory(@NotNull Factory factory, @NotNull AsyncRequestCallback<Factory> callback) {

       final String requestUrl = "/api/factory-handler/" + Config.getWorkspaceId() + "/accept";

        asyncRequestFactory.createPostRequest(requestUrl, factory)
                           .header(HTTPHeader.CONTENT_TYPE, MimeType.APPLICATION_JSON)
                           .header(HTTPHeader.ACCEPT, MimeType.APPLICATION_JSON)
                           .send(callback);
    }

    /** {@inheritDoc} */
    @Override
    public void getFactorySnippet(String factoryId, String type, AsyncRequestCallback<String> callback) {
        final String requestUrl = "/api/factory/" + factoryId + "/snippet?type=" + type;
        asyncRequestFactory.createGetRequest(requestUrl).header(HTTPHeader.ACCEPT, MimeType.TEXT_PLAIN).send(callback);
    }
}
