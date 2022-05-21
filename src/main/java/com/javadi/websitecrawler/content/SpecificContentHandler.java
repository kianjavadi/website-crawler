package com.javadi.websitecrawler.content;

import java.io.IOException;
import java.net.HttpURLConnection;

public interface SpecificContentHandler {

    String handle(String url, HttpURLConnection connection, String fileExtension) throws IOException;

}
