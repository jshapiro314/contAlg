
public class DieRollExample2 {

    public static void main (String[] argv)
    {
	double numTrials = 1000000;
	double numSuccesses = 0;

	Die die = new Die ();

	for (int n=0; n<numTrials; n++) {
	    // WRITE YOUR CODE HERE
	    int r1 = die.roll();
	    int r2 = die.roll();
	    if ((r1 % 2 == 1) && (r2 % 2 == 0)) {
		numSuccesses++;
	    }
	}

	double prob = numSuccesses / numTrials;

	System.out.println ("Pr[odd+even]=" + prob + "  theory=" + 0.25);
    }

}
