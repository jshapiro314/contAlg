// CoinExample.java
//
// Estimate g(X)

public class CoinExample {

    public static void main (String[] argv)
    {
        // "Large" # trials.
	double numTrials = 1000000;

	Coin coin = new Coin (0.6);           // Pr[heads]=0.6

        double total = 0;
        
	for (int n=0; n<numTrials; n++) {

            // INSERT YOUR CODE HERE
        int c0 = coin.flip();
        int c1 = coin.flip();
        int c2 = coin.flip();
        double sum = c0 + c1 + c2;
        total += (sum - 1.8)*(sum - 1.8);

	}

        // Estimate. (No need to cast into double's)
	double avg = total / numTrials;

	System.out.println ("Avg value of g(X): " + avg);
    }
 
}
