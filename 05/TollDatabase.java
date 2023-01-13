/*
 * This is file illustrates TollDatabase.java from hw6.
 */
package hw6;

import java.util.*;

/**
 * Creates the database of completed trips, incomplete records, and a complete list of all vehicle interactions
 * Implements TollsRUs to ensure the correct output
 *
 * @author Krystian Alexander Derhak, kad4374@rit.edu
 * @author Michael Lee, ml3406@rit.edu
 */

public class TollDatabase implements TollsRUs {

    /** a HashMap to store incomplete trips temporarily. (Keys: vehicles' license tags; Values: TillRecord) */
    Map<String, TollRecord> map = new HashMap<>();
    /** a TreeSet to store completed trips (TollRecord) */
    Set<TollRecord> setCom = new TreeSet<>();
    /** a TreeSet to store incomplete trips (TollRecord) */
    Set<TollRecord> setIncom = new TreeSet<>();

    /**
     * The constructor for the TollDatabase
     * Calls FileHandler to open and read the text file and stores those toll events (each line of the text file)
     * in a string array. After storing one event, checks if the event corresponds to an incomplete trip exists in
     * the HashMap "map" according the vehicle's license tag. If not, adds the toll event into the HashMap "map" as an
     * incomplete trip (TollRecord); If yes, completes the trip (TollRecord), removes it from "map" and adds it to the
     * TreeSet "setCom", which is used to store completed trips (TollRecord).
     * At the end, all incomplete trips (TollRecord) are added to a TreeSet "setIncom".
     */
    public TollDatabase(String filename) {
        for (String line : FileHandler.open(filename)) {
            String[] exp = line.split(",");
            int Time = Integer.parseInt(exp[0]);
            int Exit = Integer.parseInt(exp[2]);
            String tag = exp[1];
            if (map.containsKey(tag)) {
                map.get(tag).CompleteRecord(Time, Exit);
                setCom.add(map.remove(tag));
            } else map.put(tag, new TollRecord(Time, Exit, tag));
        }
        setIncom.addAll(map.values());
    }

    /**
     * Creates a string version of the complete lists
     *
     * @return string of the toll records
     */
    @Override
    public String toString() {
        StringBuilder sbr = new StringBuilder();
        for (TollRecord t : setCom) {
            sbr.append(t);
        }
        return sbr.toString();
    }

    /**
     * Creates a string summary of the completed trips
     *
     * @return string of the number of completed trips
     */
    public String summary() {
        StringBuilder sbr = new StringBuilder();
        sbr.append("\n");
        sbr.append(setCom.size());
        sbr.append(" completed trips\n");
        return sbr.toString();
    }

    /**
     * Creates a string of information of incomplete trips (vehicles on the road)
     *
     * @return string of information of incomplete trips
     */
    public String printOnRoad() {
        StringBuilder sbr = new StringBuilder();
        sbr.append("ON-ROAD REPORT\n");
        sbr.append("==============\n");
        for (TollRecord t : setIncom) {
            sbr.append(t);
            sbr.append("\n");
        }
        return sbr.toString();
    }

    /**
     * Creates a string of detailed information of speeders on the road
     *
     * @return string of detailed speeder report
     */
    public String speederReport() {
        StringBuilder sbr = new StringBuilder();
        sbr.append("\nSPEEDER REPORT\n");
        sbr.append("==============\n");
        for (TollRecord t : setCom) {
            sbr.append(t.speeding());
        }
        return sbr.toString();
    }

    /**
     * Creates a string of billing information
     *
     * @return string of billing information of completed trips
     */
    public String billingInfo() {
        StringBuilder sbr = new StringBuilder();
        sbr.append("\nBILLING INFORMATION\n");
        sbr.append("==============\n");
        double total = 0;
        for (TollRecord t : setCom) {
            sbr.append(t.toString());
            sbr.append(": ");
            sbr.append(String.format(DOLLAR_FORMAT, t.charge()));
            sbr.append("\n");
            total += t.charge();
        }
        sbr.append("Total: ");
        sbr.append(String.format(DOLLAR_FORMAT, total));
        return sbr.toString();
    }
}
