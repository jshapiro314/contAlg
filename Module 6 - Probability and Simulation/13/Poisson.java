
public class Poisson {

    public static void main (String[] argv)
    {
        double prob = poisson (2, 3);
        System.out.println ("Pr[X=3] = " + prob);
    }

    static double poisson (double gamma, int k)
    {
        // INSERT YOUR CODE HERE.
        double kFact = k;
        for(int i=k-1;i>1;i--){
            kFact *= i;
        }

        double retVal = Math.pow(Math.E,gamma*-1)*Math.pow(gamma,k)/kFact;

        return retVal;
    }

}
