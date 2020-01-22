package Server;

import Protocols.FrogstarProtocol;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

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
        while (ServerSingleton.getServer().isRunning()) {
            if (ServerSingleton.getServer().numUsers() < FrogstarProtocol.maxUsers) {
                UserState newUser = null;
                try {
                    Socket newSocket = serverSocket.accept();
                    newUser = new UserState(newSocket);
                } catch (IOException e) {
                    e.printStackTrace();
                    continue;
                }

                ServerSingleton.getServer().addUser(newUser);

                HandlerThread handler = new HandlerThread(newUser);
                ServerSingleton.addHandler(handler);
            }
            Thread.yield();
        }

        //todo Perhaps cleanup here
    }
}
