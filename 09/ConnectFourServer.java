/*
 * This is file illustrates ConnectFourServer.java from hw10.
 */
package hw10;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * The server class
 *
 * @author Krystian Alexander Derhak, kad4374@rit.edu
 * @author Michael Lee, ml3406@rit.edu
 */
public class ConnectFourServer {
    /** the port number for connection */
    private final int PORT_NUMBER;

    /**
     * The constructor sets up a server with a port number
     *
     * @param portNumber the port number for connection
     */
    public ConnectFourServer(int portNumber) {
        PORT_NUMBER = portNumber;
    }

    /**
     * This method makes the server start listening from clients via the port (PORT_NUMBER)
     */
    public void listen() {
        // a string revealed on the command line for debugging
        System.out.println("Start listening from port " + PORT_NUMBER + " ......");

        try (ServerSocket server = new ServerSocket(PORT_NUMBER)) {
            // keep listening
            while (true) {
                // waiting for player 1
                Socket client1 = server.accept();
                // waiting for player 2
                Socket client2 = server.accept();

                // a string revealed on the command line for debugging
                System.out.println("A game just started.");

                // whenever there are two players connected, create a new game (Thread)
                new Thread(new ConnectFourServerThread(client1, client2)).start();
            }
        } catch (IOException e) {
            System.err.println("Could not listen on port " + PORT_NUMBER);
            System.exit(-1);
        }
    }

    /**
     * The main method checks if the provided system arguments are valid.
     * If yes, starts listening for connection requests.
     *
     * @param args system arguments storing the port number
     */
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: ConnectFourServer portNumber");
            System.exit(-1);
        }
        int portNumber = Integer.parseInt(args[0]);
        if (portNumber > 65535 || portNumber <= 1024) {
            System.out.println("The port number should be an integer greater than 1024 and less or equal to 65535.");
        }

        ConnectFourServer hw10 = new ConnectFourServer(portNumber);
        hw10.listen();
    }
}
