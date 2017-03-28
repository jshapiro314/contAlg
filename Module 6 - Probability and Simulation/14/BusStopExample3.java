
public class BusStopExample3 {

    public static void main (String[] argv)
    {
        double myArrivalTime = 2;
        BusStop busStop = new BusStop (true); 
        double arrivalTime = 0;
        double numBuses = -1;
        while (arrivalTime < myArrivalTime) {
            numBuses ++;
            busStop.nextBus ();
            arrivalTime = busStop.getArrivalTime ();
        }
        System.out.println ("Number of buses: " + numBuses);
    }
    
}
