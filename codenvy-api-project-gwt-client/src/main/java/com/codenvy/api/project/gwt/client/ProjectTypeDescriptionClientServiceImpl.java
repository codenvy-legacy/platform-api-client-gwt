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

import com.codenvy.ide.rest.AsyncRequest;
import com.codenvy.ide.rest.AsyncRequestCallback;
import com.codenvy.ide.ui.loader.Loader;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.RequestException;
import com.google.inject.Inject;
import com.google.inject.name.Named;

import static com.codenvy.ide.MimeType.APPLICATION_JSON;
import static com.codenvy.ide.rest.HTTPHeader.ACCEPT;

/**
 * The implementation of {@link ProjectTypeDescriptionClientService}.
 *
 * @author Artem Zatsarynnyy
 */
public class ProjectTypeDescriptionClientServiceImpl implements ProjectTypeDescriptionClientService {
    private static String GET_DESCRIPTIONS;
    private final  String restContext;
    private        Loader loader;

    @Inject
    protected ProjectTypeDescriptionClientServiceImpl(@Named("restContext") String restContext, Loader loader) {
        this.restContext = restContext;
        this.loader = loader;
        GET_DESCRIPTIONS = "/project-description/descriptions";
    }

    @Override
    public void getProjectTypes(AsyncRequestCallback<String> callback) throws RequestException {
        final String requestUrl = restContext + GET_DESCRIPTIONS;
        AsyncRequest.build(RequestBuilder.GET, requestUrl).header(ACCEPT, APPLICATION_JSON).loader(loader).send(callback);
    }
}