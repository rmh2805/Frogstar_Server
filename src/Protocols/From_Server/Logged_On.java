package Protocols.From_Server;

import Protocols.FrogstarMessage;

import static Protocols.FrogstarProtocol.loggedOnHead;

public class Logged_On extends FrogstarMessage {
    public Logged_On(String name) {
        super(loggedOnHead, name);
    }
}
