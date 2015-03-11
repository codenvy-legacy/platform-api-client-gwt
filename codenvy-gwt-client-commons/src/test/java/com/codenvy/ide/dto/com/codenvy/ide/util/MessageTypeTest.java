

/*******************************************************************************
 * Copyright (c) 2012-2015 Codenvy, S.A.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Codenvy, S.A. - initial API and implementation
 *******************************************************************************/
package com.codenvy.ide.dto.com.codenvy.ide.util;

import org.eclipse.che.ide.util.MessageType;

import org.junit.Test;

import static org.eclipse.che.ide.util.MessageType.ERROR;
import static org.eclipse.che.ide.util.MessageType.GRADLE;
import static org.eclipse.che.ide.util.MessageType.INFO;
import static org.eclipse.che.ide.util.MessageType.MAVEN;
import static org.eclipse.che.ide.util.MessageType.WARNING;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author Andrey Plotnikov
 */
public class MessageTypeTest {

    private static final String SOME_TEXT = "some text";

    @Test
    public void infoMessageShouldBeDetected() throws Exception {
        String content = INFO.getPrefix() + SOME_TEXT;
        assertThat(MessageType.detect(content), is(INFO));
    }

    @Test
    public void warningMessageShouldBeDetected() throws Exception {
        String content = WARNING.getPrefix() + SOME_TEXT;
        assertThat(MessageType.detect(content), is(WARNING));
    }

    @Test
    public void errorMessageShouldBeDetected() throws Exception {
        String content = ERROR.getPrefix() + SOME_TEXT;
        assertThat(MessageType.detect(content), is(ERROR));
    }

    @Test
    public void mavenMessageShouldBeDetected() throws Exception {
        String content = MAVEN.getPrefix() + SOME_TEXT;
        assertThat(MessageType.detect(content), is(MAVEN));
    }

    @Test
    public void gradleMessageShouldBeDetected() throws Exception {
        String content = GRADLE.getPrefix() + SOME_TEXT;
        assertThat(MessageType.detect(content), is(GRADLE));
    }

    @Test(expected = IllegalStateException.class)
    public void exceptionShouldBeThrownWhenIncorrectValueIsInputted() throws Exception {
        MessageType.detect(SOME_TEXT);
    }

}