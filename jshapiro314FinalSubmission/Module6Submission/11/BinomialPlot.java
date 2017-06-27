
public class BinomialPlot {

    public static void main (String[] argv)
    {
        double prob = binomial (10, 0.6, 3);
        System.out.println ("Pr[X=3] = " + prob);


        Function plot = new Function("Binomaial Plot n = 10, p = 0.6");
        Function plot2 = new Function("Binomaial Plot n = 10, p = 0.2");

        for(int i=1;i<10;i++){
            double result = binomial(10,0.6,i);
            System.out.println(i + ", " + result);
            plot.add(i,result);
            plot2.add(i,binomial(10,0.2,i));
        }

        Function.show(plot,plot2);

    }

    static double binomial (int n, double p, int k)
    {
        // INSERT YOUR CODE HERE.

        //Let's first calculate N choose K. The formula is (n!)/((n-k)!k!)
        double nFact = (double)n;
        for(int i=n-1;i>1;i--){
            nFact *= i;
        }
        //System.out.println(nFact);

        double kFact = (double)k;
        for(int i=k-1;i>1;i--){
            kFact *= i;
        }
        //System.out.println(kFact);

        double nkFact = (double)(n-k);
        for(int i=n-k-1;i>1;i--){
            nkFact *= i;
        }
        //System.out.println(nkFact);

        double nChooseK = nFact/(nkFact*kFact);
        //System.out.println(nChooseK);
        //Now let's do the rest of the formula.
        double retVal = nChooseK * Math.pow(p,k) * Math.pow(1-p,n-k);
        //System.out.println(retVal);
        return retVal;

    }

}
