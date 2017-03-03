
public class ProjectileExercise3 {

    public static void main (String[] argv)
    {
        // Make an instance of our new simulator.
        ProjectileSimulator2 sim = new ProjectileSimulator2 ();

        // Launch velocity is 50.
        double initV = 0;
        
        // Try angles in the range [10,80]
        double bestAngle = 10;
        double bestVel = 0;
        double bestTime = 0;
        
        for (double angle=10; angle<=80; angle+=1) {
            for (initV = 0;initV <= 500;initV+=0.01){
                bestTime = sim.run (angle, initV);
                double x = sim.getX ();
                //System.out.println(x);
                if (x >= 200 && x <= 200.1) {
                    bestAngle = angle;
                    bestVel = initV;
                    System.out.println ("Best angle: " + bestAngle);
                    System.out.println ("Best velocity: " + bestVel);
                    System.out.println ("Best time: " + bestTime);
                    System.out.println ("X = " + x);

                    break;
                }
            }
        }


    }

  
}