package Protocols;

import static Protocols.FrogstarProtocol.catStrings;

public class FrogstarMessage {
    private String head;
    private String body;

    public FrogstarMessage(String head, String body) {
        this.head = head;
        this.body = body;
    }

    /**
     * Returns the head (tag) of the command
     *
     * @return The head/tag of the command
     */
    public String getHead() {
        return head;
    }

    /**
     * Returns the body of the command
     *
     * @return The body of the command
     */
    public String getBody() {
        return body;
    }

    /**
     * Returns the catted and sanitized head and body
     *
     * @return The catted and sanitized head and body
     */
    public String toString() {
        return FrogstarProtocol.sanitizeString(FrogstarProtocol.catStrings(head, body));
    }
}
