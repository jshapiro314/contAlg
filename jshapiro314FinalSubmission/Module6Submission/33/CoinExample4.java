import java.util.*;
// CoinExample4.java
//
// #flips needed for first heads

public class CoinExample4 {

    public static void main (String[] argv)
    {
        // "Large" # trials.
	double numTrials = 1000000;
    HashMap<Double,Double> counts = new HashMap<Double,Double>();

	Coin coin = new Coin (0.1);           // Pr[heads]=0.1

	for (int t=0; t<numTrials; t++) {

        // INSERT YOUR CODE HERE
        Double count = 1.0;
        while(coin.flip() == 0){
            count++;
        }

        //Need to use hashmap
        if(counts.containsKey(count)){
            counts.put(count,counts.get(count)+1);
        }else{
            counts.put(count,1.0);
        }

	}

        // AND HERE
        double sN = 0;
        Set keys = counts.keySet();
        Iterator it = keys.iterator();
        while(it.hasNext()){
            Double temp = (Double)it.next();
            sN  = sN + temp*counts.get(temp);
        }

        sN = sN/numTrials;
        System.out.println("Average value of X = " + sN);
    }

}
