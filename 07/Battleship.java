/*
 * This is file illustrates Battleship.java from hw8.
 */
package hw8;

import java.io.*;

/**
 * The top level class of this homework. It reads files (new games or saved games), creates board with cells and ships,
 * and prompts for user's inputs to play game, save game, or quit game.
 *
 * @author Krystian Alexander Derhak, kad4374@rit.edu
 * @author Michael Lee, ml3406@rit.edu
 */

public class Battleship {
    /** At end of game */
    public static final String ALL_SHIPS_SUNK = "All ships sunk!";

    /** For player commands */
    public static final String BAD_ARG_COUNT = "Wrong number of arguments for command ";

    /** For when program is started without an argument */
    public static final String MISSING_SETUP_FILE = "Usage: Battleship filename";

    /** We don't allow boards larger than this value, height or width. */
    public static final int MAX_DIM = 10;

    /** For when the config file has too large a dimension in it */
    public static final String DIM_TOO_BIG = "Boards should be larger than 1x1 without exceeding "
            + MAX_DIM + "x" + MAX_DIM + ".";

    /** When anything goes wrong while reading the text config file */
    public static final String BAD_CONFIG_FILE = "Bad configuration";

    /** What to display when the program is ready for a user command */
    public static final String PROMPT = "> ";

    /** The regular expression to use with String.split(String) */
    public static final String WHITESPACE = " ";

    /** the board for playing the game */
    private static Board board;

    /** the file path name of the file to be read in */
    private final String filename;


    /**
     * The constructor acquires and stores the file path name of the file to be read in
     *
     * @param filename the file path name of the file to be read in
     */
    public Battleship(String filename) {
        this.filename = filename;
    }

    /**
     * This function reads the file as a binary file using an ObjectInputStream (reading it as a saved file),
     * deserializes the object, and stores it to board. If it fails (an exception thrown), catches the exception
     * and calls readFile() to try reading the file as a text file.
     */
    public void deserialize() {
        System.out.print("Checking if " + filename + " is a saved game file... ");
        // try reading as a binary file (a saved game)
        try (ObjectInputStream reader = new ObjectInputStream(new FileInputStream(filename))) {
            // deserialization (might throw ClassNotFoundException)
            board = (Board) reader.readObject();
            System.out.println("yes\n");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("no; will read as a text setup file.\n");
            this.readFile();
        }
    }

