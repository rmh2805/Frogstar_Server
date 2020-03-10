package Base_Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;

public class Server {
    private Map<Integer, ClientHandler> clients;
    private Integer nextId;

    private ServerSocket serverSocket;

    /**
     * Accepts an incoming connection, creates a new handler for it, and then starts a thread for it.
     */
    public void acceptUsers() {
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
            try {
                socket.close();
            } catch (IOException ignored) {
            }

            return;
        }

        addHandler(clientInterface);
    }

    /**
     * Create and initialize a new Server instance
     *
     * @param port The port to open on
     * @throws IOException Thrown if we fail to open the server socket
     */
    public Server(int port) throws IOException {
        nextId = 0;
        clients = Collections.synchronizedMap(new HashMap<Integer, ClientHandler>());

        serverSocket = new ServerSocket(port);
    }

    /**
     * Returns the next free Id to assign to the newest user
     *
     * @return The next free Id
     */
    private Integer getNextId() {
        nextId += 1;
        return nextId - 1;
    }

    /**
     * Creates and adds a handler to the clients map
     *
     * @param clientInterface The interface for the new handler
     */
    private synchronized void addHandler(ClientInterface clientInterface) {
        Integer id = getNextId();
        ClientHandler handler = new ClientHandler(id, clientInterface, this);
        clients.put(id, handler);
        new Thread(handler).start();
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
        List<Integer> targets = null;
        if (command.getTargets().size() > 1) {
            targets = new LinkedList<>();
            for (String target : command.getTargets()) {
                targets.add(Integer.valueOf(target));
            }
        }
        else {
            targets = new LinkedList<>(clients.keySet());
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
