/*
 * CODENVY CONFIDENTIAL
 * __________________
 * 
 *  [2012] - [2014] Codenvy, S.A. 
 *  All Rights Reserved.
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
