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
package com.codenvy.ide.websocket.rest.exceptions;

import com.codenvy.ide.websocket.Message;

/**
 * Thrown when there was a HTTP Status-Code 401 (Unauthorized) was received.
 *
 * @author <a href="mailto:azatsarynnyy@exoplatfrom.com">Artem Zatsarynnyy</a>
 * @version $Id: UnauthorizedException.java Nov 9, 2012 5:09:29 PM azatsarynnyy $
 */
@SuppressWarnings("serial")
public class UnauthorizedException extends Exception {
    private Message message;

    public UnauthorizedException(Message message) {
        this.message = message;
    }

    public int getHTTPStatus() {
        return message.getResponseCode();
    }
}