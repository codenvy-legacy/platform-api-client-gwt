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

import com.codenvy.api.project.shared.dto.ProjectImporterDescriptor;
import com.codenvy.ide.MimeType;
import com.codenvy.ide.collections.Array;
import com.codenvy.ide.rest.AsyncRequestCallback;
import com.codenvy.ide.rest.AsyncRequestFactory;
import com.codenvy.ide.rest.HTTPHeader;
import com.google.inject.name.Named;

import javax.inject.Inject;

/**
 * @author Vitaly Parfonov
 */
public class ProjectImportersServiceClientImpl implements ProjectImportersServiceClient {


    private String              restContext;
    private AsyncRequestFactory asyncRequestFactory;

    @Inject
    public ProjectImportersServiceClientImpl(@Named("restContext") String restContext,
                                             AsyncRequestFactory asyncRequestFactory) {
        this.restContext = restContext;
        this.asyncRequestFactory = asyncRequestFactory;
    }

    @Override
    public void getProjectImporters(AsyncRequestCallback<Array<ProjectImporterDescriptor>> callback) {
        asyncRequestFactory.createGetRequest(restContext + "/project-importers")
                           .header(HTTPHeader.CONTENT_TYPE, MimeType.APPLICATION_JSON)
                           .send(callback);

    }
}
