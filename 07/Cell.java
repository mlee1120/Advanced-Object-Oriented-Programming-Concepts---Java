/*
 * This is file illustrates Cell.java from hw8.
 */
package hw8;

import java.io.Serializable;

/**
 * A single spot on the Battleship game board.
 * A cell knows if there is a ship on it, and it remember
 * if it has been hit.
 *
 * @author Krystian Alexander Derhak, kad4374@rit.edu
 * @author Michael Lee, ml3406@rit.edu
 */
public class Cell implements Serializable {
    /** Character to display for a ship that has been entirely sunk */
    public static final char SUNK_SHIP_SECTION = '*';

    /** Character to display for a ship that has been hit but not sunk */
    public static final char HIT_SHIP_SECTION = '☐';

    /** Character to display for a water cell that has been hit */
    public static final char HIT_WATER = '.';

    /**
     * Character to display for a water cell that has not been hit.
     * This character is also used for an unhit ship segment.
     */
    public static final char PRISTINE_WATER = '_';

    /**
     * Character to display for a ship section that has not been
     * sunk, when revealing the hidden locations of ships
     */
    public static final char HIDDEN_SHIP_SECTION = 'S';

    /** each cell on which a ship is placed has a reference to that ship */
    Ship ship;

    /** stores the state (char) of this cell */
    char state;

    /** stores the row number of this cell */
    private final int row;

    /** stores the column number of this cell */
    private final int column;


    /**
     * Creates a new cell.
     *
     * @param row    the cell's row position
     * @param column the cell's column position
     * @post This cell has not been hit, This cell currently has no ship on it.
     */
    public Cell(int row, int column) {
        this.row = row;
        this.column = column;
        state = PRISTINE_WATER;
    }

    /**
     * Places a ship on this cell. Of course, ships typically cover
     * more than one Cell, so the same ship will usually be passed
     * to more than one Cell's putShip method.
     *
     * @param ship the ship that is to be on this Cell
     * @throws OverlapException if there is already a ship here.
     */
    public void putShip(Ship ship) throws OverlapException {
        if (this.ship != null) throw new OverlapException(row, column);
        this.ship = ship;
    }

    /**
     * Simulates hitting this water cell. If there is a ship here, it will also be hit. Calling this method changes the
     * status of the cell, as reflected by displayChar() and displayHitStatus().
     *
     * @throws CellPlayedException if this cell had already been hit
     */
    public void hit() throws CellPlayedException {
        if (state == HIT_WATER || state == HIT_SHIP_SECTION || state == SUNK_SHIP_SECTION)
            throw new CellPlayedException(row, column);
        if (ship != null) {
            state = HIT_SHIP_SECTION;
            ship.hit();
            if (ship.isSunk()) state = SUNK_SHIP_SECTION;
        } else state = HIT_WATER;
    }

    /**
     * Returns a character representing the state of this Cell but without revealing unhit portions of ships. Unhit
     * portions of ships appear as PRISTINE_WATER.
     *
     * @return one of the characters declared as a constant static field in this class, according to the state of the
     * cell and the state of the ship upon it, if any.
     */
    public char displayHitStatus() {
        // makes sure all sunk ships' cells become '*' rather than '☐'
        if (ship != null && ship.isSunk()) state = SUNK_SHIP_SECTION;
        return state;
    }

    /**
     * Returns a character representing the state of this Cell. This display method reveals all.
     *
     * @return one of the characters declared as a constant static field in this class, according to the state of the
     * cell and the state of the ship upon it, if any.
     */
    public char displayChar() {
        if (state == PRISTINE_WATER && ship != null) return HIDDEN_SHIP_SECTION;
        else return state;
    }
}
