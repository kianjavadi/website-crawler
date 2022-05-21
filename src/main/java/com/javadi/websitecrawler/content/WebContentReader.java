package com.javadi.websitecrawler.content;

import java.io.IOException;
import java.net.HttpURLConnection;

/**
 * This interface deals with reading content of a resource in the website
 */
public interface WebContentReader {

    /**
     * gets a connection to a resource and reads its content as String
     * @param connection to the resource
     * @return content of the resource
     * @throws IOException if anything goes wrong during reading the content
     */
    String readAsString(HttpURLConnection connection) throws IOException;

    /**
     * gets a String url and returns an open connection to that resource
     * @param stringUrl the url of the resource
     * @return the opened connection
     * @throws IOException if anything goes wrong during opening of the connection
     */
    HttpURLConnection makeConnection(String stringUrl) throws IOException;

    /**
     * gets a connection to a resource and tries to understand its extension.
     * an html page will be parsed only if this method is able to identify it correctly
     * the returned extension must be started with 'dot', e.g. ".html"
     * @param connection the connection to the resource
     * @return the extension of the resource, starting with dot, e.g. ".html", ".jpg", ...
     */
    String getFileExtension(HttpURLConnection connection);

}
