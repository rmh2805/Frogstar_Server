package Common.Exceptions;

public class Frogstar_Exception extends Exception {
    public Frogstar_Exception(String msg) {
        super("Frogstar Exception: " + msg);
    }
}
