
public class UniformExample3 {

    public static void main (String[] argv)
    {
        int numTrials = 100000;
        DensityHistogram hist = new DensityHistogram (0, 3, 20);
        double sum = 0;
        for (int n=0; n<numTrials; n++) {
            // INSERT YOUR CODE HERE
            double x = RandTool.uniform(0.0, 1.0);
            sum += 3*x;
            hist.add(3*x);
        }
        System.out.println ("Avg: " + sum/numTrials);
        hist.display ();
    }
    
}
