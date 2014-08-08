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

import com.codenvy.api.core.ForbiddenException;
import com.codenvy.api.core.NotFoundException;
import com.codenvy.api.core.ServerException;
import com.codenvy.api.project.server.Project;
import com.codenvy.api.project.shared.dto.ImportSourceDescriptor;
import com.codenvy.api.project.shared.dto.ItemReference;
import com.codenvy.api.project.shared.dto.ProjectDescriptor;
import com.codenvy.api.project.shared.dto.ProjectReference;
import com.codenvy.api.project.shared.dto.TreeElement;
import com.codenvy.ide.MimeType;
import com.codenvy.ide.collections.Array;
import com.codenvy.ide.collections.StringMap;
import com.codenvy.ide.rest.AsyncRequest;
import com.codenvy.ide.rest.AsyncRequestCallback;
import com.codenvy.ide.rest.AsyncRequestFactory;
import com.codenvy.ide.ui.loader.Loader;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONString;
import com.google.inject.Inject;
import com.google.inject.name.Named;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;

import static com.codenvy.ide.rest.HTTPHeader.ACCEPT;
import static com.codenvy.ide.rest.HTTPHeader.CONTENTTYPE;
import static com.codenvy.ide.rest.HTTPHeader.CONTENT_TYPE;
import static com.google.gwt.http.client.RequestBuilder.DELETE;
import static com.google.gwt.http.client.RequestBuilder.PUT;

/**
 * Implementation of {@link ProjectServiceClient}.
 *
 * @author Vitaly Parfonov
 * @author Artem Zatsarynnyy
 */
public class ProjectServiceClientImpl implements ProjectServiceClient {
    private final String              PROJECT;
    private final String              MODULES;
    private final String              FILE;
    private final String              FOLDER;
    private final String              COPY;
    private final String              MOVE;
    private final String              RENAME;
    private final String              IMPORT_PROJECT;
    private final String              GENERATE_PROJECT;
    private final String              GET_CHILDREN;
    private final String              GET_TREE;
    private final String              SEARCH;
    private final String              SWITCH_VISIBILITY;
    private final Loader              loader;
    private final AsyncRequestFactory asyncRequestFactory;

    @Inject
    protected ProjectServiceClientImpl(@Named("restContext") String restContext,
                                       @Named("workspaceId") String workspaceId,
                                       Loader loader,
                                       AsyncRequestFactory asyncRequestFactory) {
        this.loader = loader;
        this.asyncRequestFactory = asyncRequestFactory;
        PROJECT = restContext + "/project/" + workspaceId;
        MODULES = restContext + "/project/" + workspaceId + "/modules";
        FILE = restContext + "/project/" + workspaceId + "/file";
        FOLDER = restContext + "/project/" + workspaceId + "/folder";
        COPY = restContext + "/project/" + workspaceId + "/copy";
        MOVE = restContext + "/project/" + workspaceId + "/move";
        RENAME = restContext + "/project/" + workspaceId + "/rename";
        IMPORT_PROJECT = restContext + "/project/" + workspaceId + "/import";
        GENERATE_PROJECT = restContext + "/project/" + workspaceId + "/generate";
        GET_CHILDREN = restContext + "/project/" + workspaceId + "/children";
        GET_TREE = restContext + "/project/" + workspaceId + "/tree";
        SEARCH = restContext + "/project/" + workspaceId + "/search";
        SWITCH_VISIBILITY = restContext + "/project/" + workspaceId + "/switch_visibility";
    }

    private static String stringMapToJson(StringMap<String> map) {
        String json = "";
        if (map != null && !map.isEmpty()) {
            final JSONObject jsonObject = new JSONObject();
            map.iterate(new StringMap.IterationCallback<String>() {
                @Override
                public void onIteration(String key, String value) {
                    jsonObject.put(key, new JSONString(value));
                }
            });
            json = jsonObject.toString();
        }
        return json;
    }

    @Override
    public void getProjects(AsyncRequestCallback<Array<ProjectReference>> callback) {
        final String requestUrl = PROJECT;
        loader.setMessage("Getting projects...");
        asyncRequestFactory.createGetRequest(requestUrl)
                           .header(ACCEPT, MimeType.APPLICATION_JSON)
                           .loader(loader)
                           .send(callback);
    }

