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
package com.codenvy.ide.dto.util.loging;

import com.codenvy.ide.util.NameUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import java.util.Arrays;
import java.util.Collection;

import static junit.framework.Assert.assertFalse;

/**
 * Tests check that NameUtils correct validate project name
 *
 * @author Andrienko Alexander
 */

@RunWith(Parameterized.class)
public class NameUtilsInValidProjectNamesTest {

    String invalidName;

    public NameUtilsInValidProjectNamesTest(String invalidName) {
        this.invalidName = invalidName;
    }

    /**
     * method get invalid names for testing
     * @return collection invalid names
     */

    @Parameterized.Parameters
    public static Collection getAllCases() {
        return Arrays.asList(new Object[][]{
                {"invalid project name"},
                {""},
                {"project:Name"},
                {"ProjectName®"},
                {"Project,Name"},
                {"Project*Name***"},
                {"(ProjectName)"},
                {"@ProjectName"},
                {"Project%Name"},
                {"ProjectName!!!"},
                {"ProjectName?"},
                {null}
        });
    }

    @Test
    public void testNameUtilsValidator() {
        assertFalse(NameUtils.checkProjectName(invalidName));
    }
}
