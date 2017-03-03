
public class SequenceExample {

    public static void main (String[] argv)
    {
        int n = 10;
        System.out.println ("An, n=" + n + ": " + computeA(n));
        n = 100;
        System.out.println ("An, n=" + n + ": " + computeA(n));
        n = 1000;
        System.out.println ("An, n=" + n + ": " + computeA(n));
        n = 10000;
        System.out.println ("An, n=" + n + ": " + computeA(n));
        
    }

    static double computeA (int n)
    {
        // INSERT YOUR CODE HERE.
        double retVal = Math.pow(1+(1/(double)n),n);
        return retVal;
    }
    
}
