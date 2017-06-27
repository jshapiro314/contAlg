
public class BusStopExample3 {

    public static void main (String[] argv)
    {
        double numTrials = 10000;
        double numSuccesses = 0;
        double myArrivalTime = 2;

        for(int i=0;i<numTrials;i++){
            BusStop busStop = new BusStop (true);
            double arrivalTime = 0;
            double numBuses = -1;
            while (arrivalTime <= myArrivalTime) {
                numBuses ++;
                busStop.nextBus ();
                arrivalTime = busStop.getArrivalTime ();
            }

            if(numBuses == 3){
                numSuccesses++;
            }
        }
        System.out.println ("Pr[exactly 3 busses arrive during the interval [0,2]] = " + numSuccesses/numTrials);
    }

}
