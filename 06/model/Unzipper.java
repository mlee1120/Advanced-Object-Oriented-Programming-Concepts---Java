/*
 * This is file illustrates Unzipper.java from hw7.
 */
package hw7.model;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 * Class that unarchives (unzips) a zip file at a given location
 *
 * @author Krystian Alexander Derhak, kad4374@rit.edu
 * @author Michael Lee, ml3406@rit.edu
 */

public class Unzipper extends Thread{

    /**
     * the constructor of Unzipper that unzips files of a given zip file to a given location
     *
     * @param basePath   a string representation of working directory
     * @param zipPath    a string representation of where the zip file is located
     * @param folderPath a string representation of where the unzipped files will be
     * @throws ZipperException if file does not exist
     */
    public Unzipper(String basePath, String zipPath, String folderPath) throws ZipperException {
        try (ZipInputStream reader = new ZipInputStream(new BufferedInputStream(new FileInputStream(basePath + zipPath)))) {
            ZipEntry zipEntry;
            while ((zipEntry = reader.getNextEntry()) != null) {
                try (BufferedOutputStream writer = new BufferedOutputStream(new FileOutputStream(basePath + folderPath + "\\" + zipEntry.getName()))) {
                    int in;
                    while ((in = reader.read()) != -1) {
                        writer.write(in);
                    }
                }
            }
        } catch (IOException e) {
            throw new ZipperException("IOException", "Extracting the archive '" + zipPath + "' and saving entries in directory '" + folderPath + "'...\nError processing most recent command!\nFailed to uncompress archive!");
        }
    }
}
