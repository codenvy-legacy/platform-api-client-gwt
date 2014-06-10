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
package com.codenvy.api.user.gwt.client;

import com.codenvy.api.user.shared.dto.User;
import com.codenvy.ide.MimeType;
import com.codenvy.ide.rest.AsyncRequestCallback;
import com.codenvy.ide.rest.AsyncRequestFactory;
import com.codenvy.ide.ui.loader.Loader;
import com.google.inject.Inject;
import com.google.inject.name.Named;

import javax.validation.constraints.NotNull;

import static com.codenvy.ide.rest.HTTPHeader.ACCEPT;
import static com.codenvy.ide.rest.HTTPHeader.CONTENT_TYPE;
import static com.google.gwt.http.client.RequestBuilder.DELETE;

/**
 * Implementation of {@link UserServiceClient}.
 * 
 * @author Ann Shumilova
 */
public class UserServiceClientImpl implements UserServiceClient {
    private final String              USER;
    private final String              CREATE;
    private final String              FIND;
    private final String              PASSWORD;
    private final Loader              loader;
    private final AsyncRequestFactory asyncRequestFactory;

    @Inject
    protected UserServiceClientImpl(@Named("restContext") String restContext,
                                    Loader loader,
                                    AsyncRequestFactory asyncRequestFactory) {
        this.loader = loader;
        this.asyncRequestFactory = asyncRequestFactory;
        USER = restContext + "/user/";
        CREATE = USER + "create";
        FIND = USER + "find";
        PASSWORD = USER + "password";
    }

    /** {@inheritDoc} */
    @Override
    public void createUser(@NotNull String token, boolean isTemporary, AsyncRequestCallback<User> callback) {
        StringBuilder requestUrl = new StringBuilder(CREATE);
        requestUrl.append("?token=").append(token).append("&temporary=").append(isTemporary);

        loader.setMessage("Creating user...");
        asyncRequestFactory.createPostRequest(requestUrl.toString(), null)
                           .header(ACCEPT, MimeType.APPLICATION_JSON)
                           .loader(loader)
                           .send(callback);
    }

    /** {@inheritDoc} */
    @Override
    public void getCurrentUser(AsyncRequestCallback<User> callback) {
        loader.setMessage("Retrieving current user...");

        asyncRequestFactory.createGetRequest(USER).header(ACCEPT, MimeType.APPLICATION_JSON).loader(loader).send(callback);
    }

    /** {@inheritDoc} */
    @Override
    public void updatePassword(@NotNull String password, AsyncRequestCallback<Void> callback) {
        // TODO form parameter
        String requestUrl = PASSWORD + "?password=" + password;
        loader.setMessage("Updating user's password...");

        asyncRequestFactory.createPostRequest(requestUrl, null).header(CONTENT_TYPE, MimeType.APPLICATION_FORM_URLENCODED).loader(loader)
                           .send(callback);
    }

    /** {@inheritDoc} */
    @Override
    public void getUserById(@NotNull String id, AsyncRequestCallback<User> callback) {
        String requestUrl = USER + id;

        loader.setMessage("Retrieving user...");
        asyncRequestFactory.createGetRequest(requestUrl).header(ACCEPT, MimeType.APPLICATION_JSON).loader(loader).send(callback);
    }

    /** {@inheritDoc} */
    @Override
    public void getUserByEmail(@NotNull String email, AsyncRequestCallback<User> callback) {
        String requestUrl = FIND + "?email=" + email;

        loader.setMessage("Retrieving user...");
        asyncRequestFactory.createGetRequest(requestUrl).header(ACCEPT, MimeType.APPLICATION_JSON).loader(loader).send(callback);
    }

    /** {@inheritDoc} */
    @Override
    public void removeUser(@NotNull String id, AsyncRequestCallback<Void> callback) {
        String requestUrl = USER + id;

        loader.setMessage("Deleting user...");
        asyncRequestFactory.createRequest(DELETE, requestUrl, null, false)
                           .loader(loader)
                           .send(callback);
    }

}
