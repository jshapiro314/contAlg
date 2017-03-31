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
    double count0 = 0;
    double count1 = 0;
    double count2 = 0;
    double count3 = 0;
    double count5 = 0;
    double count6 = 0;
	for (int n=0; n<numTrials; n++) {
            BusStop busStop = new BusStop (true);                // True => first type of distribution.
            double arrivalTime = 0;
            count2 = 0;
            count3 = 0;
            count5 = 0;
            while(arrivalTime <= 1){
            busStop.nextBus ();
            arrivalTime = busStop.getArrivalTime();

            if(arrivalTime >= 0.5 && arrivalTime <= 1){
                count2++;
                //System.out.println(count2);

            }
            if(arrivalTime >= 0 && arrivalTime <= 0.5){
                count3++;

            }

        }

        if(count2 == 1){
            count1++;

        }
        if(count3 == 1){
            count0++;
        }

        if(count5 == 1){
            count6++;
        }
        }
	double prob = count1 / numTrials;
    double probGiven = count6/count0;
	System.out.println ("Pr[B2] " + prob);
    }

}
