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
package com.codenvy.ide.ui.loader;

import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.resources.client.DataResource;
import com.google.gwt.resources.client.DataResource.MimeType;

/**
 * Resources for loader.
 *
 * @author <a href="mailto:aplotnikov@codenvy.com">Andrey Plotnikov</a>
 */
public interface LoaderResources extends ClientBundle {

    public interface PinionCss extends CssResource {
        String pinion();
    }

    @MimeType("image/png")
    @Source("com/codenvy/ide/ui/loader/pinion-icon.png")
    DataResource pinionIcon();

    @Source({"com/codenvy/ide/common/constants.css", "com/codenvy/ide/ui/loader/IdeLoader.css", "com/codenvy/ide/api/ui/style.css"})
    PinionCss Css();

}
