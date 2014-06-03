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
package com.codenvy.api.analytics.logger;

import java.util.Map;

/**
 * @author Anatoliy Bazko
 */
public interface AnalyticsEventLogger {

    int MAX_PARAMS_NUMBER      = 3;
    int MAX_PARAM_NAME_LENGTH  = 20;
    int MAX_PARAM_VALUE_LENGTH = 50;

    /**
     * Logs a client-side IDE event. Also will be logged the current user, workspace and project information.
     *
     * @param extensionClass
     *         it uses to tie event with the extension
     * @param action
     *         which action currently being happened, the action length is limited to {@link #MAX_PARAM_VALUE_LENGTH} characters
     * @param additionalParams
     *         any additional parameters to log, not more than {@link #MAX_PARAMS_NUMBER}, every parameter name and its
     *         value are limited to {@link #MAX_PARAM_NAME_LENGTH} and {@link #MAX_PARAM_VALUE_LENGTH} characters
     *         correspondingly
     */
    void log(Class<?> extensionClass, String action, Map<String, String> additionalParams);

    /**
     * Logs a client-side IDE event.
     *
     * @see #log(Class, String, java.util.Map)
     */
    void log(Class<?> extensionClass, String action);


    /**
     * Logs a client-side IDE event without reference to an extension.
     *
     * @see #log(Class, String, java.util.Map)
     */
    void log(String action);
}
