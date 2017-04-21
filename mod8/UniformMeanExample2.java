
public class UniformMeanExample2 {

    public static void main (String[] argv)
    {
        double numTrials = 1000;                // n=10

        // The first "run": set the seed to some value.
        RandTool.setSeed (1234);
        double total = 0;
        Function f = new Function("avg");
        for (int i=1; i<=numTrials; i++) {
            double x = RandTool.uniform (); 
            total += x;
            f.add(i, total/i);
            System.out.println ("i=" + i + "  S_i/i=" + (total/i));
        }
        double avg = total / numTrials; 
        System.out.println ("Avg: " + avg);   

        // The second "run": set the seed to some other value and repeat.
        RandTool.setSeed (4321);
        total = 0;
        Function g = new Function("avg2");
        for (int i=1; i<=numTrials; i++) {
            double x = RandTool.uniform (); 
            total += x;
            g.add(i, total/i);
            System.out.println ("i=" + i + "  S_i/i=" + (total/i));
        }
        double avg2 = total / numTrials; 
        System.out.println ("Avg: " + avg);   
        Function.show(f, g);
    }

}
