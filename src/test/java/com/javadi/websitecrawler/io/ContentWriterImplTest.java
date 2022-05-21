package com.javadi.websitecrawler.io;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;

import static org.junit.jupiter.api.Assertions.assertTrue;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ContentWriterImplTest {

    ContentWriterImpl contentWriter;

    @BeforeAll
    void initialize() {
        contentWriter = new ContentWriterImpl();
    }

    @Test
    void writeFromStringContentToFile() throws IOException {
        String content = "Hello!";
        File tempFile = getTempFile();
        contentWriter.write(content, tempFile.getAbsolutePath());
        assertTrue(tempFile.exists() && tempFile.length() > 0);
        tempFile.delete();
        System.out.println("writeFromStringContentToFile passed");
    }

    private File getTempFile() {
        File file;
        do {
            file = new File(System.getProperty("user.home") + File.separator + System.currentTimeMillis() + System.nanoTime() + ".txt");
        } while (file.exists());
        return file;
    }

    @Test
    void writeFromRemoteUrlToFile() throws IOException {
        URL url = this.getClass().getClassLoader().getResource("test.html");
        URLConnection connection = url.openConnection();
        connection.connect();
        File tempFile = getTempFile();
        contentWriter.write(connection, tempFile.getAbsolutePath());
        assertTrue(tempFile.exists() && tempFile.length() > 0);
        tempFile.delete();
        System.out.println("writeFromRemoteUrlToFile passed");
    }

}