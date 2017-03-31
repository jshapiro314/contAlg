// BusStopExample.java
//
// Author: Rahul Simha
// Feb, 2008
//
// Estimate Pr[A>1] where A=interarrival time


public class BusStopExample {

    public static void main (String[] argv)
    {
	double numTrials = 1000000;
        double numSuccesses = 0;
        double numSuccesses5 = 0;
	for (int n=0; n<numTrials; n++) {
            BusStop busStop = new BusStop (false);                // True => first type of distribution.
            busStop.nextBus ();
            double interarrival = busStop.getInterarrivalTime ();
            if (interarrival > 1.0) {
                numSuccesses ++;
            }

            if (interarrival > 0.5) {
                numSuccesses5 ++;
            }

	}
	double prob = numSuccesses / numTrials;
    double prob5 = numSuccesses5 / numTrials;
	System.out.println ("Pr[A > 1.0] = " + prob);
    System.out.println ("Pr[A > 0.5] = " + prob5);
    }

}
