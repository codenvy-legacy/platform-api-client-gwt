package com.codenvy.api.factory.gwt.client;

import com.codenvy.api.factory.dto.Factory;
import com.codenvy.ide.websocket.WebSocketException;
import com.codenvy.ide.websocket.rest.RequestCallback;

import javax.validation.constraints.NotNull;

/**
 * Client for IDE3 Factory service.
 *
 * @author Vladyslav Zhukovskii
 */
public interface FactoryServiceClient {
    /**
     * Get valid JSON factory object based on input factory ID or raw query string which represent non encoded factory URL.
     *
     * @param raw
     *         factory ID or query string which represents factory non encoded version
     * @param encoded
     *         if factory ID passed, encoded should be true, otherwise if non encoded factory URL passed value for this parameter should be
     *         false
     * @param callback
     *         callback which return valid JSON object of factory or exception if occurred
     * @throws WebSocketException
     *         if problem with websocket occurred
     */
    void getFactory(@NotNull String raw, boolean encoded, @NotNull RequestCallback<Factory> callback) throws WebSocketException;

    /**
     * Send valid JSON factory object to server side of IDE3 to accept it. Accepting means
     * <ul>
     * <li>create empty project directory;</li>
     * <li>perform clone project from git repository;</li>
     * <li>checkout to commit id or branch;</li>
     * <li>replacing variables;</li>
     * <li>erasing git information(if need).</li>
     * </ul>
     * If accepting was successful factory object return back to user(it may be transformed, e.g. project name parameter changed).
     *
     * @param factory
     *         JSON factory object to accept
     * @param callback
     *         callback which return valid JSON object of factory or exception if occurred
     * @throws WebSocketException
     *         if problem with websocket occurred
     */
    void acceptFactory(@NotNull Factory factory, @NotNull RequestCallback<Factory> callback) throws WebSocketException;
    
    /**
     * @param factoryId Factory's id
     * @param type snippent's type (markdown, html, etc)
     * @param callback callback which returns snippet of the factory or exception if occurred
     * @throws WebSocketException if problem with websocket occurred
     */
    void getFactorySnippet(@NotNull String factoryId, @NotNull String type, @NotNull RequestCallback<String> callback) throws WebSocketException;
}
