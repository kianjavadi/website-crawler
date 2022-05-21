package com.javadi.websitecrawler.content;

import java.net.HttpURLConnection;

/**
 * do nothing, because the extension of the resource has not been recognized
 */
public class NoopContentHandler implements SpecificContentHandler {

    @Override
    public String handle(String url, HttpURLConnection connection, String fileExtension) {
        System.out.printf("extension was not identified for %s, so it will be neither analyzed nor stored%n", url);
        return "";
    }

}
