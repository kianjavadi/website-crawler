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

    private static final Pattern URL_PATTERN = Pattern.compile("(http|ftp|https)://([\\w_-]+(?:(?:\\.[\\w_-]+)+))([\\w.,@?^=%&:/~+#-]*[\\w@?^=%&/~+#-])");
    private static final Pattern HREF_PATTERN = Pattern.compile("(href=\")([^\"]+)");

    private final String domain;
    private final Queue<String> queue;
    private final Set<String> discoveredWebsites;

    public WebsiteCrawler(String domain) {
        this.queue = new LinkedList<>();
        this.discoveredWebsites = new HashSet<>();
        this.domain = domain;
    }

    public void crawl() {
        this.queue.add(domain);
        this.discoveredWebsites.add(domain);

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
            String url = matcher.group();
            if (doesUrlBelongToSameDomain(url) && !discoveredWebsites.contains(url)) {
                discoveredWebsites.add(url);
                queue.add(url);
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

    private String getDomainNameForLinks(String url) {
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
        String domain = getDomainNameForLinks(url);
        return this.domain.contains(domain);
    }

}
