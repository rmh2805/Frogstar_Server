package Server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Queue;
import java.util.Scanner;

public class UserState {
    private String name;

    private Socket socket;
    private PrintWriter networkOut;
    private Scanner networkIn;

    public UserState(Socket socketIn) throws IOException {
        name = null;

        this.socket = socketIn;
        networkOut = new PrintWriter(socketIn.getOutputStream());
        networkIn = new Scanner(socketIn.getInputStream());
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
