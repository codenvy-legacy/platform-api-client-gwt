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
package com.codenvy.api.builder.gwt.client;

import com.codenvy.api.builder.dto.BuildOptions;
import com.codenvy.api.builder.dto.BuildTaskDescriptor;
import com.codenvy.api.core.rest.shared.dto.Link;
import com.codenvy.ide.rest.AsyncRequestCallback;

/**
 * Client for Builder service.
 *
 * @author Artem Zatsarynnyy
 */
public interface BuilderServiceClient {
    /**
     * Start new build.
     *
     * @param projectName
     *         identifier of the project we want to send for build
     * @param callback
     *         callback
     */
    public void build(String projectName, AsyncRequestCallback<BuildTaskDescriptor> callback);

    /**
     * Start new build.
     *
     * @param projectName
     *         identifier of the project we want to send for build
     * @param buildOptions
     *         Options to configure build process
     * @param callback
     *         callback
     */
    public void build(String projectName, BuildOptions buildOptions, AsyncRequestCallback<BuildTaskDescriptor> callback);

    /**
     * Cancel previously launched build.
     *
     * @param buildId
     *         ID of build
     * @param callback
     *         callback
     */
    public void cancel(String buildId, AsyncRequestCallback<StringBuilder> callback);

    /**
     * Check current status of previously launched build.
     * <p/>
     * identifier of build
     *
     * @param callback
     *         callback
     */
    public void status(Link link, AsyncRequestCallback<String> callback);

    /** Get build log. */
    public void log(Link link, AsyncRequestCallback<String> callback);

    /**
     * Get build result.
     *
     * @param buildId
     *         ID of build
     * @param callback
     *         callback
     */
    public void result(String buildId, AsyncRequestCallback<String> callback);


}
