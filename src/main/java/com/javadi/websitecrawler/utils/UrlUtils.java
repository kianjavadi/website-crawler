package com.javadi.websitecrawler.utils;

import java.util.regex.Matcher;

/**
 * deals with a given URL
 */
public interface UrlUtils {

    /**
     * encodes the illegal characters in the url, e.g.: '>'
     * @param url the given url
     * @return the given url with encoded illegal characters
     */
    String encodeUrl(String url);

    /**
     * gets a url that can be relative or absolute and returns the domain name of it
     * @param url the given url
     * @return domain name of the url
     */
    String getDomainNameForLinksWithinWebsite(String url);

    /**
     * gets the URL of a resource and its inferred extension and returns the resource's name + the given extension.
     * ContentHandlers can use this method in order to find out the name of the resource
     * @param stringUrl the url of the resource
     * @param fileExtension the extension of it
     * @return the resource's name that is going to be stored in the storage
     */
    String getFileNameWithExtension(String stringUrl, String fileExtension);

    /**
     * gets the url of a resource and returns the parent path of it. empty string is returned for the resources in the root
     * this parent path is useful when ContentWriters tries to store the resource
     * @param url the url of the resource
     * @return the parent path of it. if the resource is in the root, empty string "" must be returned
     */
    String getParentPath(String url);

    /**
     * identifies whether a given URL is eligible for analyzing and storing or not:
     *      - the resource must be within the same given domain
     *      - it should be a valid resource that can be stored, not an API url or something
     * @param domain the domain of the website that is considered for identifying internal resources
     * @param url the URL of the resource
     * @return whether the resource is eligible for analyzing and storing or not
     */
    boolean isUrlValid(String domain, String url);

    /**
     * gets the input of the application (the website that we want to crawl) and returns its domain
     * @param website the given website
     * @return the domain of the given website
     */
    String getDomainForApplicationInput(String website);

    /**
     * gets the input of the application (the website that we want to crawl) and returns its protocol. e.g. http, https
     * @param website the given website
     * @return the protocol of the given website. e.g. http, https
     */
    String getProtocol(String website);

    /**
     * gets a matcher and handles and returns the url by removing the extra attributes in it, e.g. "href="
     * @param matcher the given matcher
     * @return the url without extra attributes like "href="
     */
    String getUrlConsideringHref(Matcher matcher);

    /**
     * gets a urls and returns a clean complete url, for example it can do followings:
     *      - handles if the url is relative =< returns a complete url including protocol and domain of the website
     *      - remove sharp sign # in it
     *      - remove www
     *      - remove everything after question mark
     *      - remove forward slash / at the end
     * @param url the given url
     * @param protocol the protocol of the website
     * @return a clean complete url
     */
    String getFinalCompleteUrl(String url, String protocol);

    /**
     * is used for validating the input of the application, i.e. the website that we want to crawl
     * @param url the website's URL
     * @throws IllegalArgumentException if the given url is invalid
     */
    void validateWebUrl(String url) throws IllegalArgumentException;

}
