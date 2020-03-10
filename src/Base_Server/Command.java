package Base_Server;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.*;

public class Command {
    private String tag;
    private List<String> targets;
    private String payload;

    public static final String failedCommandTag = "fail";
    public static final String idTag = "id";

    //=============================================<Replacement Strings>==============================================//
    private static final String commaReplacement = ":c:";
    private static final String closeBracketReplacement = ":cb:";
    private static final String apostropheReplacement = ":a:";

    private static String getSubstring(String str, char startCh, char endCh) {
        int startIdx = str.indexOf(startCh);
        int endIdx = str.lastIndexOf(endCh);
        if (startIdx >= endIdx) {
            return null;
        }

        return str.substring(startIdx + 1, endIdx);
    }

    private static String cleanString(String str) {
        if (str == null) return null;

        String cleanStr = str.replaceAll(",", commaReplacement);
        cleanStr = cleanStr.replaceAll("]", closeBracketReplacement);
        return cleanStr.replaceAll("'", apostropheReplacement);
    }

    private static String uncleanString(String cleanStr) {
        if (cleanStr == null) return null;

        String str = cleanStr.replaceAll(commaReplacement, ",");
        str = str.replaceAll(closeBracketReplacement, "]");
        return str.replaceAll(apostropheReplacement, "'");
    }

    public String encode() {
        StringBuilder toReturn = new StringBuilder();
        toReturn.append("Command{tag='").append(cleanString(tag)).append("', ");
        toReturn.append("targets=[");
        for (int i = 0; i < targets.size(); i++) {
            if (i != 0) {
                toReturn.append(", ");
            }
            toReturn.append(cleanString(targets.get(i)));
        }
        toReturn.append("], ");
        toReturn.append("payload='").append(cleanString(payload)).append("'}");
        return toReturn.toString();
    }

    //Command Format is Command{tag='foo', targets=[], payload=''}
    public static Command decodeCommand(String str) {
        str = str.strip();
        if (str.indexOf(Command.class.getSimpleName()) != 0) return null;

        str = getSubstring(str, '{', '}');
        if (str == null) return null;

        String[] data = str.split(",", 2);
        if (data.length < 2) return null;
        String tag = uncleanString(getSubstring(data[0], '\'', '\''));


        str = data[1];
        String targetString = getSubstring(str, '[', ']');
        if (targetString == null) return null;
        List<String> targets = new LinkedList<>();
        for (String target : targetString.split(", "))
            targets.add(uncleanString(target));


        String payload = uncleanString(getSubstring(str.substring(str.lastIndexOf(']')), '\'', '\''));


        return new Command(tag, targets, payload);
    }

    public Command(String tag, List<String> targets, String payload) {
        this.tag = tag;
        if(targets == null)
            this.targets = new LinkedList<>();
        else
            this.targets = new LinkedList<>(targets);
        this.payload = payload;
    }


    public Command(String tag, String payload) {
        this(tag, null, payload);
    }

    public String getTag() {
        return tag;
    }

    public List<String> getTargets() {
        return targets;
    }

    public String getPayload() {
        return payload;
    }

    @Override
    public String toString() {
        return "Command{" +
                "tag='" + tag + '\'' +
                ", targets=" + targets +
                ", payload='" + payload + '\'' +
                '}';
    }
}
