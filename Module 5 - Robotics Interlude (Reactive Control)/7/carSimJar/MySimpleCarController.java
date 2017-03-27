
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

    String state = "start";


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

        double dN = sPack.sonarDistances[0];

        //using states:

        switch(state){
            case "start":
                if(dN < 30){
                    state = "turnLeft";
                    vel = 0;
                    phi = 10;
                    break;
                }

                vel = 10;
                phi = 0;
                break;
            case "turnLeft":
                if(sPack.getTheta() >= Math.PI/2){
                    state = "stop";
                    vel = 0;
                    phi = 0;
                    break;
                }

                vel = 0;
                phi = 10;
                break;
            case "stop":
                vel = 0;
                phi = 0;
                break;
            default:
                vel = 0;
                phi = 0;
                break;
        }
    }


    public void draw (Graphics2D g2, Dimension D)
    {
    }

}
