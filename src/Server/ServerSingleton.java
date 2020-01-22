package Server;

import Errors.BadName;
import Protocols.FrogstarMessage;
import Protocols.FrogstarProtocol;
import Protocols.From_Server.Logged_Off;
import Protocols.From_Server.Logged_On;

import java.util.*;

import static java.util.Collections.synchronizedList;
import static java.util.Collections.synchronizedMap;

public class ServerSingleton {
    private static ServerSingleton server;
    private static List<HandlerThread> handlerPool;

    public static void main(String[] args) {
        initialize();

        AcceptorThread acceptor = new AcceptorThread(FrogstarProtocol.port);


    }

    public static void initialize() {
        if (server != null)
            return;

        server = new ServerSingleton();
        handlerPool = new LinkedList<>();
    }

    /**
     * Gets the current server instance
     *
     * @return The current server instance
     */
    public static ServerSingleton getServer() {
        return server;
    }

    public static void addHandler(HandlerThread handler) {
        handlerPool.add(handler);
    }

    public static void removeHandler(HandlerThread handler) {
        handlerPool.remove(handler);
    }

    //============================================================================================//
    //======================================<Instance State>======================================//
    //============================================================================================//
    private List<UserState> tempUsers;
    private Map<String, UserState> userMap;
    private boolean isRunning;

    //============================================================================================//
    //=====================================<Instance Methods>=====================================//
    //============================================================================================//
    public ServerSingleton() {
        tempUsers = java.util.Collections.synchronizedList(new LinkedList<>());
        userMap = java.util.Collections.synchronizedMap(new HashMap<>());

        this.isRunning = true;
    }

    //=======================================<Misc Checks>========================================//

    /**
     * Returns the list of logged on users (for iteration)
     *
     * @return The list of logged on users
     */
    Set<String> getUserSet() {
        return userMap.keySet();
    }

    /**
     * Returns the number of connected users
     *
     * @return the number of connected users
     */
    int numUsers() {
        return tempUsers.size() + userMap.size();
    }

    boolean isRunning() {
        return this.isRunning;
    }

    //==================================<Modify the User list>====================================//

    /**
     * Add a new, unnamed user to the server list
     *
     * @param user The new user for naming
     */
    public void addUser(UserState user) {
        tempUsers.add(user);
    }

    /**
     * Assigns a name to a user
     *
     * @param name The name to assign
     * @param user The user to name
     */
    public void setUser(String name, UserState user) {
        tempUsers.remove(user);
        user.setName(name);
        userMap.put(name, user);

        messageAll(new Logged_On(name));
    }

    /**
     * Remove a named user from the server list
     *
     * @param name The name of the user to remove
     */
    public void removeUser(String name) {
        if (userMap.containsKey(name)) {
            userMap.remove(name);
            messageAll(new Logged_Off(name));
        }
    }

    /**
     * Remove an unnamed user from the server list
     *
     * @param user The user to drop
     */
    public void dropUser(UserState user) {
        tempUsers.remove(user);
    }

    //=====================================<Message Passing>======================================//

    /**
     * Send a message to a specific user
     *
     * @param message The message to send
     * @param tgt     The user to message
     * @throws BadName Thrown iff the specified target is not allocated
     */
    public void messageUser(FrogstarMessage message, String tgt) throws BadName {
        if (!userMap.containsKey(tgt)) {
            throw new BadName(tgt);
        }

        userMap.get(tgt).sendMessage(message);

    }

    /**
     * Send the same message to every named user
     *
     * @param message The message to send along
     */
    public void messageAll(FrogstarMessage message) {
        for (String key : userMap.keySet()) {
            try {
                messageUser(message, key);
            } catch (BadName badName) {
                System.out.println("[Server] Bad name on message all: " + key);
                userMap.remove(key);
            }
        }
    }

    //======================================<Name Checking>=======================================//
    public boolean nameLegal (String name) {
        if(FrogstarProtocol.nameValid(name))
            return !userMap.containsKey(name);
        return false;
    }

}
