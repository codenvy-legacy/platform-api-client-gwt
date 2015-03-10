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
package org.eclipse.che.ide.commons;

/** @author andrew00x */
@SuppressWarnings("serial")
public class ParsingResponseException extends Exception {
    public ParsingResponseException(String message, Throwable cause) {
        super(message, cause);
    }

    public ParsingResponseException(String message) {
        super(message);
    }

    public ParsingResponseException(Throwable cause) {
        super(cause);
    }
}