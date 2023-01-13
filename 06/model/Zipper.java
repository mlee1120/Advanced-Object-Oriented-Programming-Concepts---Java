/*
 * This is file illustrates Zipper.java from hw7.
 */
package hw7.model;

import java.io.*;
import java.util.ArrayList;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * Class that archives (zips) files at a given location
 *
 * @author Krystian Alexander Derhak, kad4374@rit.edu
 * @author Michael Lee, ml3406@rit.edu
 */

public class Zipper {
    /**
     * the constructor of Zipper that creates a zip file at a given location and zips files into it
     *
     * @param zipPath  a string representation of where the zip file will be located
     * @param filesAdd a list of files to be archived
     * @throws ZipperException if IOException or or file does not exist
     */
    public Zipper(String zipPath, ArrayList<File> filesAdd) throws ZipperException {
        try (ZipOutputStream writer = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(zipPath, true)))) {
            while (filesAdd.size() != 0) {
                File file = new File(filesAdd.remove(filesAdd.size() - 1).getAbsolutePath());
                try (BufferedInputStream reader = new BufferedInputStream(new FileInputStream(file))) {
                    ZipEntry zipEntry = new ZipEntry(file.getName());
                    writer.putNextEntry(zipEntry);
                    int in;
                    while ((in = reader.read()) != -1) {
                        writer.write(in);
                    }
                } catch (FileNotFoundException e) {
                    throw new ZipperException("FileNotFound!", "FileNotFound!");
                }
            }
        } catch (IOException | ZipperException e) {
            throw new ZipperException("IOException!", "IOException!");
        }
    }
}
