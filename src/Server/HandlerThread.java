package Server;

import Protocols.FrogstarMessage;

import java.util.LinkedList;
import java.util.Queue;

public class HandlerThread implements Runnable {
    private UserState user;


    public HandlerThread(UserState user) {
        this.user = user;

    }

    @Override
    public void run() {
        //You've been spawned, negotiate a name for your user


        //You've got a name, do regular command things

        //Your user is logging off, do cleanup
    }


}
