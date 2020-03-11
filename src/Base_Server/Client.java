package Base_Server;

import java.io.IOException;

public abstract class Client {
    protected ClientInterface clientInterface;
    protected int id;

    protected Client(String host, int port) throws IOException {
        clientInterface = new ClientInterface(host, port);

        Command idConfirm = clientInterface.getCommand();
        while(idConfirm == null) idConfirm = clientInterface.getCommand();
        this.id = Integer.parseInt(idConfirm.getPayload());
    }

    protected abstract void executeCommand(Command command);
}
