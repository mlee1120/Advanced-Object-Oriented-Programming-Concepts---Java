/*
 * This is file illustrates Section.java from hw2.
 */
package hw2;

import java.util.List;
import java.util.ArrayList;
import java.lang.String;
import java.lang.Boolean;
import java.lang.Object;

/**
 * Represents a single course, including name, days and time scheduled. Assumes that all courses start and end on the hour.
 *
 * @author Krystian Alexander Derhak, kad4374@rit.edu
 * @author Michael Lee, ml3406@rit.edu
 */

public class Section {
    /**  a String containing all possible days as characters, in order ("MTWRF")*/
    public static final String dayString = "MTWRF";
    /**  Name of the course*/
    private String name;
    /**  List of fixed-size 5 that represents the meeting days.*/
    private List<Boolean> meetDays = new ArrayList<Boolean>();
    /**  start time*/
    private int startTime;
    /**  end time*/
    private int endTime;

    /**
     * @param name Name of the course
     * @param days List of days that the course is held on
     * @param start Starting time (in hours, 24-hour clock)
     * @param end Ending time (in hours, 24-hour clock)
     */
    public Section(String name, List<Boolean> days, int start, int end) {
        //shadowing
        this.name = name;
        meetDays = days;
        startTime = start;
        endTime = end;
    }

    /**
     * Test for equality.
     * @param other Object to be tested against
     * @return True iff the object passed in is a Section with the same name, days, start and end time as this Section
     */
    public boolean equals(Section other) {
        if(this.name.equals(other.name)){
            if(this.meetDays != other.meetDays) System.out.println("Another section of that course is already on your schedule.\n");
            else System.out.println("That section is already on your schedule.\n");
            return true;
        }
        return false;
    }

    /**
     * Return the course of which this object is a section.
     * @return the course's name as a String
     * @see Section(String, List, int, int)
     */
    public String getCourse() {
        return name;
    }

    /**
     * Test for scheduling conflict.
     * @param other Section to test against
     * @return True if the passed in Section overlaps in time (on any day) with this Section
     */
    public boolean inConflict(Section other) {
        for(int i = 0; i < meetDays.size(); i++) {
            if(this.meetDays.get(i).equals(other.meetDays.get(i))) {
                if ((this.startTime <= other.startTime) && (this.endTime > other.startTime)) {
                    System.out.println("Time for this course conflicts with others on your schedule.\n");
                    return true;
                }
                else if ((this.startTime >= other.startTime) && (this.startTime < other.endTime)) {
                    System.out.println("Time for this course conflicts with others on your schedule.\n");
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Returns a string representing the time this course meets on the given day, if any.
     * If it does meet, the String should be in the form "start-end: Name" (for example: "8-10: Calculus")
     * @param day Day of the week, where 0 = Monday ... 4 = Friday
     * @return String as described above, or the empty String if the course does not meet on the given day.
     */
    public String meetingTime(int day) {
        if(meetDays.get(day)) return startTime + "-" + endTime + ": " + name;
        else return "That class doesn't meet today";
    }

    /**
     * String representation of the course, in the form "Name: days at start-end" (for example: "Calculus: MWF at 8-10")
     * @return String representation
     */
    public String toString() {
        String aString = "";
        for(int i = 0; i < 5; i++){
            if(meetDays.get(i)) aString += dayString.charAt(i);
        }
        return name + ": " + aString + " at " + startTime + "-" + endTime;
    }
}
