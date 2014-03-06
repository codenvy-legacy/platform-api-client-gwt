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
import com.codenvy.ide.rest.AsyncRequestCallback;

import javax.validation.constraints.NotNull;

import java.util.List;
import java.util.Map;

/**
 *  GWT Client for User Profile Service.
 * 
 * @author Ann Shumilova
 */
public interface UserProfileServiceClient {
    
    /**
     * Get current user's profile.
     * 
     * @param filter
     * @param callback
     */
    void getCurrentProfile(String filter, AsyncRequestCallback<Profile> callback);
    
    /**
     * Update current user's profile.
     * 
     * @param updates attributes to update
     * @param callback
     */
    void updateCurrentProfile(@NotNull List<Attribute> updates, AsyncRequestCallback<Profile> callback);

    /**
     * Get profile by id.
     * 
     * @param id profile's id
     * @param callback
     */
    void getProfileById(@NotNull String id, AsyncRequestCallback<Profile> callback);

    /**
     * Update profile.
     * 
     * @param id profile's id
     * @param updates attributes to update
     * @param callback
     */
    void updateProfile(@NotNull String id, List<Attribute> updates, AsyncRequestCallback<Profile> callback);

    /**
     * Update preferences.
     * 
     * @param prefsToUpdate preferences to update
     * @param callback
     */
    void updatePreferences(@NotNull Map<String, String> prefsToUpdate, AsyncRequestCallback<Profile> callback);
}
