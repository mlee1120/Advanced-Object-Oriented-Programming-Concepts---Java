/*
 * This is file illustrates OverlapException.java from hw8.
 */
package hw8;


/**
 * a subclass of BattleshipException which will be created and thrown when overlap happens
 *
 * @author Krystian Alexander Derhak, kad4374@rit.edu
 * @author Michael Lee, ml3406@rit.edu
 */
public class OverlapException extends BattleshipException {
    /** an error message for OverlapException */
    public static final String OVERLAP = "Overlap with another ship";

    /**
     * The constructor stores the coordinates of intersection and sets the error message to OVERLAP.
     *
     * @param row    the row number of the bad coordinates
     * @param column the column number of the bad coordinates
     */
    public OverlapException(int row, int column) {
        super(row, column, OVERLAP);
    }
}
