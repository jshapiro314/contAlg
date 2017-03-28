
public class CoinExample {

    public static void main (String[] argv)
    {
        // Flip a coin 5 times.
        int heads = 0;
        StrangeCoin coin = new StrangeCoin ();
        for (int i=0; i<10000; i++) {
            int c = coin.flip ();                           // Returns 1 (heads) or 0 (tails).
            heads = heads + c;
            //System.out.println(heads);
            System.out.println ("Flip #" + i + ": " + c);
        }
        System.out.println ("Probability of heads = " + (double)heads/10000);
    }

}
