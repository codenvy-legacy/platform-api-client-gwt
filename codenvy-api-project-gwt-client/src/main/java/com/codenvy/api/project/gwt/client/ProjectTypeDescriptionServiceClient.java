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
package com.codenvy.api.project.gwt.client;

import com.codenvy.api.project.shared.dto.ProjectTypeDescriptor;
import com.codenvy.ide.collections.Array;
import com.codenvy.ide.rest.AsyncRequestCallback;

/**
 * Client for Project Type Description service.
 *
 * @author Artem Zatsarynnyy
 */
public interface ProjectTypeDescriptionServiceClient {
    /**
     * Get information about all registered project types
     *
     * @param callback
     *         the callback to use for the response
     */
    public void getProjectTypes(AsyncRequestCallback<Array<ProjectTypeDescriptor>> callback);
}
