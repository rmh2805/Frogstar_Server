package Base_Server;

import Base_Server.ClientInterface;
import Base_Server.Command;
import Base_Server.Server;

import java.io.IOException;
import java.util.Queue;

public class ClientHandler implements Runnable {
    @Override
    public void run() {
        while(running) {
            Command nextCommand = clientInterface.getCommand();
            while(nextCommand == null && running) {
                nextCommand = clientInterface.getCommand();
            }
            if(nextCommand == null) break;

            if(!server.executeCommand(this.id, nextCommand)) {
                this.clientInterface.sendCommand(new Command(Command.failedCommandTag, nextCommand.toString()));
            }
        }

        try {
            clientInterface.stop();
        } catch (IOException ignored) {
        }
    }

    private int id;
    private ClientInterface clientInterface;
    private boolean running;
    private Server server;

    public ClientHandler(int id, ClientInterface clientInterface, Server server) {
        this.id = id;
        this.clientInterface = clientInterface;
        running = true;
        this.server = server;

        this.clientInterface.sendCommand(new Command(Command.idTag, id + ""));
    }

    public void sendCommand(Command command) {
        clientInterface.sendCommand(command);
    }

    public int getId() {
        return id;
    }

    public void stop() {
        sendCommand(new Command(Command.stopTag, "fromServer"));
        this.running = false;
    }
}
