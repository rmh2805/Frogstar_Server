package Server;

public class ServerSingleton {
    private static ServerSingleton server;

    public static void main(String[] args) {
        initialize();
        //Spawn acceptor and handler threads
    }

    public static void initialize() {
        server = new ServerSingleton();
    }

    public ServerSingleton getServer() {
        return server;
    }

    private ServerSingleton() {

    }
}
