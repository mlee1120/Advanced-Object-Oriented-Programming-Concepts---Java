/*
 * This is file illustrates methodController.java from hw7.
 */
package hw7.controller;

import hw7.model.Unzipper;
import hw7.model.Zipper;
import hw7.model.ZipperException;

import java.io.File;
import java.util.ArrayList;

/**
 * Creates the main interaction with the other files, userMenu, and the main method
 *
 * @author Krystian Alexander Derhak, kad4374@rit.edu
 * @author Michael Lee, ml3406@rit.edu
 */
public class MethodController {

    /**
     * filesAdd stores all files to be archive
     */
    ArrayList<File> filesAdd = new ArrayList<>();
    /**
     * basePath changes based upon the users computer and filesystem
     */
    String basePath = System.getProperty("user.dir") + "\\";

    /**
     * Method adding the file path of a file to a list of flies to be archived
     *
     * @param filepath an array of strings containing user input
     * @return a string for the user to view what the program is doing
     * @throws ZipperException if arguments are invalid or file does not exist
     */
    public String addFile(String[] filepath) throws ZipperException {
        if (filepath.length != 2) throw new ZipperException("Invalid arguments: 2 arguments required.");
        else {
            String completePath = basePath + filepath[1];
            File addFile = new File(completePath);
            if (!addFile.exists()) {
                throw new ZipperException("File does not exist",
                        "Error processing most recent command!\nFile does not exist:\n" + completePath);
            } else filesAdd.add(addFile);
            return filepath[1] + " added to list of files to be archived.";
        }
    }

    /**
     * Method archiving files in the list of files to be archived at the specified path by the user
     *
     * @param filepath an array of strings containing user input
     * @throws ZipperException if arguments are invalid
     */
    public void archiveFiles(String[] filepath) throws ZipperException {
        if (filepath.length != 2)
            throw new ZipperException("Invalid arguments: 2 arguments required.", "Invalid arguments: 2 arguments required.");
        else {
            if (filesAdd.isEmpty()) {
                throw new ZipperException("No files to zip! Use the 'Add' command to add files", "No files to zip! Use the 'Add' command to add files");
            } else if (filepath[1].substring((filepath[1].length() - 4)).equals(".zip")) {
                String completePath = basePath + filepath[1];
                new Zipper(completePath, filesAdd);
            } else {
                throw new ZipperException("Improper filepath!", "Improper filepath!");
            }
        }
    }

    /**
     * Method clearing the list of files to be archived
     *
     * @return a string for the user to view what the program is doing
     */
    public String clearFiles() {
        filesAdd.clear();
        return "Files to be archived cleared.";
    }

    /**
     * Method listing what files are stored in the list of files to be archived
     *
     * @return a string for the user to view files to be archived and what the program is doing
     */
    public String files() {
        StringBuilder sbr = new StringBuilder();
        if (filesAdd.isEmpty()) return "There are no files to be archived. Use the add command to add a file.";
        else {
            sbr.append("Listing files to be archived...\n");
            for (File file : filesAdd) {
                sbr.append(file);
                sbr.append("\n");
            }
            return sbr.toString();
        }
    }

    /**
     * Method displaying what files exist at a given filepath
     *
     * @param filePath a string of the filepath to be inspected
     * @return a string for the user to view what files are in a given directory
     * @throws ZipperException if the provided path does not exist
     */
    public String listFiles(String filePath) throws ZipperException {
        StringBuilder sbr = new StringBuilder();
        String completePath = basePath + filePath;
        File directoryPath = new File(completePath);
        if (!directoryPath.exists()) {
            throw new ZipperException("Provided path is not a directory",
                    "Error processing most recent command!\nPath is not a directory: " + filePath);
        } else {
            sbr.append("Listing files in '").append(filePath).append("'...\n");
            File[] filesList = directoryPath.listFiles();
            assert filesList != null;
            if (filesList.length != 0) {
                for (File file : filesList) {
                    sbr.append(file.getAbsolutePath());
                    sbr.append("\n");
                }
            } else sbr.append("There is no file in the directory.");
            return sbr.toString();
        }
    }

    /**
     * Method unzipping the files in a given zipped file to a given location
     *
     * @param filepath an array of strings containing user input
     * @return a string for the user to view files to be unarchived and what the program is doing
     * @throws ZipperException if arguments are invalid
     */
    public String unarchiveFiles(String[] filepath) throws ZipperException {
        StringBuilder sbr = new StringBuilder();
        if (filepath.length != 3) throw new ZipperException("Invalid arguments: 3 arguments required.");
        else {
            sbr.append("Extracting the archive '");
            sbr.append(filepath[1]);
            sbr.append("' and saving entries in directory '");
            sbr.append(filepath[2]);
            sbr.append("'...");
            new Unzipper(basePath, filepath[1], filepath[2]);
        }
        return sbr.toString();
    }
}
