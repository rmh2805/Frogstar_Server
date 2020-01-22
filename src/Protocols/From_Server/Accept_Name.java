package Protocols.From_Server;

import Protocols.FrogstarMessage;

import static Protocols.FrogstarProtocol.acceptNameHead;

public class Accept_Name extends FrogstarMessage {
    /**
     * Sent to a user to confirm that their name has been assigned
     *
     * @param name The confirmed username of the user
     */
    public Accept_Name(String name) {
        super(acceptNameHead, name);
    }
}
