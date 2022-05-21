package com.javadi.websitecrawler.utils;

import com.javadi.websitecrawler.crawler.UrlUtils;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.javadi.websitecrawler.config.ApplicationConstants.ALLOWED_CHARACTERS_IN_URL;
import static com.javadi.websitecrawler.config.ApplicationConstants.DEFAULT_PROTOCOL;
import static com.javadi.websitecrawler.config.ApplicationConstants.HREF_ATTRIBUTE;
import static com.javadi.websitecrawler.config.ApplicationConstants.HREF_ATTRIBUTE_LENGTH;
import static com.javadi.websitecrawler.config.ApplicationConstants.HTTP_PATTERN;

public class UrlUtilsImpl implements UrlUtils {

    private final String domain;
    private final Pattern uriPattern;

    public UrlUtilsImpl(String domain) {
        this.domain = getDomainForApplicationInput(domain);
        this.uriPattern = Pattern.compile("((ftp|https?)://)(.*" + this.domain + "/)(.*)");
    }

    @Override
    public String encodeUrl(String url) {
        Set<Character> needToChangeChars = getNeedToChangeChars(url);
        for (Character needToChangeChar : needToChangeChars) {
            url = url.replace(needToChangeChar.toString(), URLEncoder.encode(needToChangeChar.toString(), StandardCharsets.UTF_8));
        }
        return url;
    }

    private Set<Character> getNeedToChangeChars(String url) {
        Set<Character> needToChangeChars = new HashSet<>();
        for (int i = 0; i < url.length(); i++) {
            char ch = url.charAt(i);
            if (!ALLOWED_CHARACTERS_IN_URL.contains(ch)) {
                needToChangeChars.add(ch);
            }
        }
        return needToChangeChars;
    }

    @Override
    public String getDomainNameForLinksWithinWebsite(String url) {
        if (!url.startsWith("http"))
            return this.domain;
        URI uri;
        try {
            url = encodeUrl(url);
            uri = new URI(url);
        } catch (URISyntaxException e) {
            return "UNRECOGNIZED_DOMAIN";
        }
        String domain = uri.getHost();
        return domain == null ? "UNRECOGNIZED_DOMAIN" : (domain.startsWith("www.") ? domain.substring(4) : domain);
    }

    @Override
    public String getFileNameWithExtension(String stringUrl, String fileExtension) {
        String fileName = getFileName(stringUrl);
        if (fileExtension == null || fileName.isBlank()) {
            fileName = "home";
        } else if (!fileName.endsWith(fileExtension)) {
            fileName += fileExtension;
        }
        return URLDecoder.decode(fileName, StandardCharsets.UTF_8);
    }

    @Override
    public String getParentPath(String url) {
        String uri = "";
        try {
            Matcher matcher = uriPattern.matcher(url);
            if (matcher.find()) {
                uri = matcher.group(4);
                uri = removeLastPath(uri);
            }
        } catch (Exception e) {

        }
        return uri;
    }

    @Override
    public boolean isUrlValid(String domain, String url) {
        return this.domain.contains(domain) && !url.contains("/../") && !url.contains("tel:")
                && !url.contains("mailto:") && !url.contains("javascript:");
    }

    @Override
    public String getDomainForApplicationInput(String website) {
        Matcher matcher = HTTP_PATTERN.matcher(website);
        if (matcher.find()) {
            return matcher.group(5);
        } else return website;
    }

    @Override
    public String getProtocol(String website) {
        Matcher matcher = HTTP_PATTERN.matcher(website);
        if (matcher.find()) {
            String protocol = matcher.group(2);
            return protocol != null && !protocol.isBlank() ? protocol : DEFAULT_PROTOCOL;
        } else return DEFAULT_PROTOCOL;
    }

    @Override
    public String getUrlConsideringHref(Matcher matcher) {
        String url = matcher.group();
        if (url.startsWith(HREF_ATTRIBUTE)) {
            return url.substring(HREF_ATTRIBUTE_LENGTH);
        } else return url;
    }

    @Override
    public String getFinalCompleteUrl(String url, String protocol) {
        String completeUrl = getCompleteUrl(url, protocol);
        String removedSharpSign = removeSharpSign(completeUrl);
        String removedWww = removeWww(removedSharpSign);
        String removedAfterQuestionMark = removedQuestionMark(removedWww);
        return removeForwardSlashAtTheEnd(removedAfterQuestionMark);
    }

    private String removedQuestionMark(String url) {
        int questionMarkIndex = url.indexOf('?');
        return questionMarkIndex == -1 ? url : url.substring(0, questionMarkIndex);
    }

    private String removeForwardSlashAtTheEnd(String removedWww) {
        if (removedWww.endsWith("/")) {
            return removedWww.substring(0, removedWww.length() - 1);
        } else {
            return removedWww;
        }
    }

    private String getCompleteUrl(String url, String protocol) {
        String result;
        if (url.startsWith("http") || url.startsWith("ftp")) {
            result = url;
        } else if (url.startsWith("/")) {
            result = protocol + "://" + domain + url;
        } else result = protocol + "://" + domain + '/' + url;
        return result;
    }

    private String removeSharpSign(String url) {
        int sharpSignIndex = url.indexOf('#');
        return sharpSignIndex == -1 ? url : url.substring(0, sharpSignIndex);
    }

    private String removeWww(String url) {
        url = url.replace("https" + "://www.", "https" + "://");
        return url.replace("http" + "://www.", "http" + "://");
    }

    private String getFileName(String url) {
        if (url == null)
            return null;

        final int index = url.lastIndexOf('/');
        return url.substring(index + 1);
    }

    private String removeLastPath(String uri) {
        int i = uri.lastIndexOf('/');
        if (i != -1) {
            uri = uri.substring(0, i);
        } else {
            uri = "";
        }
        return uri;
    }

}
