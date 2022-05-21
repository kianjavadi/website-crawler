package com.javadi.websitecrawler.io;

import com.javadi.websitecrawler.content.ContentWriter;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URLConnection;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

import static com.javadi.websitecrawler.config.ApplicationConstants.DOWNLOADING_BUFFER_SIZE;

/**
 * FileSystem implementation of the ContentWriter,
 * content of the resources will be written in a filesystem
 */
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
        String url = URLDecoder.decode(connection.getURL().toString(), StandardCharsets.UTF_8);
        int completeFileSize = connection.getContentLength();
        if (completeFileSize != -1) {
            writeContentWithPrintingProgress(connection, filePath, url, completeFileSize);
        } else {
            writeContentWithoutPrintingProgress(connection, filePath, url);
        }
        return filePath;
    }

    private void writeContentWithPrintingProgress(URLConnection connection, String filePath, String url, int completeFileSize) throws IOException {
        try (BufferedInputStream in = new BufferedInputStream(connection.getInputStream());
             FileOutputStream fos = new FileOutputStream(filePath);
             BufferedOutputStream bout = new BufferedOutputStream(fos, DOWNLOADING_BUFFER_SIZE)) {
            byte[] data = new byte[DOWNLOADING_BUFFER_SIZE];
            int downloadedFileSize = 0;
            int x;
            while ((x = in.read(data, 0, DOWNLOADING_BUFFER_SIZE)) >= 0) {
                downloadedFileSize += x;
                // calculate progress
                final int currentProgress = (int) ((((double) downloadedFileSize) / completeFileSize) * 100d);
                System.out.printf("Saving: %s in: %s (%d%%)  %n", url, filePath, currentProgress);
                bout.write(data, 0, x);
            }
        }
    }

    private void writeContentWithoutPrintingProgress(URLConnection connection, String filePath, String url) throws IOException {
        System.out.printf("Saving: %s in: %s%n", url, filePath);
        Files.copy(connection.getInputStream(), Path.of(filePath), StandardCopyOption.REPLACE_EXISTING);
    }

}
