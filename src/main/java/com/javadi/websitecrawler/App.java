package com.javadi.websitecrawler;

import com.javadi.websitecrawler.crawler.WebsiteCrawler;

public class App {

	public static void main(String[] args) {
		WebsiteCrawler websiteCrawler = new WebsiteCrawler();
		websiteCrawler.crawl("https://tretton37.com/");
	}

}
