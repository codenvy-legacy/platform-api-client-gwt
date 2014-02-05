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
import com.codenvy.ide.dto.DtoFactory;
import com.codenvy.ide.rest.AsyncRequest;
import com.codenvy.ide.rest.AsyncRequestCallback;
import com.codenvy.ide.rest.AsyncRequestLoader;
import com.codenvy.ide.rest.HTTPHeader;
import com.codenvy.ide.MimeType;
import com.codenvy.ide.ui.loader.Loader;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.RequestException;
import com.google.inject.Inject;
import com.google.inject.name.Named;

/**
 * @author Vitaly Parfonov
 */
public class ProjectClientServiceImpl implements ProjectClientService {

    private final String             IMPORT_PROJECT;
    private final Loader             loader;
    private final DtoFactory         dtoFactory;


    @Inject
    protected ProjectClientServiceImpl(@Named("restContext") String restContext,
                                       @Named("workspaceId") String workspaceId,
                                       DtoFactory dtoFactory,
                                       Loader loader) {
        this.dtoFactory = dtoFactory;
        this.loader = loader;
        IMPORT_PROJECT = restContext + "/project/" + workspaceId + "/import";
    }

    @Override
    public void importProject(String projectName, ImportSourceDescriptor importSourceDescriptor, AsyncRequestCallback<String> callback)
            throws RequestException {
        final String uri = IMPORT_PROJECT + "?projectName=" + projectName;
        String json = dtoFactory.toJson(importSourceDescriptor);

        loader.setMessage("Creating new project...");
        AsyncRequest.build(RequestBuilder.POST, uri)
                    .header(HTTPHeader.CONTENT_TYPE, MimeType.APPLICATION_JSON)
                    .header(HTTPHeader.ACCEPT, MimeType.APPLICATION_JSON)
                    .loader(loader).data(json)
                    .send(callback);
    }
}
