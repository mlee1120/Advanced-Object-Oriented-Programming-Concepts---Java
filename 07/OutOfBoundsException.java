/*
 * This is file illustrates OutOfBoundsException.java from hw8.
 */
package hw8;


/**
 * a subclass of BattleshipException
 *
 * @author Krystian Alexander Derhak, kad4374@rit.edu
 * @author Michael Lee, ml3406@rit.edu
 */
public class OutOfBoundsException extends BattleshipException {
    /** an error message for OutOfBoundsException which will be created and thrown when out-of-bounds happens */
    public static final String PAST_EDGE = "Coordinates are past board edge";

    /**
     * The constructor stores the illegal coordinates where the violation occurred and sets
     * the error message to PAST_EDGE.
     *
     * @param row    the row number of the bad coordinates
     * @param column the column number of the bad coordinates
     */
    public OutOfBoundsException(int row, int column) {
        super(row, column, PAST_EDGE);
    }
}
