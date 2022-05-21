package com.javadi.websitecrawler.crawler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WebsiteCrawler {

    private static final String DEFAULT_PROTOCOL = "https";
    private static final String HREF_ATTRIBUTE = "href=\"";
    private static final int HREF_ATTRIBUTE_LENGTH = HREF_ATTRIBUTE.length();
    private static final Pattern URL_PATTERN = Pattern.compile("(http|ftp|https)://([\\w_-]+(?:(?:\\.[\\w_-]+)+))([\\w.,@?^=%&:/~+#-]*[\\w@?^=%&/~+#-])");
    private static final Pattern HREF_PATTERN = Pattern.compile("(href=\")([^\"]+)");
    private static final Pattern HTTP_PATTERN = Pattern.compile("((ftp|https?)(://))?(www.)?(.*)");

    private final String domain;
    private final String protocol;
    private final Queue<String> queue;
    private final Set<String> discoveredWebsites;

    public WebsiteCrawler(String website) {
        this.queue = new LinkedList<>();
        this.discoveredWebsites = new HashSet<>();
        this.domain = getDomainForApplicationInput(website);
        this.protocol = getProtocol(website);
    }

    public void crawl() {
        addRootUrlToQueue();

        while (!queue.isEmpty()) {
            String url = this.queue.remove();
            try {
                System.out.println("Analyzing: " + url);
                String rawHtml = readURL(url);

                Matcher urlMatcher = URL_PATTERN.matcher(rawHtml);
                discoverUrls(urlMatcher);

                Matcher hrefMatcher = HREF_PATTERN.matcher(rawHtml);
                discoverUrls(hrefMatcher);
            } catch (Exception e) {
                System.err.printf("exception occurred while analyzing url: %s | Error: %s%n", url, e.getMessage());
            }
        }
    }

    private void discoverUrls(Matcher matcher) {
        while (matcher.find()) {
            String url = getUrlConsideringHref(matcher);
            String finalCompleteUrl = getFinalCompleteUrl(url, protocol);
            if (doesUrlBelongToSameDomain(url) && !discoveredWebsites.contains(url)) {
                discoveredWebsites.add(url);
                queue.add(finalCompleteUrl);
            }
        }
    }

    private String readURL(String stringUrl) throws IOException {
        URL url = new URL(stringUrl);
        StringBuilder rawHtml = new StringBuilder();
        try (InputStreamReader inputStreamReader = new InputStreamReader(url.openConnection().getInputStream());
             BufferedReader reader = new BufferedReader(inputStreamReader)) {
            String line;
            while ((line = reader.readLine()) != null) {
                rawHtml.append(line);
            }
        }
        return rawHtml.toString();
    }

    private String getDomainNameForLinksWithinWebsite(String url) {
        if (!url.startsWith("http") && !url.startsWith("ftp"))
            return this.domain;
        URI uri;
        try {
            uri = new URI(url);
        } catch (URISyntaxException e) {
            e.printStackTrace();
            return "UNRECOGNIZED_DOMAIN";
        }
        String domain = uri.getHost();
        return domain == null ? "" : (domain.startsWith("www.") ? domain.substring(4) : domain);
    }

    private boolean doesUrlBelongToSameDomain(String url) {
        String domain = getDomainNameForLinksWithinWebsite(url);
        return this.domain.contains(domain);
    }

    private String getDomainForApplicationInput(String website) {
        Matcher matcher = HTTP_PATTERN.matcher(website);
        if (matcher.find()) {
            return matcher.group(5);
        } else return website;
    }

    private String getProtocol(String website) {
        Matcher matcher = HTTP_PATTERN.matcher(website);
        if (matcher.find()) {
            String protocol = matcher.group(2);
            return protocol != null && !protocol.isBlank() ? protocol : DEFAULT_PROTOCOL;
        } else return DEFAULT_PROTOCOL;
    }

    private void addRootUrlToQueue() {
        String website = protocol + "://" + domain;
        this.queue.add(website);
        this.discoveredWebsites.add(website);
    }

    private String getUrlConsideringHref(Matcher matcher) {
        String url = matcher.group();
        if (url.startsWith(HREF_ATTRIBUTE)) {
            return url.substring(HREF_ATTRIBUTE_LENGTH);
        } else return url;
    }

    private String getFinalCompleteUrl(String url, String protocol) {
        String completeUrl = getCompleteUrl(url, protocol);
        String removedSharpSign = removeSharpSign(completeUrl);
        String removedWww = removeWww(removedSharpSign);
        String removedAfterQuestionMark = removedQuestionMark(removedWww);
        return removeForwardSlashAtTheEnd(removedAfterQuestionMark);
    }

    private String getCompleteUrl(String url, String protocol) {
        String result;
        if (url.startsWith("http") || url.startsWith("ftp")) {
            result = url;
        } else if (url.startsWith("/")) {
            result = protocol + "://" + domain + url;
        } else result = protocol + "://" + domain + '/' + url;
        return result;
    }

    private String removeSharpSign(String url) {
        int sharpSignIndex = url.indexOf('#');
        return sharpSignIndex == -1 ? url : url.substring(0, sharpSignIndex);
    }

    private String removeWww(String url) {
        url = url.replace("https" + "://www.", "https" + "://");
        return url.replace("http" + "://www.", "http" + "://");
    }

    private String removedQuestionMark(String url) {
        int questionMarkIndex = url.indexOf('?');
        return questionMarkIndex == -1 ? url : url.substring(0, questionMarkIndex);
    }

    private String removeForwardSlashAtTheEnd(String removedWww) {
        if (removedWww.endsWith("/")) {
            return removedWww.substring(0, removedWww.length() - 1);
        } else {
            return removedWww;
        }
    }

}
