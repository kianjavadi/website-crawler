package com.javadi.websitecrawler.crawler;

import java.util.Collection;
import java.util.Collections;
import java.util.NoSuchElementException;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.RecursiveAction;

/**
 * This class uses Breadth First Search in order to crawl a given URL
 * so the urls will be put in a queue and crawler starts processing/parsing of a url from the queue,
 * every url that is processed will be put in a Set so it won't get processed twice
 * the URL will be read and parsed and every identified URL will be added to the queue for further processing
 * at the end of each iteration, a new WebsiteCrawler (task) will be instantiated and forked
 */
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
            try {
                crawl();
                spawnNewCrawler();
            } catch (NoSuchElementException e) {
                System.out.println("There's no element left in the queue at the moment");
                break;
            } catch (Exception e) {
                System.out.println("Exception while processing in a crawler: " + e.getMessage());
                break;
            }
        }
        activeWebsiteCrawlers.remove(this);
    }

    void crawl() {
        String url = queue.remove();
        String rawHtml = contentHandler.handle(url);
        discoverUrlsAndAddToQueue(rawHtml);
    }

    private void discoverUrlsAndAddToQueue(String rawHtml) {
        urlDiscoverer.discover(rawHtml).stream()
                .filter(discoveredWebsites::add)
                .forEach(queue::add);
    }

    void spawnNewCrawler() {
        WebsiteCrawler websiteCrawler = new WebsiteCrawler(queue, discoveredWebsites, activeWebsiteCrawlers, contentHandler, urlDiscoverer);
        activeWebsiteCrawlers.add(websiteCrawler);
        websiteCrawler.fork();
    }

    Collection<String> getQueue() {
        return Collections.unmodifiableCollection(queue);
    }

    Collection<WebsiteCrawler> getActiveWebsiteCrawlers() {
        return Collections.unmodifiableCollection(activeWebsiteCrawlers);
    }

}

