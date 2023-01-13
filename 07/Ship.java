/*
 * This is file illustrates Ship.java from hw8.
 */
package hw8;

import java.io.Serializable;

/**
 * A single ship in a Battleship game
 *
 * @author Krystian Alexander Derhak, kad4374@rit.edu
 * @author Michael Lee, ml3406@rit.edu
 */
public class Ship implements Serializable {
    /** a message used when a ship is sunk */
    public static final String SUNK_MESSAGE = "A battleship has been sunk!";

    /** stores the length (cells) of this ship */
    private final int length;

    /** the number of time this ship has been hit */
    private int hit = 0;

    /**
     * Orientation is a property of a ship.
     * The names of the enum values were chosen because they
     * are descriptive and match the words put in the configuration
     * files.
     *
     * @see Orientation#valueOf(String)
     */
    public enum Orientation {
        HORIZONTAL(0, 1), VERTICAL(1, 0);

        /**
         * Uses it to loop through all of the cell locations a ship
         * is on, based on the upper left end of the ship.
         */
        public int rDelta, cDelta;

        /**
         * Associate a direction vector with the orientation.
         *
         * @param rDelta how much the row number changes in this direction
         * @param cDelta how much the column number changes in this direction
         */
        Orientation(int rDelta, int cDelta) {
            this.rDelta = rDelta;
            this.cDelta = cDelta;
        }
    }

    /**
     * Initializes this new ship's state. Tell the Board object
     * and each involved Cell object about the existence of this
     * ship by trying to put the ship at each applicable Cell.
     *
     * @param board  holds a collection of ships
     * @param uRow   the uppermost row that the ship is on
     * @param lCol   the leftmost column that the ship is on
     * @param ort    the ship's orientation
     * @param length how many cells the ship is on
     * @throws OverlapException     if this ship would overlap another one
     *                              that already exists
     * @throws OutOfBoundsException if this ship would extend beyond
     *                              the board
     */
    public Ship(Board board, int uRow, int lCol, Ship.Orientation ort, int length)
            throws OverlapException, OutOfBoundsException {
        if (uRow < 0 || uRow >= board.getHeight() || lCol < 0 || lCol >= board.getWidth())
            throw new OutOfBoundsException(uRow, lCol);
        for (int i = 0; i < length; i++) {
            board.getCell((uRow + i * ort.rDelta), (lCol + i * ort.cDelta)).putShip(this);
        }
        this.length = length;
        board.ships.add(this);
    }

    /**
     * A Cell object has been hit and tells this ship that is sitting on it that the cell has been hit. If this ship
     * has been hit as many times as it is long, the SUNK_MESSAGE is displayed.
     *
     * @pre !this.isSunk()
     */
    public void hit() {
        hit += 1;
        if (hit == length) System.out.println(SUNK_MESSAGE);
    }

    /**
     * Is this ship already sunk?
     *
     * @return true iff the number of calls to hit() is the same as the ship is long
     */
    public boolean isSunk() {
        return hit == length;
    }
}
