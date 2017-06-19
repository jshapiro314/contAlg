
public class UniformExample {

    public static void main (String[] argv)
    {
        int numTrials = 100000;
        DensityHistogram hist = new DensityHistogram (0,1, 20);
        double sum = 0;
        double sumX = 0;
        for (int n=0; n<numTrials; n++) {
            // INSERT YOUR CODE HERE
            double x = RandTool.uniform(0.0, 1.0);
            sum += x*x;
            hist.add(x*x);
            sumX += x;
        }
        System.out.println ("Avg x^2: " + sum/numTrials);
        System.out.println("Avg x: " + sumX/numTrials);
        hist.display ();
    }

}
