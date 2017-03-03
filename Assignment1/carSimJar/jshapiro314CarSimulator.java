import java.util.*;
import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;

public class jshapiro314CarSimulator {

    private static double Interval = 0.1;
    private static double IntervalAngle = 0.001;
    private static double BestTime = Double.MAX_VALUE;
    private static double BestAngle = 0;
    private static double BestV1 = 0;
    private static double BestV2 = 0;
    private static DubinCarSimulator tempSimulator = new DubinCarSimulator(false);

    public static void main (String[] argv) 
    {
        //Lets make a rectangle as an obstacle
        Rectangle2D.Double obstacle = new Rectangle2D.Double(150, 100, 100, 100);
        ArrayList<Rectangle2D.Double> obstacles = new ArrayList<Rectangle2D.Double>();
        obstacles.add(obstacle);

        //Lets begin by creating for loops to iterate over every possible value
        double v1 = 10;
        double v2 = 0;
        double angle = 0;

        //for(v1 = 0; v1 <= 10; v1 += Interval){
        for(v2 = 0;v2 <= 10; v2 += Interval){
            for(angle = 0; angle <= (Math.PI/2); angle += IntervalAngle){
                System.out.println("Angle = " + angle + ", V2 = " + v2);
                    //initialize simulator
                tempSimulator.init(50, 50, 0, obstacles);

                    //rotate to the desired angle
                rotate(angle);

                    //move the car in an arc (as long as it returns true continue)
                if(moveArc(v1, v2)){
                        //Rotate back to angle of zero
                    rotateToZero();
                        //move forward if necessary
                    if(moveForward()){
                        double tempTime = tempSimulator.getTime();
                        if(tempTime < BestTime){
                            BestTime = tempTime;
                            BestAngle = angle;
                            BestV1 = v1;
                            BestV2 = v2;
                            System.out.println("Best Time: " + BestTime);
                            System.out.println("Best Angle: " + BestAngle);
                            System.out.println("Best V1: " + BestV1);
                            System.out.println("Best V2: " + BestV2);
                        }
                        
                    }
                }
            }
        }
        //}

        //At the end, print the best values and teh best time.
        System.out.println("Best Time: " + BestTime);
        System.out.println("Best Angle: " + BestAngle);
        System.out.println("Best V1: " + BestV1);
        System.out.println("Best V2: " + BestV2);
        
    }

    //Step 1: Rotate the car to the given angle
    public static void rotate(double desiredAngle){
        while(tempSimulator.getTheta() < desiredAngle){
            tempSimulator.nextStep(-10,0,0.01);
        }
        // System.out.println("Exited rotate");
        // System.out.println("X = " + tempSimulator.getX());
        // System.out.println("Y = " + tempSimulator.getY());
    }

    //Step 2: Start moving the car until collision (false)
    //or timeout (false)
    //or we hit y=50 (true)
    public static boolean moveArc(double v1, double v2){
        while((tempSimulator.getTime() < 20)){
            if(tempSimulator.hitObstacle()){
                System.out.println("HIT OBSTACLE");
                return false;
            }
            //System.out.println("In move Arc");
            if(tempSimulator.getY() <= 50 && tempSimulator.getX() <= 500 && tempSimulator.getX() > 150){
                //System.out.println("In move Arc2");
                return true;
            }else{
                tempSimulator.nextStep(v1,v2,0.1);
                //System.out.println("In move Arc3");
            }
            //System.out.println("In move Arc4");
        }
        System.out.println("Timed Out");
        return false;
    }

    //Step 3: Rotate back to zero
    public static void rotateToZero(){
        while(Math.PI > tempSimulator.getTheta()){
            tempSimulator.nextStep(-10,10,0.01);
        }
    }

    //Step 4: Move forward if necessary. Return true if no timeout.
    public static boolean moveForward(){
        while(tempSimulator.getTime() < 20){
            if(tempSimulator.getX() < 500){
                tempSimulator.nextStep(10,10,0.1);
            }else{
                return true;
            } 
        }
        return false;
    }
}