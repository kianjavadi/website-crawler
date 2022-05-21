package com.javadi.websitecrawler.content;

import com.javadi.websitecrawler.utils.UrlUtils;

import java.io.IOException;
import java.net.HttpURLConnection;

/**
 * handles every valid resource that is not an html
 */
public class BinaryContentHandler implements SpecificContentHandler {

    private final UrlUtils urlUtils;
    private final ContentWriter contentWriter;

    public BinaryContentHandler(UrlUtils urlUtils, ContentWriter contentWriter) {
        this.urlUtils = urlUtils;
        this.contentWriter = contentWriter;
    }

    @Override
    public String handle(String url, HttpURLConnection connection, String fileExtension) throws IOException {
        String parentPath = urlUtils.getParentPath(url);
        String fileName = urlUtils.getFileNameWithExtension(url, fileExtension);
        contentWriter.write(connection, parentPath, fileName);
        // we're not interested in analyzing non-html files, so we just store them
        return "";
    }

}
