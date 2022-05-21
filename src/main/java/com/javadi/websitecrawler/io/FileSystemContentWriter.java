package com.javadi.websitecrawler.io;

import com.javadi.websitecrawler.crawler.ContentWriter;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class FileSystemContentWriter implements ContentWriter {

    private final String workingDirectory;

    public FileSystemContentWriter(String domain) {
        this.workingDirectory = System.getProperty("user.home") + File.separator + domain;
        try {
            Files.createDirectories(Path.of(workingDirectory));
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("The workingDirectory cannot be initialized: " + workingDirectory, e);
        }
    }

    @Override
    public String write(String content, String parentPath, String fileName) throws IOException {
        String filePath = getFilePath(parentPath, fileName);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write(content);
            writer.flush();
        }
        return filePath;
    }

    private String getFilePath(String parentPath, String fileName) throws IOException {
        Files.createDirectories(Path.of(workingDirectory, parentPath));
        return workingDirectory + File.separator + parentPath + File.separator + fileName;
    }

    @Override
    public String write(URLConnection connection, String parentPath, String fileName) throws IOException {
        String filePath = getFilePath(parentPath, fileName);
        Files.copy(connection.getInputStream(), Path.of(filePath), StandardCopyOption.REPLACE_EXISTING);
        return filePath;
    }

}
