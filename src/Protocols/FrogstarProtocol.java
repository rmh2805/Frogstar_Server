package Protocols;

public class FrogstarProtocol {
    public static final String separator = "::";
    public static final String lineBreak = ":n:";

    //=========================<Headers From Client>==========================//
    public static final String claimHead = "ClaimName";

    //=========================<Headers From Server>==========================//
    public static final String acceptNameHead = "AcceptName";

    //============================<Server Errors>=============================//
    public static final String nameTakenHead = "NameTaken";
    public static final String invalidNameHead = "InvalidName";
    public static final String notLoggedOnHead = "MustSignIn";

    //============================<Shared Headers>============================//

    //=========================<String Manipulation>==========================//
    public static String catStrings (String l, String r) {
        return l + separator + r;
    }

    public static String sanitizeString (String str) {
        str = str.replace("\n", lineBreak);
        return str;
    }
}
