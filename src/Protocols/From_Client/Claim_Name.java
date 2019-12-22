package Protocols.From_Client;

import Protocols.FrogstarMessage;

import static Protocols.FrogstarProtocol.claimHead;

public class Claim_Name extends FrogstarMessage {
    public Claim_Name(String name) {
        super(claimHead, name);
    }
}
