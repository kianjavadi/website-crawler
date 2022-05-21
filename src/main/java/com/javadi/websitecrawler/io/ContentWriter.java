package com.javadi.websitecrawler.io;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class ContentWriter {

    public void write(String content, String filePath) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write(content);
            writer.flush();
        }
    }

    public void write(URLConnection connection, String filePath) throws IOException {
        Files.copy(connection.getInputStream(), Path.of(filePath), StandardCopyOption.REPLACE_EXISTING);
    }

}
