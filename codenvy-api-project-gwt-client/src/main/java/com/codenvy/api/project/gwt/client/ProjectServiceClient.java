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
 * Client for Project service.
 *
 * @author Vitaly Parfonov
 * @author Artem Zatsarynnyy
 */
public interface ProjectServiceClient {

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

    /**
     * Create new file in the specified folder.
     *
     * @param parentPath
     *         path to parent for new file
     * @param name
     *         file name
     * @param content
     *         file content
     * @param contentType
     *         media type of file content
     * @param callback
     */
    public void createFile(String parentPath, String name, String content, String contentType, AsyncRequestCallback<Void> callback);

    /**
     * Get file content.
     *
     * @param path
     *         path to file
     * @param callback
     */
    public void getFileContent(String path, AsyncRequestCallback<String> callback);

    /**
     * Update file content.
     *
     * @param path
     *         path to file
     * @param content
     *         new content of file
     * @param contentType
     *         content media type
     * @param callback
     */
    public void updateFile(String path, String content, String contentType, AsyncRequestCallback<Void> callback);

    /**
     * Create new folder in the specified folder.
     *
     * @param path
     *         path to parent for new folder
     * @param callback
     */
    public void createFolder(String path, AsyncRequestCallback<Void> callback);

    /**
     * Delete item.
     *
     * @param path
     *         path to item to delete
     * @param callback
     */
    public void delete(String path, AsyncRequestCallback<Void> callback);

    /**
     * Copy an item to the specified target path.
     *
     * @param path
     *         path to the item to copy
     * @param newParentPath
     *         path to the target item
     * @param callback
     */
    public void copy(String path, String newParentPath, AsyncRequestCallback<Void> callback);

    /**
     * Move an item to the specified target path.
     *
     * @param path
     *         path to the item to move
     * @param newParentPath
     *         path to the target item
     * @param callback
     */
    public void move(String path, String newParentPath, AsyncRequestCallback<Void> callback);

    /**
     * Rename and/or set new media type for item.
     *
     * @param path
     *         path to the item to rename
     * @param newName
     *         new name
     * @param newMediaType
     *         new media type
     * @param callback
     */
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
     * Get children for the specified path.
     *
     * @param path
     *         path to get its children
     * @param callback
     */
    public void getChildren(String path, AsyncRequestCallback<Array<ItemReference>> callback);

    /**
     * Get folders tree starts from the specified path.
     *
     * @param path
     *         path to get its folder tree
     * @param depth
     *         depth for discover children
     * @param callback
     */
    public void getTree(String path, int depth, AsyncRequestCallback<TreeElement> callback);

    /**
     * Search an item(s) by the specified criteria.
     *
     * @param path
     *         path to start search
     * @param name
     *         name of file to search
     * @param mediaType
     *         media type of file to search
     * @param text
     *         text to search
     * @param maxItems
     *         max number of items in response
     * @param skipCount
     *         the skip items
     * @param callback
     */
    public void search(String path, String name, String mediaType, String text, int maxItems, int skipCount,
                       AsyncRequestCallback<Array<ItemReference>> callback);
}
