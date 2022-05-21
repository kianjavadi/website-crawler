package com.javadi.websitecrawler.crawler;

import java.util.Queue;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.regex.Matcher;

import static com.javadi.websitecrawler.config.ApplicationConstants.HREF_PATTERN;
import static com.javadi.websitecrawler.config.ApplicationConstants.URL_PATTERN;

public class WebsiteCrawler {

    private final Queue<String> queue;
    private final Set<String> discoveredWebsites;

    private final UrlUtils urlUtils;
    private final ContentHandler contentHandler;
    private final String domain;
    private final String protocol;

    public WebsiteCrawler(String domain, UrlUtils urlUtils, ContentHandler contentHandler) {
        this.domain = urlUtils.getDomainForApplicationInput(domain);
        this.protocol = urlUtils.getProtocol(domain);
        this.urlUtils = urlUtils;
        this.contentHandler = contentHandler;
        this.queue = new ConcurrentLinkedQueue<>();
        this.discoveredWebsites = ConcurrentHashMap.newKeySet();
    }

    public void crawl() {
        String website = protocol + "://" + domain;
        this.queue.add(website);
        this.discoveredWebsites.add(website);

        while (!queue.isEmpty()) {
            String url = this.queue.remove();
            System.out.println("Saving: " + url);
            String rawHtml = contentHandler.handle(url);

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
                queue.add(urlUtils.encodeUrl(finalCompleteUrl));
            }
        }
    }

}
