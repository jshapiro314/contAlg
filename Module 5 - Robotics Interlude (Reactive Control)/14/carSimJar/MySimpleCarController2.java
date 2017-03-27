
import java.util.*;
import java.awt.geom.*;
import java.awt.*;


public class MySimpleCarController2 implements CarController {

    // The two controls: either (vel,phi) or (acc,phi)
    double acc;       // Acceleration.
    double vel;       // Velocity.
    double phi;       // Steering angle.

    ArrayList<Rectangle2D.Double> obstacles;
    SensorPack sensors;

    // Is the first control an accelerator?
    boolean isAccelModel = false;
    boolean isUnicycle = true;

    // Forward velocity for accelerative model.
    double v;


    double prevTheta = 0;
    double prevTime = 0;
    boolean firstTime = true;

    double endX, endY;

    double alpha = 10;
    double delta = 40;
    double epsilon = 0.05;
    double pet = 0;
    double kp = 0.5;
    double kd = 0.05;


    public void init (double initX, double initY, double initTheta, double endX, double endY, double endTheta, ArrayList<Rectangle2D.Double> obstacles, SensorPack sensors)
    {
        firstTime = true;
        this.obstacles = obstacles;
        this.sensors = sensors;
        this.endX = endX;
        this.endY = endY;
    }


    public double getControl (int i)
    {
	if (i == 1) {
	    if (isAccelModel) {
		return acc;
	    }
	    else {
		return vel;
	    }
	}
	else if (i == 2) {
	    return phi;
	}
	return 0;
    }


    public void move ()
    {
        if (! (sensors instanceof BasicSensorPack) ) {
            System.out.println ("ERROR: Incorrect sensor pack selection");
            return;
        }
        BasicSensorPack sPack = (BasicSensorPack) sensors;

        double dN = sPack.sonarDistances[0];   // Forward distance.

        // Use these in later exercises.
        double dNE = sPack.sonarDistances[7];  // Distance along NE direction.
        double dE = sPack.sonarDistances[6];
        double dSE = sPack.sonarDistances[5];  // Distance along SE direction.

        if(dNE > 2*dSE){
            dNE = 2*dSE;
        }else if(dSE > 2*dNE){
            dSE = 2*dNE;
        }

        double et = dSE - dNE;
        double etPrime = (et-pet)/0.1;
        pet = et;

        //calculate angle to goal
        double gamma = Math.atan2(endY-sPack.getY(), endX-sPack.getX());
        //System.out.println(gamma);
        // INSERT YOUR CODE HERE

        if(dN > delta && Math.abs(sPack.getTheta() - gamma) > epsilon){
            if(sPack.getTheta() - gamma < 0){
                vel = 0;
                phi = 5;
            }else{
                vel = 0;
                phi = -5;
            }

        }else if(dN > delta && Math.abs(sPack.getTheta() - gamma) < epsilon){
            vel = 10;
            phi = 0;
        }else{
            if(dE < delta){
                vel = 2;
                phi = 5;
            }else if(Math.abs(dNE-dSE) < alpha && dN > delta){
                vel = 5;
                phi = 0;
            }else{
                vel = 2;
                phi = kp*et - kd*etPrime;
            }
        }

    }


    public void draw (Graphics2D g2, Dimension D)
    {
    }

}
