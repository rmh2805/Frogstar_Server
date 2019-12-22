package Protocols.From_Server;

import Protocols.FrogstarMessage;

import static Protocols.FrogstarProtocol.acceptNameHead;

public class Accept_Name extends FrogstarMessage {
    public Accept_Name(String name) {
        super(acceptNameHead, name);
    }
}
