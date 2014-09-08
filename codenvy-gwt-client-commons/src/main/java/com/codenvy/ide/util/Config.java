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
package com.codenvy.ide.util;

import com.codenvy.api.workspace.shared.dto.WorkspaceDescriptor;

/**
 * A smattering of useful methods.
 *
 * @author Dmytro Nochevnov
 * @author Vitaliy Guliy
 */
public class Config {

    private static WorkspaceDescriptor _workspace;

    /**
     * Returns the base context of the IDE.
     * Is used to give IDE an ability to build valid URL when using history.
     * Valid IDE url looks like
     *      "/ide-context/workspace-name/project-name"
     * and can be got by code below
     *      Config.getContext() + "/" + Config.getWorkspaceName() + "/" + Config.getProjectName()
     *
     * @return
     */
    public static native String getContext() /*-{
        try {
            return $wnd.IDE.config.context;
        } catch (e) {
            return null;
        }
    }-*/;


    /**
     * Returns workspace name
     *
     * @return
     */
    public static native String getWorkspaceName() /*-{
        try {
            return $wnd.IDE.config.workspaceName;
        } catch (e) {
            return null;
        }
    }-*/;


    /**
     * Returns workspace ID
     *
     * @return
     */
    public static native String getWorkspaceId() /*-{
        try {
            return $wnd.IDE.config.workspaceId;
        } catch (e) {
            return null;
        }
    }-*/;


    /**
     * Returns project name
     *
     * @return
     */
    public static native String getProjectName() /*-{
        try {
            return $wnd.IDE.config.projectName;
        } catch (e) {
            return null;
        }
    }-*/;


    public static native String getStartupParams() /*-{
        try {
            while ($wnd.IDE.config.startupParams.indexOf("?") == 0) {
                $wnd.IDE.config.startupParams = $wnd.IDE.config.startupParams.substring(1);
            }

            return $wnd.IDE.config.startupParams;
        } catch (e) {
        }
        return null;
    }-*/;


    public static native String getStartupParam(String name) /*-{
        try {
            while ($wnd.IDE.config.startupParams.indexOf("?") == 0) {
                $wnd.IDE.config.startupParams = $wnd.IDE.config.startupParams.substring(1);
            }

            var pairs = $wnd.IDE.config.startupParams.split("&");
            for (var i = 0; i < pairs.length; i++) {
                var pair = pairs[i].split('=');
                if (pair.length == 2 && decodeURIComponent(pair[0]) == name) {
                    return decodeURIComponent(pair[1]);
                }
            }
        } catch (e) {
        }
        return null;
    }-*/;

    /**
     * Set current Workspace.
     *
     * @param workspace
     *         the Workspace to set
     */
    public static void setCurrentWorkspace(WorkspaceDescriptor workspace) {
        _workspace = workspace;
    }

    /**
     * Get current workspace information.
     *
     * @return workspace
     */
    public static WorkspaceDescriptor getCurrentWorkspace() {
        return _workspace;
    }
}
