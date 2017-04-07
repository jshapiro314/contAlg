
public class ExponentialCDF {


    public static void main (String[] argv)
    {
        Function F = makeExponentialCDF ();
        Function G = new Function("DERIVATIVE");
		double interval = 0.06;
        for(double i=0.06;i<=3;i+=interval){
            G.add(i-interval,(F.get(i)-F.get(i-interval))/interval);
        }
        F.show ();
        G.show();
    }

    static Function makeExponentialCDF ()
    {
        // INSERT YOUR CODE HERE.
        double a = 0, b = 3;
    	int M = 50;                   // Number of intervals.
    	double delta = (b-a) / M;     // Interval size.

    	double[] intervalCounts = new double [M];
    	double numTrials = 1000000;

    	for (int t=0; t<numTrials; t++) {
                // Random sample:
    	    double y = RandTool.exponential (1);
                // Find the right interval:
                int k = (int) Math.floor ((y-a) / delta);
                // Increment the count for every interval above and including k.
                for (int i=k; i<M; i++) {
                    intervalCounts[i] ++;
                }
    	}

    	// Now compute probabilities for each interval.
    	double[] cdf = new double [M];
    	for (int k=0; k<M; k++) {
    	    cdf[k] = intervalCounts[k] / numTrials;
    	}

            // Build the CDF. Use mid-point of each interval.
            Function F = new Function ("Exponential cdf");
    	for (int k=0; k<M; k++) {
    	    double midPoint = a + k * delta + delta/2;
                F.add (midPoint, cdf[k]);
    	}

            return F;
    }

}
