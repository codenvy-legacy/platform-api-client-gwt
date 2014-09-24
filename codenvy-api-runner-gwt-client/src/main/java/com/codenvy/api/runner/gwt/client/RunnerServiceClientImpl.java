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
package com.codenvy.api.runner.gwt.client;

import com.codenvy.api.core.rest.shared.dto.Link;
import com.codenvy.api.runner.dto.ApplicationProcessDescriptor;
import com.codenvy.api.runner.dto.ResourcesDescriptor;
import com.codenvy.api.runner.dto.RunOptions;
import com.codenvy.api.runner.dto.RunnerDescriptor;
import com.codenvy.ide.collections.Array;
import com.codenvy.ide.rest.AsyncRequestCallback;
import com.codenvy.ide.rest.AsyncRequestFactory;
import com.codenvy.ide.rest.AsyncRequestLoader;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.google.inject.name.Named;

/**
 * Implementation of {@link RunnerServiceClient} service.
 *
 * @author Artem Zatsarynnyy
 */
@Singleton
public class RunnerServiceClientImpl implements RunnerServiceClient {
    private final String              baseUrl;
    private final String              workspaceId;
    private final AsyncRequestLoader  loader;
    private final AsyncRequestFactory asyncRequestFactory;

    @Inject
    public RunnerServiceClientImpl(@Named("restContext") String baseUrl,
                                   @Named("workspaceId") String workspaceId,
                                   AsyncRequestLoader loader,
                                   AsyncRequestFactory asyncRequestFactory) {
        this.baseUrl = baseUrl;
        this.workspaceId = workspaceId;
        this.loader = loader;
        this.asyncRequestFactory = asyncRequestFactory;
    }

    /** {@inheritDoc} */
    @Override
    public void run(String projectName, RunOptions runOptions, AsyncRequestCallback<ApplicationProcessDescriptor> callback) {
        final String requestUrl = baseUrl + "/runner/" + workspaceId + "/run";
        final String params = "project=" + projectName;
        asyncRequestFactory.createPostRequest(requestUrl + "?" + params, runOptions).send(callback);
    }

    /** {@inheritDoc} */
    @Override
    public void getStatus(Link link, AsyncRequestCallback<ApplicationProcessDescriptor> callback) {
        asyncRequestFactory.createGetRequest(link.getHref()).send(callback);
    }

    @Override
    public void getRunningProcesses(String projectName, AsyncRequestCallback<Array<ApplicationProcessDescriptor>> callback) {
        final String requestUrl = baseUrl + "/runner/" + workspaceId + "/processes";
        final String params = "project=" + projectName;
        asyncRequestFactory.createGetRequest(requestUrl + "?" + params).send(callback);
    }

    /** {@inheritDoc} */
    @Override
    public void getLogs(Link link, AsyncRequestCallback<String> callback) {
        asyncRequestFactory.createGetRequest(link.getHref())
                           .loader(loader, "Retrieving logs...")
                           .send(callback);
    }

    /** {@inheritDoc} */
    @Override
    public void stop(Link link, AsyncRequestCallback<ApplicationProcessDescriptor> callback) {
        asyncRequestFactory.createPostRequest(link.getHref(), null)
                           .loader(loader, "Stopping an application...")
                           .send(callback);
    }

    @Override
    public void getRunners(AsyncRequestCallback<Array<RunnerDescriptor>> callback) {
        final String requestUrl = baseUrl + "/runner/" + workspaceId + "/available";
        asyncRequestFactory.createGetRequest(requestUrl, false).send(callback);
    }

    /** {@inheritDoc} */
    @Override
    public void getRunners(String projectName, AsyncRequestCallback<Array<RunnerDescriptor>> callback) {
        final String requestUrl = baseUrl + "/runner/" + workspaceId + "/available";
        final String params = "project=" + projectName;
        asyncRequestFactory.createGetRequest(requestUrl + "?" + params, false).send(callback);
    }

    /** {@inheritDoc} */
    @Override
    public void getResources(AsyncRequestCallback<ResourcesDescriptor> callback) {
        final String requestUrl = baseUrl + "/runner/" + workspaceId + "/resources";
        asyncRequestFactory.createGetRequest(requestUrl, false).send(callback);
    }
}
