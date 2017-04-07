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
        double numSuccessesLess1 = 0;
        double numSuccessesLess11 = 0;
	for (int n=0; n<numTrials; n++) {
            BusStop busStop = new BusStop (true);                // True => first type of distribution.
            busStop.nextBus ();
            double interarrival = busStop.getInterarrivalTime ();
            if (interarrival < 1.0) {
                numSuccessesLess1 ++;
            }
            if( interarrival < 1.1){
                numSuccessesLess11 ++;
            }
            if(interarrival == 1){
                numSuccesses++;
            }

	}
	double probLess1 = numSuccessesLess1 / numTrials;
	System.out.println ("Pr[X < 1.0] = " + probLess1);
    System.out.println("Pr[X < 1.1] = " + numSuccessesLess11/numTrials);
    System.out.println("Pr[X = 1] = " + numSuccesses/numTrials);
    }

}
