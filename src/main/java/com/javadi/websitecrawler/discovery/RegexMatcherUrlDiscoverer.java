package com.javadi.websitecrawler.discovery;

import com.javadi.websitecrawler.crawler.UrlDiscoverer;
import com.javadi.websitecrawler.crawler.UrlUtils;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;

import static com.javadi.websitecrawler.config.ApplicationConstants.HREF_PATTERN;
import static com.javadi.websitecrawler.config.ApplicationConstants.URL_PATTERN;

public class RegexMatcherUrlDiscoverer implements UrlDiscoverer {

    private final UrlUtils urlUtils;
    private final String protocol;

    public RegexMatcherUrlDiscoverer(UrlUtils urlUtils, String protocol) {
        this.urlUtils = urlUtils;
        this.protocol = protocol;
    }

    @Override
    public Set<String> discover(String rawHtml) {
        Set<String> result = new HashSet<>();
        Matcher hrefMatcher = HREF_PATTERN.matcher(rawHtml);
        discoverUrls(hrefMatcher, result);

        Matcher urlMatcher = URL_PATTERN.matcher(rawHtml);
        discoverUrls(urlMatcher, result);
        return result;
    }

    private void discoverUrls(Matcher hrefMatcher, Set<String> result) {
        while (hrefMatcher.find()) {
            String url = urlUtils.getUrlConsideringHref(hrefMatcher);
            String domain = urlUtils.getDomainNameForLinksWithinWebsite(url);
            String finalCompleteUrl = urlUtils.getFinalCompleteUrl(url, protocol);
            if (urlUtils.isUrlValid(domain, finalCompleteUrl)) {
                result.add(urlUtils.encodeUrl(finalCompleteUrl));
            }
        }
    }

}
