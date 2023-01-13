/*
 * This is file illustrates ZipperUtility.java from hw7.
 */
package hw7.view;


import java.util.Scanner;

import hw7.controller.MethodController;
import hw7.model.ZipperException;

/**
 * Creates the main interaction with the other files, userMenu, and the main method
 * Allows user to zip and unzip files based upon a directory
 *
 * @author Krystian Alexander Derhak, kad4374@rit.edu
 * @author Michael Lee, ml3406@rit.edu
 */

public class ZipperUtility {

    /**
     * Main user interface with basic error checking and looping
     */
    public void userMenu() {
        MethodController control = new MethodController();
        boolean userIn1 = true;
        Scanner in = new Scanner(System.in);
        while (userIn1) {
            System.out.print("enter command >> ");
            String[] menuOp = in.nextLine().split(" ");
            switch (menuOp[0].toLowerCase()) {
                case "add" -> {
                    try {
                        System.out.println(control.addFile(menuOp));
                    } catch (ZipperException e) {
                        System.out.println(e.toString());
                    }
                }
                case "archive" -> {
                    try {
                        control.archiveFiles(menuOp);
                    } catch (ZipperException e) {
                        System.out.println(e.toString());
                    }
                }
                case "clear" -> System.out.println(control.clearFiles());
                case "files" -> System.out.println(control.files());
                case "help" -> {
                    System.out.println("Enter one of the following commands:");
                    System.out.println("    add <file path> - adds file indicated by the path to the collection of " +
                            "files to archive.");
                    System.out.println("    archive <file path> - writes the collection of added files to the file " +
                            "indicated by the path.");
                    System.out.println("    clear - clears the current list of files to be archived.");
                    System.out.println("    files - prints the current list of files to be archived.");
                    System.out.println("    list <directory> - lists the files in the specified directory");
                    System.out.println("    quit - quits the Zipper Utility.");
                    System.out.println("    unarchive <source> <destination> - extracts the archive specified by " +
                            "source and saves the extracted entries in the destination directory.");
                }
                case "list" -> {
                    if (menuOp.length < 2) {
                        System.out.println("Error Processing most recent command!");
                        break;
                    }
                    try {
                        System.out.println(control.listFiles(menuOp[1]));
                    } catch (ZipperException e) {
                        System.out.println(e.toString());
                    }

                }
                case "quit" -> {
                    System.out.println("Quitting...");
                    userIn1 = false;
                }
                case "unarchive" -> {
                    try {
                        System.out.println(control.unarchiveFiles(menuOp));
                    } catch (ZipperException e) {
                        System.out.println(e.toString());
                    }
                }
                default -> {
                    System.out.println("Unrecognized command: " + menuOp[0]);
                    System.out.println("Use 'help' to display a list of commands.");
                }
            }

        }
        in.close();
    }

    /**
     * Main method asking you to set up your working directory before calling the userMenu method
     */
    public static void main(java.lang.String[] args) {
        if (args.length == 0) {
            System.out.println("Please edit your working directory to where files to zip/unzip are kept.");
            System.out.println("For example ..\\src\\hw7");
            System.out.print("Once you finish editing, please randomly enter something in program arguments and " +
                    "execute this program again");
            System.exit(0);
        }
        ZipperUtility hw7 = new ZipperUtility();
        hw7.userMenu();
    }
}
