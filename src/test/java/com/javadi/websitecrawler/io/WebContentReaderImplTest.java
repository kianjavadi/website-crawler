package com.javadi.websitecrawler.io;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class WebContentReaderImplTest {

    WebContentReaderImpl contentReader;
    URL url;

    @BeforeAll
    void initialize() throws MalformedURLException {
        contentReader = new WebContentReaderImpl();
        url = new URL("https://tretton37.com/");
    }

    @Test
    void shouldReadHtmlContentOfaRemote() throws IOException {
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.connect();
        String content = contentReader.readAsString(connection);
        assertNotNull(content);
        assertNotEquals("", content.trim());
        System.out.println("shouldReadHtmlContentOfaRemote passed");
    }

    @Test
    void shouldBeAbleToMakeConnectionToaRemoteHost() throws IOException {
        HttpURLConnection connection = contentReader.makeConnection(url.toString());
        InputStream inputStream = connection.getInputStream();
        assertNotEquals(0, inputStream.available());
        inputStream.close();
        System.out.println("shouldBeAbleToMakeConnectionToaRemoteHost passed");
    }

    @Test
    void shouldBeAbleToIdentifyTheFileExtensionFromUrl() throws IOException {
        HttpURLConnection connection = contentReader.makeConnection(url.toString());
        assertEquals(".html", contentReader.getFileExtension(connection));
        System.out.println("shouldBeAbleToIdentifyTheFileExtensionFromUrl passed");
    }

}