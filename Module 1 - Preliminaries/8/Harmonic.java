
public class Harmonic {

    public static void main (String[] argv)
    {
        int n = 10;
        System.out.println (sumDouble(n));
        System.out.println (sumFloat(n));
        n = 100;
        System.out.println (sumDouble(n));
        System.out.println (sumFloat(n));
        n = 1000;
        System.out.println (sumDouble(n));
        System.out.println (sumFloat(n));
        n = 10000;
        System.out.println (sumDouble(n));
        System.out.println (sumFloat(n));
    }

    static double sumDouble (int n)
    {
        // INSERT YOUR CODE HERE.
        double retVal = 0;
        for(int i=1;i<=n;i++){
            double temp = (double)1/i;
            retVal = retVal + temp;
        }
        return retVal;
    }

    static float sumFloat (int n)
    {
        // INSERT YOUR CODE HERE.
        float retVal = 0;
        for(int i=1;i<=n;i++){
            retVal = retVal + (1/(float)i);
        }
        return retVal;
    }
    
}
