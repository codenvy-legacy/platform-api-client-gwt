/*
 * CODENVY CONFIDENTIAL
 * __________________
 *
 * [2012] - [2013] Codenvy, S.A.
 * All Rights Reserved.
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
package com.codenvy.api.project.gwt.client;

import com.codenvy.api.project.shared.dto.ProjectTypeDescriptor;
import com.codenvy.ide.collections.Array;
import com.codenvy.ide.rest.AsyncRequestCallback;
import com.codenvy.ide.rest.AsyncRequestFactory;
import com.codenvy.ide.ui.loader.Loader;
import com.google.inject.Inject;
import com.google.inject.name.Named;

import static com.codenvy.ide.MimeType.APPLICATION_JSON;
import static com.codenvy.ide.rest.HTTPHeader.ACCEPT;

/**
 * The implementation of {@link ProjectTypeDescriptionServiceClient}.
 *
 * @author Artem Zatsarynnyy
 */
public class ProjectTypeDescriptionServiceClientImpl implements ProjectTypeDescriptionServiceClient {
    private static String              GET_DESCRIPTIONS;
    private final  String              restContext;
    private final  AsyncRequestFactory asyncRequestFactory;
    private        Loader              loader;

    @Inject
    protected ProjectTypeDescriptionServiceClientImpl(@Named("restContext") String restContext, Loader loader,
                                                      AsyncRequestFactory asyncRequestFactory) {
        this.restContext = restContext;
        this.loader = loader;
        this.asyncRequestFactory = asyncRequestFactory;
        GET_DESCRIPTIONS = "/project-description/descriptions";
    }

    @Override
    public void getProjectTypes(AsyncRequestCallback<Array<ProjectTypeDescriptor>> callback) {
        final String requestUrl = restContext + GET_DESCRIPTIONS;
        asyncRequestFactory.createGetRequest(requestUrl).header(ACCEPT, APPLICATION_JSON).loader(loader).send(callback);
    }
}