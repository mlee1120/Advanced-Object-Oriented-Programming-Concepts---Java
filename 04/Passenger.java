/*
 * This file illustrates Passenger.java from hw5.
 */
package hw5;

/**
 * Passenger class that is comparable and holds information of name, group, and sequence.
 *
 * @author Krystian Alexander Derhak, kad4374@rit.edu
 * @author Michael Lee, ml3406@rit.edu
 */

public class Passenger implements Comparable<Passenger> {
    private String name;
    private String group;
    private int sequence;

    /**
     * The constructor for the Passenger
     */
    public Passenger(String name, String group, int sequence) {
        this.name = name;
        this.group = group;
        this.sequence = sequence;
    }


    /**
     * Getter for the name
     *
     * @return String of name
     */
    public String getName() {
        return name;
    }

    /**
     * Getter for the group
     *
     * @return String of group
     */
    public String getGroup() {
        return group;
    }

    /**
     * Getter for the sequence
     *
     * @return int sequence
     */
    public int getSequence() {
        return sequence;
    }

    /**
     * Getter for string representation of the object
     *
     * @return String of name, group, and sequence.
     */
    public String toString() {
        StringBuilder sbr = new StringBuilder();
        sbr.append("Removing : ");
        sbr.append(name);
        sbr.append(" in seat ");
        sbr.append(group);
        sbr.append(sequence);
        return sbr.toString();
    }

    /**
     * CompareTo establishes natural order of how to order passengers, implemented from comparable interface
     *
     * @param o
     * @return int 1 if current passenger is greater, -1 if less than, otherwise 0 for the same passenger.
     */
    @Override
    public int compareTo(Passenger o) {
        if (this.group.compareTo(o.group) < 0) return 1;
        else if (this.group.compareTo(o.group) > 0) return -1;
        else if (this.sequence < o.sequence) return 1;
        else if (this.sequence > o.sequence) return -1;
        else return 0;
    }

    /**
     * Indicates whether some other object is "equal to" this one.
     * (overrides public method equals from class Object)
     *
     * @param other Object to be compared to
     * @return true if the passenger is the same; otherwise returns false
     */
    @Override
    public boolean equals(Object other) {
        boolean result = false;
        if (other instanceof Passenger) {
            Passenger p = (Passenger) other;
            result = this.group.equals(p.group) && this.sequence == p.sequence;
        }
        return result;
    }

    /**
     * Returns a hash code value for the object.
     *
     * @return a hash code value (int) which is the summation of hash code values of group and sequence
     */
    @Override
    public int hashCode() {
        return this.group.hashCode() + this.sequence;
    }
}
