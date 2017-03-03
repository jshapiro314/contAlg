
import java.util.*;

public class FunctionExample3 {

    public static void main (String[] argv)
    {
        // Make a Function object and give it a name.
        Function F = new Function ("Silly function");

        for (double x=0; x<=5; x=x+0.05) {
            //double f = 3*x + 5;
            //double f = x*x - 2;
            //double f = 5/(x*x);
            double f = Math.pow(Math.E,-2*x);              
            // Feed the x,f(x) combinations into the object.
            F.add (x, f);
        }

        // Write to screen.
        System.out.println (F);

        // Display.
        F.show ();
    }

}
