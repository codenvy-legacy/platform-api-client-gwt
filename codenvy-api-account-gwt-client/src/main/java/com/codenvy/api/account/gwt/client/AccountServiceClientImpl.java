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
package com.codenvy.api.account.gwt.client;

import com.codenvy.api.account.shared.dto.AccountDescriptor;
import com.codenvy.api.account.shared.dto.MemberDescriptor;
import com.codenvy.api.account.shared.dto.SubscriptionDescriptor;
import com.codenvy.ide.MimeType;
import com.codenvy.ide.collections.Array;
import com.codenvy.ide.rest.AsyncRequestCallback;
import com.codenvy.ide.rest.AsyncRequestFactory;
import com.codenvy.ide.rest.HTTPHeader;
import com.google.inject.Inject;
import com.google.inject.Singleton;

/**
 * Implementation of {@link AccountServiceClient} service.
 *
 * @author Sergii Leschenko
 */
@Singleton
public class AccountServiceClientImpl implements AccountServiceClient {
    private final AsyncRequestFactory asyncRequestFactory;

    @Inject
    public AccountServiceClientImpl(AsyncRequestFactory asyncRequestFactory) {
        this.asyncRequestFactory = asyncRequestFactory;
    }

    /** {@inheritDoc} */
    @Override
    public void getSubscriptions(String accountId, AsyncRequestCallback<Array<SubscriptionDescriptor>> callback) {
        final String requestUrl = "/api/account/" + accountId + "/subscriptions";
        asyncRequestFactory.createGetRequest(requestUrl)
                           .header(HTTPHeader.ACCEPT, MimeType.APPLICATION_JSON)
                           .send(callback);
    }

    /** {@inheritDoc} */
    @Override
    public void getMemberships(AsyncRequestCallback<Array<MemberDescriptor>> callback) {
        final String requestUrl = "/api/account/";
        asyncRequestFactory.createGetRequest(requestUrl)
                           .header(HTTPHeader.ACCEPT, MimeType.APPLICATION_JSON)
                           .send(callback);
    }

    /** {@inheritDoc} */
    @Override
    public void getAccountById(String accountId, AsyncRequestCallback<AccountDescriptor> callback) {
        final String requestUrl = "/api/account/" + accountId;
        asyncRequestFactory.createGetRequest(requestUrl)
                           .header(HTTPHeader.ACCEPT, MimeType.APPLICATION_JSON)
                           .send(callback);
    }
}
