package Base_Server.Test;

import Base_Server.Server;

import java.io.IOException;
import java.util.Scanner;

public class TestServer {
    public static void main(String[] args) throws IOException {
        if(args.length == 1) {
            testServer(args);
        } else if(args.length == 2) {
            testClient(args);
        }
    }

    public static void testServer(String[] args) throws IOException {
        Server server = new Server(Integer.parseInt(args[0]));
        Scanner consoleIn = new Scanner(System.in);
        new Thread(server).start();

        while(!consoleIn.nextLine().strip().toLowerCase().equals("quit"));
        server.stop();
    }

    public static void testClient(String[] args) throws IOException {

    }
}
