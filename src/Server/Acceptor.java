package Server;

import java.io.IOException;
import java.net.ServerSocket;

public class Acceptor implements Runnable {
    ServerSocket serverSocket;

    public Acceptor (int port) {
        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {

    }
}
