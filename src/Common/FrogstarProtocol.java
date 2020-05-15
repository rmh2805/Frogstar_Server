package Common;

import Common.Exceptions.ParseException;

import java.io.InputStream;
import java.util.Scanner;

public interface FrogstarProtocol {
    //===================================<Constants>====================================//
    public static final int kDefPort = 5526;    // The default UDP port to listen on
    public static final String kTokenSep = "::";    // Separator for msg tokens

    //==================================<Server Tags>===================================//

    //==================================<Client Tags>===================================//

    //====================================<Methods>=====================================//

    /**
     * Returns the next line (if any) from `reader`
     * @param reader A scanner for the input stream to read from
     * @return The next line from the input stream (null if none available)
     */
    default String nextLine (Scanner reader) {
        if(!reader.hasNextLine())
            return null;

        String toReturn = reader.nextLine();
        if(toReturn == null || toReturn.isEmpty())
            return null;

        return toReturn;
    }

    /**
     * Handles creation (client side) or decoding/execution (server side) of client side commands
     *
     * @param strIn The data to process
     * @throws ParseException Thrown on failure to parse out input
     */
    void handleClientCommand(String strIn) throws ParseException;

    /**
     * Handles creation (server side) or decoding/execution (client side) of server side commands
     * @param strIn The data to process
     * @throws ParseException Thrown on failure to parse the input
     */
    void handleServerCommand(String strIn) throws ParseException;


}
