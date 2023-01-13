/*
 * This is file illustrates BattleshipException.java from hw8.
 */
package hw8;


/**
 * a class of a custom checked exception
 *
 * @author Krystian Alexander Derhak, kad4374@rit.edu
 * @author Michael Lee, ml3406@rit.edu
 */
public class BattleshipException extends Exception {
    /** an unset value for row and column */
    public static final int UNSET = -1;

    /** stores the row number of the cell where a subclass of BattleshipException occurs */
    public int row = UNSET;

    /** stores the column number of the cell where a subclass of BattleshipException occurs */
    public int column = UNSET;

    /**
     * The constructor stores the illegal coordinates where the violation occurred, sets the error message, and pass
     * the message up to its superclass.
     *
     * @param row     the row number of the cell where a subclass of BattleshipException occurs
     * @param column  the column number of the cell where a subclass of BattleshipException occurs
     * @param message the message about the exception from a subclass of BattleshipException
     */
    public BattleshipException(int row, int column, String message) {
        super(message + ", row=" + row + ", column=" + column);
        this.row = row;
        this.column = column;
    }

    /**
     * A second (overloading) constructor that only takes a message string and pass it up to its superclass.
     *
     * @param msg the string to be passed to the superclass of this BattleshipException
     */
    public BattleshipException(String msg) {
        super(msg);
    }
}
