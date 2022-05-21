package com.javadi.websitecrawler.utils;

import java.util.regex.Matcher;

public interface UrlUtils {

    String encodeUrl(String url);

    String getDomainNameForLinksWithinWebsite(String url);

    String getFileNameWithExtension(String stringUrl, String fileExtension);

    String getParentPath(String url);

    boolean isUrlValid(String domain, String url);

    String getDomainForApplicationInput(String website);

    String getProtocol(String website);

    String getUrlConsideringHref(Matcher matcher);

    String getFinalCompleteUrl(String url, String protocol);

    void validateWebUrl(String url) throws IllegalArgumentException;

}
