/*
 * CODENVY CONFIDENTIAL
 * __________________
 *
 * [2012] - [2013] Codenvy, S.A.
 * All Rights Reserved.
 *
 * NOTICE:  All information contained herein is, and remains
 * the property of Codenvy S.A. and its suppliers,
 * if any.  The intellectual and technical concepts contained
 * herein are proprietary to Codenvy S.A.
 * and its suppliers and may be covered by U.S. and Foreign Patents,
 * patents in process, and are protected by trade secret or copyright law.
 * Dissemination of this information or reproduction of this material
 * is strictly forbidden unless prior written permission is obtained
 * from Codenvy S.A..
 */

package com.codenvy.ide.rest;

import com.codenvy.ide.commons.exception.UnmarshallerException;
import com.google.gwt.http.client.Response;

/**
 * Deserializer for response's body.
 * <p/>
 * By the contract:
 * getPayload() should never return null (should be initialized in impl's constructor
 * and return the same object (with different content) before and after unmarshal().
 *
 * @param <T>
 *         the return type of the unmarshalled object
 */
public interface Unmarshallable<T> {

    /**
     * Prepares an object from the incoming {@link Response}.
     *
     * @param response
     *         incoming response
     */
    void unmarshal(Response response) throws UnmarshallerException;

    /**
     * The content of the returned object normally differs before and
     * after unmarshall() but by the contract it should never be {@code null}.
     *
     * @return the object deserialized from the response
     */
    T getPayload();

}
