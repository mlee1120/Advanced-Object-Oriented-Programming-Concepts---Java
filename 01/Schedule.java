/*
 * This is file illustrates Schedule.java from hw2.
 */
package hw2;

import java.util.ArrayList;
import java.util.List;
import java.lang.String;

/**
 * Class that holds a number of classes that do not conflict and can print out a simple day-by-day schedule.
 *
 * @author Krystian Alexander Derhak, kad4374@rit.edu
 * @author Michael Lee, ml3406@rit.edu
 */

public class Schedule {
    /**  List of classes.*/
    private List<Section> classes = new ArrayList<Section>();

    /**
     * Constructor - creates/initializes any necessary data structures.
     */
    public Schedule() {}

    /**
     * Tests whether a given Section is currently on the schedule.
     * @param courseName A course (String name) to check for.
     * @return True if the course is on the schedule.
     */
    public boolean contains (String courseName) {
        for(int i = 0; i < classes.size(); i++) {
            if(classes.get(i).getCourse() == courseName)
                return true;
        }
        return false;
    }

    /**
     * Tests whether a given Section is currently on the schedule.
     * @param section A single course to check for.
     * @return True if the course is on the schedule.
     */
    public boolean contains (Section section) {
        for(int i = 0; i < classes.size(); i++){
            if(classes.get(i).equals(section)) return true;
            if(classes.get(i).inConflict(section)) return true;
        }
        return false;
    }

    /**
     * Returns true if the schedule contains no classes
     * @return True if the schedule contains no classes
     */
    public boolean isEmpty () {
        return classes.isEmpty();
    }

    /**
     * Checks if the given course section is in conflict with classes currently on this schedule.
     * @param s Course section to attempt to add
     * @return Whether the course can be successfully added
     */
    public boolean fits(Section s) {
        /*for(int i = 0; i < classes.size(); i++) {
            //equal() first because if equal() is true, then inConflict() must be true
            if(classes.get(i).equals(s)) return false;
            if(classes.get(i).inConflict(s)) return false;
        }*/
        if (contains(s)) return false;
        else if (contains(s.getCourse())) return false;
        else return true;
    }

    /**
     * Adds the given course to the schedule.<br>
     * Precondition: this.fits( s )
     * @param s Section to add
     */
    public void add(Section s) {
        classes.add(s);
        System.out.println("Course section successfully added.\n");
    }

    /**
     * Remove from the schedule the course with the given name
     * @param course The course's name to remove
     * @return True if a course were removed
     */
    public boolean remove(String course) {
        for(int i = 0; i < classes.size(); i++) {
            //ignore case
            if(classes.get(i).getCourse().equalsIgnoreCase(course)) {
                classes.remove(i);
                return true;
            }
        }
        return false;
    }

    /**
     * Simple string representation: "Schedule with n classes" where n is the number of classes on the schedule.
     * @return String as above
     */
    public String toString() {
        return "Schedule with " + classes.size() + " classes";
    }

    /**
     * Displays a day-by-day schedule (using relevant functions from the Section class), on standard output.
     * The format is as follows:<br>
     *  ----Monday----<br>
     *  1-2: WaterPolo<br>
     *  9-11: Calculus<br>
     *  ----Tuesday----<br>
     *  1-2: WaterPolo<br>
     *  ----Wednesday----<br>
     *  1-2: WaterPolo<br>
     *  9-11: Calculus<br>
     *  ----Thursday----<br>
     *  ----Friday----<br>
     *  1-2: WaterPolo<br>
     *  9-11: Calculus<br>
     *<br>
     * Note that sorting the classes on each day is not required.
     */
    public void show() {
        System.out.println("\n-----Monday-----");
        for(int i = 0; i < classes.size(); i++) {
            if(classes.get(i).meetingTime(0) != "That class doesn't meet today") {
                System.out.println(classes.get(i).meetingTime(0));
            }
        }
        System.out.println("-----Tuesday-----");
        for(int i = 0; i < classes.size(); i++) {
            if(classes.get(i).meetingTime(1) != "That class doesn't meet today") {
                System.out.println(classes.get(i).meetingTime(1));
            }
        }
        System.out.println("-----Wednesday-----");
        for(int i = 0; i < classes.size(); i++) {
            if(classes.get(i).meetingTime(2) != "That class doesn't meet today") {
                System.out.println(classes.get(i).meetingTime(2));
            }
        }
        System.out.println("-----Thurdsay-----");
        for(int i = 0; i < classes.size(); i++) {
            if(classes.get(i).meetingTime(3) != "That class doesn't meet today") {
                System.out.println(classes.get(i).meetingTime(3));
            }
        }
        System.out.println("-----Friday-----");
        for(int i = 0; i < classes.size(); i++) {
            if(classes.get(i).meetingTime(4) != "That class doesn't meet today") {
                System.out.println(classes.get(i).meetingTime(4));
            }
        }
        System.out.print("\n");
    }
}
