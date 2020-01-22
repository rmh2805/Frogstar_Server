package Protocols.From_Server;

import Protocols.FrogstarMessage;

import static Protocols.FrogstarProtocol.loggedOnHead;

public class Logged_On extends FrogstarMessage {
    /**
     * Sends an alert that a new user has logged on, along with passing their name
     *
     * @param name The name of the new user
     */
    public Logged_On(String name) {
        super(loggedOnHead, name);
    }
}
