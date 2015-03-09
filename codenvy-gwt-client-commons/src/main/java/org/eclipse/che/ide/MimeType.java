/*
 * The contents of this file are subject to the terms
 * of the Common Development and Distribution License
 * (the "License").  You may not use this file except
 * in compliance with the License.
 * 
 * You can obtain a copy of the license at
 * http://www.opensource.org/licenses/cddl1.php
 * See the License for the specific language governing
 * permissions and limitations under the License.
 */

/*
 * MediaType.java
 *
 * Created on March 22, 2007, 2:35 PM
 *
 */

package org.eclipse.che.ide;

import java.lang.String;

/**
 * An abstraction for a media type. Instances are immutable.
 *
 * @see <a href="http://www.w3.org/Protocols/rfc2616/rfc2616-sec3.html#sec3.7">HTTP/1.1 section 3.7</a>
 */
public interface MimeType {

    final static String WILDCARD = "*/*";

    /** "application/xml" */
    final static String APPLICATION_XML = "application/xml";

    /** "application/atom+xml" */
    final static String APPLICATION_ATOM_XML = "application/atom+xml";

    /** "application/xhtml+xml" */
    final static String APPLICATION_XHTML_XML = "application/xhtml+xml";

    /** "application/svg+xml" */
    final static String APPLICATION_SVG_XML = "application/svg+xml";

    /** "application/json" */
    final static String APPLICATION_JSON = "application/json";

    /** "application/x-www-form-urlencoded" */
    final static String APPLICATION_FORM_URLENCODED = "application/x-www-form-urlencoded";

    /** "multipart/form-data" */
    final static String MULTIPART_FORM_DATA = "multipart/form-data";

    /** "application/octet-stream" */
    final static String APPLICATION_OCTET_STREAM = "application/octet-stream";

    /** "text/plain" */
    final static String TEXT_PLAIN = "text/plain";

    /** "text/xml" */
    final static String TEXT_XML = "text/xml";

    /** "text/html" */
    final static String TEXT_HTML = "text/html";

    /** "text/css" */
    final static String TEXT_CSS = "text/css";

    /** "text/x-c" */
    final static String TEXT_C = "text/x-c";

    /** "text/x-h" */
    final static String TEXT_H = "text/x-h";

    /** "text/x-markdown" */
    final static String TEXT_MARKDOWN = "text/x-markdown";

    /**
     * temporary  application/x-jaxrs+groovy replaced on application/x-jaxrs-groovy to avoid error of PROPFIND response which returns
     * "application/x-jaxrs groovy
     */
    final static String GROOVY_SERVICE = "application/x-jaxrs+groovy";//"script/groovy"; //application/x-jaxrs+groovy

    /** "application/x-groovy" */
    final static String APPLICATION_GROOVY = "application/x-groovy";

    /** "application/javascript" */
    final static String APPLICATION_JAVASCRIPT = "application/javascript";

    /** "application/x-javascript" */
    final static String APPLICATION_X_JAVASCRIPT = "application/x-javascript";

    /** "text/javascript" */
    final static String TEXT_JAVASCRIPT = "text/javascript";

    /** "application/x-google-gadget" */
    final static String GOOGLE_GADGET = "application/x-google-gadget";

    /** "application/x-uwa-widget" */
    final static String UWA_WIDGET = "application/x-uwa-widget";

    /**
     * temporary "application/x-groovy+html" replaced on "application/x-groovy-html" to avoid error of PROPFIND response which returns
     * "application/x-groovy html"
     */
    final static String GROOVY_TEMPLATE = "application/x-groovy+html";

    /** Chromattic Data Object */
    final static String CHROMATTIC_DATA_OBJECT = "application/x-chromattic+groovy";

    /** "text/x-java" */
    final static String TEXT_X_JAVA = "text/x-java";

    /** "text/x-java-source" */
    final static String TEXT_X_JAVA_SOURCE = "text/x-java-source";

    /** "application/java-class" */
    final static String APPLICATION_JAVA_CLASS = "application/java-class";

    /** "application/jsp" */
    final static String APPLICATION_JSP = "application/jsp";

    /** Ruby script Mime type "application/x-ruby" */
    final static String APPLICATION_RUBY = "application/x-ruby";

    /** PHP script Mime types */
    final static String APPLICATION_PHP         = "application/php";
    final static String APPLICATION_X_PHP       = "application/x-php";
    final static String APPLICATION_X_HTTPD_PHP = "application/x-httpd-php";

    /** Diff mime-type "text/x-diff" */
    final static String DIFF = "text/x-diff";

    /** text/x-python */
    final static String TEXT_X_PYTHON = "text/x-python";

    /** text/yaml */
    final static String TEXT_YAML = "text/yaml";

    /** Image types */
    final static String IMAGE_BMP = "image/bmp";

    final static String IMAGE_GIF = "image/gif";

    final static String IMAGE_X_ICON = "image/x-icon";

    final static String IMAGE_JPEG = "image/jpeg";

    final static String IMAGE_PNG = "image/png";

    final static String IMAGE_SVG_XML = "image/svg+xml";
}