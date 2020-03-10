package Base_Server;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static Base_Server.Command.decodeCommand;

public class Test {

    public static void main(String[] args) {
        String tag = "foo";

        String[] oneUserList = {"Jonath,an"};
        String[] twoUserList = {"Jonathan]", "Joseph,"};
        String[] threeUserList = {"Jonathan", "Josep'h", "]Jotaro"};


        String emptyPayload = "";
        String payload = "bar";

        String[][] userLists = {oneUserList, twoUserList, threeUserList};
        String[] payloads = {emptyPayload, payload};

        for (String[] userList : userLists) {
            for (String s : payloads) {
                Command command = new Command(tag, Arrays.asList(userList), s);

                command = Command.decodeCommand(command.encode());
                System.out.println(command);
            }
        }
    }
}
