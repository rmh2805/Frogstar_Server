package Server;

public class ServerSingleton {
    static ServerSingleton server;

    public static void main(String[] args) {

    }

    public static void initialize() {
        server = new ServerSingleton();
    }


    public ServerSingleton() {

    }
}
