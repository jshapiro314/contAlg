
import java.util.*;
import java.awt.geom.*;
import java.awt.*;


public class MySimpleCarController implements CarController {

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

    double prevE = 0;

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
        double dSE = sPack.sonarDistances[5];  // Distance along SE direction.
        double dE = sPack.sonarDistances[6];

        // INSERT YOUR CODE HERE
        double alpha = 10;
        double delta = 40;
        double deltaE = 30;
        double kp = 1;
        double kd = 0.5;
        if(dNE > 2* dSE){
            dNE = 2*dSE;
        }
        if (dSE > 2 * dNE) {
            dSE = 2 * dNE;
        }
        double e = dSE - dNE;
     
        if (dE < deltaE) {
            vel = 2;
            phi = 5;
        } else if (Math.abs(e) < alpha && dN > delta) {
            vel = 10;
            phi = 0;
        } else {
            vel = 3;
            phi = kp * e - kd * Math.abs(e - prevE)/0.1;
        }
        prevE = e;
        /*} else if (dNE > dSE) {
            vel = 2;
            phi = -10;//(-Math.PI/2)*(20/Math.PI);
        } else {
            vel = 2;
            phi = 10;//Math.PI/2*(20/Math.PI);
        }*/
    }


    public void draw (Graphics2D g2, Dimension D)
    {
    }

}
