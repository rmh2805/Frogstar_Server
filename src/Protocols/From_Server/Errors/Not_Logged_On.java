package Protocols.From_Server.Errors;

import Protocols.FrogstarMessage;

import static Protocols.FrogstarProtocol.notLoggedOnHead;

public class Not_Logged_On extends FrogstarMessage {
    public Not_Logged_On() {
        super(notLoggedOnHead, "You Must Log On First");
    }
}
