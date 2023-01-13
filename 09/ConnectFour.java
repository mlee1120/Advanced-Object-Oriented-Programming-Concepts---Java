/*
 * This is file illustrates ConnectFour.java from hw10.
 */
package hw10;

/**
 * A basic implementation of the Connect Four game.
 *
 * @author Krystian Alexander Derhak, kad4374@rit.edu
 * @author Michael Lee, ml3406@rit.edu
 */
public class ConnectFour {
    /**
     * the number of rows
     */
    public final static int ROWS = 6;
    /**
     * the number of columns
     */
    public final static int COLS = 7;
    /**
     * how big a line one needs to win
     */
    public final static int WIN_LEN = 4;


    /**
     * Used to indicate a move that has been made on the board.
     */
    public enum Move {
        PLAYER_ONE('X'),
        PLAYER_TWO('O'),
        NONE('.');

        private char symbol;

        private Move(char symbol) {
            this.symbol = symbol;
        }

        public char getSymbol() {
            return symbol;
        }
    }

    /**
     * The number of rows in the board.
     */
    private final int rows;

    /**
     * The number of columns in the board.
     */
    private final int cols;

    /**
     * The board.
     */
    private final Move[][] board;

    /**
     * Used to keep track of which player's turn it is; 0 for player 1, and 1
     * for player 2.
     */
    private int turn;

    /**
     * The last column a piece was placed.  Used for win checking.
     */
    private int lastCol;

    /**
     * The row the last piece was placed.  Used for win checking.
     */
    private int lastRow;

    /**
     * The total number of cells on the board;
     */
    private final int maxMove;

    /**
     * The number of valid moves made so far.
     */
    private int currentMove;


    /**
     * Creates a Connect Four game using a board with the standard number of
     * rows (6) and columns (7).
     */
    public ConnectFour() {
        this(ROWS, COLS);
    }

    /**
     * Creates a Connect Four game using a board with the specified number of
     * rows and columns. Assumes that player 1 is the first to move.
     *
     * @param rows The number of rows in the board.
     * @param cols The number of columns in the board.
     */
    public ConnectFour(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        board = new Move[cols][rows];
        for (int col = 0; col < cols; col++) {
            for (int row = 0; row < rows; row++) {
                board[col][row] = Move.NONE;
            }
        }
        turn = 0;
        maxMove = rows * cols;
        currentMove = 0;
    }

    /**
     * Makes a move for the player whose turn it is. If the move is successful,
     * play automatically switches to the other player's turn.
     *
     * @param column The column in which the player is moving.
     * @throws ConnectFourException If the move is invalid for any reason.
     */
    public void makeMove(int column) throws ConnectFourException {
        if (cols > column && column >= 0) {
            if (board[column][rows - 1] != Move.NONE) throw new ConnectFourException("That column is already full.");
            for (int r = 0; r < rows; r++) {
                if (board[column][r] == Move.NONE) {
                    lastRow = r;
                    if (turn == 0) board[column][r] = Move.PLAYER_ONE;
                    else board[column][r] = Move.PLAYER_TWO;
                    break;
                }
            }
            lastCol = column;
            currentMove += 1;
            turn = (turn == 0 ? 1 : 0);
        } else throw new ConnectFourException("Invalid column number.");
    }

    /**
     * Look over the entire board for any N-in-a-row situations.
     * (By N we mean {@link #WIN_LEN}.)
     *
     * @return true if one of the players has an N-in-a-row situation.
     */
    public boolean hasWonGame() {
        // checks vertically
        int total = 1;
        if (lastRow >= 3) {
            for (int i = 1; i <= 3; i++) {
                if (board[lastCol][lastRow - i] != board[lastCol][lastRow]) break;
                else total += 1;
            }
        }
        if (total >= WIN_LEN) return true;

        // checks horizontally
        total = 1;
        // to the left
        int currentCol = lastCol - 1;
        while (currentCol >= 0) {
            if (board[currentCol][lastRow] != board[lastCol][lastRow]) break;
            else total += 1;
            currentCol -= 1;
        }
        // to the right
        currentCol = lastCol + 1;
        while (currentCol < cols) {
            if (board[currentCol][lastRow] != board[lastCol][lastRow]) break;
            else total += 1;
            currentCol += 1;
        }
        if (total >= WIN_LEN) return true;

        // checks in "\" direction
        total = 1;
        // to upper left
        currentCol = lastCol - 1;
        int currentRow = lastRow + 1;
        while (currentCol >= 0 && currentRow < rows) {
            if (board[currentCol][currentRow] != board[lastCol][lastRow]) break;
            else total += 1;
            currentCol -= 1;
            currentRow += 1;
        }
        // to lower right
        currentCol = lastCol + 1;
        currentRow = lastRow - 1;
        while (currentCol < cols && currentRow >= 0) {
            if (board[currentCol][currentRow] != board[lastCol][lastRow]) break;
            else total += 1;
            currentCol += 1;
            currentRow -= 1;
        }
        if (total >= WIN_LEN) return true;

        // checks in "/" direction
        total = 1;
        // to upper right
        currentCol = lastCol + 1;
        currentRow = lastRow + 1;
        while (currentCol < cols && currentRow < rows) {
            if (board[currentCol][currentRow] != board[lastCol][lastRow]) break;
            else total += 1;
            currentCol += 1;
            currentRow += 1;
        }
        // to lower right
        currentCol = lastCol - 1;
        currentRow = lastRow - 1;
        while (currentCol >= 0 && currentRow >= 0) {
            if (board[currentCol][currentRow] != board[lastCol][lastRow]) break;
            else total += 1;
            currentCol -= 1;
            currentRow -= 1;
        }
        return total >= WIN_LEN;
    }

    /**
     * Checks to see if the game is tied - no NONE moves left in board.  This
     * is called after hasGameWon.
     *
     * @return whether game is tied or not
     */
    public boolean hasTiedGame() {
        return currentMove == maxMove;
    }

    /**
     * Returns a {@link String} representation of the board, suitable for
     * printing.
     *
     * @return A {@link String} representation of the board.
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        for (int r = rows - 1; r > -1; r--) {
            for (int c = 0; c < cols; c++) {
                builder.append('[');
                builder.append(board[c][r].getSymbol());
                builder.append(']');
            }
            builder.append('\n');
        }
        return builder.toString();
    }
}