    @Override
    public void getProject(String path, AsyncRequestCallback<ProjectDescriptor> callback) {
        final String requestUrl = PROJECT + normalizePath(path);
        loader.setMessage("Getting project...");
        asyncRequestFactory.createGetRequest(requestUrl)
                           .header(ACCEPT, MimeType.APPLICATION_JSON)
                           .loader(loader)
                           .send(callback);
    }

    @Override
    public void createProject(String name, ProjectDescriptor descriptor, AsyncRequestCallback<ProjectDescriptor> callback) {
        final String requestUrl = PROJECT + "?name=" + name;
        loader.setMessage("Creating project...");
        asyncRequestFactory.createPostRequest(requestUrl, descriptor)
                           .header(ACCEPT, MimeType.APPLICATION_JSON)
                           .loader(loader)
                           .send(callback);
    }

    @Override
    public void getModules(String path, AsyncRequestCallback<Array<ProjectDescriptor>> callback) {
        final String requestUrl = MODULES + normalizePath(path);
        loader.setMessage("Getting modules...");
        asyncRequestFactory.createGetRequest(requestUrl)
                           .header(ACCEPT, MimeType.APPLICATION_JSON)
                           .loader(loader)
                           .send(callback);
    }

    @Override
    public void createModule(String parentProjectPath, String name, ProjectDescriptor descriptor,
                             AsyncRequestCallback<ProjectDescriptor> callback) {
        final String requestUrl = PROJECT + normalizePath(parentProjectPath) + "?name=" + name;
        loader.setMessage("Creating module...");
        asyncRequestFactory.createPostRequest(requestUrl, descriptor)
                           .header(ACCEPT, MimeType.APPLICATION_JSON)
                           .loader(loader)
                           .send(callback);
    }

    @Override
    public void updateProject(String path, ProjectDescriptor descriptor, AsyncRequestCallback<ProjectDescriptor> callback) {
        final String requestUrl = PROJECT + normalizePath(path);
        loader.setMessage("Updating project...");
        asyncRequestFactory.createRequest(PUT, requestUrl, descriptor, false)
                           .header(CONTENT_TYPE, MimeType.APPLICATION_JSON)
                           .header(ACCEPT, MimeType.APPLICATION_JSON)
                           .loader(loader)
                           .send(callback);
    }

    @Override
    public void createFile(String parentPath, String name, String content, String contentType, AsyncRequestCallback<Void> callback) {
        final String requestUrl = FILE + normalizePath(parentPath) + "?name=" + name;
        loader.setMessage("Creating file...");
        asyncRequestFactory.createPostRequest(requestUrl, null)
                           .header(CONTENT_TYPE, contentType)
                           .data(content)
                           .loader(loader)
                           .send(callback);
    }

    @Override
    public void getFileContent(String path, AsyncRequestCallback<String> callback) {
        final String requestUrl = FILE + normalizePath(path);
        loader.setMessage("Loading file content...");
        asyncRequestFactory.createGetRequest(requestUrl)
                           .loader(loader)
                           .send(callback);
    }

    @Override
    public void updateFile(String path, String content, String contentType, AsyncRequestCallback<Void> callback) {
        final String requestUrl = FILE + normalizePath(path);
        loader.setMessage("Updating file content...");
        asyncRequestFactory.createRequest(PUT, requestUrl, null, false)
                           .header(CONTENT_TYPE, contentType)
                           .data(content)
                           .loader(loader)
                           .send(callback);
    }

    @Override
    public void createFolder(String path, AsyncRequestCallback<Void> callback) {
        final String requestUrl = FOLDER + normalizePath(path);
        loader.setMessage("Creating folder...");
        asyncRequestFactory.createPostRequest(requestUrl, null)
                           .loader(loader)
                           .send(callback);
    }

    @Override
    public void delete(String path, AsyncRequestCallback<Void> callback) {
        final String requestUrl = PROJECT + normalizePath(path);
        loader.setMessage("Deleting project...");
        asyncRequestFactory.createRequest(DELETE, requestUrl, null, false)
                           .loader(loader)
                           .send(callback);
    }

    @Override
    public void copy(String path, String newParentPath, AsyncRequestCallback<Void> callback) {
        final String requestUrl = COPY + normalizePath(path) + "?to=" + newParentPath;
        loader.setMessage("Copying item...");
        asyncRequestFactory.createPostRequest(requestUrl, null)
                           .loader(loader)
                           .send(callback);
    }

