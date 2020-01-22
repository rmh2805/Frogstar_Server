package Protocols.From_Client;

import Protocols.FrogstarMessage;

import static Protocols.FrogstarProtocol.logOffHead;

public class Log_Off extends FrogstarMessage {
    /**
     * Sent from a user to request to log off
     * @param name The name of the user to log off
     */
    public Log_Off(String name) {
        super(logOffHead, name);
    }
}
