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

    }


}