    @Override
    public void move(String path, String newParentPath, AsyncRequestCallback<Void> callback) {
        final String requestUrl = MOVE + normalizePath(path) + "?to=" + newParentPath;
        loader.setMessage("Moving item...");
        asyncRequestFactory.createPostRequest(requestUrl, null)
                           .loader(loader)
                           .send(callback);
    }

    @Override
    public void rename(String path, String newName, String newMediaType, AsyncRequestCallback<Void> callback) {
        final String requestUrl = RENAME + normalizePath(path) + "?name=" + newName + "&mediaType=" + newMediaType;
        loader.setMessage("Renaming item...");
        asyncRequestFactory.createPostRequest(requestUrl, null)
                           .loader(loader)
                           .send(callback);
    }

    @Override
    public void importProject(String path, ImportSourceDescriptor importSourceDescriptor,
                              AsyncRequestCallback<ProjectDescriptor> callback) {
        final String requestUrl = IMPORT_PROJECT + normalizePath(path);
        loader.setMessage("Importing sources into project...");
        asyncRequestFactory.createPostRequest(requestUrl, importSourceDescriptor)
                           .header(ACCEPT, MimeType.APPLICATION_JSON)
                           .loader(loader)
                           .send(callback);
    }

    @Override
    public void generateProject(String path, String generatorName, StringMap<String> options,
                                AsyncRequestCallback<ProjectDescriptor> callback) {
        final String requestUrl = GENERATE_PROJECT + normalizePath(path) + "?generator=" + generatorName;
        loader.setMessage("Generating project...");
        //we need to send map, so don't use asyncRequestFactory
        AsyncRequest.build(RequestBuilder.POST, requestUrl).header(ACCEPT, MimeType.APPLICATION_JSON).
                    header(CONTENTTYPE, MimeType.APPLICATION_JSON).loader(loader)
                    .data(stringMapToJson(options)).send(
                callback);
    }

    @Override
    public void getChildren(String path, AsyncRequestCallback<Array<ItemReference>> callback) {
        final String requestUrl = GET_CHILDREN + normalizePath(path);
        asyncRequestFactory.createGetRequest(requestUrl)
                           .header(ACCEPT, MimeType.APPLICATION_JSON)
                           .send(callback);
    }

    @Override
    public void getTree(String path, int depth, AsyncRequestCallback<TreeElement> callback) {
        final String requestUrl = GET_TREE + normalizePath(path) + "?depth=" + depth;
        asyncRequestFactory.createGetRequest(requestUrl)
                           .header(ACCEPT, MimeType.APPLICATION_JSON)
                           .send(callback);
    }

    @Override
    public void search(QueryExpression expression, AsyncRequestCallback<Array<ItemReference>> callback) {
        final String requestUrl = SEARCH + normalizePath(expression.getPath());

        StringBuilder queryParameters = new StringBuilder();
        if (expression.getName() != null && !expression.getName().isEmpty()) {
            queryParameters.append("&name=").append(expression.getName());
        }
        if (expression.getMediaType() != null && !expression.getMediaType().isEmpty()) {
            queryParameters.append("&mediatype=").append(expression.getMediaType());
        }
        if (expression.getText() != null && !expression.getText().isEmpty()) {
            queryParameters.append("&text=").append(expression.getText());
        }
        if (expression.getMaxItems() != 0) {
            queryParameters.append("&maxItems=").append(expression.getMaxItems());
        }
        if (expression.getSkipCount() != 0) {
            queryParameters.append("&skipCount=").append(expression.getSkipCount());
        }

        asyncRequestFactory.createGetRequest(requestUrl + queryParameters.toString().replaceFirst("&", "?"))
                           .header(ACCEPT, MimeType.APPLICATION_JSON)
                           .send(callback);
    }
    
    /** {@inheritDoc} */
    @Override
    public void switchVisibility(String path, String visibility, AsyncRequestCallback<Void> callback) {
        final String requestUrl = SWITCH_VISIBILITY + normalizePath(path) + "?visibility=" + visibility;
        loader.setMessage("Switching visibility...");
        asyncRequestFactory.createPostRequest(requestUrl, null)
                           .loader(loader)
                           .send(callback);

    }

    /**
     * Normalizes the path by adding a leading '/' if it doesn't exist.
     *
     * @param path
     *         path to normalize
     * @return normalized path
     */
    private String normalizePath(String path) {
        return path.startsWith("/") ? path : '/' + path;
    }
}
