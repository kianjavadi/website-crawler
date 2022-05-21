package com.javadi.websitecrawler.content;

import java.io.IOException;

public interface ContentReader {

    String readAsString(String filePath) throws IOException;

}
