package Protocols.From_Server;

import Protocols.FrogstarMessage;

import static Protocols.FrogstarProtocol.loggedOffHead;

public class Logged_Off extends FrogstarMessage {


    public Logged_Off(String name) {
        super(loggedOffHead, name);
    }
}
