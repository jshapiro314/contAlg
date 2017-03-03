
public class BallDropSimulatorExercise3 {

    public static void main (String[] argv)
    {
        BallDropSimulator2 sim = new BallDropSimulator2 ();

        // Drop the ball from a height of 1000.
        double height = 1000;
        sim.run (height);

        double vel1000 = sim.getV();
        
        double finalVelocity = sim.getV();

        while ( finalVelocity < vel1000/2 ) {
            // Try different heights systematically.
            height -= 1;
            sim.run (height);
            finalVelocity = sim.getV ();
        }

        System.out.println ("Height required: " + height);
        System.out.println ("Time required: " + sim.getT());
        System.out.println ("Final Velocity: " + finalVelocity);
        System.out.println ("1000 Velocity: " + vel1000);
    }
    
}
