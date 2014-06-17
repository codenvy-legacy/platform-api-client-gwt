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
package com.codenvy.ide.ui.loader;

import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.inject.Inject;

/**
 * The loader for rest request.
 *
 * @author <a href="mailto:aplotnikov@codenvy.com">Andrey Plotnikov</a>
 */
public class IdeLoader extends Loader {
    private PopupPanel loader;

    /**
     * Create loader.
     *
     * @param resources
     */
    @Inject
    public IdeLoader(LoaderResources resources) {
        resources.Css().ensureInjected();
        loader = new PopupPanel();
        FlowPanel container = new FlowPanel();
        HTML pinionWidget = new HTML("<i></i><i></i>");
        pinionWidget.getElement().setClassName(resources.Css().pinion());
        Grid grid = new Grid(1, 2);
        grid.setWidget(0, 0, pinionWidget);
        grid.setText(0, 1, getMessage());
        container.add(grid);

        loader.add(container);
        loader.ensureDebugId("loader");
    }

    /** {@inheritDoc} */
    @Override
    public void show() {
        loader.center();
        loader.show();
    }

    /** {@inheritDoc} */
    @Override
    public void hide() {
        loader.hide();
    }
}