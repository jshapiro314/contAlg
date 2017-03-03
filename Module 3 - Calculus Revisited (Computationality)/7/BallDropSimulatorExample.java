
public class BallDropSimulatorExample {

    public static void main (String[] argv)
    {
        BallDropSimulator sim = new BallDropSimulator ();
        
        Function dist = new Function ("distance");
        Function velocity = new Function("velocity");
        Function acceleration = new Function("acceleration");
        double dd = 0.0;
        double vv = 0.0;
        for (double t=1; t<=10; t+=1) {
            double d = sim.run (t, 1000);
            double vel = (d-dd)/0.01;
            double acc = (vel - vv)/0.01;
            velocity.add(t,vel);
            acceleration.add(t,acc);
            dd = d;
            vv = vel;
            dist.add (t, d);
        }
        
        dist.show ();
        velocity.show();
        acceleration.show();
    }
    
}
