/*******************************************************************************
 * Copyright (c) 2012-2015 Codenvy, S.A.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Codenvy, S.A. - initial API and implementation
 *******************************************************************************/
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
