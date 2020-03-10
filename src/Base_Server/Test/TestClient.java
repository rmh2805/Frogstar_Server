package Base_Server.Test;

import Base_Server.ClientInterface;
import Base_Server.Command;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class TestClient {
    public static final String fibTag = "fib";

    public static void main(String[] args) throws IOException {
        ClientInterface clientInterface = null;
        if(args.length >= 1) {
            //This is the "server" side
            System.out.println("Server Side");
            ServerSocket serverSocket = new ServerSocket(5526);
            clientInterface = new ClientInterface(serverSocket.accept());
            serverSocket.close();
        } else {
            System.out.println("Client Side");
            clientInterface = new ClientInterface("localHost", 5526);
            Command fibStart = new Command(fibTag, "0,1");
            System.out.println("-> " + fibStart.toString() + "\n");
            clientInterface.sendCommand(fibStart);
        }

        int num = -1;

        while (num <= 100) {
            Command nextCommand = clientInterface.getCommand();
            while (nextCommand == null) nextCommand = clientInterface.getCommand();
            System.out.println("<- " + nextCommand.toString());

            String[] temp = nextCommand.getPayload().split(",");
            int lowNum = Integer.parseInt(temp[0].strip());
            int highNum = Integer.parseInt(temp[1].strip());
            num = lowNum + highNum;

            Command reply = new Command(fibTag, highNum + "," + num);
            System.out.println("-> " + reply.toString() + "\n");
            clientInterface.sendCommand(reply);
        }


    }
}