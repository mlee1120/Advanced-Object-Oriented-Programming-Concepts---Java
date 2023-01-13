/*
 * This file illustrates Flight.java from hw5.
 */
package hw5;

import java.util.Scanner;

/**
 * Creates the main interaction with the other files, userMenu, and the main method
 *
 * @author Krystian Alexander Derhak, kad4374@rit.edu
 * @author Michael Lee, ml3406@rit.edu
 */

public class Flight {

    /**
     * Main user interface with basic error checking and looping
     *
     * @return void
     */

    public void userMenu() {
        boolean userIn1 = true, userIn2 = false;
        //HeapQueue<Passenger> heap = new HeapQueue<>();
        LinkedQueue<Passenger> linked = new LinkedQueue<>();
        System.out.println("bash-3.2$ java Flight");
        Scanner in = new Scanner(System.in);
        while (userIn1) {
            System.out.println("Enter an option");
            System.out.println("1: Add passenger to the boarding queue");
            System.out.println("2: Remove and print the next passenger from the queue with the highest priority");
            System.out.println("3: Quit");
            System.out.print("Your Choice: ");
            String menuOp = in.nextLine();
            switch (menuOp) {
                case "1":
                    System.out.print("Passenger Name: ");
                    String name = in.nextLine();
                    String group = "";
                    System.out.print("Boarding Group: ");
                    do {
                        group = in.nextLine().toUpperCase();
                        if (!group.equals("A") && !group.equals("B") && !group.equals("C")) {
                            System.out.println("Please enter A, B. or C: ");
                            userIn2 = true;
                        } else userIn2 = false;
                    } while (userIn2);
                    int sequence = 0;
                    System.out.print("Sequence: ");
                    do {
                        sequence = Integer.parseInt(in.nextLine());
                        if (sequence < 1 || sequence > 60) {
                            System.out.println("Please enter an integer between 1 to 60: ");
                            userIn2 = true;
                        } else userIn2 = false;
                    } while (userIn2);
                    linked.enqueue(new Passenger(name, group, sequence));
                    break;
                case "2":
                    if (!linked.isEmpty()) {
                        Passenger boarder = linked.dequeue();
                        System.out.println(boarder.toString());
                    } else System.out.println("Queue is empty.");
                    break;
                case "3":
                    System.out.println("bash-3.2$");
                    userIn1 = false;
                    break;
                default:
                    System.out.println("Error with your input, please try again.");
            }
        }
        in.close();
    }

    /**
     * creates a new Flight object and runs main menu method
     *
     * @param args unused in this instance
     *             returns a null
     */
    public static void main(java.lang.String[] args) {
        Flight hw5 = new Flight();
        hw5.userMenu();
    }
}
