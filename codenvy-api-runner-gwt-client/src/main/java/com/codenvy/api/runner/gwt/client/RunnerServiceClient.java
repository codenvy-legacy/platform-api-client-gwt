/*
 * CODENVY CONFIDENTIAL
 * __________________
 * 
 *  [2012] - [2013] Codenvy, S.A. 
 *  All Rights Reserved.
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
package com.codenvy.api.runner.gwt.client;

import com.codenvy.api.core.rest.shared.dto.Link;
import com.codenvy.api.runner.dto.ApplicationProcessDescriptor;
import com.codenvy.api.runner.dto.RunOptions;
import com.codenvy.api.runner.dto.RunnerDescriptor;
import com.codenvy.ide.collections.Array;
import com.codenvy.ide.rest.AsyncRequestCallback;

/**
 * Client for Runner service.
 *
 * @author Artem Zatsarynnyy
 */
public interface RunnerServiceClient {
    /**
     * Run an app on the application server.
     *
     * @param projectName
     *         name of the project to run
     * @param runOptions
     *         options to configure run process
     * @param callback
     *         the callback to use for the response
     */
    public void run(String projectName, RunOptions runOptions, AsyncRequestCallback<ApplicationProcessDescriptor> callback);

    /**
     * Get status of app.
     *
     * @param link
     *         link to get application's status
     * @param callback
     *         the callback to use for the response
     */
    public void getStatus(Link link, AsyncRequestCallback<ApplicationProcessDescriptor> callback);

    /**
     * Retrieve logs from application server where app is launched.
     *
     * @param link
     *         link to retrieve logs
     * @param callback
     *         the callback to use for the response
     */
    public void getLogs(Link link, AsyncRequestCallback<String> callback);

    /**
     * Stop application server where app is launched.
     *
     * @param link
     *         link to stop an app
     * @param callback
     *         the callback to use for the response
     */
    public void stop(Link link, AsyncRequestCallback<ApplicationProcessDescriptor> callback);

    /**
     * Get available runners.
     *
     * @param callback
     *         the callback to use for the response
     */
    public void getRunners(AsyncRequestCallback<Array<RunnerDescriptor>> callback);

    /**
     * Get available runners.
     *
     * @param projectName
     *         name of the project
     * @param callback
     *         the callback to use for the response
     */
    public void getRunners(String projectName, AsyncRequestCallback<Array<RunnerDescriptor>> callback);
}
