package Base_Server.Test;

import Base_Server.Command;

import java.util.Arrays;

public class TestCommand {

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
