package com.javadi.websitecrawler.crawler;

import java.util.Queue;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ForkJoinPool;

public class CrawlerInitializer {

    private final String protocol;
    private final String domain;
    private final ContentHandler contentHandler;
    private final UrlDiscoverer urlDiscoverer;
    private final Queue<String> queue;
    private final Set<String> discoveredWebsites;
    private final Set<WebsiteCrawler> activeWebsiteCrawlers;

    public CrawlerInitializer(String protocol, String domain, ContentHandler contentHandler, UrlDiscoverer urlDiscoverer) {
        this.protocol = protocol;
        this.domain = domain;
        this.contentHandler = contentHandler;
        this.urlDiscoverer = urlDiscoverer;
        this.activeWebsiteCrawlers = ConcurrentHashMap.newKeySet();
        this.queue = new ConcurrentLinkedQueue<>();
        this.discoveredWebsites = ConcurrentHashMap.newKeySet();
    }

    public void crawl() {
        addRootUrlToQueue();
        WebsiteCrawler websiteCrawler = new WebsiteCrawler(queue, discoveredWebsites, activeWebsiteCrawlers, contentHandler, urlDiscoverer);
        activeWebsiteCrawlers.add(websiteCrawler);
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        forkJoinPool.invoke(websiteCrawler);
        waitForCompletion();
    }

    private void addRootUrlToQueue() {
        String website = protocol + "://" + domain;
        this.queue.add(website);
        this.discoveredWebsites.add(website);
    }

    private void waitForCompletion() {
        while (true) {
            try {
                if (activeWebsiteCrawlers.isEmpty()) {
                    break;
                }
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
