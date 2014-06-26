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

import com.codenvy.api.user.shared.dto.Attribute;
import com.codenvy.api.user.shared.dto.Profile;
import com.codenvy.api.workspace.shared.dto.Workspace;

/**
 * A smattering of useful methods.
 *
 * @author Dmytro Nochevnov
 * @author Vitaliy Guliy
 */
public class Config {

    private static Workspace    _workspace;
    private static Profile      _profile;

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
    public static void setCurrentWorkspace(Workspace workspace) {
        _workspace = workspace;
    }

    /**
     * Get current workspace information.
     *
     * @return workspace
     */
    public static Workspace getCurrentWorkspace() {
        return _workspace;
    }

    /**
     * Set current user Profile.
     *
     * @param profile
     *          user profile
     */
    public static void setCurrentProfile(Profile profile) {
        _profile = profile;
    }

    /**
     * Get current user Profile.
     *
     * @return
     *          current profile
     */
    public static Profile getCurrentProfile() {
        return _profile;
    }

    /**
     * Determines whether the user is permanent.
     *
     * @return <b>true</b> for permanent user, <b>false</b> otherwise
     */
    public static boolean isUserPermanent() {
        if (_profile != null && _profile.getAttributes() != null) {
            for (Attribute attribute : _profile.getAttributes()) {
                if ("temporary".equals(attribute.getName()) && "true".equals(attribute.getValue())) {
                    return false;
                }
            }
        }

        return true;
    }

}
