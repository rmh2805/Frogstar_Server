package Protocols.From_Client;

import Protocols.FrogstarMessage;

import static Protocols.FrogstarProtocol.logOffHead;

public class Log_Off extends FrogstarMessage {
    public Log_Off(String name) {
        super(logOffHead, name);
    }
}
