package com.javadi.websitecrawler.crawler;

import java.io.IOException;
import java.net.URLConnection;

public interface ContentWriter {

    void write(String content, String filePath) throws IOException;

    void write(URLConnection connection, String filePath) throws IOException;

}
