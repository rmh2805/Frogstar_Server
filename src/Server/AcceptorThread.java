package Server;

import java.io.IOException;
import java.net.ServerSocket;

public class AcceptorThread implements Runnable {
    ServerSocket serverSocket;

    public AcceptorThread(int port) {
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
