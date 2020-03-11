package Base_Server;

import java.io.IOException;

public class SimpleClient extends Client implements Runnable {
    private static final String sayTag = "Say";

    private boolean running;

    public SimpleClient(String host, int port) throws IOException {
        super(host, port);
        this.running = true;
    }

    @Override
    protected void executeCommand(Command command) {
        if (command == null) return;

        if (command.getTag().equals(Command.stopTag)) {
            try {
                this.stop();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if (command.getTag().equals(sayTag)) {
            System.out.print("You <- " + command.getTargets().get(0) + "\t:");
            System.out.println(command.getPayload());
        }
        else {
            System.out.println("Unrecognized command '" + command.toString() + "'");
        }
    }


    public void Say(String payload) {
        Command toSend = new Command(sayTag, payload);
        clientInterface.sendCommand(toSend);
    }

    /**
     * Prints incoming commands to the screen
     */
    @Override
    public void run() {

        while (running) {
            Command commandIn = null;
            while (commandIn == null && running) commandIn = clientInterface.getCommand();
            if (commandIn == null) break;

            this.executeCommand(commandIn);
        }
    }

    public void stop() throws IOException {
        this.running = false;
        clientInterface.stop();
    }
}
