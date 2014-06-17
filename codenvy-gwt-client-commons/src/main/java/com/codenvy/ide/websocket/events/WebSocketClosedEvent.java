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
package com.codenvy.ide.websocket.events;

/**
 * Event is fired, when WebSocket connection is closed.
 *
 * @author <a href="mailto:azatsarynnyy@exoplatform.org">Artem Zatsarynnyy</a>
 * @version $Id: WebSocketClosedEvent.java Jun 18, 2012 14:33:50 PM azatsarynnyy $
 */
public class WebSocketClosedEvent {
    /**
     * The WebSocket connection close code provided by the server.
     *
     * @see https://developer.mozilla.org/en/WebSockets/WebSockets_reference/CloseEvent#Close_codes
     */
    private int code;

    /** A string indicating the reason the server closed the connection. This is specific to the particular server and sub-protocol. */
    private String reason;

    /** Indicates whether or not the connection was cleanly closed. */
    private boolean wasClean;

    public WebSocketClosedEvent() {
    }

    public WebSocketClosedEvent(int code, String reason, boolean wasClean) {
        this.code = code;
        this.reason = reason;
        this.wasClean = wasClean;
    }

    /**
     * Returns close code.
     *
     * @return close code
     */
    public int getCode() {
        return code;
    }

    /**
     * Returns the reason closed the connection.
     *
     * @return reason
     */
    public String getReason() {
        return reason;
    }

    /**
     * Checks weather the connection was cleanly closed.
     *
     * @return <code>true</code> when WebSocket connection was cleanly closed;
     *         <code>false</code> when WebSocket connection was not cleanly closed
     */
    public boolean wasClean() {
        return wasClean;
    }
}