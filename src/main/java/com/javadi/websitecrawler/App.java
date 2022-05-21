package com.javadi.websitecrawler;

import com.javadi.websitecrawler.content.ContentHandlerImpl;
import com.javadi.websitecrawler.crawler.ContentHandler;
import com.javadi.websitecrawler.crawler.UrlUtils;
import com.javadi.websitecrawler.crawler.WebsiteCrawler;
import com.javadi.websitecrawler.io.ContentReaderImpl;
import com.javadi.websitecrawler.io.FileSystemContentWriter;
import com.javadi.websitecrawler.utils.UrlUtilsImpl;

public class App {

	public static void main(String[] args) throws Exception {
		String domain= "tretton37.com";
		long start = System.currentTimeMillis();
		UrlUtils urlUtils = new UrlUtilsImpl(domain);
		ContentHandler contentHandler = new ContentHandlerImpl(urlUtils, new ContentReaderImpl(), new FileSystemContentWriter(domain));
		WebsiteCrawler websiteCrawler = new WebsiteCrawler(domain, urlUtils, contentHandler);
		websiteCrawler.crawl();
		System.out.println("TOTAL TIME TAKEN: " + (System.currentTimeMillis() - start));
	}

}
