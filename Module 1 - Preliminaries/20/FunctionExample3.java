
import java.util.*;

public class FunctionExample3 {

    public static void main (String[] argv)
    {
        // Make a Function object and give it a name.
        Function F = new Function ("Silly function");

        for (double x=0; x<=5; x=x+1) {
            double f = 1/(x-2);              
            // Feed the x,f(x) combinations into the object.
            F.add (x, f);
        }

        // Write to screen.
        System.out.println (F);

        // Display.
        F.show ();
    }

}
