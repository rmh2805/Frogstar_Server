package Errors;

public class BadName extends Exception {
    public BadName (String name) {
        super("Couldn't find any user " + name);
    }
}
