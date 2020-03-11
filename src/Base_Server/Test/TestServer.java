package Base_Server.Test;

import Base_Server.Server;

import java.io.IOException;

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
        System.out.println("Server opened on port " + args[0]);

        while(true) {
            server.acceptUsers();
            System.out.println("Accepted a new user");
        }
    }

    public static void testClient(String[] args) throws IOException {

    }
}
