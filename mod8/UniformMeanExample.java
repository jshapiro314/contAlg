
public class UniformMeanExample {

    public static void main (String[] argv)
    {
        double numTrials = 1000;                // n=10
        double total = 0;
        Function f = new Function("avg");
        for (int i=1; i<=numTrials; i++) {
            double x = RandTool.uniform ();   // X_i = outcome of i-th call to uniform()
            total += x;
            double avg = total / i;       // S_n / n
            f.add(i, avg);                      // = (X_1 + ... + X_n) / n
            System.out.println ("Avg: " + avg);  
        }
        f.show();
         
    }

}
