// CoinExample3.java
//
// Estimate Pr[X=k] for 3-coin-flip example.

public class CoinExample3 {

    public static void main (String[] argv)
    {
        // "Large" # trials.
	double numTrials = 1000000;
    double num0 = 0;
    double num1 = 0;
    double num2 = 0;
    double num3 = 0;

	Coin coin = new Coin (0.6);           // Pr[heads]=0.6

	for (int t=0; t<numTrials; t++) {

            // INSERT YOUR CODE HERE
            int heads = coin.flip() + coin.flip() + coin.flip();
            if(heads == 0){
                num0++;
            }else if(heads == 1){
                num1++;
            }else if(heads == 2){
                num2++;
            }else if(heads == 3){
                num3++;
            }

	}

        // AND HERE
        double sN = 0*num0 + 1*num1 + 2*num2 + 3*num3;
        System.out.println("Average value of X using Sn/n = " + (sN/numTrials));
        double prob0 = num0/numTrials;
        double prob1 = num1/numTrials;
        double prob2 = num2/numTrials;
        double prob3 = num3/numTrials;
        double kN = 0*prob0 + 1*prob1 + 2*prob2 + 3*prob3;
        System.out.println("Estimated probability of 0 heads: " + prob0);
        System.out.println("Estimated probability of 1 heads: " + prob1);
        System.out.println("Estimated probability of 2 heads: " + prob2);
        System.out.println("Estimated probability of 3 heads: " + prob3);
        System.out.println("Summation of k*nk/n = " + kN);

    }

}
