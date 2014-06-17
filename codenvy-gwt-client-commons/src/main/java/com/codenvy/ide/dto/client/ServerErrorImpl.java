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
package com.codenvy.ide.dto.client;

import com.codenvy.ide.dto.shared.ServerError;

/**
 * @author <a href="mailto:evidolob@codenvy.com">Evgen Vidolob</a>
 * @version $Id:
 */
public class ServerErrorImpl extends RoutableDtoClientImpl implements ServerError {

    protected ServerErrorImpl() {

    }

    @Override
    public final native String getDetails() /*-{
        return this["details"];
    }-*/;

    public final native ServerErrorImpl setDetails(String details) /*-{
        this["details"] = details;
        return this;
    }-*/;

    public final native boolean hasDetails() /*-{
        return this.hasOwnProperty("details");
    }-*/;

    @Override
    public final native FailureReason getFailureReason() /*-{
        return @org.exoplatform.ide.dtogen.shared.ServerError.FailureReason::valueOf(Ljava/lang/String;)(this["failureReason"]);
    }-*/;

    public final native ServerErrorImpl setFailureReason(FailureReason failureReason) /*-{
        failureReason = failureReason.@org.exoplatform.ide.dtogen.shared.ServerError.FailureReason::toString()();
        this["failureReason"] = failureReason;
        return this;
    }-*/;

    public final native boolean hasFailureReason() /*-{
        return this.hasOwnProperty("failureReason");
    }-*/;
}