package com.javadi.websitecrawler.io;

import com.javadi.websitecrawler.content.ContentReader;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * FileSystem implementation of the ContentReader,
 * content of the resources will be read from a filesystem
 */
public class FileSystemContentReader implements ContentReader {

    @Override
    public String readAsString(String filePath) throws IOException {
        return Files.readString(Path.of(filePath), StandardCharsets.UTF_8);
    }

}
