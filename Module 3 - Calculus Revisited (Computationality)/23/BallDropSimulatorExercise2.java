
public class BallDropSimulatorExercise2 {

    public static void main (String[] argv)
    {
        BallDropSimulator2 sim = new BallDropSimulator2 ();

        // Drop the ball from a height of 1000.
        double height = 1000;
        sim.run (height);
        
        double finalVelocity = sim.getV ();
        int count = 0;

        while ( Math.abs (finalVelocity + 200) > 1 ) {
            // Try different heights systematically.
            height += 1;
            sim.run (height);
            finalVelocity = sim.getV ();
            count++;
        }

        System.out.println ("Height required: " + height);
        System.out.println("COUNT = " + count);
    }
    
}
