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

import java.util.HashMap;
import java.util.Map;


/**
 * The loader for rest request.
 *
 * @author Andrey Plotnikov
 * @author Sergii Leschenko
 */
public class IdeLoader extends Loader {
    private PopupPanel loader;
    private Grid       grid;
    private MessageHeap messageHeap = new MessageHeap();

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
        grid = new Grid(1, 2);
        grid.setWidget(0, 0, pinionWidget);
        container.add(grid);
        loader.add(container);
        loader.ensureDebugId("loader");
    }

    /** {@inheritDoc} */
    @Override
    public void show() {
        //show with default message
        show(getMessage());
    }

    /** {@inheritDoc} */
    @Override
    public void show(String message) {
        messageHeap.push(message);
        updateMessage(message);
        loader.center();
        loader.show();
    }

    /** {@inheritDoc} */
    @Override
    public void hide() {
        hide(getMessage());
    }

    /** {@inheritDoc} */
    @Override
    public void hide(String message) {
        String newMessage = messageHeap.drop(message);
        if (newMessage != null) {
            updateMessage(newMessage);
        } else {
            loader.hide();
        }
    }

    private void updateMessage(String message) {
        grid.setText(0, 1, message);
    }

    private class MessageHeap {
        private final Map<String, Integer> messages = new HashMap<>();

        /**
         * Pushes message to heap
         *
         * @param message
         *         message for push
         */
        public void push(String message) {
            if (messages.containsKey(message)) {
                messages.put(message, messages.get(message) + 1);
            } else {
                messages.put(message, 1);
            }
        }

        /**
         * Drop message from heap
         *
         * @param message
         *         message for drop
         * @return
         * any message from heap or <code>null</code> if heap does have message
         */
        public String drop(String message) {
            int count = messages.get(message) - 1;
            if (count == 0) {
                messages.remove(message);
                if (message.equals(grid.getText(0, 1)) && !messages.isEmpty()) {
                    return messages.keySet().iterator().next();
                }

                return null;
            } else {
                messages.put(message, count);
                return message;
            }
        }
    }
}