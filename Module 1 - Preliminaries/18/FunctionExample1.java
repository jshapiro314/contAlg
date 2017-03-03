
import java.util.*;

public class FunctionExample1 {

    public static void main (String[] argv)
    {
        // This is what reads from the keyboard:
        Scanner scanner = new Scanner (System.in);

        // Put out a prompt:
        System.out.print ("Enter x: ");

        // Read in a "double" (real) value:
        double x = scanner.nextDouble ();

        // Compute function:
        double f = 1/(x-2);

        // Print result (output):
        System.out.println ("x = " + x + ", f = " + f);
    }

}
