package com.javadi.websitecrawler.discovery;

import com.javadi.websitecrawler.utils.UrlUtils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Set;
import java.util.regex.Matcher;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class RegexMatcherUrlDiscovererTest {

    RegexMatcherUrlDiscoverer regexMatcherUrlDiscoverer;

    @BeforeAll
    void init(@Mock UrlUtils urlUtils) {
        regexMatcherUrlDiscoverer = new RegexMatcherUrlDiscoverer(urlUtils, "https");
        Mockito.lenient().when(urlUtils.getUrlConsideringHref(any(Matcher.class))).thenReturn("https://tretton37.com/meet/agron-kabashi");
        Mockito.lenient().when(urlUtils.getDomainNameForLinksWithinWebsite(any(String.class))).thenReturn("tretton37.com");
        Mockito.lenient().when(urlUtils.getFinalCompleteUrl(any(String.class), any(String.class))).thenReturn("https://tretton37.com/meet/agron-kabashi");
        Mockito.lenient().when(urlUtils.isUrlValid(any(String.class), any(String.class))).thenReturn(true);
        Mockito.lenient().when(urlUtils.encodeUrl(any(String.class))).thenReturn("https://tretton37.com/meet/agron-kabashi");
    }

    @Test
    void shouldBeAbleToDiscoverAbsoluteUrls() {
        String rawHtml = "There's an absolute url here: https://tretton37.com/meet/agron-kabashi method needs to identify and returns.";
        Set<String> discover = regexMatcherUrlDiscoverer.discover(rawHtml);
        assertEquals(1, discover.size());

        rawHtml = "There's a relative url within the href=\"/meet/agron-kabashi\" this method needs to identify it and returns a complete absolute url.";
        discover = regexMatcherUrlDiscoverer.discover(rawHtml);
        assertEquals(1, discover.size());
        System.out.println("shouldBeAbleToDiscoverAbsoluteUrls passed");
    }

    @Test
    void shouldBeAbleToDiscoverRelativeUrlsWithinHref() {
        String rawHtml = "There's a relative url within the href=\"/what-we-do\" this method needs to identify it and returns a complete absolute url.";
        Set<String> discover = regexMatcherUrlDiscoverer.discover(rawHtml);
        assertEquals(1, discover.size());
        System.out.println("shouldBeAbleToDiscoverRelativeUrlsWithinHref passed");
    }

}