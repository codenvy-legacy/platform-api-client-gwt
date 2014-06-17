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
package com.codenvy.ide.rest;

/**
 * @version $Id: $
 *          <p/>
 *          Marshaller of a request body to be passed to server
 */

public interface Marshallable {

    /**
     * @return serialized object
     *         Note: the marshaller should have prepared object inside or be the object itself
     */
    String marshal();

}
