/*
 * This is file illustrates ConnectFourClient.java from hw10.
 */
package hw10;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * The client class (players)
 *
 * @author Krystian Alexander Derhak, kad4374@rit.edu
 * @author Michael Lee, ml3406@rit.edu
 */
public class ConnectFourClient implements ConnectFourProtocol {
    /** the Sockets for communicating with the server */
    private final Socket socket;
    /** the game */
    private final ConnectFour board;

    /**
     * The constructor sets up a client representing a player with a Socket
     * for connecting to the server and also a game.
     *
     * @param hostname represents the computer (server) you want to connect to (localhost means your own machine)
     * @param portNumber the port used for connection
     * @throws IOException thrown if there is something wrong with the networking
     */
    public ConnectFourClient(String hostname, int portNumber) throws IOException {
        socket = new Socket(hostname, portNumber);
        board = new ConnectFour();
    }

    /**
     * This method sets up IO connections with the server and communicates with it
     * for playing the game with another player connected to the server.
     *
     * @throws IOException thrown if there is something wrong with the networking
     */
    public void joinGame() throws IOException {
        OutputStream output = socket.getOutputStream();
        PrintStream printer = new PrintStream(output);
        InputStream input = socket.getInputStream();
        Scanner scanner = new Scanner(input);
        Scanner prompt = new Scanner(System.in);

        boolean connection = true;
        while (connection) {
            // receives requests from the server and deals with them and checks if the server is working fine
            String request1 = null;
            try {
                request1 = scanner.nextLine();
            } catch (NoSuchElementException e){
                // exits if the server goes down
                System.out.println("There is something wrong with the server. Shutting down the program...");
                System.exit(-1);
            }
            String[] request2 = request1.split(" ");
            // deals with different requests from the server
            switch (request2[0]) {
                case CONNECT -> {
                    System.out.println("Connected!");
                    System.out.println(board.toString());
                }
                case OPPONENT_TURN -> System.out.println("Opponents Turn");
                case MAKE_MOVE -> {
                    System.out.print("Your turn! Enter a column: ");
                    printer.println(MOVE + " " + prompt.nextLine());
                }
                case MOVE_MADE -> {
                    System.out.println("A move has been made in column " + request2[1]);
                    try {
                        board.makeMove(Integer.parseInt(request2[1]));
                    } catch (ConnectFourException e) {
                        System.out.println(e.toString());
                    }
                    System.out.println(board.toString());
                }
                case GAME_WON -> {
                    System.out.println("You win! Yay!");
                    connection = false;
                }
                case GAME_LOST -> {
                    System.out.println("You lost.....");
                    connection = false;
                }
                case GAME_TIED -> {
                    System.out.println("It's a tie !");
                    connection = false;
                }
                case ERROR -> {
                    for (int i = 1; i < request2.length; i++) {
                        System.out.print(request2[i] + " ");
                    }
                    System.out.println("\nShutting down the program...");
                    socket.shutdownOutput();
                    socket.shutdownInput();
                    System.exit(-1);
                }
                default -> {
                    System.out.println("Something wrong happened. Shutting down the program.");
                    socket.shutdownOutput();
                    socket.shutdownInput();
                    System.exit(-1);
                }
            }
        }
        System.out.println("Thanks for playing.");
        socket.shutdownOutput();
        socket.shutdownInput();
    }


    /**
     * The main method check if the the provided system arguments are valid.
     * If yes, sets up a client and tries to connect to the server.
     *
     * @param args system arguments storing the hostname and the port number
     */
    public static void main(String[] args)  {
        if (args.length != 2) {
            System.out.println("Usage: ConnectFourClient hostname portNumber");
            System.exit(-1);
        }
        int portNumber = Integer.parseInt(args[1]);
        if (portNumber > 65535 || portNumber <= 1024) {
            System.out.println("The port number should be an integer greater than 1024 and less or equal to 65535.");
        }
        try {
            ConnectFourClient hw10 = new ConnectFourClient(args[0], portNumber);
            hw10.joinGame();
        } catch (IOException e){
            e.printStackTrace();
            System.exit(-1);
        }
    }
}
