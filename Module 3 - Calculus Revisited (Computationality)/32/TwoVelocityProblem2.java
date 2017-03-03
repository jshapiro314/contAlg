
public class TwoVelocityProblem2 {

    public static void main (String[] argv)
    {
        double y = findBestY (1, 1.5, 10, 10, 5);
        System.out.println ("best y=" + y);
    }


    static double findBestY (double v1, double v2, double a, double b, double x)
    {
        // FILL IN YOUR CODE HERE.
        double min = timeTaken(v1, v2, a, b, x, 0);
        double miny = 0;
        for (double i = 0.00001; i <= 10; i+=0.00001) {
            double t = timeTaken (1, 1.5, 10, 10, 5, i);
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
        double tAP = Math.sqrt(x*x + (y-a)*(y-a))/v1;
        double tPB = Math.sqrt((b-x)*(b-x) + (-y)*(-y))/v2;
        return tAP + tPB;
    }


    static double distance (double x1, double y1, double x2, double y2)
    {
        return Math.sqrt ((x1-x2)*(x1-x2) + (y1-y2)*(y1-y2));
    }

}
