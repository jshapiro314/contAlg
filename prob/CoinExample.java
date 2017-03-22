
public class CoinExample {

    public static void main (String[] argv)
    {
        // Flip a coin 5 times.
        Coin coin = new Coin ();
        for (int i=0; i<5; i++) {
            int c = coin.flip ();                           // Returns 1 (heads) or 0 (tails).
            System.out.println ("Flip #" + i + ": " + c);
        }
        System.out.println();
        StrangeCoin scoin = new StrangeCoin();
        int count0 = 0; int count1 = 0;
        for (int i = 0; i < 10000; i++) {
        	int c = scoin.flip();
        	if (c == 0) {
        		count0++;
        	} else {
        		count1++;
        	}
        	
        }
        double probability = (double)count1/(count0 + count1);
        System.out.println("probability of heads: " + probability);
    }
 
}
