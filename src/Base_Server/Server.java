package Base_Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;

public class Server {
    private Map<Integer, ClientHandler> clients;
    private Integer nextId;

    private ServerSocket serverSocket;

    public static void main(String[] args) throws IOException {
        Server server = new Server(Integer.parseInt(args[0]));
        while(true) {
            server.getUsers();
        }
    }

    public void getUsers() throws IOException {
        Socket socket = null;
        try {
            socket = serverSocket.accept();
        } catch (IOException e) {
            return;
        }
        ClientInterface clientInterface;
        try {
            clientInterface = new ClientInterface(socket);
        } catch (IOException e) {
            socket.close();
            return;
        }

        ClientHandler handler = addHandler(clientInterface);
        new Thread(handler).start();
    }

    public Server(int port) throws IOException {
        nextId = 0;
        clients = Collections.synchronizedMap(new HashMap<Integer, ClientHandler>());

        serverSocket = new ServerSocket(port);

    }

    private Integer getNextId() {
        nextId += 1;
        return nextId - 1;
    }

    /**
     * Creates and adds a handler to the clients map
     *
     * @param clientInterface The interface for the new handler
     */
    private synchronized ClientHandler addHandler(ClientInterface clientInterface) {
        Integer id = getNextId();
        ClientHandler handler = new ClientHandler(id, clientInterface, this);
        clients.put(id, handler);
        return handler;
    }

    /**
     * Basic command implementation, takes in commands and sends them on to all valid recipients, replacing the target
     * field with the sender's ID
     *
     * @param id      The ID of the sender
     * @param command The command to execute
     * @return Whether or not the command was sent to anyone
     */
    public boolean executeCommand(int id, Command command) {
        List<Integer> targets = new LinkedList<>();
        for (String target : command.getTargets()) {
            targets.add(Integer.valueOf(target));
        }

        List<String> senders = new LinkedList<>();
        senders.add("" + id);

        boolean sentCommand = false;
        Command toSend = new Command(command.getTag(), senders, command.getPayload());

        for (Integer target : targets) {
            if (!clients.containsKey(target)) continue;
            clients.get(target).sendCommand(toSend);
            sentCommand = true;
        }


        return sentCommand;
    }
}
