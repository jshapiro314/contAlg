
import java.util.*;
import java.awt.geom.*;
import java.awt.*;


public class MySimpleCarController3 implements CarController {

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
    String state = "turnToGoal";

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
        double distGoal = Math.sqrt((endY-sPack.getY())*(endY-sPack.getY()) + (endX-sPack.getX())*(endX-sPack.getX()));
        //System.out.println(gamma);
        // INSERT YOUR CODE HERE

        if(state.equals("turnToGoal")){
            phi = kp*(gamma - sPack.getTheta());
            vel = 0;
            if(Math.abs(gamma - sPack.getTheta()) < epsilon){
                state = "moveForward";
            }
        }else if(state.equals("moveForward")){
            if(dN > delta && Math.abs(sPack.getTheta() - gamma) < epsilon){
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
            if(openToGoal(gamma, distGoal, sPack.getTheta(), sPack.sonarDistances)){
                state = "turnToGoal";
            }
        }
    }


    boolean openToGoal (double goalAngle, double distToGoal, double theta, double[] sonarDistances)
    {
	double sectorAngle = 2*Math.PI / sonarDistances.length;
	for (int i=0; i<sonarDistances.length; i++) {

            // Get the angle of the i-th range sensor.
	    double a1 = angleFix (theta + i * sectorAngle);
	    int j = i + 1;
	    if (j >= sonarDistances.length) {
		j = 0;
	    }

            // Get the angle of the next (successive) sensor in anti-clockwise direction.
	    double a2 = angleFix (theta + j * sectorAngle);

            // If the goal angle is between
	    if ( isBetween (a1, goalAngle, a2) ) {
                // Check if the ranges are farther than goal.
		if ( (sonarDistances[i] > distToGoal) && (sonarDistances[j] > distToGoal) ) {
                    // If so, the goal is assumed to be visible.
		    return true;
		}
		else {
                    // Else not.
		    return false;
		}
	    }
	}

	// Shouldn't reach here.
	return false;
    }



    double angleFix (double a)
    {
        // Make each angle an angle between 0 and 2*PI.
        //** Note: this code can be optimized.
        if (a < 0) {
            while (a < 0) {
                a = a + 2*Math.PI;
            }
        }
        else if (a > 2*Math.PI) {
            while (a > 2*Math.PI) {
                a = a - 2*Math.PI;
            }
        }
        return a;
    }


    boolean isBetween (double a1, double a, double a2)
    {
        // Adjust so that all are between 0 and 2*pi.
        a1 = angleFix (a1);
        a = angleFix (a);
        a2 = angleFix (a2);

        if (a1 <= a2) {
            // This is the simple case.
            if ( (a1 <= a) && (a <= a2) ) {
                return true;
            }
            return false;
        }

        if ( (a <= a2) || (a >= a1) ) {
            return true;
        }

        return false;
    }



    public void draw (Graphics2D g2, Dimension D)
    {
    }

}
