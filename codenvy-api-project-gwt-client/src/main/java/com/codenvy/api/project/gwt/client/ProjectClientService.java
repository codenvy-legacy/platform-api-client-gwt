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
package com.codenvy.api.project.gwt.client;

import com.codenvy.api.project.shared.dto.ImportSourceDescriptor;
import com.codenvy.api.project.shared.dto.ProjectDescriptor;
import com.codenvy.ide.rest.AsyncRequestCallback;

/** @author Vitaly Parfonov */
public interface ProjectClientService {

    /**
     * Get all projects.
     *
     * @param callback
     */
    public void getProjects(AsyncRequestCallback<String> callback);

    /**
     * Update project.
     *
     * @param projectName
     *         name of the project to update
     * @param descriptor
     *         descriptor of the project
     * @param callback
     */
    public void updateProject(String projectName, ProjectDescriptor descriptor, AsyncRequestCallback<String> callback);

    /**
     * Import sources into project.
     *
     * @param projectName
     *         name of the project to import sources
     * @param importSourceDescriptor
     *         {@link ImportSourceDescriptor}
     * @param callback
     */
    public void importProject(String projectName, ImportSourceDescriptor importSourceDescriptor,
                              AsyncRequestCallback<ProjectDescriptor> callback);
}
