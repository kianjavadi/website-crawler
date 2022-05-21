package com.javadi.websitecrawler.io;

import com.javadi.websitecrawler.content.WebContentReader;
import com.javadi.websitecrawler.utils.MimeTypes;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class WebContentReaderImpl implements WebContentReader {

    @Override
    public String readAsString(HttpURLConnection connection) throws IOException {
        StringBuilder rawHtml = new StringBuilder();
        try (InputStreamReader inputStreamReader = new InputStreamReader(connection.getInputStream());
             BufferedReader reader = new BufferedReader(inputStreamReader)) {
            String line = "";
            while ((line = reader.readLine()) != null) {
                rawHtml.append(line);
            }
        }
        return rawHtml.toString();
    }

    @Override
    public HttpURLConnection makeConnection(String stringUrl) throws IOException {
        URL url = new URL(stringUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setAllowUserInteraction(false);
        connection.setDoInput(true);
        connection.setDoOutput(false);
        connection.setUseCaches(true);
        connection.setRequestMethod("GET");
        connection.connect();
        return connection;
    }

    @Override
    public String getFileExtension(HttpURLConnection connection) {
        String mime = connection.getContentType();
        if (mime == null)
            return null;
        return getFileExtensionFromMimeType(mime);
    }

    private String getFileExtensionFromMimeType(String mimeType) {
        String fileExtension = MimeTypes.getDefaultExtWithDot(mimeType);
        if (fileExtension.equals(".unknown")) {
            int semi = mimeType.indexOf(';');
            if (semi != -1) {
                mimeType = mimeType.substring(0, semi);
                fileExtension = MimeTypes.getDefaultExtWithDot(mimeType);
            }
        }
        return fileExtension;
    }

}
