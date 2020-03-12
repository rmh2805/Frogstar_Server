package Base_Server;

import Base_Server.Command;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 * Used on both ends of each connection, handle network interfacing
 */
public class FrogstarInterface {
    private boolean running;

    private Socket socket;
    private PrintWriter networkOut;
    private Scanner networkIn;

    /**
     * Opens a new interface on the given socket
     *
     * @param socket The socket to open the interface on
     * @throws IOException Thrown on failure to get IO streams
     */
    public FrogstarInterface(Socket socket) throws IOException {
        this.running = true;

        this.socket = socket;
        this.networkIn = new Scanner(socket.getInputStream());
        this.networkOut = new PrintWriter(socket.getOutputStream());
    }

    /**
     * Opens a new interface on a socket to host:port
     *
     * @param host The host to connect to
     * @param port The port to connect to
     * @throws IOException Thrown on failure to get IO streams or to open a new socket
     */
    public FrogstarInterface(String host, int port) throws IOException {
        this(new Socket(host, port));
    }

    /**
     * Send a command down the line if the interface is running
     *
     * @param command The command to send
     */
    public void sendCommand(Command command) {
        if (!running) return;
        writeCommand(command);
    }

    /**
     * Read the next command from the line (auto fails if interface isn't running)
     *
     * @return The next command from the line
     */
    public Command getCommand() {
        if (!running) return null;
        return readCommand();
    }

    /**
     * Returns true if the interface is accepting IO
     *
     * @return Whether or not the interface is accepting IO
     */
    public boolean isRunning() {
        return running;
    }

    /**
     * Set the flag to ignore incoming and outgoing requests, write a stop command to the line, and close connections
     */
    public void stop() {
        if(!running) return;    //Fail out if already called

        running = false;
        writeCommand(new Command(Command.stopTag));
        try {
            socket.close();
            networkOut.close();
            networkIn.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * Returns the next command from the line (if available)
     *
     * @return The next command read from the line (null if no command available)
     */
    private synchronized Command readCommand() {
        if (!networkIn.hasNextLine()) return null;
        Command read = Command.decodeCommand(networkIn.nextLine());
        if(read == null) return null;
        if(read.getTag().equals(Command.stopTag)) {
            this.stop();
            return null;
        }

        return read;
    }

    /**
     * Write a command to the line.
     *
     * @param command The command to write
     */
    private synchronized void writeCommand(Command command) {
        networkOut.println(command.encode());
        networkOut.flush();
    }

}
