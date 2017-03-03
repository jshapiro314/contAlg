
public class InclineSimulatorExample {

    public static void main (String[] argv)
    {
        // Make a new instance of the class.
        InclineSimulator sim = new InclineSimulator ();

        // Set mass and angle.
        sim.mass = 1;
        sim.angle = 30;
        
        // We'll measure distance travelled at t=1, t=2, ... and put
        // these values into a Function object.
        Function dist = new Function ("dist");
        //Function derivative = new Function("derivative");
        //double dd = 0.0;

        for (double t=1; t<=10; t+=0.01) {
            double d = sim.run (t);
            //derivative.add(t,(d-dd)/0.01);
            //dd = d;
            dist.add (t, d);
            System.out.println ("t=" + t + "  d=" + d);
        }
        
        // Display result.
        dist.show ();
        //derivative.show();
    }

}
