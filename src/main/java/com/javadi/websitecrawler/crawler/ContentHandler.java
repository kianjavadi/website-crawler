package com.javadi.websitecrawler.crawler;

/**
 * This interface is responsible for handling a given url, it means that it needs to:
 *      - read the resource
 *      - write its content
 *      - returns the content of the resource ONLY IF the resource is an html page, otherwise it must return empty String ""
 */
public interface ContentHandler {

    /**
     * gets a url to the resource, reads it, stores it and if it's HTML, returns its content
     * @param url the url of the resource
     * @return the content of the resource only if it's an HTML page
     */
    String handle(String url);

}
