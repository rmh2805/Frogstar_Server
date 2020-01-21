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

    /**
     * Concatenates parts of a message together, marking the boundary
     * @param l The first piece to be concatenated
     * @param r The second piece to be concatenated
     * @return r concatenated to the separator concatenated to l
     */
    public static String catStrings (String l, String r) {
        return l + separator + r;
    }

    /**
     * Cleans the string for parsing and sending
     * @param str The string to clean
     * @return The cleaned string
     */
    public static String sanitizeString (String str) {
        str = str.replace("\n", lineBreak);
        return str;
    }

    /**
     * Undoes sanitizeString to restore original content
     * @param str The cleaned string
     * @return The original string
     */
    public static String desanitizeString (String str) {
        str = str.replace(lineBreak, "\n");
        return str;
    }
}
