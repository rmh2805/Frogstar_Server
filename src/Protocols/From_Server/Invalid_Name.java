package Protocols.From_Server;

import Protocols.FrogstarMessage;

import static Protocols.FrogstarProtocol.invalidNameHead;

public class Invalid_Name extends FrogstarMessage {
    public Invalid_Name(String name) {
        super(invalidNameHead, name);
    }
}
