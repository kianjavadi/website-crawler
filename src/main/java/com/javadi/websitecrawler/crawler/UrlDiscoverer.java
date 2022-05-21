package com.javadi.websitecrawler.crawler;

import java.util.Set;

public interface UrlDiscoverer {

    Set<String> discover(String rawHtml);

}
