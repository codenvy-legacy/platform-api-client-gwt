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
package com.codenvy.api.project.gwt.client;

/** @author Artem Zatsarynnyy */
public class QueryExpression {
    private String name;
    private String path;
    private String mediaType;
    private String text;
    private int    maxItems;
    private int    skipCount;

    /**
     * Get path to start search.
     *
     * @return path to start search
     */
    public String getPath() {
        return path;
    }

    /**
     * Set path to start search.
     *
     * @param path
     *         path to start search
     * @return this {@code QueryExpression}
     */
    public QueryExpression setPath(String path) {
        this.path = path;
        return this;
    }

    /**
     * Get name of file to search.
     *
     * @return file name to search
     */
    public String getName() {
        return name;
    }

    /**
     * Set name of file to search.
     * <p/>
     * Supported wildcards are:
     * <ul>
     * <li><code>*</code>, which matches any character sequence (including the empty one);
     * <li><code>?</code>, which matches any single character.
     * </ul>
     *
     * @param name
     *         file name to search
     * @return this {@code QueryExpression}
     */
    public QueryExpression setName(String name) {
        this.name = name;
        return this;
    }

    /**
     * Get media type of file to search.
     *
     * @return media type of file to search
     */
    public String getMediaType() {
        return mediaType;
    }

    /**
     * Set media type of file to search.
     *
     * @param mediaType
     *         media type of file to search
     * @return this {@code QueryExpression}
     */
    public QueryExpression setMediaType(String mediaType) {
        this.mediaType = mediaType;
        return this;
    }

    /**
     * Get text to search.
     *
     * @return text to search
     */
    public String getText() {
        return text;
    }

    /**
     * Set text to search.
     *
     * @param text
     *         text to search
     * @return this {@code QueryExpression}
     */
    public QueryExpression setText(String text) {
        this.text = text;
        return this;
    }

    /**
     * Get maximum number of items in response.
     *
     * @return maximum number of items in response
     */
    public int getMaxItems() {
        return maxItems;
    }

    /**
     * Set maximum number of items in response.
     *
     * @param maxItems
     *         maximum number of items in response
     * @return this {@code QueryExpression}
     */
    public QueryExpression setMaxItems(int maxItems) {
        this.maxItems = maxItems;
        return this;
    }

    /**
     * Get amount of items to skip.
     *
     * @return amount of items to skip
     */
    public int getSkipCount() {
        return skipCount;
    }

    /**
     * Set amount of items to skip.
     *
     * @param skipCount
     *         amount of items to skip
     * @return this {@code QueryExpression}
     */
    public QueryExpression setSkipCount(int skipCount) {
        this.skipCount = skipCount;
        return this;
    }
}
