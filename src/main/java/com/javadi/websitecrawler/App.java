package com.javadi.websitecrawler;

import com.javadi.websitecrawler.content.ContentHandlerImpl;
import com.javadi.websitecrawler.crawler.ContentHandler;
import com.javadi.websitecrawler.crawler.UrlDiscoverer;
import com.javadi.websitecrawler.crawler.UrlUtils;
import com.javadi.websitecrawler.crawler.WebsiteCrawler;
import com.javadi.websitecrawler.discovery.RegexMatcherUrlDiscoverer;
import com.javadi.websitecrawler.io.ContentReaderImpl;
import com.javadi.websitecrawler.io.FileSystemContentWriter;
import com.javadi.websitecrawler.utils.UrlUtilsImpl;

public class App {

	public static void main(String[] args) {
		String domain;
		if (args == null || args.length == 0) {
			domain = "tretton37.com";
		} else {
			domain = args[0];
		}
		long start = System.currentTimeMillis();
		UrlUtils urlUtils = new UrlUtilsImpl(domain);
		urlUtils.validateWebUrl(domain);

		String website = urlUtils.getDomainForApplicationInput(domain);
		String protocol = urlUtils.getProtocol(domain);
		ContentHandler contentHandler = new ContentHandlerImpl(urlUtils, new ContentReaderImpl(), new FileSystemContentWriter(domain));
		UrlDiscoverer urlDiscoverer = new RegexMatcherUrlDiscoverer(urlUtils, protocol);
		WebsiteCrawler websiteCrawler = new WebsiteCrawler(protocol, website, contentHandler, urlDiscoverer);
		websiteCrawler.crawl();
		System.out.println("TOTAL TIME TAKEN: " + (System.currentTimeMillis() - start) + " (ms)");
	}

}
