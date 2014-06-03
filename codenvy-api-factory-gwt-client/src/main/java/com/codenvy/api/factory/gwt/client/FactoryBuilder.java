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
package com.codenvy.api.factory.gwt.client;

import com.codenvy.api.factory.NonEncodedFactoryBuilder;
import com.codenvy.api.factory.dto.Variable;
import com.codenvy.ide.collections.Collections;
import com.codenvy.ide.dto.DtoFactory;
import com.google.gwt.http.client.URL;

import java.util.List;

/**
 * Is used for converting factory to non encoded url version by using
 * {@link FactoryBuilder#buildNonEncoded(com.codenvy.api.factory.dto.Factory)}
 * Has implementation of GWT specific methods from {@link NonEncodedFactoryBuilder}.
 * 
 * @author Ann Shumilova
 */
public class FactoryBuilder extends NonEncodedFactoryBuilder {
    
    private final DtoFactory dtoFactory;
    
    /**
     * @param dtoFactory DTO factory
     */
    public FactoryBuilder(DtoFactory dtoFactory) {
        this.dtoFactory = dtoFactory;
    }
    
    /** {@inheritDoc} */
    @Override
    protected String safeGwtEncode(String value) {
        return URL.encode(value);
    }

    /** {@inheritDoc} */
    @Override
    protected String safeGwtToJson(List<Variable> dto) {
        return dtoFactory.toJson(Collections.createArray(dto));
    }

}
