package com.javadi.websitecrawler.content;

import java.io.IOException;
import java.net.HttpURLConnection;

/**
 * This interface is used within the ContentHandler
 * based on the extension of the resource, we might want to handle its content differently,
 * for example if the resource is HTML then we need to store it as well as reading and parsing its content,
 * but for other resources we only need to store them.
 * so it has all the responsibility of the ContentHandler, it means that it needs to:
 *      - read the resource
 *      - write its content
 *      - returns the content of the resource ONLY IF the resource is an html page, otherwise it must return empty String ""
 */
public interface SpecificContentHandler {

    /**
     * gets a url to the resource and its extension, and if its needed reads it by using the provided connection, stores it and if it's HTML, returns its content
     * @param url of the given resource
     * @param connection to the resource
     * @param fileExtension of the resource
     * @return the content of the resource only if it's an HTML page
     * @throws IOException
     */
    String handle(String url, HttpURLConnection connection, String fileExtension) throws IOException;

}
