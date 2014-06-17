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
package com.codenvy.ide.rest;

import com.codenvy.ide.MimeType;
import com.codenvy.ide.dto.DtoFactory;
import com.google.gwt.http.client.RequestBuilder;
import com.google.inject.Inject;

/**
 * Provides implementations of {@link AsyncRequest} instances.
 *
 * @author Artem Zatsarynnyy
 */
public class AsyncRequestFactory {
    private static final String DTO_CONTENT_TYPE = MimeType.APPLICATION_JSON;
    private final DtoFactory dtoFactory;

    @Inject
    public AsyncRequestFactory(DtoFactory dtoFactory) {
        this.dtoFactory = dtoFactory;
    }

    /**
     * Creates new GET request to the specified {@code url}.
     *
     * @param url
     *         request URL
     * @return new {@link AsyncRequest} instance to send GET request
     */
    public AsyncRequest createGetRequest(String url) {
        return createGetRequest(url, false);
    }

    /**
     * Creates new GET request to the specified {@code url}.
     *
     * @param url
     *         request URL
     * @param async
     *         if <b>true</b> - request will be sent in asynchronous mode
     * @return new {@link AsyncRequest} instance to send GET request
     */
    public AsyncRequest createGetRequest(String url, boolean async) {
        return createRequest(RequestBuilder.GET, url, null, async);
    }

    /**
     * Creates new POST request to the specified {@code url} with the provided {@code data}.
     *
     * @param url
     *         request URL
     * @param dtoData
     *         the DTO to send as body of the request. Must implement {@code com.codenvy.dto.shared.DTO} interface. May be {@code null}.
     * @return new {@link AsyncRequest} instance to send POST request
     * @see com.codenvy.dto.shared.DTO
     */
    public AsyncRequest createPostRequest(String url, Object dtoData) {
        return createPostRequest(url, dtoData, false);
    }

    /**
     * Creates new POST request to the specified {@code url} with the provided {@code data}.
     *
     * @param url
     *         request URL
     * @param dtoData
     *         the DTO to send as body of the request. Must implement {@code com.codenvy.dto.shared.DTO} interface. May be {@code null}.
     * @param async
     *         if <b>true</b> - request will be sent in asynchronous mode
     * @return new {@link AsyncRequest} instance to send POST request
     * @see com.codenvy.dto.shared.DTO
     */
    public AsyncRequest createPostRequest(String url, Object dtoData, boolean async) {
        return createRequest(RequestBuilder.POST, url, dtoData, async);
    }

    /**
     * Creates new HTTP request to the specified {@code url}.
     *
     * @param method
     *         request method
     * @param url
     *         request URL
     * @param dtoBody
     *         the DTO to send as body of the request. Must implement {@code com.codenvy.dto.shared.DTO} interface. May be {@code null}.
     * @param async
     *         if <b>true</b> - request will be sent in asynchronous mode
     * @return new {@link AsyncRequest} instance to send POST request
     * @see com.codenvy.dto.shared.DTO
     */
    public AsyncRequest createRequest(RequestBuilder.Method method, String url, Object dtoBody, boolean async) {
        AsyncRequest asyncRequest = new AsyncRequest(method, url, async);
        if (dtoBody != null) {
            asyncRequest.data(dtoFactory.toJson(dtoBody));
            asyncRequest.header(HTTPHeader.CONTENT_TYPE, DTO_CONTENT_TYPE);
        }
        return asyncRequest;
    }
}
