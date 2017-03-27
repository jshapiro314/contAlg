
import java.text.*;

public class ConcCalcRecursive {
	// Rate parameters:
	public static double K_ab = 1.0;
	public static double K_c = 0.5;

	// Initial values:
	public static double A = 3.0;
	public static double B = 2.0;
	public static double C = 1.5;

	// Initialize time:
	public static double t = 0;

	// Set our time increment:
	public static double s = 0.01;

	// Set final time:
	public static double endTime = 1;

	//Counts:
	public static int countRecursive = 0;
	public static int countIterative = 0;

    public static void main (String[] argv)
    {
	// For printing/formatting:
	DecimalFormat df = new DecimalFormat ("###.###");



	// Compute iteratively
	while (t < endTime) {
		countIterative++;
	    // Compute the new values at time t+s:
		A =  A + s*(K_c*C-K_ab*A*B);   // INSERT YOUR CODE HERE
	    B =  B + s*(K_c*C-K_ab*A*B);   // INSERT YOUR CODE HERE
	    C =  C + s*(K_ab*A*B-K_c*C);   // INSERT YOUR CODE HERE

	    // Change t, and repeat.
	    t = t + s;
	}

	//Compute recursively
	double[] values = {3.0, 2.0, 1.5};
	double[] results = recursive(values,0);



	System.out.println("Iterative: A = " + A + ", B = " + B + ", C = " + C + ", Count = " + countIterative);
	System.out.println("Recursive: A = " + results[0] + ", B = " + results[1] + ", C = " + results[2] + ", Count = " + countRecursive);
}

	public static double[] recursive (double[] inputs, double currentTime){
		if(currentTime >= endTime){
			return inputs;
		}else{
		countRecursive++;
		double recA = inputs[0] + s*(K_c*inputs[2]-K_ab*inputs[0]*inputs[1]);
		double recB = inputs[1] + s*(K_c*inputs[2]-K_ab*inputs[0]*inputs[1]);
		double recC = inputs[2] + s*(K_ab*inputs[0]*inputs[1]-K_c*inputs[2]);
		// System.out.println(recA);
		// System.out.println(recB);
		// System.out.println(recC);
		// System.out.println(currentTime);
		// System.out.println();

		double[] initialInputs = {recA, recB, recC};

		return recursive(initialInputs, currentTime+s);
		}
	}
}
