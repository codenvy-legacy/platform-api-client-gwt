/*
 * CODENVY CONFIDENTIAL
 * __________________
 *
 *  [2012] - [2014] Codenvy, S.A.
 *  All Rights Reserved.
 *
 * NOTICE:  All information contained herein is, and remains
 * the property of Codenvy S.A. and its suppliers,
 * if any.  The intellectual and technical concepts contained
 * herein are proprietary to Codenvy S.A.
 * and its suppliers and may be covered by U.S. and Foreign Patents,
 * patents in process, and are protected by trade secret or copyright law.
 * Dissemination of this information or reproduction of this material
 * is strictly forbidden unless prior written permission is obtained
 * from Codenvy S.A..
 */
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
