package com.javadi.websitecrawler.content;

import java.io.IOException;

/**
 * This interface is responsible for reading a stored resource in the local storage
 * It is used along with the ContentWriter, it means that this interface should be able to read the contents that ContentWriter has written
 */
public interface ContentReader {

    /**
     * This method gets a filePath (or fileId or ..., it depends on the storage type) and reads the content of it as String
     * it's used for reading HTML files
     * @param filePath the filePath, fileId, ...
     * @return the content of the HTML file
     * @throws IOException if anything goes wrong during reading the content
     */
    String readAsString(String filePath) throws IOException;

}
