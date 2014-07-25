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

import com.codenvy.api.user.shared.dto.ProfileDescriptor;
import com.codenvy.ide.MimeType;
import com.codenvy.ide.dto.DtoFactory;
import com.codenvy.ide.json.JsonHelper;
import com.codenvy.ide.rest.AsyncRequestCallback;
import com.codenvy.ide.rest.AsyncRequestFactory;
import com.codenvy.ide.ui.loader.Loader;
import com.google.inject.Inject;
import com.google.inject.name.Named;

import java.util.List;
import java.util.Map;

import static com.codenvy.ide.rest.HTTPHeader.ACCEPT;
import static com.codenvy.ide.rest.HTTPHeader.CONTENT_TYPE;

/**
 * Implementation for {@link UserProfileServiceClient}.
 * 
 * @author Ann Shumilova
 */
public class UserProfileServiceClientImpl implements UserProfileServiceClient {
    private final String              PROFILE;
    private final String              PREFS;
    private final Loader              loader;
    private final AsyncRequestFactory asyncRequestFactory;
    private final DtoFactory          dtoFactory;

    @Inject
    protected UserProfileServiceClientImpl(@Named("restContext") String restContext,
                                           Loader loader,
                                           AsyncRequestFactory asyncRequestFactory, DtoFactory dtoFactory) {
        this.loader = loader;
        this.asyncRequestFactory = asyncRequestFactory;
        this.dtoFactory = dtoFactory;
        PROFILE = restContext + "/profile/";
        PREFS = PROFILE + "prefs";
    }

    /** {@inheritDoc} */
    @Override
    public void getCurrentProfile(String filter, AsyncRequestCallback<ProfileDescriptor> callback) {
        String requestUrl = (filter != null && !filter.isEmpty()) ? PROFILE + "?filter=" + filter : PROFILE;

        loader.setMessage("Retrieving current user's profile...");

        asyncRequestFactory.createGetRequest(requestUrl).header(ACCEPT, MimeType.APPLICATION_JSON).loader(loader).send(callback);
    }

    /** {@inheritDoc} */
    @Override
    public void updateCurrentProfile(Map<String, String> updates, AsyncRequestCallback<ProfileDescriptor> callback) {
        loader.setMessage("Updating current user's profile...");
        asyncRequestFactory.createPostRequest(PROFILE, null).header(ACCEPT, MimeType.APPLICATION_JSON)
                           .header(CONTENT_TYPE, MimeType.APPLICATION_JSON).data(dtoFactory.toJson(updates)).loader(loader).send(callback);
    }

    /** {@inheritDoc} */
    @Override
    public void getProfileById(String id, AsyncRequestCallback<ProfileDescriptor> callback) {
        String requestUrl = PROFILE + id;

        loader.setMessage("Getting user's profile...");
        asyncRequestFactory.createGetRequest(requestUrl).header(ACCEPT, MimeType.APPLICATION_JSON).loader(loader).send(callback);
    }

    /** {@inheritDoc} */
    @Override
    public void updateProfile(String id, Map<String, String> updates, AsyncRequestCallback<ProfileDescriptor> callback) {
        String requestUrl = PROFILE + id;

        loader.setMessage("Updating user's profile...");
        asyncRequestFactory.createPostRequest(requestUrl, null).header(ACCEPT, MimeType.APPLICATION_JSON)
                           .header(CONTENT_TYPE, MimeType.APPLICATION_JSON).data(dtoFactory.toJson(updates)).loader(loader).send(callback);
    }

    /** {@inheritDoc} */
    @Override
    public void updatePreferences(Map<String, String> prefsToUpdate, AsyncRequestCallback<ProfileDescriptor> callback) {
        final String data = JsonHelper.toJson(prefsToUpdate);
        asyncRequestFactory.createPostRequest(PREFS, null).header(ACCEPT, MimeType.APPLICATION_JSON)
                           .header(CONTENT_TYPE, MimeType.APPLICATION_JSON).data(data).loader(loader).send(callback);
    }

}
