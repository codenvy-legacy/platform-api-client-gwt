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
import com.codenvy.ide.MimeType;
import com.codenvy.ide.rest.AsyncRequestCallback;
import com.codenvy.ide.rest.AsyncRequestFactory;
import com.codenvy.ide.rest.HTTPHeader;
import com.codenvy.ide.ui.loader.Loader;
import com.google.inject.Inject;
import com.google.inject.name.Named;

/** @author Vitaly Parfonov */
public class ProjectClientServiceImpl implements ProjectClientService {
    private final String              IMPORT_PROJECT;
    private final String              UPDATE_PROJECT;
    private final String              LIST_PROJECTS;
    private final Loader              loader;
    private final AsyncRequestFactory asyncRequestFactory;

    @Inject
    protected ProjectClientServiceImpl(@Named("restContext") String restContext,
                                       @Named("workspaceId") String workspaceId,
                                       Loader loader,
                                       AsyncRequestFactory asyncRequestFactory) {
        this.loader = loader;
        this.asyncRequestFactory = asyncRequestFactory;
        IMPORT_PROJECT = restContext + "/project/" + workspaceId + "/import";
        UPDATE_PROJECT = restContext + "/project/" + workspaceId + "/update";
        LIST_PROJECTS = restContext + "/project/" + workspaceId + "/list";
    }

    @Override
    public void getProjects(AsyncRequestCallback<String> callback) {
        final String requestUrl = LIST_PROJECTS;
        loader.setMessage("Getting projects...");
        asyncRequestFactory.createGetRequest(requestUrl)
                           .header(HTTPHeader.ACCEPT, MimeType.APPLICATION_JSON)
                           .loader(loader)
                           .send(callback);
    }

    @Override
    public void updateProject(String projectName, ProjectDescriptor descriptor, AsyncRequestCallback<String> callback) {
        final String requestUrl = UPDATE_PROJECT + "?name=" + projectName;
        loader.setMessage("Updating project...");
        asyncRequestFactory.createPostRequest(requestUrl, descriptor)
                           .header(HTTPHeader.ACCEPT, MimeType.APPLICATION_JSON)
                           .loader(loader)
                           .send(callback);
    }

    @Override
    public void importProject(String projectName, ImportSourceDescriptor importSourceDescriptor,
                              AsyncRequestCallback<ProjectDescriptor> callback) {
        final String requestUrl = IMPORT_PROJECT + "?projectName=" + projectName;
        loader.setMessage("Creating new project...");
        asyncRequestFactory.createPostRequest(requestUrl, importSourceDescriptor)
                           .header(HTTPHeader.ACCEPT, MimeType.APPLICATION_JSON)
                           .loader(loader)
                           .send(callback);
    }
}
