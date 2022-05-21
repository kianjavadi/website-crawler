package com.javadi.websitecrawler.utils;

import com.javadi.websitecrawler.config.ApplicationConstants;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.Set;
import java.util.regex.Matcher;

import static com.javadi.websitecrawler.config.ApplicationConstants.HREF_PATTERN;
import static com.javadi.websitecrawler.config.ApplicationConstants.URL_PATTERN;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class UrlUtilsImplTest {

    UrlUtilsImpl urlUtils;

    @BeforeAll
    void initialize() {
        urlUtils = new UrlUtilsImpl("https://tretton37.com");
    }

    @Test
    void shouldNotContainAnyDisallowedCharsAfterEncoding() {
        String url = "https://tretton37.com/invalid/ / <> ";
        String encodeUrl = urlUtils.encodeUrl(url);
        for (String needEncoding : ApplicationConstants.NEED_ENCODING) {
            assertEquals(-1, encodeUrl.indexOf(needEncoding));
        }
        System.out.println("shouldNotContainAnyDisallowedCharsAfterEncoding passed");
    }

    @Test
    void tretton37ShouldBeTakenAsDomain_validForAllInternalLinksInTheWebsite() {
        Set<String> urlsInsideDomain = Set.of("https://tretton37.com/meet", "http://tretton37.com/meet", "ftp://tretton37.com/meet", "https://www.tretton37.com/meet", "tretton37.com/meet", "assets/css/main.css", "/assets/i/who-we-are.jpg");
        urlsInsideDomain.forEach(url ->
                assertEquals("tretton37.com", urlUtils.getDomainNameForLinksWithinWebsite(url)));
        System.out.println("tretton37ShouldBeTakenAsDomain_validForAllInternalLinksInTheWebsite passed");
    }

    @Test
    void tretton37ShouldNotBeTakenAsDomain_validForAllExternalLinksOfTheWebsite() {
        Set<String> urlsOutsideDomain = Set.of("https://google.com");
        urlsOutsideDomain.forEach(url ->
                assertNotEquals("tretton37.com", urlUtils.getDomainNameForLinksWithinWebsite(url)));
        System.out.println("tretton37ShouldNotBeTakenAsDomain_validForAllExternalLinksOfTheWebsite passed");
    }

    @Test
    void fileNameShouldBeInferredCorrectlyFromUrl() {
        String url = "https://tretton37.com/assets/i/join.jpg";
        String extension = ".jpg";
        assertEquals("join.jpg", urlUtils.getFileNameWithExtension(url, extension));

        url = "https://tretton37.com/who-we-are";
        extension = ".html";
        assertEquals("who-we-are.html", urlUtils.getFileNameWithExtension(url, extension));
        System.out.println("fileNameShouldBeInferredCorrectlyFromUrl passed");
    }

    @Test
    void parentPathShouldBeInferredCorrectlyFromUrl() {
        String url = "https://tretton37.com/assets/i/join.jpg";
        assertEquals("assets/i", urlUtils.getParentPath(url));

        url = "https://tretton37.com/who-we-are";
        assertEquals("", urlUtils.getParentPath(url));
        System.out.println("parentPathShouldBeInferredCorrectlyFromUrl passed");
    }

    @Test
    void urlShouldBeConsideredValid() {
        Set<String> validUrls = Set.of("https://tretton37.com/meet", "http://tretton37.com/meet", "ftp://tretton37.com/meet", "https://www.tretton37.com/meet", "tretton37.com/meet");
        validUrls.forEach(url ->
                assertTrue(urlUtils.isUrlValid(urlUtils.getDomainNameForLinksWithinWebsite(url), url)));
        System.out.println("urlShouldBeConsideredValid passed");
    }

    @Test
    void urlShouldNotBeConsideredValid() {
        Set<String> invalidUrls = Set.of("https://tretton37.com/tel:123", "http://tretton37.com/mailto:54545", "https://google.com");
        invalidUrls.forEach(url ->
                assertFalse(urlUtils.isUrlValid(urlUtils.getDomainNameForLinksWithinWebsite(url), url)));
        System.out.println("urlShouldNotBeConsideredValid passed");
    }

    @Test
    void allUrlsShouldBeConsideredAsSameDomain() {
        Set<String> sameDomainUrls = Set.of("https://tretton37.com", "tretton37.com", "https://www.tretton37.com");
        sameDomainUrls.forEach(url ->
                assertEquals("tretton37.com", urlUtils.getDomainForApplicationInput(url)));
        System.out.println("allUrlsShouldBeConsideredAsSameDomain passed");
    }

    @Test
    void protocolShouldBeInferredCorrectly() {
        String url = "https://tretton37.com";
        assertEquals("https", urlUtils.getProtocol(url));

        url = "http://tretton37.com";
        assertEquals("http", urlUtils.getProtocol(url));

        url = "ftp://tretton37.com";
        assertEquals("ftp", urlUtils.getProtocol(url));
        System.out.println("protocolShouldBeInferredCorrectly passed");
    }

    @Test
    void shouldFineUrlsConsideringRelativeHrefs() {
        String rawHtml = "<!DOCTYPE html> <html lang=\"en\" xmlns:fb=\"http://ogp.me/ns/fb#\"> <head> <title>Your trusted technology partner | tretton37</title> <meta charset=\"UTF-8\"> <meta name=\"viewport\" content=\"width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no\"> <link rel=\"prefetch\" href=\"/assets/i/join.jpg\"> <link rel=\"prefetch\" href=\"/assets/i/contact.jpg\"> <link rel=\"prefetch\" href=\"/assets/i/covid-19.jpg\"> <link rel=\"prefetch\" href=\"/assets/i/events.jpg\"> <link rel=\"prefetch\" href=\"/assets/i/blog.jpg\"> <link rel=\"prefetch\" href=\"/assets/i/knowledge-sharing.jpg\"> <link rel=\"prefetch\" href=\"/assets/i/meet.jpg\"> <link rel=\"prefetch\" href=\"/assets/i/privacy-policy.jpg\"> <link rel=\"prefetch\" href=\"/assets/i/what-we-do.jpg\"> <link rel=\"prefetch\" href=\"/assets/i/who-we-are.jpg\"> <meta property=\"og:title\" content=\"Your trusted technology partner\"> <meta property=\"og:site_name\" content=\"tretton37\"> <meta property=\"twitter:title\" content=\"Your trusted technology partner\"> <meta itemprop=\"name\" content=\"Your trusted technology partner\"> <meta property=\"og:type\" content=\"article\"> <meta property=\"twitter:card\" content=\"summary_large_image\"> <meta property=\"og:url\" content=\"https://www.tretton37.com/index.html\"> <meta property=\"twitter:url\" content=\"https://www.tretton37.com/index.html\"> <meta name=\"description\" content=\"We are an international knowledge-driven consultancy. We aid forward thinking organizations in pioneering digital transformation with well-crafted, innovative &amp; high quality software. We are tretton37, we&#x27;ve got this.\"> <meta property=\"og:description\" content=\"We are an international knowledge-driven consultancy. We aid forward thinking organizations in pioneering digital transformation with well-crafted, innovative &amp; high quality software. We are tretton37, we&#x27;ve got this.\"> <meta property=\"twitter:description\" content=\"We are an international knowledge-driven consultancy. We aid forward thinking organizations in pioneering digital transformation with well-crafted, innovative &amp; high quality software. We are tretton37, we&#x27;ve got this.\"> <meta itemprop=\"description\" content=\"We are an international knowledge-driven consultancy. We aid forward thinking organizations in pioneering digital transformation with well-crafted, innovative &amp; high quality software. We are tretton37, we&#x27;ve got this.\"> <meta property=\"og:image\" content=\"https://www.tretton37.com/files/images/tretton37square.png\"> <meta property=\"twitter:image\" content=\"https://www.tretton37.com/files/images/tretton37square.png\"> <meta itemprop=\"image\" content=\"https://www.tretton37.com/files/images/tretton37square.png\"> <meta name=\"thumbnail\" content=\"https://www.tretton37.com/files/images/tretton37square.png\"> <link rel=\"image_src\" content=\"https://www.tretton37.com/files/images/tretton37square.png\"> <meta name=\"parsely-image-url\" content=\"https://www.tretton37.com/files/images/tretton37square.png\"> <link rel=\"author\" type=\"text/x-markdown\" href=\"/humans.txt\"> <link href=\"assets/css/main.css?bac32a47-2d4a-4bfe-bf41-ab0c87093d14\" rel=\"stylesheet\"> <link rel=\"icon\" type=\"image/png\" href=\"assets/i/favicon.png\"> <link href=\"https://fonts.googleapis.com/css?family=Montserrat:300,400,500,700\" rel=\"stylesheet\"> <link href=\"https://unpkg.com/aos@2.3.1/dist/aos.css\" rel=\"stylesheet\"> <style> @import url(\"https://fonts.googleapis.com/css2?family=Sora:wght@400;500;700&display=swap\");* {  margin: 0;  padding: 0;  box-sizing: border-box;}#subs-cont {  font-size: 12.5px;  font-family: \"Sora\", sans-serif;}.subscribe-container {  width: 100vw;  min-height: 100vh;  background: #0c0c91ed;  width: 100%;    height: 100%;    z-index: 999999;    position: fixed ;  display: none;  align-items: center;  justify-content: center;  padding: 30px 0;}.subscribe-container.show {  display: flex;}.subscribe {  display: flex;  background-color: #fff;  width: 90%;  max-width: 800px;  border-radius: 10px;  position: relative;}.subscribe .close {  cursor: pointer;  position: absolute;  font-size: 3rem;  top: -20px;  right: -20px;  background: #fff;  height: 40px;  width: 40px;  text-align: center;  line-height: 30px;padding-top:4px;  border-radius: 10px;  box-shadow: rgba(17, 17, 26, 0.1) 0px 4px 16px,    rgba(17, 17, 26, 0.05) 0px 8px 32px;}.subscribe .close:hover {  color: #0C0C91;}.subscribe .img {  min-width: 260px;  background: #2193b0;  background: -webkit-linear-gradient(to right bottom, #6dd5ed, #2193b0);  background: linear-gradient(to right bottom, #6dd5ed, #2193b0);  border-radius: 10px 0 0 10px;  display: flex;  align-items: center;  justify-content: center;  overflow: hidden;}.subscribe .img img {  width: 90%;  filter: drop-shadow(0 0 15px rgba(0, 0, 0, 0.5));  animation: animate_subs_img 1s alternate infinite linear;}.subscribe .content {    overflow: auto;  overflow-x: hidden;  max-height: 90vh;  padding: 2.5rem 1.5rem;}.subscribe .content h2 {  font-size: 2rem;  margin-bottom: 3rem;}.subscribe .content p {  font-size: 0.9rem; margin-top: 2rem;  margin-bottom: 2.5rem;  line-height: 1.35;  color: #555;}.mainbtn {  font-size: 200%;  background: #05E273;  padding: 15px;  color: #0C0C91;  text-decoration: none;  margin: auto;  margin-top: 40px;  font-weight: bold;  left: 30%;  position: relative;  transform: translateX(-100%);}.mainbtn:hover {  opacity: 0.9;}.subscribe-container h2 {  color: #0C0C91;display: block; margin-bottom: 30px; font-weight: bold;}.iframe-container {  position: relative;  padding-top: 56.25%;}.iframe-container iframe {  position: absolute;  top: 0;  left: 0;  width: 100%;  height: 100%;}@media screen and (max-width: 650px) {  .subscribe {    display: block;    max-width: 80%;  max-height: 80vh;  margin-top: -10vh;  }  .subscribe .content h2 {  font-size: 1rem;  margin-bottom: 2rem;}  .mainbtn {  font-size: 100%;  position: absolute;  left: 50%;  margin-top: 0px;  text-align: center;  transform: translateX(-50%);}} </style> <!--[if lte IE 8]>    <script src=\"/assets/js/lib/rem.min.js\" type=\"text/javascript\"></script>    <script src=\"//cdn.jsdelivr.net/g/html5shiv@3.7.0,selectivizr@1.0.3b,respond@1.4.2\"></script>    <link href=\"/assets/css/main.ie.css?484a51da-d4de-4554-88b0-2d510eec863e\" rel=\"stylesheet\" />  <![endif]--> </head> <body class=\"spotlight-inactive\"> <div id=\"subs-cont\" class=\"subscribe-container show\" data-nosnippet> <div class=\"subscribe\"> <span id=\"subs-close\" class=\"close\">&times;</span> <div class=\"content\"> <h2>We've gone from tretton37 to 13<span style=\"color:#05E273\">|</span>37.</h2> <div class=\"iframe-container\"> <iframe src=\"https://www.youtube.com/embed/PwVexSg4S9k\" title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture\" allowfullscreen></iframe> </div> <p> We’re still the same company with the same heart and focus on empowering clients and colleagues – and that will never change – but now we’re shorthand! If you want to get in touch to talk to us about empowering your organisation, uncovering your digital capabilities or find out more about life at 13|37 then head on over to <a href=\"https://1337.tech\">1337.tech</a>. <br><br> tretton37.com will be available for a limited transition period only. Have a wonderful day. </p> <a href=\"https://1337.tech\" class=\"mainbtn\">Visit us at 1337.tech</a> <span></span> </div> </div> </div> <header class=\"navbar\" role=\"navigation\"> <div class=\"inner-scroll\"> <ul class=\"navbar-menu\" role=\"navigation\"> <li><a href=\"/who-we-are\">Who we are</a></li> <li><a href=\"/what-we-do\">What we do</a></li> <li><a href=\"/knowledge-sharing\">Knowledge sharing</a></li> <li><a href=\"/join\" data-switch-to=\"#hiring!\">Join</a></li> <li><a class=\"typeform-share button\" href=\"https://form.typeform.com/to/wmJczaqk\" data-mode=\"popup\" data-submit-close-delay=\"2\" target=\"_blank\">Inquire</a></li> <li><a href=\"/contact\">Contact</a></li> <!--<li><a href=\"/covid-19\" data-special=\"covid-19\" >COVID-19</a></li>--> </ul> <ul class=\"navbar-menu navbar-menu-sub\"> <li><a href=\"/events\">Events</a></li> <li><a href=\"/blog\">Blog</a></li> <li><a href=\"https://leetspeak.se\" target=\"_blank\">Leetspeak 2020 <i class=\"icon-external-link icon-right\"></i></a></li> </ul> </div> <div class=\"branding\"> <a href=\"/\"> <svg xmlns=\"http://www.w3.org/2000/svg\" viewBox=\"0 0 456.82 89.62\"><defs></defs><title>_tretton37 logo green</title><g id=\"Layer_2\" data-name=\"Layer 2\"><g id=\"Layer_1-2\" data-name=\"Layer 1\"><path class=\"logoFill\" d=\"M16.19,74.86a27.32,27.32,0,0,1-6.86-.8,11.4,11.4,0,0,1-4.89-2.64,12.94,12.94,0,0,1-3.23-5A24,24,0,0,1,0,58.1V0H14V14.76H28.47V27H14V55.33c0,5,2.61,7.72,7.35,7.72a21,21,0,0,0,6.89-1V72.28A33.94,33.94,0,0,1,23.39,74,26,26,0,0,1,16.19,74.86Z\"/><path class=\"logoFill\" d=\"M240.23,74.87c-12.88,0-25-7.41-29.46-19.72a30.1,30.1,0,0,1,9.83-33.82A33.44,33.44,0,0,1,256.84,19,30,30,0,0,1,271,51c-2.61,12.48-13.44,21.82-25.92,23.53A36.25,36.25,0,0,1,240.23,74.87Zm0-49.06a17.38,17.38,0,0,0-16.64,12.45c-2.25,7.29-.4,15.74,5.47,20.79a18,18,0,0,0,20.72,1.7c6.55-4,9.18-12.18,7.94-19.53C256.27,32.68,249,25.81,240.23,25.81Z\"/><polygon class=\"logoFill\" points=\"402.4 14.76 402.4 27.02 441.25 27.02 406.7 89.62 422.68 89.62 456.82 25.82 456.82 14.76 402.4 14.76\"/><path class=\"logoFill\" d=\"M305,14.76H278.67v60.1h14V27h12.77a11,11,0,0,1,8.07,3.1c3.89,4.11,3.89,13.42,3.89,20.69V74.86h14V41.12C331.4,18.18,314.88,14.76,305,14.76Z\"/><polygon class=\"logoFill\" points=\"54.14 14.76 38.47 14.76 38.47 74.86 52.47 74.86 52.47 27.01 67.05 27.01 54.14 14.76\"/><path class=\"logoFill\" d=\"M119.4,68.2a32.45,32.45,0,0,1-7.92,6.23,23.94,23.94,0,0,1-11.57,2.68,14.17,14.17,0,0,1-9.32-3,10.43,10.43,0,0,1-3.78-8.41v-.23a10.09,10.09,0,0,1,4.94-9c3-1.92,7.24-2.9,12.53-2.9h2.38l2.19-8.92L91.33,27h33.72V14.76h-51v11L93.22,44l-.15,0a39.83,39.83,0,0,0-7.66,2.09A21.81,21.81,0,0,0,79,49.93a17.82,17.82,0,0,0-4.48,6,21,21,0,0,0-1.68,8.86v.23a25,25,0,0,0,1.88,9.83,23,23,0,0,0,5.38,7.75,24.5,24.5,0,0,0,8.57,5.14,34.1,34.1,0,0,0,11.49,1.85c7,0,12.94-1.24,17.76-3.69a38.59,38.59,0,0,0,11.21-8.47Z\"/><path class=\"logoFill\" d=\"M347.05,68.2A32.68,32.68,0,0,0,355,74.43a23.87,23.87,0,0,0,11.56,2.68,14.15,14.15,0,0,0,9.32-3,10.43,10.43,0,0,0,3.78-8.41v-.23a10.1,10.1,0,0,0-4.93-9c-3-1.92-7.25-2.9-12.53-2.9h-2.39l-2.19-8.92L375.13,27H341.4V14.76h51v11L373.23,44l.15,0a40.13,40.13,0,0,1,7.67,2.09,22.07,22.07,0,0,1,6.43,3.83A17.8,17.8,0,0,1,392,56a21,21,0,0,1,1.69,8.86v.23a25,25,0,0,1-1.89,9.83,23,23,0,0,1-5.38,7.75,24.5,24.5,0,0,1-8.57,5.14,34.06,34.06,0,0,1-11.49,1.85c-7,0-12.94-1.24-17.76-3.69a38.42,38.42,0,0,1-11.2-8.47Z\"/><path class=\"logoFill\" d=\"M151.28,74.88a26.84,26.84,0,0,1-6.86-.81,11.28,11.28,0,0,1-4.89-2.63,13.09,13.09,0,0,1-3.23-5,24,24,0,0,1-1.21-8.29V0h14V14.77h14.47V27H149.09V55.34c0,5,2.61,7.72,7.35,7.72a21,21,0,0,0,6.89-1V72.29A31.64,31.64,0,0,1,158.48,74,25.56,25.56,0,0,1,151.28,74.88Z\"/><path class=\"logoFill\" d=\"M189.74,74.88a26.76,26.76,0,0,1-6.85-.81A11.22,11.22,0,0,1,178,71.44a13,13,0,0,1-3.23-5,23.81,23.81,0,0,1-1.21-8.29V0h14V14.77H202V27H187.55V55.34c0,5,2.62,7.72,7.36,7.72a20.88,20.88,0,0,0,6.88-1V72.29A31.52,31.52,0,0,1,197,74,25.63,25.63,0,0,1,189.74,74.88Z\"/></g></g></svg> </a> </div> <div class=\"menu-link menu-link-top\"><a data-toggle-menu-top><span></span></a></div> <div class=\"menu-link menu-link-sub\"><a data-toggle-menu-sub><span></span></a></div> </header> <section class=\"hero hero-index\"> <video autoplay loop muted playsinline preload=\"metadata\" data-videosrc=\"mainintro\" src=\"/assets/v/mainintro_small.mp4\"></video> <div class=\"title type-js\" style=\"\"> <p></p><h1><span data-aos=\"fade-right\" data-aos-anchor-placement=\"top-left\" data-aos-once=\"true\" data-aos-duration=\"350\" data-aos-delay=\"100\" data-aos-easing=\"ease-in-out\">your trusted</span> <span data-aos=\"fade-right\" data-aos-anchor-placement=\"top-left\" data-aos-once=\"true\" data-aos-duration=\"350\" data-aos-delay=\"300\" data-aos-easing=\"ease-in-out\">technology partner</span></h1> <h3 data-aos=\"fade-right\" data-aos-anchor-placement=\"top-left\" data-aos-once=\"true\" data-aos-duration=\"350\" data-aos-delay=\"500\" data-aos-easing=\"ease-in-out\">We are an international knowledge-driven consultancy. We aid forward thinking organizations in <strong>pioneering digital transformation</strong> with <strong>well-crafted</strong>, <strong>innovative</strong> & <strong>high quality software</strong>. <span data-aos=\"fade-right\" data-aos-anchor-placement=\"top-left\" data-aos-once=\"true\" data-aos-duration=\"350\" data-aos-delay=\"700\" data-aos-easing=\"ease-in-out\">We are tretton37, <strong>we've got this.</strong></span></h3><p></p> </div> <a href=\"#intro\" class=\"hint-arrow animate-hint-arrow hide-legacy\" data-scroll-to-section data-aos=\"fade-up\" data-aos-anchor-placement=\"top-bottom\" data-aos-once=\"true\" data-aos-duration=\"350\" data-aos-delay=\"2000\" data-aos-offset=\"-20\" data-aos-easing=\"ease-in-out\"><svg version=\"1.1\" xmlns=\"http://www.w3.org/2000/svg\" xmlns:xlink=\"http://www.w3.org/1999/xlink\" x=\"0px\" y=\"0px\" viewBox=\"0 0 154 89.3\" style=\"enable-background:new 0 0 154 89.3;\" xml:space=\"preserve\"> <g> <g> <polygon class=\"shorthand_thirteen\" points=\"29,24.4 34.8,24.4 34.8,52.2 29.6,52.2 29.6,28.9 24.2,28.9 \t\t\"/> <path class=\"shorthand_thirteen\" d=\"M63.8,57.1c-0.9,0.9-1.9,1.7-3,2.3c-1.3,0.7-2.8,1-4.3,1c-1.3,0.1-2.5-0.4-3.5-1.1c-0.9-0.8-1.5-1.9-1.4-3.1    \t\t\tv-0.1c0-1.4,0.7-2.6,1.8-3.3c1.4-0.8,3-1.2,4.7-1.1h0.9l0.8-3.3l-6.5-6.5h12.6v-4.6H47v4.1l7.1,6.8l0,0c-1,0.2-1.9,0.4-2.8,0.8    \t\t\tc-0.9,0.3-1.7,0.8-2.4,1.4c-0.7,0.6-1.3,1.4-1.7,2.2c-0.4,1-0.7,2.2-0.6,3.3v0.1c0,1.3,0.2,2.5,0.7,3.7c0.5,1.1,1.1,2.1,2,2.9    \t\t\tc0.9,0.8,2,1.5,3.2,1.9c1.4,0.5,2.8,0.7,4.3,0.7c2.3,0.1,4.5-0.4,6.6-1.4c1.6-0.8,3-1.8,4.2-3.1L63.8,57.1z\"/> <polygon class=\"shorthand_thirtyseven\" points=\"109.6,37.3 109.6,41.8 124,41.8 111.2,65.1 117.1,65.1 129.8,41.4 129.8,37.3 \t\t\"/> <path class=\"shorthand_thirtyseven\" d=\"M80.3,44.2c0.9,0.9,1.8,1.7,2.9,2.3c1.3,0.7,2.8,1,4.3,1c1.3,0.1,2.5-0.3,3.5-1.1c0.9-0.8,1.4-1.9,1.4-3.1    \t\t\tv-0.1c0-1.4-0.7-2.7-1.8-3.3c-1.4-0.8-3-1.2-4.7-1.1H85l-0.8-3.3l6.5-6.5H78.2v-4.5h18.9v4.1L90,35.2h0.1c1,0.2,1.9,0.4,2.8,0.8    \t\t\tc0.9,0.3,1.7,0.8,2.4,1.4c0.7,0.6,1.3,1.4,1.7,2.2c0.5,1.1,0.7,2.2,0.6,3.3l0,0c0,1.3-0.2,2.5-0.7,3.7c-0.5,1.1-1.1,2.1-2,2.9    \t\t\tc-0.9,0.9-2,1.5-3.2,1.9c-1.4,0.5-2.8,0.7-4.3,0.7c-2.3,0.1-4.5-0.4-6.6-1.4c-1.6-0.8-3-1.9-4.2-3.2L80.3,44.2z\"/> </g> </g> </svg><span></span></a> </section> <section class=\"site-section light-gray software-professionals\"> <article class=\"mast-head\" data-aos=\"fade-up\" data-aos-anchor-placement=\"top-center\" data-aos-once=\"true\" data-aos-duration=\"350\" data-aos-offset=\"-100\" data-aos-delay=\"0\" data-aos-easing=\"ease-in-out\"> <p>_the backbone of our collective DNA:</p> <h1>well-crafted deliveries</h1> </article> </section> <section class=\"site-section light-gray home software-professionals\" id=\"intro\"> <div class=\"three-column-container left-aligned-paragraphs\"> <div data-aos=\"fade-up\" data-aos-anchor-placement=\"top-center\" data-aos-once=\"true\" data-aos-duration=\"350\" data-aos-offset=\"-100\" data-aos-delay=\"200\" data-aos-easing=\"ease-in-out\"> <h1>Software <span>professionals</span></h1> <img src=\"assets/i/delivery_teams.svg\"> <div> <p>We help businesses grow, develop and mature by strengthening existing teams & development organizations with <strong>exceptional talent</strong>.</p> <span>Our highly experienced professionals can help your business needs either with on-site or remote deliveries within various stages of software delivery or maturity.</span> <a class=\"encrypted-email btn-primary btn-small typeform-share button\" href=\"https://form.typeform.com/to/wmJczaqk\" data-mode=\"popup\" data-submit-close-delay=\"2\" target=\"_blank\"><span>Grow with software experts →</span></a></div> </div> <div data-aos=\"fade-up\" data-aos-anchor-placement=\"top-center\" data-aos-once=\"true\" data-aos-duration=\"350\" data-aos-offset=\"-100\" data-aos-delay=\"400\" data-aos-easing=\"ease-in-out\"> <h1>Turn-key <span>deliveries</span></h1> <img src=\"assets/i/software_delivery.svg\"> <div> <p>We deliver your new products and bring new initiatives to commercial reality by utilizing <strong>our exceptional talent, experience & structure</strong>.</p> <span>Rest assure that our cross-functional delivery teams will deliver highly robust, scalable and secure custom software, in budget and in time. <strong>That is our tretton37 promise.</strong></span> <a class=\"encrypted-email btn-primary btn-small typeform-share button\" href=\"https://form.typeform.com/to/wmJczaqk\" data-mode=\"popup\" data-submit-close-delay=\"2\" target=\"_blank\"><span>Let's start a project together →</span></a> </div> </div> <div data-aos=\"fade-up\" data-aos-anchor-placement=\"top-center\" data-aos-once=\"true\" data-aos-duration=\"350\" data-aos-offset=\"-100\" data-aos-delay=\"600\" data-aos-easing=\"ease-in-out\"> <h1>Tailored <span>software services</span></h1> <img src=\"assets/i/services.svg\"> <div> <p>Our <strong>exploration, ideation and growth</strong> services are custom tailored to fit your next idea or uplift existing product development.</p> <span>We pride ourselves in expertise ranging from planning, design, prototyping, cloud, testing all the way to quality reviews or close care of your product during its lifetime.</span> <a class=\"encrypted-email btn-primary btn-small typeform-share button\" href=\"https://form.typeform.com/to/wmJczaqk\" data-mode=\"popup\" data-submit-close-delay=\"2\" target=\"_blank\"><span>Inquire about our services →</span></a> </div> </div> </div></section> <section class=\"hero inline hero-we-help-clients\"> <div data-aos=\"fade-up\" data-aos-anchor-placement=\"top-bottom\" data-aos-once=\"true\" data-aos-duration=\"350\" data-aos-offset=\"-20\" data-aos-easing=\"ease-in-out\">We help our clients to <strong>embrace their digital destiny</strong>, deliver well-crafted custom software, tailored to <strong>any critical business ambition</strong>. </div></section> <section class=\"site-section\"> <article class=\"mast-head\" data-aos=\"fade-up\" data-aos-anchor-placement=\"top-center\" data-aos-once=\"true\" data-aos-duration=\"350\" data-aos-offset=\"-100\" data-aos-delay=\"0\" data-aos-easing=\"ease-in-out\"> _a few select <h1>clients</h1> </article> <div class=\"container customers\"> <div data-aos=\"fade-up\" data-aos-easing=\"ease-in-out\" data-aos-once=\"true\"><img src=\"/assets/i/logos/alfa_laval.svg\"></div> <div data-aos=\"fade-up\" data-aos-easing=\"ease-in-out\" data-aos-once=\"true\"><img src=\"/assets/i/logos/dfds.svg\"></div> <div data-aos=\"fade-up\" data-aos-easing=\"ease-in-out\" data-aos-once=\"true\"><img src=\"/assets/i/logos/arriva.svg\"></div> <div data-aos=\"fade-up\" data-aos-easing=\"ease-in-out\" data-aos-once=\"true\"><img src=\"/assets/i/logos/cdon.svg\"></div> <div data-aos=\"fade-up\" data-aos-easing=\"ease-in-out\" data-aos-once=\"true\"><img class=\"sm\" src=\"/assets/i/logos/db.svg\"></div> <div data-aos=\"fade-up\" data-aos-easing=\"ease-in-out\" data-aos-once=\"true\"><img class=\"sm\" src=\"/assets/i/logos/diaverum.svg\"></div> <div data-aos=\"fade-up\" data-aos-easing=\"ease-in-out\" data-aos-once=\"true\"><img class=\"sm\" src=\"/assets/i/logos/elon.svg\"></div> <div data-aos=\"fade-up\" data-aos-easing=\"ease-in-out\" data-aos-once=\"true\"><img class=\"sm\" src=\"/assets/i/logos/ica.svg\"></div> <div data-aos=\"fade-up\" data-aos-easing=\"ease-in-out\" data-aos-once=\"true\"><img src=\"/assets/i/logos/modity.svg\"></div> <div data-aos=\"fade-up\" data-aos-easing=\"ease-in-out\" data-aos-once=\"true\"><img class=\"sm\" src=\"/assets/i/logos/paradox.svg\"></div> <div data-aos=\"fade-up\" data-aos-easing=\"ease-in-out\" data-aos-once=\"true\"><img src=\"/assets/i/logos/silverrail.svg\"></div> <div data-aos=\"fade-up\" data-aos-easing=\"ease-in-out\" data-aos-once=\"true\"><img src=\"/assets/i/logos/verisure.svg\"></div> </div> </section> <section class=\"site-section light-gray joinus-front\"> <article class=\"mast-head\" data-aos=\"fade-up\" data-aos-once=\"true\" data-aos-duration=\"350\" data-aos-easing=\"ease-in-out\"><p>_our emphasis on</p> <h1>knowledge and quality</h1> </article> <div class=\"container\"> <div data-aos=\"fade-right\" data-aos-once=\"true\" data-aos-duration=\"350\" data-aos-easing=\"ease-in-out\"> <p></p><h2>A <strong>people-centric</strong> company with a unique culture.</h2><p></p> <p>We proudly employ <strong><a href=\"/meet\">industry leading experts</a></strong> who thrive in our <strong><a href=\"/who-we-are#our-values\">organisational culture</a></strong> where <strong><a href=\"/what-we-do\">well-crafted deliveries</a></strong> are the backbone our collective DNA.</p> <p>We believe in people, <strong><a href=\"/knowledge-sharing\">life-long learning</a></strong> and empowerment to make sure that we continuously improve, challenge, create, and make an impact.</p> <p>We take close care of them and their knowledge so they <strong>take the best care of you.</strong></p> </div> <div class=\"videosection\" data-aos=\"fade-left\" data-aos-once=\"true\" data-aos-duration=\"350\" data-aos-easing=\"ease-in-out\"> <video autoplay=\"\" loop=\"\" muted=\"\" playsinline=\"\" preload=\"metadata\" class=\"show join-front\" data-videosrc=\"culture\" src=\"/assets/v/culture.mp4\"></video> <div class=\"positions-rotate\"> <span> <span>We are looking for a</span> <a href=\"/jobs/1239820-javascript-developer-fullstack\" target=\"_blank\">JavaScript Developer</a><span>Fullstack in \uD83C\uDDF8\uD83C\uDDEA Stockholm</span> </span> <span> <span>We are looking for a</span> <a href=\"/jobs/1130593-frontend-developer\" target=\"_blank\">Frontend Developer</a><span> in \uD83C\uDDF8\uD83C\uDDEA Borlänge</span> </span> <span> <span>We are looking for a</span> <a href=\"/jobs/1130604-backend-developer\" target=\"_blank\">Backend Developer</a><span> in \uD83C\uDDF8\uD83C\uDDEA Stockholm</span> </span> <span> <span>We are looking for a</span> <a href=\"/jobs/1130605-cloud-engineer-aws-azure-or-gcp\" target=\"_blank\">Cloud Engineer</a><span>AWS, Azure or GCP in \uD83C\uDDF8\uD83C\uDDEA Stockholm</span> </span> <span> <span>We are looking for a</span> <a href=\"/jobs/1130609-frontend-developer\" target=\"_blank\">Frontend Developer</a><span> in \uD83C\uDDF8\uD83C\uDDEA Stockholm</span> </span> <span> <span>We are looking for a</span> <a href=\"/jobs/1130611-ux-designer\" target=\"_blank\">UX Designer</a><span> in \uD83C\uDDF8\uD83C\uDDEA Stockholm</span> </span> <span> <span>We are looking for a</span> <a href=\"/jobs/1165504-net-developer\" target=\"_blank\">.NET Developer</a><span> in \uD83C\uDDF8\uD83C\uDDEA Stockholm</span> </span> <span> <span>We are looking for a</span> <a href=\"/jobs/1130592-backend-developer\" target=\"_blank\">Backend Developer</a><span> in \uD83C\uDDF8\uD83C\uDDEA Borlänge</span> </span> <span> <span>We are looking for a</span> <a href=\"/jobs/1318834-ios-developer\" target=\"_blank\">iOS Developer</a><span> in \uD83C\uDDF8\uD83C\uDDEE Ljubljana</span> </span> <span> <span>We are looking for a</span> <a href=\"/jobs/1533596-frontend-developer\" target=\"_blank\">Frontend Developer</a><span> in \uD83C\uDDF8\uD83C\uDDEE Ljubljana</span> </span> <span> <span>We are looking for a</span> <a href=\"/jobs/1534478-full-stack-developer\" target=\"_blank\">Full Stack Developer</a><span> in \uD83C\uDDF8\uD83C\uDDEE Ljubljana</span> </span> <span> <span>We are looking for a</span> <a href=\"/jobs/1541074-devops-engineer\" target=\"_blank\">DevOps Engineer</a><span> in \uD83C\uDDF8\uD83C\uDDEE Ljubljana</span> </span> <span> <span>We are looking for a</span> <a href=\"/jobs/1543812-business-developer\" target=\"_blank\">Business Developer</a><span> in \uD83C\uDDF8\uD83C\uDDEA Lund</span> </span> <span> <span>We are looking for a</span> <a href=\"/jobs/1565429-devops-engineer-aws-azure-or-gcp\" target=\"_blank\">DevOps Engineer</a><span>AWS, Azure or GCP in \uD83C\uDDF8\uD83C\uDDEA Lund</span> </span> <span> <span>We are looking for a</span> <a href=\"/jobs/1565462-javascript-developer\" target=\"_blank\">JavaScript Developer</a><span> in \uD83C\uDDF8\uD83C\uDDEA Lund</span> </span> <span> <span>We are looking for a</span> <a href=\"/jobs/1565505-backend-developer\" target=\"_blank\">Backend Developer</a><span> in \uD83C\uDDF8\uD83C\uDDEA Lund</span> </span> <span> <span>We are looking for a</span> <a href=\"/jobs/1565523-javascript-developer\" target=\"_blank\">JavaScript Developer</a><span> in \uD83C\uDDF8\uD83C\uDDEA Helsingborg</span> </span> <span> <span>We are looking for a</span> <a href=\"/jobs/1565565-backend-developer\" target=\"_blank\">Backend Developer</a><span> in \uD83C\uDDF8\uD83C\uDDEA Helsingborg</span> </span> <span> <span>We are looking for a</span> <a href=\"/jobs/1594919-java-developer\" target=\"_blank\">Java Developer</a><span> in \uD83C\uDDF8\uD83C\uDDEA Stockholm</span> </span> <span> <span>We are looking for a</span> <a href=\"/jobs/1640413-talent-acquisition-specialist\" target=\"_blank\">Talent Acquisition Specialist</a><span> in \uD83C\uDDF8\uD83C\uDDEA Stockholm</span> </span> </div> <span class=\"top-join\"> <a href=\"/join#vacancies\">#Join us</a> </span> <span class=\"bottom-join\"> <span>We have <strong>21</strong> vacancies in \uD83C\uDDF8\uD83C\uDDEA Sweden & \uD83C\uDDF8\uD83C\uDDEE Slovenia</span> </span> </div> </div> </section> <footer> <section> <aside class=\"aside-left hide-small footerText\"><img src=\"/assets/i/_tretton37_slogan_white.svg\" data-aos=\"fade-right\" data-aos-duration=\"250\" data-aos-offset=\"-200\" data-aos-mirror=\"true\" data-aos-easing=\"ease-in-out\"></aside> <nav> <ul> <li><a href=\"/contact\">Contact</a></li> <li><a href=\"http://leetspeak.se\" target=\"_blank\">Leetspeak 2020<i class=\"icon-external-link icon-right\"></i></a></li> <li><a href=\"/events\">Events</a></li> <li><a href=\"/blog\">Blog</a></li> <li><a href=\"/cookies\">Cookies</a></li> <li><a href=\"/privacy-policy\">Privacy Policy</a></li> </ul> </nav> <aside class=\"aside-right\" data-aos=\"fade-left\" data-aos-duration=\"250\" data-aos-offset=\"-200\" data-aos-mirror=\"true\" data-aos-easing=\"ease-in-out\"> <a href=\"https://www.linkedin.com/company/tretton37\" target=\"_blank\" class=\"icon-linkedin big\"></a> <a href=\"https://www.facebook.com/tretton37ab\" target=\"_blank\" class=\"icon-facebook big\"></a> <a href=\"https://twitter.com/tretton37\" target=\"_blank\" class=\"icon-twitter big\"></a> <a href=\"https://www.youtube.com/channel/UCyDoSePfYWLL4re0Dr5fRMw\" target=\"_blank\" class=\"icon-youtube big\"></a> <a href=\"https://github.com/tretton37\" target=\"_blank\" class=\"icon-github big\"></a> <a href=\"https://instagram.com/tretton37ab\" target=\"_blank\" class=\"icon-instagram big\"></a> <a href=\"https://www.flickr.com/photos/tretton37/albums\" target=\"_blank\" class=\"icon-flickr big\"></a> </aside> <aside class=\"aside-left hide-big\"><img src=\"/assets/i/_tretton37_slogan_white.svg\"></aside> </section> </footer> <script src=\"assets/js/lib/polyfills.js\"></script> <script src=\"//cdn.jsdelivr.net/g/jquery@1.11.1,lodash@2.4.1,knockout@3.1.0,enquire.js@2.0.2,momentjs@2.10.6\"></script> <script src=\"https://cdn.jsdelivr.net/npm/@splidejs/splide@2.4.14/dist/js/splide.min.js\"></script> <script src=\"https://unpkg.com/aos@2.3.1/dist/aos.js\"></script> <script src=\"assets/js/lib/common.js?6486927b-e79b-4dce-a98f-efb23a573bc0\"></script> <script src=\"/assets/js/video.js\"></script> <script src=\"/assets/js/index.js\"></script> <!-- Tracking codes --> <script> if(window.triggerInProductionEnvironment()) {      // Google analytics START      script = document.createElement('script');      script.async = true;      script.onload = function(){        window.dataLayer = window.dataLayer || [];        function gtag(){dataLayer.push(arguments);}        gtag('js', new Date());        gtag('config', 'UA-162290178-1');      };      script.src = 'https://www.googletagmanager.com/gtag/js?id=UA-162290178-1';      document.getElementsByTagName('body')[0].appendChild(script);      // Google analytics END      // Facebook Pixel Code START      !function(f,b,e,v,n,t,s)      {if(f.fbq)return;n=f.fbq=function(){n.callMethod?      n.callMethod.apply(n,arguments):n.queue.push(arguments)};      if(!f._fbq)f._fbq=n;n.push=n;n.loaded=!0;n.version='2.0';      n.queue=[];t=b.createElement(e);t.async=!0;      t.src=v;s=b.getElementsByTagName(e)[0];      s.parentNode.insertBefore(t,s)}(window,document,'script',      'https://connect.facebook.net/en_US/fbevents.js');      fbq('init', '154055993505720');      fbq('track', 'PageView');      document.body.insertAdjacentHTML('beforeend', '<noscript><img height=\"1\" width=\"1\" src=\"https://www.facebook.com/tr?id=154055993505720&ev=PageView&noscript=1\"/></noscript>');      // Facebook Pixel Code END    } </script> <script> const subs_cont_elm = document.getElementById(\"subs-cont\");  const btn_sub_close = document.getElementById(\"subs-close\");  btn_sub_close.addEventListener(\"click\", () => {    subs_cont_elm.classList.remove(\"show\");    /* Code To Show Subscribe Modal After 2 Seconds */    setTimeout(() => {      subs_cont_elm.classList.add(\"show\");    }, 1000 * 60);  }); </script> <script> (function() { var qs,js,q,s,d=document, gi=d.getElementById, ce=d.createElement, gt=d.getElementsByTagName, id=\"typef_orm_share\", b=\"https://embed.typeform.com/\"; if(!gi.call(d,id)){ js=ce.call(d,\"script\"); js.id=id; js.src=b+\"embed.js\"; q=gt.call(d,\"script\")[0]; q.parentNode.insertBefore(js,q) } })() </script> <script type=\"text/javascript\"> _linkedin_partner_id = \"1845220\"; window._linkedin_data_partner_ids = window._linkedin_data_partner_ids || []; window._linkedin_data_partner_ids.push(_linkedin_partner_id); </script><script type=\"text/javascript\"> (function(){var s = document.getElementsByTagName(\"script\")[0]; var b = document.createElement(\"script\"); b.type = \"text/javascript\";b.async = true; b.src = \"https://snap.licdn.com/li.lms-analytics/insight.min.js\"; s.parentNode.insertBefore(b, s);})(); </script> <noscript> <img height=\"1\" width=\"1\" style=\"display:none;\" alt=\"\" src=\"https://px.ads.linkedin.com/collect/?pid=1845220&fmt=gif\"> </noscript> </body> </html> ";
        Matcher hrefMatcher = HREF_PATTERN.matcher(rawHtml);
        hrefMatcher.find();
        String urlConsideringHref = urlUtils.getUrlConsideringHref(hrefMatcher);
        assertNotNull(urlConsideringHref);

        Matcher urlMatcher = URL_PATTERN.matcher(rawHtml);
        urlMatcher.find();
        urlConsideringHref = urlUtils.getUrlConsideringHref(urlMatcher);
        assertNotNull(urlConsideringHref);
        System.out.println("shouldFineUrlsConsideringRelativeHrefs passed");
    }

    @Test
    void shouldCalculateACompleteValidUrl() {
        String protocol = "https";
        String url = "/assets/i/join.jpg/";
        assertEquals("https://tretton37.com/assets/i/join.jpg", urlUtils.getFinalCompleteUrl(url, protocol));

        url = "who-we-are";
        assertEquals("https://tretton37.com/who-we-are", urlUtils.getFinalCompleteUrl(url, protocol));

        url = "who-we-are#tag";
        assertEquals("https://tretton37.com/who-we-are", urlUtils.getFinalCompleteUrl(url, protocol));

        url = "https://www.tretton37.com/who-we-are";
        assertEquals("https://tretton37.com/who-we-are", urlUtils.getFinalCompleteUrl(url, protocol));

        url = "https://www.tretton37.com/who-we-are?query=123";
        assertEquals("https://tretton37.com/who-we-are", urlUtils.getFinalCompleteUrl(url, protocol));
        System.out.println("shouldCalculateACompleteValidUrl passed");
    }

    @Test
    void shouldPassWhenGivenUrlIsValid() {
        String url = "https://www.google.com";
        urlUtils.validateWebUrl(url);

        url = "https://google.com";
        urlUtils.validateWebUrl(url);

        url = "google.com";
        urlUtils.validateWebUrl(url);

        url = "maps.google.com";
        urlUtils.validateWebUrl(url);
    }

    @Test
    void shouldThrowExceptionWhenGivenUrlIsNotValid() {
        String url = "https://some.invalid.website.ccccc";
        assertThrows(IllegalArgumentException.class, () -> urlUtils.validateWebUrl(url));
    }

}