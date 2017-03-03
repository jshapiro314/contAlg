
public class BallTossSimulatorExample {

    public static void main (String[] argv)
    {
        BallTossSimulator sim = new BallTossSimulator ();
        
        Function dist = new Function ("distance");
        Function vel = new Function ("velocity");

        double dd = 0;
        for (double t=1; t<=10; t+=1) {
            double d = sim.run (t, 50);
            vel.add(t, d-dd);
            dd = d;
            dist.add (t, d);
        }

        dist.show ();
        vel.show ();
    }
    
}
