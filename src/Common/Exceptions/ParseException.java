package Common.Exceptions;

public class ParseException extends Frogstar_Exception {
    public ParseException(String msg) {
        super("Parse Error:" + msg);
    }
}
