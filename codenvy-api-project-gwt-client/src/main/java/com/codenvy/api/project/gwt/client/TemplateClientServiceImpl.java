/*
 * CODENVY CONFIDENTIAL
 * __________________
 *
 *  [2012] - [2014] Codenvy, S.A.
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
package com.codenvy.api.project.gwt.client;

import com.codenvy.api.project.shared.dto.ProjectTypeDescriptor;
import com.codenvy.ide.MimeType;
import com.codenvy.ide.rest.AsyncRequest;
import com.codenvy.ide.rest.AsyncRequestCallback;
import com.codenvy.ide.rest.AsyncRequestLoader;
import com.codenvy.ide.rest.HTTPHeader;
import com.codenvy.ide.ui.loader.Loader;
import com.google.gwt.http.client.RequestException;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.google.inject.name.Named;

import static com.google.gwt.http.client.RequestBuilder.GET;

/**
 * The implementation of {@link TemplateClientService}.
 *
 * @author Artem Zatsarynnyy
 */
@Singleton
public class TemplateClientServiceImpl implements TemplateClientService {
    private final String             GET_TEMPLATE_DESCRIPTORS;
    private final String             restContext;
    private       AsyncRequestLoader loader;

    @Inject
    protected TemplateClientServiceImpl(@Named("restContext") String restContext, @Named("workspaceId") String workspaceId, Loader loader) {
        this.restContext = restContext;
        this.loader = loader;
        GET_TEMPLATE_DESCRIPTORS = "/project-template/" + workspaceId + "/get";
    }

    @Override
    public void getTemplates(ProjectTypeDescriptor projectTypeDescriptor, AsyncRequestCallback<String> callback) throws RequestException {
        final String requestUrl = restContext + GET_TEMPLATE_DESCRIPTORS;
        final String param = "?projectTypeId=" + projectTypeDescriptor.getProjectTypeId();
        AsyncRequest.build(GET, requestUrl + param).header(HTTPHeader.ACCEPT, MimeType.APPLICATION_JSON).loader(loader).send(callback);
    }

    @Override
    public void getAllTemplates(AsyncRequestCallback<String> callback) throws RequestException {
        final String requestUrl = restContext + GET_TEMPLATE_DESCRIPTORS;
        AsyncRequest.build(GET, requestUrl).header(HTTPHeader.ACCEPT, MimeType.APPLICATION_JSON).loader(loader).send(callback);
    }

}
