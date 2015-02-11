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
package com.codenvy.ide.rest;

import com.codenvy.ide.commons.exception.UnmarshallerException;
import com.codenvy.ide.json.JsonHelper;
import com.google.gwt.http.client.Response;

import java.util.List;
import java.util.Map;

/**
 * @author Eugene Voevodin
 */
public class StringMapListUnmarshaller implements Unmarshallable<Map<String, List<String>>> {

    private Map<String, List<String>> payload;

    @Override
    public void unmarshal(Response response) throws UnmarshallerException {
        payload = JsonHelper.toMapOfLists(response.getText());
    }

    @Override
    public Map<String, List<String>> getPayload() {
        return payload;
    }
}
