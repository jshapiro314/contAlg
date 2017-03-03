
import java.util.*;
import java.awt.geom.*;
import java.awt.*;


public class jshapiro314CarController implements CarController {

    // Angular velocities.
    double mu1=0, mu2=1;
    ArrayList<Rectangle2D.Double> obstacles;
    SensorPack sensors;

    // Are the two controls accelerators?
    boolean isAccelModel;


    public void init (double initX, double initY, double initTheta, double endX, double endY, double endTheta, ArrayList<Rectangle2D.Double> obstacles, SensorPack sensors)
    {
        this.obstacles = obstacles;
        this.sensors = sensors;
    }
    

    public double getControl (int i)
    {
        if (i == 1) {
            return mu1;
        }
        else if (i == 2) {
            return mu2;
        }
        else {
            System.out.println ("ERROR: DubinCarController.getControl(): incorrect input");
            return 0;
        }
    }


    public void move ()
    {
        double angle = 0.75;
        double v1 = 10;
        double v2 = 9.4;


        mu1 = 0;
        mu2 = 0;
        double x = sensors.getX();
        double y = sensors.getY();
        double theta = sensors.getTheta();

        System.out.println("\nTIME = " + sensors.getTime());
        System.out.println("X = " + x);
        System.out.println("Y = " + y);
        System.out.println("THETA = " + theta);
        
        if(theta < angle && x < 51 && y < 51){
            System.out.println("ROTATE");
            mu1 = -10;
            mu2 = 0;
        }else if(y >= 48 && y <= 52 && theta > Math.PI*3/2){
            System.out.println("ROTATE 0");
            mu1 = -3;
            mu2 = 3;
        }else if(y >= 48 && y <= 52 && x > 480 && theta <= 0.1){
            System.out.println("STRAIGHT");
            mu1 = 10;
            mu2 = 10;
        }else{
            System.out.println("ARC");
            mu1 = v1;
            mu2 = v2;
        }

    }
    
    public void draw (Graphics2D g2, Dimension D)
    {
    }
    

}