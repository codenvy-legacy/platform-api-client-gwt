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
package com.codenvy.api.user.gwt.client;

import com.codenvy.api.user.shared.dto.Attribute;
import com.codenvy.api.user.shared.dto.Profile;
import com.codenvy.ide.MimeType;
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

    @Inject
    protected UserProfileServiceClientImpl(@Named("restContext") String restContext,
                                           Loader loader,
                                           AsyncRequestFactory asyncRequestFactory) {
        this.loader = loader;
        this.asyncRequestFactory = asyncRequestFactory;
        PROFILE = restContext + "/profile/";
        PREFS = PROFILE + "prefs";
    }

    /** {@inheritDoc} */
    @Override
    public void getCurrentProfile(String filter, AsyncRequestCallback<Profile> callback) {
        String requestUrl = (filter != null && !filter.isEmpty()) ? PROFILE + "?filter=" + filter : PROFILE;

        loader.setMessage("Retrieving current user's profile...");

        asyncRequestFactory.createGetRequest(requestUrl).header(ACCEPT, MimeType.APPLICATION_JSON).loader(loader).send(callback);
    }

    /** {@inheritDoc} */
    @Override
    public void updateCurrentProfile(List<Attribute> updates, AsyncRequestCallback<Profile> callback) {
        loader.setMessage("Updating current user's profile...");
        // TODO check posted data
        asyncRequestFactory.createPostRequest(PROFILE, null).header(ACCEPT, MimeType.APPLICATION_JSON)
                           .header(CONTENT_TYPE, MimeType.APPLICATION_JSON).data(null).loader(loader).send(callback);
    }

    /** {@inheritDoc} */
    @Override
    public void getProfileById(String id, AsyncRequestCallback<Profile> callback) {
        String requestUrl = PROFILE + id;

        loader.setMessage("Getting user's profile...");
        asyncRequestFactory.createGetRequest(requestUrl).header(ACCEPT, MimeType.APPLICATION_JSON).loader(loader).send(callback);
    }

    /** {@inheritDoc} */
    @Override
    public void updateProfile(String id, List<Attribute> updates, AsyncRequestCallback<Profile> callback) {
        String requestUrl = PROFILE + id;

        loader.setMessage("Updating user's profile...");
        // TODO data
        asyncRequestFactory.createPostRequest(requestUrl, null).header(ACCEPT, MimeType.APPLICATION_JSON)
                           .header(CONTENT_TYPE, MimeType.APPLICATION_JSON).data(null).loader(loader).send(callback);
    }

    /** {@inheritDoc} */
    @Override
    public void updatePreferences(Map<String, String> prefsToUpdate, AsyncRequestCallback<Profile> callback) {
        final String data = JsonHelper.toJson(prefsToUpdate);
        asyncRequestFactory.createPostRequest(PREFS, null).header(ACCEPT, MimeType.APPLICATION_JSON)
                           .header(CONTENT_TYPE, MimeType.APPLICATION_JSON).data(data).loader(loader).send(callback);
    }

}
