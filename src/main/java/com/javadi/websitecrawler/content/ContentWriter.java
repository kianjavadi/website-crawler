package com.javadi.websitecrawler.content;

import java.io.IOException;
import java.net.URLConnection;

public interface ContentWriter {

    String write(String content, String parentPath, String fileName) throws IOException;

    String write(URLConnection connection, String parentPath, String fileName) throws IOException;

}
