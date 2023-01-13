/*
 * This is file illustrates TollReport.java from hw6.
 */
package hw6;

import java.util.Scanner;

/**
 * Creates the main interaction with the other files, userMenu, and the main method
 *
 * @author Krystian Alexander Derhak, kad4374@rit.edu
 * @author Michael Lee, ml3406@rit.edu
 */

public class TollReport implements TollsRUs {

    /**
     * Main user interface with basic error checking and looping
     *
     * @param filename file path name of the file to be opened and read for database
     */
    public void userMenu(String filename) {
        // A toll database storing all TollRecords and providing methods getting important information
        TollDatabase data = new TollDatabase(filename);
        boolean userIn1 = true;
        Scanner in = new Scanner(System.in);
        // Prints summary of all completed trips
        System.out.println(data.summary());
        // Prints information of incomplete trips
        System.out.println(data.printOnRoad());
        // Prints detailed speeder report
        System.out.println(data.speederReport());
        // Prints billing information
        System.out.println(data.billingInfo());

        // Prints a command line interface into which the user may enter the following commands:
        while (userIn1) {
            System.out.println("\n'b <string>' to see bill for license tag");
            System.out.println("'e <number>' to see activity at exit");
            System.out.println("'q' to quit");
            System.out.print("> ");
            String[] menuOp = in.nextLine().split(" ");

            // "b" - Prints single vehicle's billing information and total amount of toll
            // "e" - Prints information of all events, including completed and incomplete trips, related to an exit
            // "q" - Quits and leaves the program
            switch (menuOp[0].toLowerCase()) {
                case "b":
                    if (menuOp.length != 2) {
                        System.out.print("Illegal command. Try again");
                        break;
                    }
                    double total = 0;
                    for (TollRecord t : data.setCom) {
                        if (t.getTag().equals(menuOp[1].toUpperCase())) {
                            System.out.print(t);
                            System.out.print(": ");
                            System.out.println(String.format(DOLLAR_FORMAT, t.charge()));
                            total += t.charge();
                        }
                    }
                    System.out.print("\nVehicle total due: ");
                    System.out.println(String.format(DOLLAR_FORMAT, total));
                    break;
                case "e":
                    if (Integer.parseInt(menuOp[1]) < 0 || Integer.parseInt(menuOp[1]) > 62) {
                        System.out.println("Exit does not exist in the system");
                        break;
                    }
                    System.out.print("\nEXIT ");
                    System.out.print(menuOp[1]);
                    System.out.println(" REPORT");
                    System.out.println("==============");
                    for (TollRecord t : data.setCom) {
                        if (Integer.parseInt(menuOp[1]) == t.getaExit() || Integer.parseInt(menuOp[1]) == t.geteExit())
                            System.out.println(t);
                    }
                    for (TollRecord t : data.setIncom) {
                        if (Integer.parseInt(menuOp[1]) == t.getaExit() || Integer.parseInt(menuOp[1]) == t.geteExit())
                            System.out.println(t);
                    }
                    break;
                case "q":
                    userIn1 = false;
                    break;
                default:
                    System.out.print("Illegal command. Try again");
                    break;
            }
        }
        // Closes the scanner
        in.close();
    }

    /**
     * Creates a new TollReport object and runs main menu method
     *
     * @param args name of file acting as toll information
     */
    public static void main(java.lang.String[] args) {
        TollReport hw6 = new TollReport();
        hw6.userMenu(args[0]);
    }
}
