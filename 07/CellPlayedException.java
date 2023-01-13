/*
 * This is file illustrates CellPlayedException.java from hw8.
 */
package hw8;

/**
 * a subclass of BattleshipException which will be created and thrown when a cell is already played
 *
 * @author Krystian Alexander Derhak, kad4374@rit.edu
 * @author Michael Lee, ml3406@rit.edu
 */
public class CellPlayedException extends BattleshipException {
    /** an error message for CellPlayedException */
    public static final String ALREADY_HIT = "This cell has already been hit";

    /**
     * The constructor stores the coordinates where the violation occurred and sets the error message to ALREADY_HIT.
     *
     * @param row    the row number of the cell's coordinates
     * @param column the column number of the cell's coordinates
     */
    public CellPlayedException(int row, int column) {
        super(row, column, ALREADY_HIT);
    }
}
