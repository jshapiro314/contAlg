
import java.util.*;

public class jshapiro314UnicycleState extends State{

    //Cost values
    public double costFromStart = 0;
    public double estimatedCostToGoal = -1;

    // Note: for comparisons, we're using something that's larger
    // than the tolerance.
    private double epsilon = 1;

    // Pointer to parent. This needs to be set by the appropriate problem
    private jshapiro314UnicycleState parent = null;

    // Location.
    private double x=-1, y=-1;
    // Angle.
    private double theta = 0;


    public jshapiro314UnicycleState (jshapiro314UnicycleState parent, double x, double y, double theta)
    {
        this.parent=parent;
        this.x=x;
        this.y=y;
        this.theta = theta;
    }

    public jshapiro314UnicycleState getParent ()
    {
       return parent;
   }

       public double getX ()
    {
       return x;
   }

       public double getY ()
    {
       return y;
   }

       public double getTheta ()
    {
       return theta;
   }


   public boolean equals (Object obj)
   {
       if (! (obj instanceof jshapiro314UnicycleState) ) {
            return false;
        }
        
        jshapiro314UnicycleState a = (jshapiro314UnicycleState) obj;

        //Currently not checking if the angle matches, only x an y coordinates
        if ( (Math.abs(x - a.x) > epsilon) || (Math.abs(y - a.y) > epsilon) ) {
            return false;
        }
        
        return true;
   }

   public double distance (double targetX, double targetY){
        return Math.sqrt((x-targetX) * (x-targetX) + (y-targetY) * (y-targetY));
   }

   public String toString ()
   {
       String str = "jshapiro314UnicycleState: [x = " + x + ", y = " + y + ", theta = " + theta +", cost = "+ costFromStart + " est = " + estimatedCostToGoal + "]";
       return str;
   }

}
