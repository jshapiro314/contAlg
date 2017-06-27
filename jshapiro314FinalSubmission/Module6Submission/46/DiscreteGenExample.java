
public class DiscreteGenExample {

    public static void main (String[] argv)
    {
        // INSERT YOUR CODE FOR TESTING (HISTOGRAM) HERE.
        PropHistogram histogram = new PropHistogram(0,4,4);
        for(int i=0;i<100000;i++){
            histogram.add(generateNext());
        }
        histogram.display();
    }


    static int generateNext ()
    {
        // INSERT YOUR CODE HERE.
        double u = RandTool.uniform();
        if(u < 0.064){
            return 0;
        }else if(u < 0.352){
            return 1;
        }else if(u < 0.784){
            return 2;
        }else{
            return 3;
        }
    }

}
