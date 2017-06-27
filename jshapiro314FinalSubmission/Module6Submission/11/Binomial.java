
public class Binomial {

    public static void main (String[] argv)
    {
        double prob = binomial (10, 0.6, 3);
        System.out.println ("Pr[X=3] = " + prob);
    }

    static double binomial (int n, double p, int k)
    {
        // INSERT YOUR CODE HERE.

        //Let's first calculate N choose K. The formula is (n!)/((n-k)!k!)
        double nFact = n;
        for(int i=n-1;i>1;i--){
            nFact *= i;
        }

        double kFact = k;
        for(int i=k-1;i>1;i--){
            kFact *= i;
        }

        double nkFact = n-k;
        for(int i=n-k-1;i>1;i--){
            nkFact *= i;
        }

        double nChooseK = nFact/(nkFact*kFact);

        //Now let's do the rest of the formula.
        double retVal = nChooseK * Math.pow(p,k) * Math.pow(1-p,n-k);
        return retVal;

    }

}
