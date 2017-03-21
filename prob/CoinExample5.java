
public class CoinExample5 {

    public static void main (String[] argv)
    {
        // "Large" # trials.
	double numTrials = 1000000;

        // Count # times desired outcome shows up.
	double numSuccesses = 0;

	Coin coin = new Coin (0.6);           // Pr[heads]=0.6

	for (int n=0; n<numTrials; n++) {

	    // WRITE YOUR CODE HERE
	    int c1 = coin.flip();
	    int c2 = coin.flip();
	    if (c1 == 1 || c2 == 1) {
	    	numSuccesses++;
	    }
	}

        // Estimate. (No need to cast into double's)
	double prob = 1 - (numSuccesses / numTrials);

	System.out.println ("Pr[need at least 3 flips]=" + prob + "  theory=" + 0.16);
    }
 
}
