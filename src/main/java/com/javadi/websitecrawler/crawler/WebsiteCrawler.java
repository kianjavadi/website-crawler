package com.javadi.websitecrawler.crawler;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

public class WebsiteCrawler {

    private final String protocol;
    private final String domain;
    private final ContentHandler contentHandler;
    private final UrlDiscoverer urlDiscoverer;
    private final Queue<String> queue;
    private final Set<String> discoveredWebsites;

    public WebsiteCrawler(String protocol, String domain, ContentHandler contentHandler, UrlDiscoverer urlDiscoverer) {
        this.protocol = protocol;
        this.domain = domain;
        this.contentHandler = contentHandler;
        this.urlDiscoverer = urlDiscoverer;
        this.queue = new ConcurrentLinkedQueue<>();
        this.discoveredWebsites = ConcurrentHashMap.newKeySet();
    }

    public void crawl() {
        addRootUrlToQueue();
        while (!queue.isEmpty()) {
            exploreQueueAndDiscover();
        }
    }

    private void addRootUrlToQueue() {
        String website = protocol + "://" + domain;
        this.queue.add(website);
        this.discoveredWebsites.add(website);
    }

    private void exploreQueueAndDiscover() {
        String url = this.queue.remove();
        System.out.println("Saving: " + URLDecoder.decode(url, StandardCharsets.UTF_8));
        String rawHtml = contentHandler.handle(url);
        urlDiscoverer.discover(rawHtml).stream()
                .filter(discoveredWebsites::add)
                .forEach(queue::add);
    }

}
