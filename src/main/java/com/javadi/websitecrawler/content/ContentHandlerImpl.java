package com.javadi.websitecrawler.content;

import com.javadi.websitecrawler.crawler.ContentHandler;
import com.javadi.websitecrawler.crawler.UrlUtils;

import java.net.HttpURLConnection;

import static com.javadi.websitecrawler.config.ApplicationConstants.HTML_EXTENSION_WITH_DOT;

public class ContentHandlerImpl implements ContentHandler {

    private final WebContentReader webContentReader;
    private final BinaryContentHandler binaryContentHandler;
    private final HtmlContentHandler htmlContentHandler;
    private final NoopContentHandler noopContentHandler;

    public ContentHandlerImpl(UrlUtils urlUtils, WebContentReader webContentReader, ContentReader contentReader, ContentWriter contentWriter) {
        this.webContentReader = webContentReader;
        binaryContentHandler = new BinaryContentHandler(urlUtils, contentWriter);
        htmlContentHandler = new HtmlContentHandler(urlUtils, contentReader, contentWriter);
        noopContentHandler = new NoopContentHandler();
    }


    @Override
    public String handle(String url) {
        try {
            HttpURLConnection connection = webContentReader.makeConnection(url);
            String fileExtension = webContentReader.getFileExtension(connection);
            SpecificContentHandler contentHandler = getProperContentHandler(fileExtension);
            return contentHandler.handle(url, connection, fileExtension);
        } catch (Exception e) {
            System.out.printf("Exception occurred during handling content of: %s > Exception: %s%n", url, e.getMessage());
            return "";
        }
    }

    private SpecificContentHandler getProperContentHandler(String fileExtension) {
        if (fileExtension == null) {
            return noopContentHandler;
        } else if (fileExtension.equals(HTML_EXTENSION_WITH_DOT)) {
            return htmlContentHandler;
        } else {
            return binaryContentHandler;
        }
    }

}
