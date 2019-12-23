package Server;

import Protocols.FrogstarMessage;

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

    public String getMessage () {
        if(networkIn.hasNextLine())
            return null;

        String str = networkIn.nextLine().trim();

        if(str.isEmpty())
            return null;

        return str;
    }

    public void sendMessage(FrogstarMessage msg) {
        if(msg == null)
            return;

        networkOut.println(msg.toString().trim());
        networkOut.flush();
    }
}