package Base_Server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ClientInterface {
    private Socket socket;

    private Scanner netIn;
    private PrintWriter netOut;

    public ClientInterface(String host, int port) throws IOException {
        this(new Socket(host, port));
    }

    public ClientInterface(Socket socket) throws IOException {
        this.socket = socket;
        netIn = new Scanner(socket.getInputStream());
        netOut = new PrintWriter(socket.getOutputStream());
    }

    public void stop() throws IOException {
        netIn = null;
        netOut = null;
        socket.close();
    }

    public void sendCommand(Command command) {
        netOut.println(command.encode());
        netOut.flush();
    }

    public Command getCommand() {
        if (!netIn.hasNextLine()) {
            return null;
        }

        return Command.decodeCommand(netIn.nextLine());
    }

}
