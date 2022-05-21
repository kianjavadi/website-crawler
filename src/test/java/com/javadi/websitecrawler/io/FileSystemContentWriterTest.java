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
class FileSystemContentWriterTest {

    FileSystemContentWriter contentWriter;

    @BeforeAll
    void initialize() {
        String domain = "tretton37.com";
        contentWriter = new FileSystemContentWriter(domain);
    }

    @Test
    void writeFromStringContentToFile() throws IOException {
        String content = "Hello!";
        String tempParentPath = getTempDirectory();
        String tempFileName = getTempFile();
        String filePath = contentWriter.write(content, tempParentPath, tempFileName);
        File tempFile = new File(filePath);
        assertTrue(tempFile.exists() && tempFile.length() > 0);
        tempFile.delete();
        tempFile.getParentFile().delete();
        System.out.println("writeFromStringContentToFile passed");
    }

    private String getTempDirectory() {
        return System.currentTimeMillis() + System.nanoTime() + "";
    }

    private String getTempFile() {
        return System.currentTimeMillis() + System.nanoTime() + ".txt";
    }

    @Test
    void writeFromRemoteUrlToFile() throws IOException {
        URL url = this.getClass().getClassLoader().getResource("test.html");
        URLConnection connection = url.openConnection();
        connection.connect();
        String tempParentPath = getTempDirectory();
        String tempFileName = getTempFile();
        String filePath = contentWriter.write(connection, tempParentPath, tempFileName);
        File tempFile = new File(filePath);
        assertTrue(tempFile.exists() && tempFile.length() > 0);
        tempFile.delete();
        tempFile.getParentFile().delete();
        System.out.println("writeFromRemoteUrlToFile passed");
    }

}