    /**
     * This function reads the file with a BufferedReader(a new game as configuration file), and creates a new board
     * with ships and cells according to the configuration file. If it also fails, throws an exception which will be
     * caught to print an error message and terminates the program.
     */
    public void readFile() {
        // try reading as a text file (a configuration file)
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            // first line provides height and width for the board
            String in1 = reader.readLine();
            String[] in2 = in1.split(WHITESPACE);
            int height = Integer.parseInt(in2[0]);
            int width = Integer.parseInt(in2[1]);
            // checks limitations of height and width
            if (height > MAX_DIM || width > MAX_DIM || height <= 0 || width <= 0)
                throw new BattleshipException(DIM_TOO_BIG);
            // creates a new board
            board = new Board(height, width);
            // the remaining lines provide all ships' information
            while ((in1 = reader.readLine()) != null) {
                in2 = in1.split(WHITESPACE);
                int uppermost = Integer.parseInt(in2[0]);
                int leftmost = Integer.parseInt(in2[1]);
                int length = Integer.parseInt(in2[3]);
                // checks if this ship's upper and left most position is out of bound and if its length is valid
                if (uppermost >= height || leftmost >= width || uppermost < 0 || leftmost < 0 || length < 1)
                    throw new BattleshipException(BAD_CONFIG_FILE);
                if (in2[2].toUpperCase().equals("HORIZONTAL")) {
                    board.addShip(new Ship(board, uppermost, leftmost, Ship.Orientation.HORIZONTAL, length));
                } else if (in2[2].toUpperCase().equals("VERTICAL")) {
                    board.addShip(new Ship(board, uppermost, leftmost, Ship.Orientation.VERTICAL, length));
                } else throw new BattleshipException(BAD_CONFIG_FILE);
            }
        } catch (BattleshipException e1) {
            System.out.println(e1.toString());
            System.exit(0);
        } catch (Exception e2) {
            System.out.println(BAD_CONFIG_FILE + " or non-existing file");
            e2.printStackTrace();
            System.exit(0);
        }
    }

    /**
     * This functions displays the game board every round and prompts for user's inputs.
     */
    public void userMenu() {
        boolean userIn1 = true;
        String[] userIn2;
        /* if you want to print the game processes to somewhere else other than on to the screen, just create
           another PrintStream and pass it to display function.
           e.g. to a text file:
            File file = new File(filename);
            try (BufferedOutputStream writer = new BufferedOutputStream(new FileOutputStream(file))){
                PrintStream ps = new PrintStream(writer);
                board.display(ps);
            } catch (Exception e){
                e..printStackTrace();
            }
         */
        board.display(System.out);
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            while (userIn1) {
                System.out.print(PROMPT);
                String userIn3 = reader.readLine();
                userIn2 = userIn3.split(WHITESPACE);
                switch (userIn2[0].toLowerCase()) {
                    case "h":
                        if (userIn2.length != 3) System.out.println(BAD_ARG_COUNT + "h");
                        else {
                            try {
                                board.getCell(Integer.parseInt(userIn2[1]), Integer.parseInt(userIn2[2])).hit();
                                System.out.println();
                                board.display(System.out);
                            } catch (NumberFormatException e1) {
                                System.out.println("Wrong arguments for command h: h row(int) column(int)");
                            } catch (BattleshipException e2) {
                                System.out.println(e2.toString());
                            }
                        }
                        if (board.allSunk()) {
                            System.out.println(ALL_SHIPS_SUNK);
                            userIn1 = false;
                        }
                        break;
                    case "s":
                        if (userIn2.length != 2) System.out.println(BAD_ARG_COUNT + "s");
                        else serialize(userIn2[1]);
                        break;
                    case "q":
                        if (userIn2.length != 1) System.out.println(BAD_ARG_COUNT + "q");
                        else userIn1 = false;
                        break;
                    case "!":
                        if (userIn2.length != 1) System.out.println(BAD_ARG_COUNT + "!");
                        else board.fullDisplay(System.out);
                        break;
                    default:
                        System.out.println("User commands:");
                        System.out.println("h row column - Hit a cell.");
                        System.out.println("s file - Save game state to ﬁle. (Serialization process)");
                        System.out.println("q - Quit game.");
                        System.out.println("! - Reveal all ship locations.");
                        break;
                }
            }
        } catch (IOException e1) {
            e1.printStackTrace();
        } catch (BattleshipException e2) {
            System.out.println(e2.toString());
        }
    }

    /**
     * This function serializes the game (board) current states to a file.
     *
     * @param savedFilename name of the saved game file
     * @throws BattleshipException exceptions caused by game rules during the game
     * @throws IOException         exceptions caused while dealing with IO streams
     */
    public void serialize(String savedFilename) throws BattleshipException, IOException {
        File file = new File(savedFilename);
        if (file.exists()) throw new BattleshipException("File already exists!");
        ObjectOutputStream writer = new ObjectOutputStream(new FileOutputStream(file));
        writer.writeObject(board);
        writer.close();
    }

    /**
     * Read the setup file and build all data structures needed later. The setup file is either a text file describing
     * the initial state of a new game or an ObjectStream file that contains a saved game.
     *
     * @param args one element: the name of the setup file
     */
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println(Battleship.MISSING_SETUP_FILE);
            System.exit(0);
        }
        Battleship hw8 = new Battleship(args[0]);
        hw8.deserialize();
        hw8.userMenu();
    }
}
