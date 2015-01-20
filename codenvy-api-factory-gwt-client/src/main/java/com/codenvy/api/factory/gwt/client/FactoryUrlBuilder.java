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
package com.codenvy.api.factory.gwt.client;

import com.codenvy.api.factory.NonEncodedFactoryBuilder;
import com.codenvy.api.vfs.shared.dto.ReplacementSet;
import com.codenvy.ide.collections.Collections;
import com.codenvy.ide.dto.DtoFactory;
import com.google.gwt.http.client.URL;

import java.util.List;

/**
 * Is used for converting factory to non encoded url version by using
 * {@link FactoryUrlBuilder#buildNonEncoded(com.codenvy.api.factory.dto.Factory)}
 * Has implementation of GWT specific methods from {@link NonEncodedFactoryBuilder}.
 *
 * @author Ann Shumilova
 * @author Sergii Leschenko
 */
public class FactoryUrlBuilder extends NonEncodedFactoryBuilder {

    private final DtoFactory dtoFactory;

    public FactoryUrlBuilder(DtoFactory dtoFactory) {
        this.dtoFactory = dtoFactory;
    }

    /** {@inheritDoc} */
    @Override
    protected String encode(String value) {
        return URL.encode(value);
    }

    /** {@inheritDoc} */
    @Override
    protected String toJson(List<ReplacementSet> dto) {
        return dtoFactory.toJson(Collections.createArray(dto));
    }

}