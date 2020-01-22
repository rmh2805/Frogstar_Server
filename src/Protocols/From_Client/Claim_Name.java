package Protocols.From_Client;

import Protocols.FrogstarMessage;

import static Protocols.FrogstarProtocol.claimHead;

public class Claim_Name extends FrogstarMessage {
    /**
     * Sent from a user in an attempt to claim a name and log on
     * @param name The requested name
     */
    public Claim_Name(String name) {
        super(claimHead, name);
    }
}
