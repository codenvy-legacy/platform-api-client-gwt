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

import com.codenvy.api.project.shared.dto.ProjectTypeDefinition;
import com.codenvy.ide.collections.Array;
import com.codenvy.ide.rest.AsyncRequestCallback;
import com.codenvy.ide.rest.AsyncRequestFactory;
import com.codenvy.ide.rest.AsyncRequestLoader;
import com.google.inject.Inject;
import com.google.inject.name.Named;

import static com.codenvy.ide.MimeType.APPLICATION_JSON;
import static com.codenvy.ide.rest.HTTPHeader.ACCEPT;

/**
 * The implementation of {@link ProjectTypeServiceClient}.
 *
 * @author Artem Zatsarynnyy
 */
public class ProjectTypeServiceClientImpl implements ProjectTypeServiceClient {
    private final String              baseUrl;
    private final AsyncRequestFactory asyncRequestFactory;
    private final AsyncRequestLoader  loader;

    @Inject
    protected ProjectTypeServiceClientImpl(@Named("restContext") String restContext,
                                           AsyncRequestLoader loader,
                                           AsyncRequestFactory asyncRequestFactory) {
        this.loader = loader;
        this.asyncRequestFactory = asyncRequestFactory;
        baseUrl = restContext + "/project-type";
    }

    @Override
    public void getProjectTypes(AsyncRequestCallback<Array<ProjectTypeDefinition>> callback) {
        asyncRequestFactory.createGetRequest(baseUrl).header(ACCEPT, APPLICATION_JSON).loader(loader).send(callback);
    }
}