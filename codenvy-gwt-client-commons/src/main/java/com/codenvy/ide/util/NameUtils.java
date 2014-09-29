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

import com.google.gwt.regexp.shared.RegExp;

/**
 * Utility methods for checking names.
 * @author Evgen Vidolob
 */
public class NameUtils {
    private static RegExp PROJECT_NAME = RegExp.compile("^[A-Za-z0-9_-]+$");

    /**
     * Check project name.
     *
     * @param name the name
     * @return {@code true} if name valid and {@code false} otherwise
     */
    public static boolean checkProjectName(String name) {
        return PROJECT_NAME.test(name);
    }

}
