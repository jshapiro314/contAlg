
import java.text.*;

public class ConcCalc {

    public static void main (String[] argv)
    {
	// For printing/formatting:
	DecimalFormat df = new DecimalFormat ("###.###");

	// Rate parameters:
	double K_ab = 1.0;
	double K_c = 0.5;

	// Initial values:
	double A = 3.0;  
	double B = 2.0;
	double C = 1.5;

	// Initialize time:
	double t = 0;
	
	// Set our time increment:
	double s = 0.01;

	// Set final time:
	double endTime = 0.1;

	// Compute.
	while (t < endTime) {

	    // Compute the new values at time t+s:
	    A =     // INSERT YOUR CODE HERE
	    B =     // INSERT YOUR CODE HERE
	    C =     // INSERT YOUR CODE HERE

	    // Print out the values to screen:
	    System.out.println ("At time t=" + df.format(t) + ":  A(t)=" + df.format(A) + "   B=" + df.format(B) + "   C(t)=" + df.format(C));

	    // Change t, and repeat.
	    t = t + s;
	}

    }

}
