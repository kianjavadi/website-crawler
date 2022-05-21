package com.javadi.websitecrawler.io;

import com.javadi.websitecrawler.content.ContentReader;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileSystemContentReader implements ContentReader {

    @Override
    public String readAsString(String filePath) throws IOException {
        return Files.readString(Path.of(filePath), StandardCharsets.UTF_8);
    }

}
