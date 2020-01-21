package Errors;

public class BadName extends Exception {
    private String name;

    /**
     * Raised when a username is targeted, without that name being logged onto the server
     * @param name The missing name
     */
    public BadName (String name) {
        super("Couldn't find any user " + name);
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
