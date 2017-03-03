
public class InclineSimulatorExample2 {

    public static void main (String[] argv)
    {
        // Make a new instance of the class.
        InclineSimulator sim = new InclineSimulator ();

        // Set mass and angle.
        sim.mass = 1;
        sim.angle = 30;
        
        // Measure x(t) = distance moved along x-axis.
        Function dist = new Function ("dist");
        Function vel = new Function ("velocity");
        double xx = 0;

        for (double t=1; t<=10; t+=1) {
            sim.run (t);
            double x = sim.getX ();
            dist.add (t, x);
            if(t == 1){
                vel.add (t,0);

            }else{
                vel.add (t,x-xx);
            }
            xx = x;
        }
        
        // Display result.
        dist.show ();
        vel.show ();
    }

}
