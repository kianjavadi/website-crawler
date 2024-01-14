package com.javadi.websitecrawler;

import com.javadi.websitecrawler.crawler.UrlDiscoverer;
import com.javadi.websitecrawler.discovery.RegexMatcherUrlDiscoverer;
import com.javadi.websitecrawler.utils.UrlUtils;
import com.javadi.websitecrawler.utils.UrlUtilsImpl;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 * This class finds all the links inside a local stored HTML file
 */
public class HtmlFileLinkFinder {

    // first argument: domain
    // second argument: filePath
    // third argument: mustContains (comma-separated)
    public static void main(String[] args) throws Exception {
        String domain;
        String rawHtml;
        List<String> mustContains;
        switch (args.length) {
            case 3:
                mustContains = Arrays.asList(args[2].split(","));
                rawHtml = Files.readString(Path.of(args[1]));
                domain = args[0];
                break;
            case 2:
                mustContains = Collections.emptyList();
                rawHtml = Files.readString(Path.of(args[1]));
                domain = args[0];
                break;
            case 1:
                mustContains = Collections.emptyList();
                rawHtml = "";
                domain = args[0];
                break;
            default:
                mustContains = Collections.emptyList();
                domain = "google.com";
                rawHtml = "";
        }

        UrlUtils urlUtils = new UrlUtilsImpl(domain);
        String protocol = urlUtils.getProtocol(domain);
        UrlDiscoverer urlDiscoverer = new RegexMatcherUrlDiscoverer(urlUtils, protocol);
        Set<String> discoveredLinks = urlDiscoverer.discover(rawHtml);
        discoveredLinks.stream()
                .filter(link -> mustContains.isEmpty() || mustContains.stream().allMatch(link::contains))
                .forEach(System.out::println);
    }

}

