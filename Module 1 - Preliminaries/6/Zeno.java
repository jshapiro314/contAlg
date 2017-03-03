
import java.lang.Math;

public class Zeno {

    public static void main (String[] argv)
    {
        //int n = 10;
        //System.out.println (sum(n));
        System.out.println("Sum");
		System.out.println("5 = " + sum(5));
		System.out.println("10 = " + sum(10));
        System.out.println("100 = " + sum(100));
        System.out.println();

        System.out.println("Sum - 1");
        System.out.println("5 = " + sumMinusOne(5));
		System.out.println("10 = " + sumMinusOne(10));
        System.out.println("100 = " + sumMinusOne(100));
    }

    static double sum (int n)
    {
        // Write your code here.
        double retVal = 0;
        double tempVal = 0.5;
        for(int i=0;i<n;i++){
        	retVal += tempVal;
        	tempVal /= 2;
        }

        return retVal;
    }

    //Function for in class exercise 7
    static double sumMinusOne (int n)
    {
    	        // Write your code here.
        return (1 - (2 / (Math.pow(2,n))));
    }

}
