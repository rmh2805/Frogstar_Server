package Protocols.From_Server.Errors;

import Protocols.FrogstarMessage;

import static Protocols.FrogstarProtocol.nameTakenHead;

public class Name_Taken extends FrogstarMessage {
    /**
     * Sent to a user who attempts to claim a taken name
     * @param name The taken name
     */
    public Name_Taken(String name) {
        super(nameTakenHead, name);
    }
}
