/*
 * This is file illustrates Board.java from hw8.
 */
package hw8;


import java.io.PrintStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * The class to represent the grid of cells (squares).
 * A collection of ships is also kept so the Board can be asked if the game is over.
 * The class is Serializable so that its instance can be saved to a file in binary form using an
 * {@link java.io.ObjectOutputStream} and restored with an {@link java.io.ObjectInputStream}.
 * Because the object holds references to all other objects in the system, no other objects need to be separately saved.
 *
 * @author Krystian Alexander Derhak, kad4374@rit.edu
 * @author Michael Lee, ml3406@rit.edu
 */
public class Board implements Serializable {
    /** stores the number of cells down */
    private final int height;

    /** stores the number of cells across */
    private final int width;

    /** a collection of ships */
    List<Ship> ships = new ArrayList<>();

    /** the grid of cells */
    List<List<Cell>> cells = new ArrayList<>();

    /** determines if a deserialized object has been serialized with the same version of the class. */
    private static final long serialVersionUID = 605L;

    /**
     * Constructs a board.
     *
     * @param height number of cells down
     * @param width  number of cells across
     */
    public Board(int height, int width) {
        this.height = height;
        this.width = width;
        for (int i = 0; i < height; i++) {
            cells.add(new ArrayList<>());
            for (int j = 0; j < width; j++) {
                cells.get(i).add(new Cell(i, j));
            }
        }
    }

    /**
     * Used for input error checking.
     *
     * @return the number of rows in the board
     */
    public int getHeight() {
        return height;
    }

    /**
     * Used for input error checking.
     *
     * @return the number of columns in the board
     */
    public int getWidth() {
        return width;
    }

    /**
     * Fetches the Cell object at the given location.
     *
     * @param row    row number (0-based)
     * @param column column number (0-based)
     * @return the Cell created for this position on the board
     * @throws OutOfBoundsException if either coordinate is negative or too high
     */
    public Cell getCell(int row, int column) throws OutOfBoundsException {
        if (row < 0 || row >= height || column < 0 || column >= width) throw new OutOfBoundsException(row, column);
        return cells.get(row).get(column);
    }

    /**
     * Useful for debugging. This is not the method that displays the board to the user.
     *
     * @return a one-line (hopefully) description of the board
     */
    @Override
    public String toString() {
        return "This is a " + height + "x" + width + " board with " +
                ships.size() + " ships. All ship sunk? " + this.allSunk();
    }

    /**
     * Displays the board in character form to the user. Cells' display characters are described in Cell. Output is
     * double-spaced in both dimensions. The numbers of the columns appear above the first row, and the numbers of
     * each row appears to the left of the row.
     *
     * @param out the output stream to which the display should be sent
     */
    public void display(PrintStream out) {
        PrintStream initial = System.out;
        System.setOut(out);
        System.out.print("  ");
        for (int i = 0; i < width; i++) {
            System.out.print(" " + i);
        }
        System.out.println();
        for (int j = 0; j < height; j++) {
            System.out.print(" " + j);
            for (int k = 0; k < width; k++) {
                System.out.print(" " + cells.get(j).get(k).displayHitStatus());
            }
            System.out.println();
        }
        System.out.println();
        // set back to default
        System.setOut(initial);
    }

    /**
     * This is the "cheating" form of the display because the user can see where the unsunk parts of ships are. Cells'
     * display characters are described in Cell. Output is double-spaced in both dimensions. The numbers of the columns
     * appear above the first row, and the numbers of each row appears to the left of the row.
     *
     * @param out the output stream to which the display should be sent
     */
    public void fullDisplay(PrintStream out) {
        PrintStream initial = System.out;
        System.setOut(out);
        System.out.print("\n  ");
        for (int i = 0; i < width; i++) {
            System.out.print(" " + i);
        }
        System.out.println();
        for (int j = 0; j < height; j++) {
            System.out.print(" " + j);
            for (int k = 0; k < width; k++) {
                if (cells.get(j).get(k).ship != null) {
                    if (!cells.get(j).get(k).ship.isSunk() && cells.get(j).get(k).state != Cell.HIT_SHIP_SECTION)
                        System.out.print(" " + cells.get(j).get(k).displayChar());
                    else System.out.print(" " + cells.get(j).get(k).displayHitStatus());
                } else System.out.print(" " + cells.get(j).get(k).displayHitStatus());
            }
            System.out.println();
        }
        System.out.println();
        // set back to default
        System.setOut(initial);
    }

    /**
     * Addd a ship to the board. The only current reason that the
     * board needs direct access to the ships is to poll them
     * to see if they are all sunk and the game is over.
     *
     * @param ship the as-yet un-added ship
     * @rit.pre This ship has already informed the Cells of the board
     * that are involved.
     * @see Cell#putShip(Ship)
     */
    public void addShip(Ship ship) {
        if (!ships.contains(ship)) ships.add(ship);
    }

    /**
     * Is the game over?
     *
     * @return true iff all ships report being sunk
     */
    public boolean allSunk() {
        for (Ship ship : ships) {
            if (!ship.isSunk()) return false;
        }
        return true;
    }

}
