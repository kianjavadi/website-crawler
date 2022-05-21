package com.javadi.websitecrawler.crawler;

import java.util.Set;

/**
 * This interface is responsible for reading and parsing an HTML page and returns all the urls within it that belongs to the same domain
 * any URL that is outside the domain must be filtered by the interface itself
 */
public interface UrlDiscoverer {

    /**
     * gets a raw html page in string and returns all the URLs in it that belong to the given domain
     * @param rawHtml the html content in String
     * @return the URLs that belong to the same domain and are not outside of it
     */
    Set<String> discover(String rawHtml);

}
