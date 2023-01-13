/*
 * This is file illustrates TollReocrd.java from hw6.
 */
package hw6;

/**
 * Class that creates a toll record to include license, times, exits, and all necessary methods for interacting with
 * the necessary data
 *
 * @author Krystian Alexander Derhak, kad4374@rit.edu
 * @author Michael Lee, ml3406@rit.edu
 */

public class TollRecord implements Comparable<TollRecord>, TollsRUs {

    /**  the time of arrival */
    private final int aTime;
    /**  the exit number of arrival */
    private final int aExit;
    /**  the license tag of the vehicle of this TollRecord */
    private final String tag;
    /**  the time of departure */
    private int eTime;
    /**  the exit number of departure */
    private int eExit;
    /**  state of this TollRecord: the vehicle is still on the road if false */
    private boolean complete;

    /**
     * Constructor for the TollRecord
     */
    public TollRecord(int aTime, int aExit, String tag) {
        this.aTime = aTime;
        this.aExit = aExit;
        this.tag = tag;
        this.eExit = UNINITIALIZED;
        this.eTime = 0;
        this.complete = false;
    }

    /**
     * Completes the TollRecord if the vehicle exit the highway
     *
     * @param eTime int of the time of departure
     * @param eExit int of exit number of departure
     */
    public void CompleteRecord(int eTime, int eExit) {
        this.eExit = eExit;
        this.eTime = eTime;
        this.complete = true;
    }

    /**
     * Getter for arrival time
     *
     * @return int of the time of arrival
     */
    public int getaTime() {
        return aTime;
    }

    /**
     * Getter for the exit number of arrival
     *
     * @return int of the exit number of arrival
     */
    public int getaExit() {
        return aExit;
    }

    /**
     * Getter for the exit number of departure
     *
     * @return int of the exit number of departure
     */
    public int geteExit() {
        return eExit;
    }

    /**
     * Getter for license tag of the vehicle
     *
     * @return string of the license tag
     */
    public String getTag() {
        return tag;
    }

    /**
     * Getter for departure time
     *
     * @return int of the time of departure
     */
    public int geteTime() {
        return eTime;
    }

    /**
     * Getter for the state of the TollRecord
     *
     * @return boolean value of the state of the trip (complete or not)
     */
    public boolean getComplete() {
        return complete;
    }

    /**
     * Getter for charge of 1 complete trip
     *
     * @return double of the amount of toll for the trip
     */
    public double charge() {
        assert (eExit != UNINITIALIZED);
        return ExitInfo.getToll(aExit, eExit);
    }

    /**
     * Produces a string representation of either a complete or incomplete trip (TollRecord)
     *
     * @return string of the toll record
     */
    public String toString() {
        StringBuilder sbr = new StringBuilder();
        if (eExit == UNINITIALIZED) sbr.append(String.format(INCOMPLE_TOLL_RECORD_FORMAT, tag, aExit, aTime));
        else sbr.append(String.format(COMPLETE_TOLL_RECORD_FORMAT, tag, aExit, aTime, eExit, eTime));
        return sbr.toString();
    }

    /**
     * Overrides the equal testing if two Toll records are the same
     *
     * @param other object representation of the toll record that will be compared
     * @return boolean result if they are the same
     */
    @Override
    public boolean equals(Object other) {
        boolean result;
        TollRecord t = (TollRecord) other;
        result = this.aExit == t.aExit && this.aTime == t.aTime && this.tag.equals(t.tag);
        return result;
    }

    /**
     * Produces a new hashcode for every record
     *
     * @return int hashcode unique to each record
     */
    @Override
    public int hashCode() {
        return this.aExit + this.aTime + this.tag.hashCode();
    }

    /**
     * Allows comparison to other toll records allowing for a natural order
     *
     * @param o Toll record to be compared to for ordering
     * @return int indicating if a given record is greater/less/or equal to object in question
     */
    @Override
    public int compareTo(TollRecord o) {
        if (this.tag.compareTo(o.tag) > 0) return 1;
        else if (this.tag.compareTo(o.tag) < 0) return -1;
        else {
            if (this.aTime > o.aTime) return 1;
            else if (this.aTime < o.aTime) return -1;
            else return 0;
        }
    }

    /**
     * Method determining the if a vehicle was speeding and by how much
     *
     * @return string of speeding information
     */
    public String speeding() {
        assert (eExit != UNINITIALIZED);
        double speed = Math.abs(ExitInfo.getMileMarker(aExit) - ExitInfo.getMileMarker(eExit)) / ((eTime - aTime) / MINUTES_PER_HOUR);
        StringBuilder sbr = new StringBuilder();
        if (speed > SPEED_LIMIT) {
            sbr.append("Vehicle ");
            sbr.append(tag);
            sbr.append(", starting at time ");
            sbr.append(aTime);
            sbr.append("\n");
            sbr.append("       from ");
            sbr.append(ExitInfo.getName(aExit));
            sbr.append("\n       to ");
            sbr.append(ExitInfo.getName(eExit));
            sbr.append("\n       ");
            sbr.append(String.format(SPEED_FORMAT, speed));
            sbr.append("\n");
        }
        return sbr.toString();
    }
}
