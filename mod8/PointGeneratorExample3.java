
import java.awt.geom.*;


public class PointGeneratorExample3 {

    public static void main (String[] argv)
    {
        double numPoints = 100000;
        double numPoints2 = 0;
        double numSuccesses = 0;
        double numSuccesses2 = 0;
        for (int n=0; n<numPoints; n++) {
            Point2D.Double p = PointGenerator.randomPoint();
            if ((5 <= p.y) && (p.y <= 7) ) {
                numSuccesses ++;
            }
            if ((3 <= p.x) && (p.x <= 4)) {
                numPoints2++;
                if ((5 <= p.y) && (p.y <= 7)) {
                    numSuccesses2 ++;
                }
            }
        }
        System.out.println ("Pr[Y in [5,7]]: " + (numSuccesses/numPoints));

        // INSERT YOUR CODE HERE

        // AND in the println:
        System.out.println ("Pr[Y in [5,7] | X in [3,4]]: " + (numSuccesses2/numPoints2)); 
    }

}
