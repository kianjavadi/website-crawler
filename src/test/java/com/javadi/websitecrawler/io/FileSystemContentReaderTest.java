package com.javadi.websitecrawler.io;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class FileSystemContentReaderTest {

    FileSystemContentReader contentReader;

    @BeforeAll
    void initialize() {
        contentReader = new FileSystemContentReader();
    }

    @Test
    void shouldBeAbleToReadTheContentOfAFile() throws IOException {
        File testFile = new File(getClass().getClassLoader().getResource("test.html").getFile()).getAbsoluteFile();
        String content = contentReader.readAsString(URLDecoder.decode(testFile.getAbsolutePath(), StandardCharsets.UTF_8));
        assertNotNull(content);
        assertNotEquals("", content);
        System.out.println("shouldBeAbleToReadTheContentOfAFile passed");
    }

}