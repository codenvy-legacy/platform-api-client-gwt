/*******************************************************************************
 * Copyright (c) 2012-2014 Codenvy, S.A.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Codenvy, S.A. - initial API and implementation
 *******************************************************************************/
package com.codenvy.api.project.gwt.client;

import com.codenvy.api.project.shared.dto.ProjectTypeDescriptor;
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
    private static String              GET_DESCRIPTIONS;
    private final  String              restContext;
    private final  AsyncRequestFactory asyncRequestFactory;
    private        AsyncRequestLoader  loader;

    @Inject
    protected ProjectTypeServiceClientImpl(@Named("restContext") String restContext, AsyncRequestLoader loader,
                                           AsyncRequestFactory asyncRequestFactory) {
        this.restContext = restContext;
        this.loader = loader;
        this.asyncRequestFactory = asyncRequestFactory;
        GET_DESCRIPTIONS = "/project-type";
    }

    @Override
    public void getProjectTypes(AsyncRequestCallback<Array<ProjectTypeDescriptor>> callback) {
        final String requestUrl = restContext + GET_DESCRIPTIONS;
        asyncRequestFactory.createGetRequest(requestUrl).header(ACCEPT, APPLICATION_JSON).loader(loader).send(callback);
    }
}