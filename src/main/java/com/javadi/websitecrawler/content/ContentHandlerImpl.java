package com.javadi.websitecrawler.content;

import com.javadi.websitecrawler.crawler.ContentHandler;
import com.javadi.websitecrawler.crawler.ContentReader;
import com.javadi.websitecrawler.crawler.ContentWriter;
import com.javadi.websitecrawler.crawler.UrlUtils;

import java.net.HttpURLConnection;

import static com.javadi.websitecrawler.config.ApplicationConstants.HTML_EXTENSION_WITH_DOT;

public class ContentHandlerImpl implements ContentHandler {

    private final UrlUtils urlUtils;
    private final ContentReader contentReader;
    private final ContentWriter contentWriter;

    public ContentHandlerImpl(UrlUtils urlUtils, ContentReader contentReader, ContentWriter contentWriter) {
        this.urlUtils = urlUtils;
        this.contentReader = contentReader;
        this.contentWriter = contentWriter;
    }

    @Override
    public String handle(String url) {
        try {
            HttpURLConnection connection = contentReader.makeConnection(url);
            String fileExtension = contentReader.getFileExtension(connection);
            String content;
            if (fileExtension == null) {
                System.out.printf("extension was not identified for %s, so it will be neither analyzed nor stored%n", url);
                content = "";
            } else if (fileExtension.equals(HTML_EXTENSION_WITH_DOT)) {
                content = contentReader.readAsString(connection);
                String parentPath = urlUtils.getParentPath(url);
                String fileName = urlUtils.getFileNameWithExtension(url, fileExtension);
                contentWriter.write(content, parentPath, fileName);
            } else {
                // we're not interested in analyzing non-html files
                String parentPath = urlUtils.getParentPath(url);
                String fileName = urlUtils.getFileNameWithExtension(url, fileExtension);
                contentWriter.write(connection, parentPath, fileName);
                content = "";
            }
            return content;
        } catch (Exception e) {
            System.out.printf("Exception happened during handling content of: %s%n", e.getMessage());
            return "";
        }
    }

}
