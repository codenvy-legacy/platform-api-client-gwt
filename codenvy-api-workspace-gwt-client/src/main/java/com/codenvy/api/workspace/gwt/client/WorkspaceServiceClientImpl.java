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
package com.codenvy.api.workspace.gwt.client;

import com.codenvy.api.workspace.shared.dto.MemberDescriptor;
import com.codenvy.api.workspace.shared.dto.WorkspaceDescriptor;
import com.codenvy.ide.collections.Array;
import com.codenvy.ide.rest.AsyncRequestCallback;
import com.codenvy.ide.rest.AsyncRequestFactory;
import com.codenvy.ide.ui.loader.Loader;
import com.google.inject.Inject;
import com.google.inject.name.Named;

import static com.codenvy.ide.MimeType.APPLICATION_JSON;
import static com.codenvy.ide.rest.HTTPHeader.ACCEPT;

/**
 * Implementation for {@link com.codenvy.api.workspace.gwt.client.WorkspaceServiceClient}.
 *
 * @author Roman Nikitenko
 */
public class WorkspaceServiceClientImpl implements WorkspaceServiceClient {

    private final Loader              loader;
    private final String              restContext;
    private final AsyncRequestFactory asyncRequestFactory;

    @Inject
    protected WorkspaceServiceClientImpl(Loader loader,
                                         @Named("restContext") String restContext,
                                         AsyncRequestFactory asyncRequestFactory) {
        this.loader = loader;
        this.restContext = restContext;
        this.asyncRequestFactory = asyncRequestFactory;
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
        loader.setMessage("Getting memberships");
        asyncRequestFactory.createGetRequest(restContext + "/workspace/all")
                           .loader(loader)
                           .header(ACCEPT, APPLICATION_JSON)
                           .send(callback);
    }
}
