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

import com.codenvy.api.project.server.type.ProjectType2;
import com.codenvy.api.project.shared.dto.ProjectTypeDefinition;
import com.codenvy.ide.collections.Array;
import com.codenvy.ide.rest.AsyncRequestCallback;

/**
 * Client for Project Type Description service.
 *
 * @author Artem Zatsarynnyy
 */
public interface ProjectTypeServiceClient {
    /**
     * Get information about all registered project types
     *
     * @param callback
     *         the callback to use for the response
     */
    public void getProjectTypes(AsyncRequestCallback<Array<ProjectTypeDefinition>> callback);
}
