package Common._Test;

import Common.Exceptions.ParseException;
import Common.FrogstarProtocol;

import java.util.Scanner;

public class FrogstarProtocolTest implements FrogstarProtocol {
    public FrogstarProtocolTest() {
    }

    @Override
    public void handleClientCommand(String strIn) throws ParseException {

    }

    @Override
    public void handleServerCommand(String strIn) throws ParseException {

    }

    public static void main(String[] args) {
        String st = "";
        Scanner sc = new Scanner(st);
        FrogstarProtocolTest fp = new FrogstarProtocolTest();


        System.out.println("Scanning tokens until null token encountered");
        String next = fp.nextLine(sc);
        while(next != null) {
            System.out.print("Next: ");
            System.out.println(next);

            next = fp.nextLine(sc);
        }
        System.out.println("Done");
    }
}
