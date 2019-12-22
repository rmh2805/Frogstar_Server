package Protocols;

public class FrogstarMessage {
    private String head;
    private String body;

    public FrogstarMessage(String head, String body) {
        this.head = head;
        this.body = body;
    }

    public String getHead() {
        return head;
    }

    public String getBody() {
        return body;
    }

    @Override
    public String toString() {
        return FrogstarProtocol.catStrings(head, body);
    }
}
