
public class InclineVelocityExample {

    public static void main (String[] argv)
    {
        // Make a new instance of the class.
        InclineSimulator sim = new InclineSimulator ();

        // Set mass and angle.
        sim.mass = 1;
        sim.angle = 30;
        
        // We'll make a Function object to store "velocity" readings.
        Function velocity = new Function ("velocity");
        Function acceleration = new Function ("acceleration");

        for (double t=1; t<=10; t+=1) {
            // Run simulator up to time t.
            sim.run (t);
            // Get the velocity and put that in our function object.
            double v = sim.getV ();
            velocity.add (t, v);
            sim.run(t+1);
            double vv = sim.getV();
            double a = vv-v;
            acceleration.add (t,a);

            System.out.println("Time = " + t);
            System.out.println("Velocity = " + v);
        }
        
        // Display result.
        velocity.show ();
        acceleration.show ();
    }

}
