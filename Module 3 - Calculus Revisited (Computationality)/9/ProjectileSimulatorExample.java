
public class ProjectileSimulatorExample {

    public static void main (String[] argv)
    {
        // Make a new simulator object.
        ProjectileSimulator proSim = new ProjectileSimulator ();

        // We want to plot d vs. t
        Function dist = new Function ("distance");
        Function vel = new Function ("velocity");
        Function acc = new Function ("acceleration");

        double v = 0;
        for (double t=0.1; t<=2.3; t+=0.1) {
            // mass=1, angle=37, initVel=20, s=0.01
            proSim.run (1, 70, 20, t, 0.01);
            double d = proSim.getD();
            dist.add (t, proSim.getD());
            proSim.run (1, 70, 20, t+0.01, 0.01);
            double dd = proSim.getD();
            double vv = (dd-d)/0.1;
            vel.add (t, vv);
            acc.add (t, (vv-v)/0.1);
            v = vv;
        }

        // Display.
        dist.show ();
        vel.show ();
        acc.show ();
    }

}
