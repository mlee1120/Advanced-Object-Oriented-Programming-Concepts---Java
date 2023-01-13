/*
 * This file illustrates CourseList.java from hw2.
 */
package hw2;

import java.io.FileNotFoundException;
import java.io.File;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.lang.String;
import java.lang.Boolean;
import hw2.Section;

/**
 * Represents a list of sections. This class reads all the sections
 * from a data file and storing them in an accessible manner.
 *
 * @author Krystian Alexander Derhak, kad4374@rit.edu
 * @author Michael Lee, ml3406@rit.edu
 */

public class CourseList {
    /**  List of sections*/
    private List<Section> sections = new ArrayList<Section>();

    /**
     * takes the name of a file that holds course info. Section file should be in the form:<br>
     * course_name days start_time end_time<br>
     * where days contains a subset of the letters MTWRF.
     * @param filename Name of data file
     */
    public CourseList (String filename) throws FileNotFoundException {
        File file = new File (filename);
        Scanner sc = new Scanner(file);
        String fileLine = "";
        String[] splitSc = fileLine.split("\\s");
        while (sc.hasNextLine()) {
            fileLine = sc.nextLine();
            splitSc = fileLine.split("\\s");
            List<Boolean> days = new ArrayList<Boolean>();
            if(splitSc[1].equals("MWF")) {
                days.add(true);
                days.add(false);
                days.add(true);
                days.add(false);
                days.add(true);
            }
            else if (splitSc[1].equals("TR")) {
                days.add(false);
                days.add(true);
                days.add(false);
                days.add(true);
                days.add(false);
            }
            else {
                days.add(true);
                days.add(true);
                days.add(true);
                days.add(false);
                days.add(true);
            }
            Section sectionN = new Section(splitSc[0], days, Integer.parseInt(splitSc[2]), Integer.parseInt(splitSc[3]));
            sections.add(sectionN);
        }
    }

    /**
     * Returns the class at the given location in the list.
     * @param cnum class number
     * @return The course
     */
    public Section getClass(int cnum) {
        return sections.get(cnum);
    }

    /**
     * Returns a string with all of the sections listed.
     * @return String representing the course list
     */
    public String toString() {
        String aString = "";
        for (int i = 0; i < sections.size(); i++ ){
            aString += i + ": " + sections.get(i) + "\n";
        }
        return aString;
    }
}
