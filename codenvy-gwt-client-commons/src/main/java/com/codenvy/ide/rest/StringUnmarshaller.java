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

import com.google.gwt.http.client.Response;

/**
 * Deserializer for responses body.
 *
 * @author Vitaly Parfonov
 */
public class StringUnmarshaller implements Unmarshallable<String> {
    protected String builder;

    /** {@inheritDoc} */
    @Override
    public void unmarshal(Response response) {
        builder = response.getText();
    }

    /** {@inheritDoc} */
    @Override
    public String getPayload() {
        return builder;
    }
}
