package Protocols.From_Server;

import Protocols.FrogstarMessage;

import static Protocols.FrogstarProtocol.nameTakenHead;

public class Name_Taken extends FrogstarMessage {
    public Name_Taken(String name) {
        super(nameTakenHead, name);
    }
}
