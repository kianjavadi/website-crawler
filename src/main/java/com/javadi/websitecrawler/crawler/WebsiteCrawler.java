package com.javadi.websitecrawler.crawler;

import java.util.NoSuchElementException;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.RecursiveAction;

public class WebsiteCrawler extends RecursiveAction {

    private final Queue<String> queue;
    private final Set<String> discoveredWebsites;
    private final Set<WebsiteCrawler> activeWebsiteCrawlers;
    private final ContentHandler contentHandler;
    private final UrlDiscoverer urlDiscoverer;

    public WebsiteCrawler(Queue<String> queue, Set<String> discoveredWebsites, Set<WebsiteCrawler> activeWebsiteCrawlers, ContentHandler contentHandler, UrlDiscoverer urlDiscoverer) {
        this.queue = queue;
        this.discoveredWebsites = discoveredWebsites;
        this.activeWebsiteCrawlers = activeWebsiteCrawlers;
        this.contentHandler = contentHandler;
        this.urlDiscoverer = urlDiscoverer;
    }

    @Override
    protected void compute() {
        while (!queue.isEmpty()) {
            String url;
            try {
                url = queue.remove();
            } catch (NoSuchElementException e) {
                System.out.println("There's no element left in the queue at the moment");
                return;
            }
            String rawHtml = contentHandler.handle(url);
            urlDiscoverer.discover(rawHtml).stream()
                    .filter(discoveredWebsites::add)
                    .forEach(queue::add);
            WebsiteCrawler websiteCrawler = new WebsiteCrawler(queue, discoveredWebsites, activeWebsiteCrawlers, contentHandler, urlDiscoverer);
            activeWebsiteCrawlers.add(websiteCrawler);
            websiteCrawler.fork();
        }
        activeWebsiteCrawlers.remove(this);
    }

}

