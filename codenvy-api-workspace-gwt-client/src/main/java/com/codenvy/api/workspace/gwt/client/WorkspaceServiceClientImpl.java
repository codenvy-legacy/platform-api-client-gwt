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
package com.codenvy.api.workspace.gwt.client;

import com.codenvy.api.workspace.shared.dto.MemberDescriptor;
import com.codenvy.api.workspace.shared.dto.WorkspaceDescriptor;
import com.codenvy.api.workspace.shared.dto.WorkspaceUpdate;
import com.codenvy.ide.collections.Array;
import com.codenvy.ide.dto.DtoFactory;
import com.codenvy.ide.rest.AsyncRequestCallback;
import com.codenvy.ide.rest.AsyncRequestFactory;
import com.codenvy.ide.rest.AsyncRequestLoader;
import com.google.inject.Inject;
import com.google.inject.name.Named;

import java.util.Map;

import static com.codenvy.ide.MimeType.APPLICATION_JSON;
import static com.codenvy.ide.rest.HTTPHeader.ACCEPT;
import static com.codenvy.ide.rest.HTTPHeader.CONTENT_TYPE;

/**
 * Implementation for {@link com.codenvy.api.workspace.gwt.client.WorkspaceServiceClient}.
 *
 * @author Roman Nikitenko
 */
public class WorkspaceServiceClientImpl implements WorkspaceServiceClient {

    private final AsyncRequestLoader  loader;
    private final String              restContext;
    private final AsyncRequestFactory asyncRequestFactory;
    private final DtoFactory          dtoFactory;
    private final String              workspaceId;

    @Inject
    protected WorkspaceServiceClientImpl(AsyncRequestLoader loader,
                                         @Named("restContext") String restContext,
                                         @Named("workspaceId") String workspaceId,
                                         AsyncRequestFactory asyncRequestFactory, DtoFactory dtoFactory) {
        this.loader = loader;
        this.restContext = restContext;
        this.asyncRequestFactory = asyncRequestFactory;
        this.dtoFactory = dtoFactory;
        this.workspaceId = workspaceId;
    }

    /** {@inheritDoc} */
    @Override
    public void getWorkspace(String wsId, AsyncRequestCallback<WorkspaceDescriptor> callback) {
        asyncRequestFactory.createGetRequest(restContext + "/workspace/" + wsId)
                           .loader(loader)
                           .header(ACCEPT, APPLICATION_JSON)
                           .send(callback);
    }

    /** {@inheritDoc} */
    @Override
    public void getMembership(String wsId, AsyncRequestCallback<MemberDescriptor> callback) {
        asyncRequestFactory.createGetRequest(restContext + "/workspace/" + wsId + "/membership")
                           .loader(loader)
                           .header(ACCEPT, APPLICATION_JSON)
                           .send(callback);
    }

    /** {@inheritDoc} */
    @Override
    public void getMemberships(AsyncRequestCallback<Array<MemberDescriptor>> callback) {
        asyncRequestFactory.createGetRequest(restContext + "/workspace/all")
                           .loader(loader, "Getting memberships")
                           .header(ACCEPT, APPLICATION_JSON)
                           .send(callback);
    }

    @Override
    public void update(String wsId, WorkspaceUpdate update, AsyncRequestCallback<WorkspaceDescriptor> callback) {
        asyncRequestFactory.createPostRequest(restContext + "/workspace/all/" + wsId, update)
                           .loader(loader, "Updating workspace")
                           .header(ACCEPT, APPLICATION_JSON)
                           .header(CONTENT_TYPE, APPLICATION_JSON)
                           .send(callback);
    }

    @Override
    public void updateAttributes(Map<String, String> attributes, AsyncRequestCallback<WorkspaceDescriptor> callback) {
        WorkspaceUpdate workspaceUpdate = dtoFactory.createDto(WorkspaceUpdate.class).withAttributes(attributes);

        asyncRequestFactory.createPostRequest(restContext + "/workspace/" + workspaceId, workspaceUpdate)
                           .loader(loader)
                           .header(ACCEPT, APPLICATION_JSON)
                           .send(callback);
    }
}
