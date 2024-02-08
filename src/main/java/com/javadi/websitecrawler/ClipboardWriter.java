package com.javadi.websitecrawler;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * read the given file line by line and copy each line to the clipboard, then:
 * if there are some unprocessed lines, it writes them into the file (processed lines will be deleted)
 * otherwise it deletes the file at the end of execution
 */
public class ClipboardWriter {

    private static final String OUTPUT = ".out/clipboard_files";

    public static void main(String[] args) throws Exception {
        var file = args.length > 0 ? Path.of(args[0]) : Path.of("src/main/resources/clipboard.txt");
        var links = read((file));
        var writeLinks = new LinkedHashSet<>(links);
        var initialSize = links.size();
        var remained = new AtomicInteger(initialSize);
        System.out.printf("there are %d lines\n", initialSize);
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            try {
                write(writeLinks, Path.of(getFilePath(file.toFile().getName())));
                if (remained.get() != 0) {
                    System.out.printf("file was successfully updated (%d lines were processed and %d are left)\n",
                            (initialSize - remained.get()), remained.get());
                } else System.out.printf("file was removed (%d lines were processed)\n", initialSize);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }));
        links.forEach(link -> {
            copyToClipBoard(link);
            System.out.println(remained.decrementAndGet());
            try {
                Thread.sleep(550);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            writeLinks.remove(link);
        });
    }

    private static void copyToClipBoard(String content) {
        StringSelection stringSelection = new StringSelection(content);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(stringSelection, null);
    }

    public static List<String> read(Path file) throws IOException {
        try (var stream = Files.lines(file, StandardCharsets.UTF_8)) {
            return stream.collect(Collectors.toList());
        }
    }

    public static void write(Iterable<String> content, Path file) throws IOException {
        Files.createDirectories(file.getParent());
        Files.deleteIfExists(file);
        if (content.iterator().hasNext()) {
            try (BufferedWriter writer = new BufferedWriter(new java.io.FileWriter(file.toFile()))) {
                for (String line : content) {
                    writer.write(line);
                    writer.write(System.lineSeparator());
                }
                writer.flush();
            }
        }
    }

    private static String getFilePath(String fileName) {
        return OUTPUT + File.separator + fileName;
    }


}