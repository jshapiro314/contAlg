
public class Diff {

    public static void main (String[] argv)
    {
        
         Function F = new Function ("F");
         Function G = new Function ("G");

        double d = 0.01;
        // Compute for x-values in [0,10]
        for (double x=0; x<=10; x+=1) {
            // Compute f.
            double f = 3*x*x;

            // Compute (f(x+d) - f(x)) / d
            double f_xd = 3 * (x+d)*(x+d);
            double g = (f_xd - f) / d;

            F.add (x, f);
            G.add (x, g);
            
            // Print.
            System.out.println ("x=" + x + "  g(x)=" + g);
	       }

           // Display.
        F.show ();
        G.show ();
    }

}

