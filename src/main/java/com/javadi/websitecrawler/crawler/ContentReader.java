package com.javadi.websitecrawler.crawler;

import java.io.IOException;

public interface ContentReader {

    String readAsString(String filePath) throws IOException;

}
