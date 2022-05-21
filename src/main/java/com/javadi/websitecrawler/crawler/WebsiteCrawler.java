package com.javadi.websitecrawler.crawler;

import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.regex.Matcher;

import static com.javadi.websitecrawler.config.ApplicationConstants.HREF_PATTERN;
import static com.javadi.websitecrawler.config.ApplicationConstants.HTML_EXTENSION_WITH_DOT;
import static com.javadi.websitecrawler.config.ApplicationConstants.URL_PATTERN;

public class WebsiteCrawler {

    private final Queue<String> queue;
    private final Set<String> discoveredWebsites;
    private final String workingDirectory;

    private final UrlUtils urlUtils;
    private final ContentReader contentReader;
    private final ContentWriter contentWriter;
    private final String domain;
    private final String protocol;

    public WebsiteCrawler(String domain, UrlUtils urlUtils, ContentReader contentReader, ContentWriter contentWriter) {
        this.domain = urlUtils.getDomainForApplicationInput(domain);
        this.protocol = urlUtils.getProtocol(domain);
        this.urlUtils = urlUtils;
        this.contentReader = contentReader;
        this.contentWriter = contentWriter;
        this.queue = new LinkedBlockingDeque<>();
        this.discoveredWebsites = ConcurrentHashMap.newKeySet();
        workingDirectory = System.getProperty("user.home") + File.separator + this.domain;
    }

    public void crawl() throws Exception {
        Files.createDirectories(Path.of(workingDirectory));
        String website = protocol + "://" + domain;
        this.queue.add(website);
        this.discoveredWebsites.add(website);

        while (!queue.isEmpty()) {
            String url = this.queue.remove();
            System.out.println("Analyzing: " + url);
            String rawHtml = readAndStoreURL(url);

            Matcher hrefMatcher = HREF_PATTERN.matcher(rawHtml);
            discoverUrls(hrefMatcher);

            Matcher urlMatcher = URL_PATTERN.matcher(rawHtml);
            discoverUrls(urlMatcher);
        }
    }

    private void discoverUrls(Matcher hrefMatcher) {
        while (hrefMatcher.find()) {
            String url = urlUtils.getUrlConsideringHref(hrefMatcher);
            String domain = urlUtils.getDomainNameForLinksWithinWebsite(url);
            String finalCompleteUrl = urlUtils.getFinalCompleteUrl(url, protocol);
            if (urlUtils.isUrlValid(domain, finalCompleteUrl) && discoveredWebsites.add(finalCompleteUrl)) {
                System.out.println(finalCompleteUrl);
                queue.add(urlUtils.encodeUrl(finalCompleteUrl));
            }
        }
    }

    private String readAndStoreURL(String stringUrl) {
        try {
            HttpURLConnection connection = contentReader.makeConnection(stringUrl);
            String fileExtension = contentReader.getFileExtension(connection);
            if (fileExtension == null)
                return "";
            String filePath = getFilePath(stringUrl, fileExtension);
            return readAndWriteContent(connection, fileExtension, filePath);
        } catch (Exception e) {
            return "";
        }
    }

    private String getFilePath(String stringUrl, String fileExtension) throws IOException {
        String parentDirectory = urlUtils.getParentPath(stringUrl);
        Files.createDirectories(Path.of(workingDirectory, parentDirectory));
        String fileName = urlUtils.getFileNameWithExtension(stringUrl, fileExtension);
        return workingDirectory + File.separator + parentDirectory + File.separator + fileName;
    }

    private String readAndWriteContent(HttpURLConnection connection, String fileExtension, String filePath) throws IOException {
        if (fileExtension.equals(HTML_EXTENSION_WITH_DOT)) {
            return readAndWriteHtmlContent(connection, filePath);
        } else {
            // we're not interested in non-html files
            contentWriter.write(connection, filePath);
            return "";
        }
    }

    private String readAndWriteHtmlContent(HttpURLConnection connection, String filePath) throws IOException {
        String content = contentReader.readAsString(connection);
        contentWriter.write(content, filePath);
        return content;
    }

}
