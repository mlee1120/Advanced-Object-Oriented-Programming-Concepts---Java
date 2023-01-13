/*
 * This is file illustrates ConnectFourServerThread.java from hw10.
 */
package hw10;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * The server thread class monitoring a game
 *
 * @author Krystian Alexander Derhak, kad4374@rit.edu
 * @author Michael Lee, ml3406@rit.edu
 */
public class ConnectFourServerThread implements Runnable, ConnectFourProtocol {
    /** stores two Sockets for communicating with two clients (players) */
    private final LinkedList<Socket> sockets;
    /** stores two Scanners for receiving responses from two players */
    private final LinkedList<Scanner> scanners;
    /** stores two PrintStreams for sending data to players  */
    private final LinkedList<PrintStream> printers;

    /**
     * The constructor sets up a new server thread which represents a game. It is responsible
     * for communicating with two players and making sure the game goes well.
     *
     * @param socket1 a Socket for communicating with player 1
     * @param socket2 a Socket for communicating with player 2
     * @throws IOException thrown if there is something wrong with the networking
     */
    public ConnectFourServerThread(Socket socket1, Socket socket2) throws IOException {
        sockets = new LinkedList<>();
        sockets.add(socket1);
        sockets.add(socket2);
        scanners = new LinkedList<>();
        scanners.add(new Scanner(socket1.getInputStream()));
        scanners.add(new Scanner(socket2.getInputStream()));
        printers = new LinkedList<>();
        printers.add(new PrintStream(socket1.getOutputStream()));
        printers.add(new PrintStream(socket2.getOutputStream()));
    }

    /**
     * If this method is called directly, the codes in this method would be executed
     * without creating a new thread (no concurrency).
     * If this method is called by calling .start(), a new thread would be created and
     * the codes in this method would be executed concurrently with the main thread.
     */
    @Override
    public void run() {
        // creates a game
        ConnectFour hw10 = new ConnectFour();
        // informs both players that they are connected to the server
        printers.getFirst().println(CONNECT);
        printers.getLast().println(CONNECT);
        // stores column number from players
        int col;
        while (true) {
            // informs a player to make move
            printers.getFirst().println(MAKE_MOVE);
            // informs the other player to wait
            printers.getLast().println(OPPONENT_TURN);
            // checks if the response is numeric and if two players are still connected
            try {
                String request1 = scanners.getFirst().nextLine();
                String[] request2 = request1.split(" ");
                col = Integer.parseInt(request2[1]);
            } catch (NumberFormatException e1) {
                // if a player has enter something not numeric, informs both players and end this game (just the thread)
                try {
                    throw new ConnectFourException("You made an invalid response.", e1);
                }catch(ConnectFourException e2) {
                    printers.getFirst().println(ERROR + " " + e2.toString());
                    printers.getLast().println(ERROR + " Your opponent has violated the rules. You win!");
                    System.out.println("A game just ended because a player has violated the rules.");
                    break;
                }
            }catch (NoSuchElementException e3){
                // if one player loses connection, informs the other player and ends this game (thread)
                try {
                    throw new ConnectFourException("Your opponent has lost connection. You win!", e3);
                } catch (ConnectFourException e4) {
                    printers.getLast().println(ERROR + " " + e4.toString());
                    System.out.println("A game just ended because of poor connection.");
                    break;
                }
            }

            // checks if the move (number of column) is valid
            try {
                hw10.makeMove(col);
            } catch (ConnectFourException e) {
                // informs both players and ends this game (thread)
                printers.getFirst().println(ERROR + " " + e.toString());
                printers.getLast().println(ERROR + " Your opponent has violated the rules. You win!");
                System.out.println("A game just ended because a player has violated the rules.");
                break;
            }

            // informs both players the move just made
            printers.getFirst().println(MOVE_MADE + " " + col);
            printers.getLast().println(MOVE_MADE + " " + col);

            // checks if there is a winner (informs both players if yes) and end the game (thread)
            if (hw10.hasWonGame()) {
                printers.getFirst().println(GAME_WON);
                printers.getLast().println(GAME_LOST);
                System.out.println("A game just had a winner and ended.");
                break;
            }

            // checks if the game is tied (informs both players if yes) and ends the game (thread)
            if (hw10.hasTiedGame()) {
                printers.getFirst().println(GAME_TIED);
                printers.getLast().println(GAME_TIED);
                System.out.println("A game just tied and ended.");
                break;
            }

            // the game goes on => the other player's turn
            scanners.add(scanners.remove());
            printers.add(printers.remove());
        }
        try {
            // closes Sockets' IO streams
            close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * This methods close both Sockets' IO streams.
     *
     * @throws IOException thrown if there is something wrong when closing IO streams
     */
    public void close() throws IOException {
        for (Socket i : sockets) {
            i.shutdownOutput();
            i.shutdownInput();
        }
    }
}
