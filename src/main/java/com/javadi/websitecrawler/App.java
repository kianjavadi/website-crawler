package com.javadi.websitecrawler;

import com.javadi.websitecrawler.crawler.UrlUtils;
import com.javadi.websitecrawler.crawler.WebsiteCrawler;
import com.javadi.websitecrawler.io.ContentReaderImpl;
import com.javadi.websitecrawler.io.ContentWriterImpl;
import com.javadi.websitecrawler.utils.UrlUtilsImpl;

public class App {

	public static void main(String[] args) throws Exception {
		String domain = "https://tretton37.com";
		UrlUtils urlUtils = new UrlUtilsImpl(domain);
		WebsiteCrawler websiteCrawler = new WebsiteCrawler(domain, urlUtils, new ContentReaderImpl(), new ContentWriterImpl());
		websiteCrawler.crawl();
	}

}
