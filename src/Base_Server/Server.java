package Base_Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;

public class Server implements Runnable {
    protected Map<Integer, ClientHandler> clients;
    protected Integer nextId;

    protected ServerSocket serverSocket;

    protected boolean continueRunning;
    protected boolean doPrint;


    /**
     * Accepts an incoming connection, creates a new handler for it, and then starts a thread for it.
     */
    protected void acceptUsers() {
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
     * @param port    The port to open on
     * @param doPrint Should I print on execution
     * @throws IOException Thrown if we fail to open the server socket
     */
    public Server(int port, boolean doPrint) throws IOException {
        this.nextId = 0;

        this.clients = Collections.synchronizedMap(new HashMap<Integer, ClientHandler>());
        this.serverSocket = new ServerSocket(port);

        this.continueRunning = true;
        this.doPrint = doPrint;
    }

    public Server(int port) throws IOException {
        this(port, true);
    }

    /**
     * Returns the next free Id to assign to the newest user
     *
     * @return The next free Id
     */
    protected Integer getNextId() {
        nextId += 1;
        return nextId - 1;
    }

    /**
     * Creates and adds a handler to the clients map
     *
     * @param clientInterface The interface for the new handler
     */
    protected synchronized void addHandler(ClientInterface clientInterface) {
        Integer id = getNextId();

        if (doPrint) System.out.println("Added a new user with id " + id);

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
    public boolean executeCommand(Integer id, Command command) {
        //todo check to drop leaving users
        if (doPrint) System.out.println(command);

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

    /**
     * Sets volatile flag false and creates a new connection to break the exception route
     *
     * @throws IOException If creating the new socket fails
     */
    public void stop() throws IOException {
        continueRunning = false;

        //Throw a new connection at the acceptor thread to have it drop out of its accept wait
        Socket breakAccept = new Socket("127.0.0.1", serverSocket.getLocalPort());
        breakAccept.close();    //Close the loop breaker
    }


    /**
     * Accepts new users until stop is called on another thread. Then closes outs all active connections
     */
    @Override
    public void run() {
        if (doPrint) System.out.println("Server open on local port " + serverSocket.getLocalPort());

        while (continueRunning) {
            acceptUsers();
        }

        // Now that we're done, Close out all of your clients
        for (Integer id : clients.keySet()) {
            clients.get(id).stop();
        }
    }

}
