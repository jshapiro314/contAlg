
public class CardExample2 {

    public static void main (String[] argv)
    {
	double numTrials = 100000;
	double numSuccesses = 0;
	double tries = 0;
	for (int n=0; n<numTrials; n++) {

	    CardDeck deck = new CardDeck ();
	    int c1 = deck.drawWithoutReplacement ();
	    int c2 = deck.drawWithoutReplacement ();

	    // INSERT YOUR CODE HERE
        if (deck.isClub(c2)) {
	    	tries++;
	    	if (deck.isClub(c1)) {
	    		numSuccesses++;
	    	}
	    }


	}
	double prob = numSuccesses/tries;
	System.out.println ("Pr[c1=club given c2=club]=" + prob + "  theory=" + (12.0/51.0));
    }

}
