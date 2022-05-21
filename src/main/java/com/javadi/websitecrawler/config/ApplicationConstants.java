package com.javadi.websitecrawler.config;

import java.util.Set;
import java.util.regex.Pattern;

public final class ApplicationConstants {

    public static final Pattern HREF_PATTERN = Pattern.compile("(href=\")([^\"]+)");
    public static final Pattern URL_PATTERN = Pattern.compile("(http|ftp|https)://([\\w_-]+(?:(?:\\.[\\w_-]+)+))([\\w.,@?^=%&:/~+#-]*[\\w@?^=%&/~+#-])");
    public static final Pattern HTTP_PATTERN = Pattern.compile("((ftp|https?)(://))?(www.)?(.*)");

    public static final String[] NEED_ENCODING = {">", "<", " "};
    public static final Set<Character> ALLOWED_CHARACTERS_IN_URL = Set.of('A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J'
            , 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e'
            , 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'
            , '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '-', '.', '_', '~', ':', '/', '?', '#', '[', ']', '@'
            , '!', '$', '&', '\\', '(', ')', '*', '+', ',', ';', '=', '%');

    public static final String HTML_EXTENSION_WITH_DOT = ".html";
    public static final String DEFAULT_PROTOCOL = "https";
    public static final String HREF_ATTRIBUTE = "href=\"";
    public static final int HREF_ATTRIBUTE_LENGTH = HREF_ATTRIBUTE.length();

    private ApplicationConstants() {
        throw new IllegalCallerException("do not instantiate me!!");
    }

}
