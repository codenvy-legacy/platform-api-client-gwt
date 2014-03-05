/*
 * CODENVY CONFIDENTIAL
 * __________________
 * 
 * [2012] - [$today.year] Codenvy, S.A. 
 * All Rights Reserved.
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

import com.codenvy.api.user.shared.dto.User;
import com.codenvy.ide.rest.AsyncRequestCallback;

import javax.validation.constraints.NotNull;

/**
 * GWT Client for User Service.
 * 
 * @author Ann Shumilova
 */
public interface UserServiceClient {

    /**
     * Create new user.
     * 
     * @param token user's token
     * @param isTemporary if <code>true</code> - is temporary user
     * @param callback
     */
    public void createUser(@NotNull String token, boolean isTemporary, AsyncRequestCallback<User> callback);

    /**
     * Get current user's information.
     * 
     * @param callback
     */
    public void getCurrentUser(AsyncRequestCallback<User> callback);

    /**
     * Update user's password.
     * 
     * @param password new password
     * @param callback
     */
    public void updatePassword(@NotNull String password, AsyncRequestCallback<Void> callback);

    /**
     * Get user's information by its id.
     * 
     * @param id user's id
     * @param callback
     */
    public void getUserById(@NotNull String id, AsyncRequestCallback<User> callback);

    /**
     * Get user's information by its email.
     * 
     * @param email user's email
     * @param callback
     */
    public void getUserByEmail(@NotNull String email, AsyncRequestCallback<User> callback);

    /**
     * Remove user.
     * 
     * @param id user's id to remove
     * @param callback
     */
    public void removeUser(@NotNull String id, AsyncRequestCallback<Void> callback);
}
