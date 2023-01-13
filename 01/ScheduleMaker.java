/*
 * This is file illustrates ScheduleMaker.java from hw2.
 */
package hw2;

import java.util.Scanner;
import hw2.CourseList;
import hw2.Schedule;

/**
 * Interactive schedule maker. Loads a list of courses and
 * gives the user the option to add them to their schedule
 * if they do not create a conflict, and to print their schedule.
 *
 * @author Krystian Alexander Derhak, kad4374@rit.edu
 * @author Michael Lee, ml3406@rit.edu
 */

public class ScheduleMaker {
    /**  Option menu's code for adding a new class*/
    private static final int ADD_CLASS = 1;
    /**  Option menu's code for deleting a class from the schedule*/
    private static final int REMOVE_CLASS = 2;
    /**  Option menu's code for displaying the user's schedule*/
    private static final int SHOW_SCHED = 3;
    /**  Option menu's code for quitting the program*/
    private static final int QUIT = 4;
    /**  Error message for invalid option*/
    private static final String INVALID_OPTION = "Not a valid option, please try again.";
    /**  Inventory list of available courses*/
    private CourseList courseList;
    /**  User's schedule*/
    private Schedule schedule;
    /**  Input processor for user inquiries*/
    private Scanner in;

    /**
     * Schedule Maker constructor that initializes all its instance fields
     * @param filename the name of the file containing the courses information
     */
    public ScheduleMaker(String filename) {
        try {courseList = new CourseList(filename);}
        catch(Exception ex) {}
        schedule = new Schedule();
        in = new Scanner(System.in);
    }

    /**
     * Display to the user the option to add a new section to their schedule if they do not create a conflict
     */
    private void addClass() {
        System.out.println("\nHere are the courses and meeting times:");
        System.out.println(courseList);
        System.out.print("What class would you like to add? ");
        int userIn = in.nextInt();
        if (schedule.isEmpty()) schedule.add(courseList.getClass(userIn));
        else if(schedule.fits(courseList.getClass(userIn))) schedule.add(courseList.getClass(userIn));
    }

    /**
     * Display to the user the option to remove a course from their schedule
     */
    private void removeClass() {
        System.out.println("\nThis is your current schedule:");
        schedule.show();
        System.out.print("What class would you like to remove (enter the course's name)? ");
        String userIn = in.next();
        if(schedule.remove(userIn)) System.out.println("Course successfully deleted.\n");
        else System.out.println("There is no course with that name in your schedule.\n");
    }

    /**
     * Display the program's menu.
     * Option ADD_CLASS allows users to add a new section into their schedule if there is no conflict<br>
     * <br>
     * Option REMOVE_CLASS allows users to remove a course from their schedule<br>
     * <br>
     * Option SHOW_SCHED displays the current user's schedule<br>
     * <br>
     * Option QUIT closes the program nicely.
     */
    public void printMenu() {
        boolean UserIn = true;
        System.out.println("$ java ScheduleMaker\n");
        System.out.println("Usage: java ScheduleMaker course-list-file\n");
        System.out.println("$ java ScheduleMaker courses.txt\n");
        System.out.println("Welcome to Schedule Maker!\n");
        while (UserIn) {
            System.out.println("Choices are");
            System.out.println("1. Find a class to add to your schedule ");
            System.out.println("2. Remove a class from your schedule");
            System.out.println("3. Print your schedule ");
            System.out.println("4. Quit");
            System.out.print("What would you like to do? ");
            int choice = in.nextInt();
            switch(choice) {
                case ADD_CLASS:
                    addClass();
                    break;
                case REMOVE_CLASS:
                    removeClass();
                    break;
                case SHOW_SCHED:
                    schedule.show();
                    break;
                case QUIT:
                    System.out.println("\nThanks for using Schedule Maker.");
                    UserIn = false;
                    break;
                default:
                    System.out.println(INVALID_OPTION + "\n");
                    break;
            }
        }
        in.close();
    }

    /**
     * Main method.
     * @param args
     */
    public static void main( String[] args ) {
        ScheduleMaker hw2 = new ScheduleMaker(args[0]);
        hw2.printMenu();
    }
}
