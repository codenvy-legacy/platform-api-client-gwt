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

import com.codenvy.api.project.shared.dto.ProjectTemplateDescriptor;
import com.codenvy.ide.collections.Array;
import com.codenvy.ide.rest.AsyncRequestCallback;
import com.codenvy.ide.rest.AsyncRequestFactory;
import com.codenvy.ide.rest.AsyncRequestLoader;
import com.google.inject.Inject;
import com.google.inject.name.Named;

import javax.annotation.Nonnull;

import static com.codenvy.ide.MimeType.APPLICATION_JSON;
import static com.codenvy.ide.rest.HTTPHeader.ACCEPT;

/**
 * The implementation for {@link ProjectTemplateServiceClient}.
 *
 * @author Artem Zatsarynnyy
 */
public class ProjectTemplateServiceClientImpl implements ProjectTemplateServiceClient {
    private final String              baseUrl;
    private final AsyncRequestFactory asyncRequestFactory;
    private final AsyncRequestLoader  loader;

    @Inject
    protected ProjectTemplateServiceClientImpl(@Named("restContext") String restContext,
                                               AsyncRequestFactory asyncRequestFactory,
                                               AsyncRequestLoader loader) {
        this.asyncRequestFactory = asyncRequestFactory;
        this.loader = loader;
        baseUrl = restContext + "/project-template";
    }

    @Override
    public void getProjectTemplates(@Nonnull String projectTypeId,
                                    @Nonnull AsyncRequestCallback<Array<ProjectTemplateDescriptor>> callback) {
        final String requestUrl = baseUrl + projectTypeId;
        asyncRequestFactory.createGetRequest(requestUrl).header(ACCEPT, APPLICATION_JSON).loader(loader).send(callback);
    }

    @Override
    public void getProjectTemplates(@Nonnull AsyncRequestCallback<Array<ProjectTemplateDescriptor>> callback) {
        asyncRequestFactory.createGetRequest(baseUrl).header(ACCEPT, APPLICATION_JSON).loader(loader).send(callback);
    }
}