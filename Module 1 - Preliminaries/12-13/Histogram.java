
public class Histogram {

    static int size = 10;

    public static void main (String[] argv)
    {
        int numSamples = 10000;
        int n = 493;
        histogramForU (n, numSamples);
        histogramForV (n, numSamples);
        histogramForW (n, numSamples);
    }
    
    static double computeU (int n)
    {
        //return UniformRandom.uniform ();
        return Math.pow(UniformRandom.uniform(),UniformRandom.uniform());
    }
    
    static double computeV (int n)
    {
        // INSERT YOUR CODE HERE.
        double sum = 0;
        for(int i=0;i<n;i++){
            sum += computeU(i);
        }
        sum /= n;
        return sum;
    }

        static double computeW (int n)
    {
        // INSERT YOUR CODE HERE.
        double v = computeV(n);

        return Math.sqrt(n) * (v - 0.7); 
    }

    static void histogramForU (int n, int numSamples)
    {
        // Create space: one counter per interval.
        int[] bins = new int [size];

        // Interval size.
        double interval = 1.0 / size;

        // Generate the data set, and identify the intervals.
        for (int k=0; k<numSamples; k++) {
            double u = computeU (n);
            int b = (int) Math.floor (u / interval);   // Which bin or interval?
            bins[b] ++;                                // Increment count accordingly.
        }

        // Print histogram.
        System.out.println ("Histogram for U");
        for (int b=0; b<size; b++) {
            System.out.println ("  b=" + b + " bins[b]=" + bins[b]);
        }
        
    }

    static void histogramForV (int n, int numSamples)
    {
        // INSERT YOUR CODE HERE.
                // Create space: one counter per interval.
        int[] bins = new int [size];

        // Interval size.
        double interval = 1.0 / size;

        // Generate the data set, and identify the intervals.
        for (int k=0; k<numSamples; k++) {
            double v = computeV (n);
            int b = (int) Math.floor (v / interval);   // Which bin or interval?
            bins[b] ++;                                // Increment count accordingly.
        }

        // Print histogram.
        System.out.println ("Histogram for V");
        for (int b=0; b<size; b++) {
            System.out.println ("  b=" + b + " bins[b]=" + bins[b]);
        }

    }


    static void histogramForW (int n, int numSamples)
    {
        // INSERT YOUR CODE HERE.
        // Create space: one counter per interval.
        int[] bins = new int [size];

        // Interval size.
        double interval = 1.0 / size;

        // Generate the data set, and identify the intervals.
        for (int k=0; k<numSamples; k++) {
            double w = computeW (n);
            int b = (int) Math.floor (w / interval);   // Which bin or interval?
            if(b < size && b >= 0){
                bins[b] ++;             // Increment count accordingly.
            }                                
        }

        // Print histogram.
        System.out.println ("Histogram for W");
        for (int b=0; b<size; b++) {
            System.out.println ("  b=" + b + " bins[b]=" + bins[b]);
        }
    }
    
    
}
