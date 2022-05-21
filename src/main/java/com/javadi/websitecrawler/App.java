package com.javadi.websitecrawler;

import com.javadi.websitecrawler.crawler.WebsiteCrawler;

public class App {

	public static void main(String[] args) {
		String domain = "https://tretton37.com";
		WebsiteCrawler websiteCrawler = new WebsiteCrawler(domain);
		websiteCrawler.crawl();
	}

}
