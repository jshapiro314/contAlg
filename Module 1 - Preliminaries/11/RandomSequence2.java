
public class RandomSequence2 {

    public static void main (String[] argv)
    {
        for (int n=1; n<=10000; n++) {
            System.out.println ("Vn, n=" + n + ": " + computeV(n));
        }
    }

    static double computeV (int n)
    {
	// INSERT YOUR CODE HERE
        double sum = 0;
        for(int i=0;i<n;i++){
            sum += computeU(i);
        }
        sum /= n;
        return sum;
    }

    static double computeU (int n)
    {
        //return UniformRandom.uniform ();
        return UniformRandom.uniform();
    }
    
}

