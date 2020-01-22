package Protocols.From_Server.Errors;

import Protocols.FrogstarMessage;
import Protocols.FrogstarProtocol;

import static Protocols.FrogstarProtocol.invalidNameHead;

public class Invalid_Name extends FrogstarMessage {
    /**
     * Sent to a user who attempted to create an illegal name
     * @param name The illegal name
     */
    public Invalid_Name(String name) {
        super(invalidNameHead, name);
    }
}
