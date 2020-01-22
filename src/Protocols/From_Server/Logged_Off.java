package Protocols.From_Server;

import Protocols.FrogstarMessage;

import static Protocols.FrogstarProtocol.loggedOffHead;

public class Logged_Off extends FrogstarMessage {

    /**
     * Sends an alert that a user has logged off
     *
     * @param name The name of the logged user
     */
    public Logged_Off(String name) {
        super(loggedOffHead, name);
    }
}
