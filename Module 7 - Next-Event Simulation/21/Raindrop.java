
public class Raindrop {

    public static void main (String[] argv)
    {
        double numTrials = 100000;

        double s = 1;
        double p = 0.5;
        PropHistogram xHist = new PropHistogram(-25, 15, 40);
        PropHistogram tHist = new PropHistogram(10, 40, 40);

        for (int n=0; n<numTrials; n++) {

            // INSERT YOUR CODE HERE.
            double[] temp = drop(10,s,p);
            xHist.add(temp[0]);
            tHist.add(temp[1]);
        }

        xHist.display();
        tHist.display();

    }

    public static double[] drop(double h, double s, double p){
        double x = 0;
        double t = 0;

        while(h>0){
            double rand = RandTool.uniform();
            if(rand < p){
                h-=s;
            }else if(rand < 0.5){
                x += s;
            }else{
                x -= s;
            }
            t += 1;
        }

        double[] r = {x,t};
        return r;
    }

}
