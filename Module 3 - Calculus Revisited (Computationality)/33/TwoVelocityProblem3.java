
public class TwoVelocityProblem3 {

    public static void main (String[] argv)
    {
        double a = 10;
        double b = 10;
        double x = 5;
        
        for (double v2=1; v2<=2; v2+=0.1) {
            double y = findBestY (1, v2, 10, 10, 5);

            double d1 = distance (0,a, x,y);
            double d2 = distance (x,y, b,0);
            
            //System.out.println ("v2/v1=" + v2 + "  best y=" + y + " d1=" + d1 + " d2=" + d2 + " (a-y)=" + (a-y) + " y=" + y);
            System.out.println("v2/v1=" + v2 + " best y=" + y + " d2/d1= " + d2/d1 + " (y/d2)/((a-y)/d1)= " + (y/d2)/((a - y)/d1));
        }
    }


    static double findBestY (double v1, double v2, double a, double b, double x)
    {
        // FILL IN YOUR CODE HERE.
        double min = timeTaken(v1, v2, a, b, x, 0);
        double miny = 0;
        for (double i = 0.00001; i <= 10; i+=0.00001) {
            double t = timeTaken (v1, v2, a, b, x, i);
            if (t < min) {
                min = t;
                miny = i;
            }
        }
        return miny;
    }



    static double timeTaken (double v1, double v2, double a, double b, double x, double y)
    {
        // FILL IN YOUR CODE HERE.
        return TwoVelocityProblem2.timeTaken(v1, v2, a, b, x,  y);
    }


    static double distance (double x1, double y1, double x2, double y2)
    {
        return Math.sqrt ((x1-x2)*(x1-x2) + (y1-y2)*(y1-y2));
    }

}
