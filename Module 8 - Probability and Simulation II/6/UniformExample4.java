
public class UniformExample4 {

    public static void main (String[] argv)
    {
        int numTrials = 100000;
        double total = 0;
        for (int n=0; n<numTrials; n++) {
            // INSERT YOUR CODE HERE
            double x = RandTool.uniform(0.0, 1.0);
            total += (x - 0.5)*(x - 0.5);
        }
        System.out.println ("Avg value of g(X): " + total/numTrials);
    }
    
}
