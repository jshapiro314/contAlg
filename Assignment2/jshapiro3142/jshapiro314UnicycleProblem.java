//Based off of Professor Simha's ArmProblem.java
//Significantly modified to do away with GUI implementation

import java.util.*;
import java.awt.geom.*;


public class jshapiro314UnicycleProblem {

    // Delta = the change for a new (neighboring) state.
    static double delta = 5;  // 5 pixels;

    // For equality comparisons.
    static double epsilon = 0.01;

    // Target location.
    double targetX=0, targetY=0;

    // Obstacles: a single rectangle.
    ArrayList<Rectangle2D.Double> obstacles;
    
    
    // Start State
    jshapiro314UnicycleState currentState;


    //CONSTRUCTOR
    public jshapiro314UnicycleProblem(double targetX, double targetY, ArrayList<Rectangle2D.Double> obstacles, jshapiro314UnicycleState startState){
        this.targetX = targetX;
        this.targetY = targetY;
        this.obstacles = obstacles;
        this.currentState = startState;
    }



    //First simply fetch the 8 closest neighbors. Eventually extend to 5s and 10s as well
    public ArrayList<jshapiro314UnicycleState> getNeighbors (State state){
        jshapiro314UnicycleState a = (jshapiro314UnicycleState) state;
        ArrayList<jshapiro314UnicycleState> neighbors = new ArrayList<jshapiro314UnicycleState> ();
        
        // Look at the 8 neighboring points at distance (+- delta, +-delta).
        for (double delX= -delta; delX<=delta; delX+=delta) {
            for (double delY= -delta; delY<=delta; delY+=delta) {
                jshapiro314UnicycleState b = makeState (a, delX, delY);
                if (isValid (b)) {
                    b.costFromStart = a.costFromStart + delta;
                    b.estimatedCostToGoal = b.distance (targetX, targetY);
                    neighbors.add (b);
                } 
            }
        }

        return neighbors;



        // //Second try

        // for(double angVel = -10*Math.PI/20; angVel <= 10*Math.PI/20; angVel += Math.PI/20){
        //     for(int i=1;i<10;i++){

        //         //Using angular velocity and linear velocity to calcualte x and y coordiantes for neighbors
        //         double newTheta = a.getTheta() + angVel*0.1;
        //         double distance = i*0.1;
        //         double delX = distance * Math.cos(newTheta);
        //         double delY = distance * Math.sin(newTheta);
        //         //System.out.println("delX = " + delX + " delY = " + delY);
        //         jshapiro314UnicycleState b = makeState (a, delX, delY);
        //         if (isValid (b)) {
        //             //System.out.println("HERE");
        //             b.costFromStart = a.costFromStart + delta;
        //             b.estimatedCostToGoal = b.distance (targetX, targetY);
        //             neighbors.add (b);
        //         } 
        //     }
        // }

        // return neighbors;
    }



    public State getStartState (){
        return currentState;
    }


    public boolean satisfiesGoal (State state){
        jshapiro314UnicycleState a = (jshapiro314UnicycleState) state;
        double x = a.getX();
        double y = a.getY();
        
        // Use a distance of at least delta.
        if ( (Math.abs(targetX-x) > delta+epsilon) || (Math.abs(targetY-y) > delta+epsilon) ) {
            return false;
        }
        return true;
    }


    //////////////////////////////////////////////////////////////////////
    // Utility methods


    //Checks to see if:
    //1) The state is within bounds
    //2) The state is within an obstacle
    //3) To get to the state from its parent, do you have to pass through an obstacle
    //PART 3 STILL NEEDS TO BE IMPLEMENTED
    boolean isValid (jshapiro314UnicycleState a){
        
        if (a == null) {
            return false;
        }

        // See if within bounds.
        double x = a.getX();
        double y = a.getY();
        if ( (x < 0) || (y < 0) ) {
            return false;
        }

        //Convert the obstacles into rectangles with corners in bottom left instead of top left
        //This is due to how the screen displays objects

        ArrayList<Rectangle2D.Double> reconfiguredObstacles = new ArrayList<Rectangle2D.Double>();
        for(int i=0;i<obstacles.size();i++){
            Rectangle2D.Double tempRect = new Rectangle2D.Double(obstacles.get(i).getX(), obstacles.get(i).getY()-obstacles.get(i).getHeight(), obstacles.get(i).getWidth(), obstacles.get(i).getHeight());
            reconfiguredObstacles.add(tempRect);
        }


        //Generate an ellipse bounding the unicycle
        Rectangle2D.Double unicycle = new Rectangle2D.Double(a.getX()-15, a.getY()-8, 30, 16);
        AffineTransform at = AffineTransform.getRotateInstance(a.getTheta(), a.getX(), a.getY());
        Path2D.Double rotUnicycle = (Path2D.Double) at.createTransformedShape(unicycle);

        // See if the new state hits the obstacles.
        for(int i=0;i<obstacles.size();i++){
            //System.out.println("X = " + a.getX() + " Y = " + a.getY());
            if(rotUnicycle.intersects(reconfiguredObstacles.get(i))){
                //System.out.println("HIT OBSTACLE: " + obstacles.get(i));
                //System.out.println("X = " + a.getX() + " Y = " + a.getY());
                return false;
            }
        }

        return true;
    }


    //Will need to eventually modify if we change what neighbors means
    boolean areNeighbors (jshapiro314UnicycleState a1, jshapiro314UnicycleState a2) {
        if ( (a1 == null) || (a2 == null) ) {
            return false;
        }

            // See if distance of last node is within delta+epsilon.
        double x1 = a1.getX();
        double y1 = a1.getY();
        double x2 = a2.getX();
        double y2 = a2.getY();

        double d = distance (x1,y1, x2,y2);
        if (d <= delta+epsilon) {
            return true;
        }

        return false;
    }


    double distance (double x1, double y1, double x2, double y2){
    	return Math.sqrt ( (x1-x2)*(x1-x2) + (y1-y2)*(y1-y2) );
    }


    jshapiro314UnicycleState copy (jshapiro314UnicycleState a){
        // Make a new instance that's a copy of a.
        jshapiro314UnicycleState b = new jshapiro314UnicycleState (a.getParent(),a.getX(),a.getY(),a.getTheta());
        return b;
    }



    public jshapiro314UnicycleState makeState (jshapiro314UnicycleState a, double deltaX, double deltaY){
        double newX = a.getX() + deltaX;
        double newY = a.getY() + deltaY;
        double newTheta = Math.atan(deltaY/deltaX);

        //need to convert newTheta to non-negative. The conversion back to negative is done in the Controller.
        if(newTheta < 0){
            newTheta = newTheta + 2*Math.PI;
        }

        jshapiro314UnicycleState b = new jshapiro314UnicycleState(a,newX,newY,newTheta);

        return b;
    }


    double distance (Point2D.Double p, Point2D.Double q){
        return distance (q.x,q.y, p.x,p.y);
    }

}
