/*
 * CODENVY CONFIDENTIAL
 * __________________
 * 
 * [2012] - [$today.year] Codenvy, S.A. 
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

import com.codenvy.api.project.shared.dto.ImportSourceDescriptor;
import com.codenvy.api.project.shared.dto.ItemReference;
import com.codenvy.api.project.shared.dto.ProjectDescriptor;
import com.codenvy.api.project.shared.dto.ProjectReference;
import com.codenvy.api.project.shared.dto.TreeElement;
import com.codenvy.ide.collections.Array;
import com.codenvy.ide.collections.StringMap;
import com.codenvy.ide.rest.AsyncRequestCallback;

/**
 * Client service for Codenvy Project API.
 *
 * @author Vitaly Parfonov
 * @author Artem Zatsarynnyy
 */
public interface ProjectClientService {

    /**
     * Get all projects.
     *
     * @param callback
     */
    public void getProjects(AsyncRequestCallback<Array<ProjectReference>> callback);

    /**
     * Get project.
     *
     * @param path
     *         path to the project to get
     * @param callback
     */
    public void getProject(String path, AsyncRequestCallback<ProjectDescriptor> callback);

    /**
     * Create project.
     *
     * @param name
     *         name of the project to create
     * @param descriptor
     *         descriptor of the project to create
     * @param callback
     */
    public void createProject(String name, ProjectDescriptor descriptor, AsyncRequestCallback<ProjectDescriptor> callback);

    /**
     * Create module in project.
     *
     * @param parentProjectPath
     *         path to the parent project
     * @param name
     *         name of the module to create
     * @param descriptor
     *         descriptor of the project to create
     * @param callback
     */
    public void createModule(String parentProjectPath, String name, ProjectDescriptor descriptor,
                             AsyncRequestCallback<ProjectDescriptor> callback);

    /**
     * Update project.
     *
     * @param path
     *         path to the project to get
     * @param descriptor
     *         descriptor of the project to update
     * @param callback
     */
    public void updateProject(String path, ProjectDescriptor descriptor, AsyncRequestCallback<ProjectDescriptor> callback);

    public void createFile(String parentPath, String name, String content, String contentType, AsyncRequestCallback<Void> callback);

    public void getFile(String path, AsyncRequestCallback<String> callback);

    public void updateFile(String path, String content, String contentType, AsyncRequestCallback<Void> callback);

    public void createFolder(String path, AsyncRequestCallback<Void> callback);

    public void delete(String path, AsyncRequestCallback<Void> callback);

    public void copy(String path, String newParentPath, AsyncRequestCallback<Void> callback);

    public void move(String path, String newParentPath, AsyncRequestCallback<Void> callback);

    public void rename(String path, String newName, String newMediaType, AsyncRequestCallback<Void> callback);

    /**
     * Import sources into project.
     *
     * @param path
     *         path to the project to import sources
     * @param importSourceDescriptor
     *         {@link ImportSourceDescriptor}
     * @param callback
     */
    public void importProject(String path, ImportSourceDescriptor importSourceDescriptor,
                              AsyncRequestCallback<ProjectDescriptor> callback);

    /**
     * Generate project.
     *
     * @param path
     *         path to the project to generate
     * @param generatorName
     *         project generator's name
     * @param options
     *         additional options for project generator
     * @param callback
     */
    public void generateProject(String path, String generatorName, StringMap<String> options,
                                AsyncRequestCallback<ProjectDescriptor> callback);

    /**
     * Get project/folder children.
     *
     * @param path
     *         path to the project/folder to get its children
     * @param callback
     */
    public void getChildren(String path, AsyncRequestCallback<Array<ItemReference>> callback);

    /**
     * Get project/folder tree.
     *
     * @param path
     *         path to the project/folder to get its tree
     * @param depth
     *         depth of tree to get
     * @param callback
     */
    public void getTree(String path, int depth, AsyncRequestCallback<TreeElement> callback);

    public void search(String path, String name, String mediaType, String text, int maxItems, int skipCount,
                       AsyncRequestCallback<Array<ItemReference>> callback);
}
