package com.javadi.websitecrawler.content;

import java.io.IOException;
import java.net.URLConnection;

/**
 * This interface is used for storing the contents of the website
 */
public interface ContentWriter {

    /**
     * gets the content as string and stores it in the underlying storage.
     * returns the filePath/id/... that can be used for retrieving the resource
     * parentPath and fileName should be used for locating a meaningful path/id/... while storing the file
     * @param content the content of the resource as String
     * @param parentPath refers to the parent URI of the website's resource
     * @param fileName refers to the name of the website's resource
     * @return the filePath/id/... that can be used for retrieving the file
     * @throws IOException if anything goes wrong during writing the content
     */
    String write(String content, String parentPath, String fileName) throws IOException;

    /**
     * gets a connection to the resource and stores it in the underlying storage.
     * returns the filePath/id/... that can be used for retrieving the resource
     * parentPath and fileName should be used for locating a meaningful path/id/... while storing the file
     * @param connection the connection to the resource
     * @param parentPath refers to the parent URI of the website's resource
     * @param fileName refers to the name of the website's resource
     * @return the filePath/id/... that can be used for retrieving the file
     * @throws IOException if anything goes wrong during writing the content
     */
    String write(URLConnection connection, String parentPath, String fileName) throws IOException;

}
