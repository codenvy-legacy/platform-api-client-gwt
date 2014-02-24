/*
 * CODENVY CONFIDENTIAL
 * __________________
 * 
 * [2012] - [$today.year] Codenvy, S.A. 
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
package com.codenvy.ide.websocket.rest;

import com.codenvy.ide.dto.DtoFactory;
import com.codenvy.ide.websocket.Message;
import com.google.gwt.json.client.JSONParser;

/**
 * Deserializer for responses websocket messages.
 *
 * @author Artem Zatsarynnyy
 */
public class DtoUnmarshallerWS<T> implements Unmarshallable<T> {
    protected T          payload;
    private   Class<?>   dtoInterface;
    private   DtoFactory dtoFactory;

    /**
     * Create new {@link DtoUnmarshaller} to deserialize response
     * to the DTO of type specified by {@code dtoInterface}.
     *
     * @param dtoInterface
     */
    public DtoUnmarshallerWS(Class<?> dtoInterface, DtoFactory dtoFactory) {
        this.dtoInterface = dtoInterface;
        this.dtoFactory = dtoFactory;
    }

    /** {@inheritDoc} */
    @Override
    public void unmarshal(Message message) {
        if (isJsonArray(message)) {
            payload = (T)dtoFactory.createListDtoFromJson(message.getBody(), dtoInterface);
        } else {
            payload = (T)dtoFactory.createDtoFromJson(message.getBody(), dtoInterface);
        }
    }

    /** {@inheritDoc} */
    @Override
    public T getPayload() {
        return payload;
    }

    private boolean isJsonArray(Message message) {
        return JSONParser.parseStrict(message.getBody()).isArray() != null;
    }
}
