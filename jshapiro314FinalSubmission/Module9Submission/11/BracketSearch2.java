
public class BracketSearch2 {

    public static void main (String[] argv)
    {
        bracketSearch (0, 100);
    }


    static void bracketSearch (double a, double b)
    {
        int numEvals = 1;
        int M = 10;
        int N = 0;
        double bestx = 1000;
        double bestf = computef (bestx);
        double epsilon = 0.1;
        double prevBestf = bestf + 2*epsilon;
        double delta;
        double f;

        // INSERT YOUR CODE HERE
        while(Math.abs(bestf - prevBestf) > epsilon){
            delta = (b-a)/M;
            bestx = a;
            prevBestf = bestf;
            bestf = computef(bestx);
            numEvals++;
            for(double x = a+delta; x<b; x+=delta){
                f = computef(x);
                numEvals++;
                if(f < bestf){
                    bestf = f;
                    bestx = x;
                }
            }
            a = bestx - delta;
            b = bestx + delta;
            N = N+1;
        }

        System.out.println ("N=" + N + " bestx=" + bestx + " bestf=" + bestf + " prevBestf=" + prevBestf);
    }


    static double computef (double x)
    {
        double f = 2.5 + (x - 4.71) * (x - 4.71);
        return f;
    }

}
