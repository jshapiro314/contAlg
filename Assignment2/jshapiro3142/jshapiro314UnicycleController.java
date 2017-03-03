import java.util.*;
import java.awt.geom.*;
import java.awt.*;


public class jshapiro314UnicycleController implements CarController {

    //keep track of how many times move is called
    int count = 0;

    double epsilon = 0.2;
    double epsilonAngle = 0.05;

    //Store the solution
    LinkedList<jshapiro314UnicycleState> solution;


    // Linear velocity.
    double linVel = 0;
    // Anglular velocity
    double angVel = 0;

    ArrayList<Rectangle2D.Double> obstacles;
    SensorPack sensors;

    // Are the two controls accelerators?
    boolean isAccelModel;


    public void init (double initX, double initY, double initTheta, double endX, double endY, double endTheta, ArrayList<Rectangle2D.Double> obstacles, SensorPack sensors)
    {
        this.obstacles = obstacles;
        this.sensors = sensors;
        count = 0;
        linVel = 0;
        angVel = 0;

        jshapiro314UnicycleState startState = new jshapiro314UnicycleState(null,initX,initY,initTheta);
        jshapiro314UnicycleProblem currentProblem = new jshapiro314UnicycleProblem(endX, endY, obstacles, startState);

        jshapiro314Planner planner = new jshapiro314Planner();

        solution = planner.makePlan(currentProblem, startState);
        System.out.println("MADE PLAN");
    }
    

    public double getControl (int i)
    {
        if (i == 1) {
            return linVel;
        }
        else if (i == 2) {
            return angVel;
        }
        else {
            System.out.println ("ERROR: jshapiro314UnicycleController.getControl(): incorrect input");
            return 0;
        }
    }


    public void move ()
    {
        System.out.println("X: " + sensors.getX() + ", Y: " + sensors.getY() + ", Theta: " + sensors.getTheta() + ", Velocity: " + linVel + ", AngVel: " + angVel + ", Time: " + sensors.getTime());
        //System.out.println(sensors.getY());


        jshapiro314UnicycleState nextState = solution.get(count);

        if((Math.abs(sensors.getX() - nextState.getX()) < epsilon) && (Math.abs(sensors.getY() - nextState.getY()) < epsilon) && ((Math.abs(sensors.getTheta() - nextState.getTheta()) <= epsilonAngle) || Math.abs(sensors.getTheta() - nextState.getTheta()) >= 6.283)){
            count++;
            if(count > solution.size()){
                linVel = 0;
                angVel = 0;
                //count = 0;
            }
            nextState = solution.get(count);
            System.out.println(nextState);


            if(Math.abs(sensors.getTheta() - nextState.getTheta()) > epsilonAngle && Math.abs(sensors.getTheta() - nextState.getTheta()) < 6.28){
                double deltaAngle = nextState.getTheta() - sensors.getTheta();
            //if deltaAngle is larger than 180 degrees, use the complimentary angle
                if(deltaAngle > Math.PI){
                    deltaAngle = deltaAngle-2*Math.PI;
                }else if(deltaAngle < -Math.PI){
                    deltaAngle = deltaAngle + 2*Math.PI;
                }

            //based on the delta angle, lets set our angular velocity
                angVel = (deltaAngle / 0.1)/(Math.PI/20);

            //If the limit is 10 and angVel is > 10, simply set it to 10.
                if(angVel > 10){
                    angVel = 10;
                }else if (angVel < -10){
                    angVel = -10;
                }
                linVel = 0;

                return;
            }else{
            //If the angle is correct and the x and y coordinates are wrong,
            //we must figure out the velocity needed to get to the desired coordinates

                double distance = Math.sqrt((sensors.getX() - nextState.getX())*(sensors.getX() - nextState.getX())+(sensors.getY() - nextState.getY())*(sensors.getY() - nextState.getY()));
                linVel = distance/0.1;

            //If the limit is 10 and linVel is > 10, simply set it to 10.
                if(linVel > 10){
                    linVel = 10;
                }
                angVel = 0;

                return;
            }

        }else if(Math.abs(sensors.getTheta() - nextState.getTheta()) > epsilonAngle && Math.abs(sensors.getTheta() - nextState.getTheta()) < 6.28){
            double deltaAngle = nextState.getTheta() - sensors.getTheta();
            //if deltaAngle is larger than 180 degrees, use the complimentary angle
            if(deltaAngle > Math.PI){
                deltaAngle = deltaAngle-2*Math.PI;
            }else if(deltaAngle < -Math.PI){
                    deltaAngle = deltaAngle + 2*Math.PI;
                }

            //based on the delta angle, lets set our angular velocity
            angVel = (deltaAngle / 0.1)/(Math.PI/20);

            //If the limit is 10 and angVel is > 10, simply set it to 10.
            if(angVel > 10){
                angVel = 10;
            }else if (angVel < -10){
                angVel = -10;
            }
            linVel = 0;

            return;
        }else{
            //If the angle is correct and the x and y coordinates are wrong,
            //we must figure out the velocity needed to get to the desired coordinates

            //System.out.println("SENSORS: (")
            double distance = Math.sqrt((sensors.getX() - nextState.getX())*(sensors.getX() - nextState.getX())+(sensors.getY() - nextState.getY())*(sensors.getY() - nextState.getY()));
            System.out.println("DISTANCE = " + distance);
            linVel = distance/0.1;

            //If the limit is 10 and linVel is > 10, simply set it to 10.
            if(linVel > 10){
                linVel = 10;
            }
            angVel = 0;

            return;
        }



    }
    
    public void draw (Graphics2D g2, Dimension D)
    {
    }

    //Methods that typically would exist in a problem class
    

}
