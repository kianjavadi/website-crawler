package com.javadi.websitecrawler;

import com.javadi.websitecrawler.crawler.WebsiteCrawler;
import com.javadi.websitecrawler.io.ContentReader;
import com.javadi.websitecrawler.io.ContentWriter;

public class App {

	public static void main(String[] args) {
		String domain = "https://tretton37.com";
		ContentReader contentReader = new ContentReader();
		ContentWriter contentWriter = new ContentWriter();
		WebsiteCrawler websiteCrawler = new WebsiteCrawler(domain, contentReader, contentWriter);
		websiteCrawler.crawl();
	}

}
