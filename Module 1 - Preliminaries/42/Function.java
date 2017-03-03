
public class Function {

    public static void main (String[] argv)
    {
       
        // Print result.
        System.out.println ("x = 0.5, f(x) = " + calculate(0.5,4.97));
        System.out.println ("x = 1.5, f(x) = " + calculate(1.5,4.77));
        System.out.println ("x = 2.5, f(x) = " + calculate(2.5,4.33));
        System.out.println ("x = 3.5, f(x) = " + calculate(3.5,3.57));
        System.out.println ("x = 4.5, f(x) = " + calculate(4.5,2.18));
    }

    static double calculate(double x, double y){
        return x * x + y * y;
    }

}
