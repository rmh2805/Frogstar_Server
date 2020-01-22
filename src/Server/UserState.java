package Server;

import Protocols.FrogstarMessage;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class UserState {
    private String name;

    private Socket socket;
    private PrintWriter networkOut;
    private Scanner networkIn;

    /**
     * Creates a new user with the given connection
     *
     * @param socketIn The connection to client
     * @throws IOException If the socket doesn't allow access to in or out streams
     */
    public UserState(Socket socketIn) throws IOException {
        name = null;

        this.socket = socketIn;
        networkOut = new PrintWriter(socketIn.getOutputStream());
        networkIn = new Scanner(socketIn.getInputStream());
    }

    /**
     * Sets the name of this user
     *
     * @param name The username to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the username of the user
     *
     * @return The user's username
     */
    public String getName() {
        return name;
    }

    /**
     * Reads the next line (command) from the client
     *
     * @return The next command from the client (null if no command available)
     */
    public String getMessage () {
        if(networkIn.hasNextLine())
            return null;

        String str = networkIn.nextLine().trim();

        if(str.isEmpty())
            return null;

        return str;
    }

    /**
     * Sends a command to the client
     *
     * @param msg The command to send to the client
     */
    public void sendMessage(FrogstarMessage msg) {
        if(msg == null)
            return;

        networkOut.println(msg.toString().trim());
        networkOut.flush();
    }

    /**
     * Closes the user's connection at end of life
     */
    public void closeUser() {
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}