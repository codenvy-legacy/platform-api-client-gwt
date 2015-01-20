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
package com.codenvy.api.account.gwt.client;

import com.codenvy.api.account.shared.dto.AccountDescriptor;
import com.codenvy.api.account.shared.dto.MemberDescriptor;
import com.codenvy.api.account.shared.dto.SubscriptionDescriptor;
import com.codenvy.ide.collections.Array;
import com.codenvy.ide.rest.AsyncRequestCallback;

import javax.annotation.Nonnull;

/**
 * Client for IDE3 Subscription service.
 *
 * @author Sergii Leschenko
 */
public interface AccountServiceClient {

    /**
     * Get account by id.
     *
     * @param accountId
     *         id of account
     * @param callback
     *         the callback to use for the response
     */
    void getAccountById(@Nonnull String accountId, AsyncRequestCallback<AccountDescriptor> callback);

    /**
     * Get memberships for current user
     *
     * @param callback
     *         the callback to use for the response
     */
    void getMemberships(AsyncRequestCallback<Array<MemberDescriptor>> callback);

    /**
     * Get subscriptions of specified account.
     *
     * @param accountId
     *         id of account
     * @param callback
     *         the callback to use for the response
     */
    void getSubscriptions(@Nonnull String accountId, AsyncRequestCallback<Array<SubscriptionDescriptor>> callback);

    /**
     * Get subscription with specified id of specified account.
     *
     * @param accountId
     *         id of account
     * @param serviceId
     *         id of service
     * @param callback
     *         the callback to use for the response
     */
    void getSubscriptionByServiceId(@Nonnull String accountId,
                                    @Nonnull String serviceId,
                                    AsyncRequestCallback<Array<SubscriptionDescriptor>> callback);
}
