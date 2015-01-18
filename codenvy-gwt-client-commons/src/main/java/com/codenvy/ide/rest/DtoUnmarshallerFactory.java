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

import com.codenvy.ide.collections.Array;
import com.codenvy.ide.dto.DtoFactory;
import com.google.inject.Inject;
import com.google.inject.Singleton;

/**
 * Provides implementations of Unmarshallable instances to
 * deserialize HTTP request result or WebSocket message to DTO.
 *
 * @author Artem Zatsarynnyy
 */
@Singleton
public class DtoUnmarshallerFactory {
    private DtoFactory dtoFactory;

    @Inject
    public DtoUnmarshallerFactory(DtoFactory dtoFactory) {
        this.dtoFactory = dtoFactory;
    }

    /**
     * Create new instance of {@link com.codenvy.ide.rest.Unmarshallable}
     * to deserialize HTTP request to DTO.
     *
     * @param dtoType
     *         type of DTO.
     * @return new instance of {@link com.codenvy.ide.rest.Unmarshallable}
     * @see com.codenvy.dto.shared.DTO
     */
    public <T> com.codenvy.ide.rest.Unmarshallable<T> newUnmarshaller(Class<T> dtoType) {
        return new DtoUnmarshaller<>(dtoType, dtoFactory);
    }

    /**
     * Create new instance of {@link com.codenvy.ide.rest.Unmarshallable}
     * to deserialize HTTP request to {@link Array} of DTO.
     *
     * @param dtoType
     *         type of DTO
     * @return new instance of {@link com.codenvy.ide.rest.Unmarshallable}
     * @see com.codenvy.dto.shared.DTO
     */
    public <T> com.codenvy.ide.rest.Unmarshallable<Array<T>> newArrayUnmarshaller(Class<T> dtoType) {
        return new DtoUnmarshaller<>(dtoType, dtoFactory);
    }

    /**
     * Create new instance of {@link com.codenvy.ide.websocket.rest.Unmarshallable}
     * to deserialize WebSocket message to DTO.
     *
     * @param dtoType
     *         type of DTO
     * @return new instance of {@link com.codenvy.ide.websocket.rest.Unmarshallable}
     * @see com.codenvy.dto.shared.DTO
     */
    public <T> com.codenvy.ide.websocket.rest.Unmarshallable<T> newWSUnmarshaller(Class<T> dtoType) {
        return new com.codenvy.ide.websocket.rest.DtoUnmarshaller<>(dtoType, dtoFactory);
    }

    /**
     * Create new instance of {@link com.codenvy.ide.websocket.rest.Unmarshallable}
     * to deserialize WebSocket message to {@link Array} of DTO.
     *
     * @param dtoType
     *         type of DTO
     * @return new instance of {@link com.codenvy.ide.websocket.rest.Unmarshallable}
     * @see com.codenvy.dto.shared.DTO
     */
    public <T> com.codenvy.ide.websocket.rest.Unmarshallable<Array<T>> newWSArrayUnmarshaller(Class<T> dtoType) {
        return new com.codenvy.ide.websocket.rest.DtoUnmarshaller<>(dtoType, dtoFactory);
    }
}
