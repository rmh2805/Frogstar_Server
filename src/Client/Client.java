package Client;

import Common.Exceptions.ParseException;
import Common.FrogstarProtocol;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class Client implements FrogstarProtocol{
    //============================================================================================//
    //=========================================<Members>==========================================//
    //============================================================================================//
    //=================================<Network Layer>==================================//
    private Socket socket;  // Connection to server

    //=======================================<IO>=======================================//
    //==============================<Console IO>==============================//
    private Scanner consoleReader;
    //Console output is handled by System.out.print()

    //==============================<Server IO>===============================//
    private Scanner serverReader;
    private PrintStream serverPrinter;

    //=================================<Misc>=================================//
    private boolean signedIn;
    private boolean String;

    //============================================================================================//
    //=========================================<Methods>==========================================//
    //============================================================================================//
    //=================================<Initialization>=================================//
    public Client() {
        this("localhost", kDefPort);
    }

    public Client(String hostName) {
        this(hostName, kDefPort);
    }

    /**
     * Connects to the
     * @param hostName
     * @param port
     */
    public Client(String hostName, int port) {
        try {
            this.socket = new Socket(hostName, port);
        } catch (IOException e) {
            System.err.println("Failed to connect to Server @ " + hostName + ":" + port + ". Shutting Down.");
            System.exit(0);
        }
    }

    @Override
    public void handleClientCommand(java.lang.String strIn) throws ParseException {

    }

    @Override
    public void handleServerCommand(java.lang.String strIn) throws ParseException {

    }
}
