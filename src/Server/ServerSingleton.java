package Server;

import Errors.BadName;
import Protocols.FrogstarMessage;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class ServerSingleton {
    private static ServerSingleton server;
    private static List<UserState> tempUsers;
    private static Map<String, UserState> userMap;

    public static void main(String[] args) {
        initialize();

        //Spawn acceptor and handler threads

    }

    public static void initialize() {
        if(server == null)
            server = new ServerSingleton();
    }

    /**
     * Gets the current server instance
     * @return The current server instance
     */
    public static ServerSingleton getServer(){
        return server;
    }

    //============================================================================================//
    //=====================================<Instance Methods>=====================================//
    //============================================================================================//
    public ServerSingleton() {
        tempUsers = new LinkedList<>();
        userMap = new HashMap<>();
    }

    //==================================<Modify the User list>====================================//
    /**
     * Add a new, unnamed user to the server list
     * @param user The new user for naming
     */
    public void addUser(UserState user) {
         tempUsers.add(user);
    }

    /**
     * Assigns a name to a user
     * @param name The name to assign
     * @param user The user to name
     */
    public void setUser (String name, UserState user) {
        tempUsers.remove(user);
        user.setName(name);
        userMap.put(name, user);
    }

    /**
     * Remove a named user from the server list
     * @param name The name of the user to remove
     */
    public void removeUser (String name) {
        userMap.remove(name);
    }

    /**
     * Remove an unnamed user from the server list
     * @param user The user to drop
     */
    public void dropUser(UserState user) {
        tempUsers.remove(user);
    }

    //=====================================<Message Passing>======================================//
    /**
     * Send a message to a specific user
     * @param message The message to send
     * @param tgt The user to message
     * @throws BadName Thrown iff the specified target is not allocated
     */
    public void messageUser(FrogstarMessage message, String tgt) throws BadName {
        if(!userMap.containsKey(tgt)) {
            throw new BadName(tgt);
        }

        userMap.get(tgt).sendMessage(message);

    }

    /**
     * Send the same message to every named user
     * @param message The message to send along
     */
    public void messageAll (FrogstarMessage message) {
        for(String key : userMap.keySet()) {
            try {
                messageUser(message, key);
            } catch (BadName badName) {
                System.out.println("[Server] Bad name on message all:" + key);
            }
        }
    }


}
