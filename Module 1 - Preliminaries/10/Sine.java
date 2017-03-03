import java.lang.Math;

public class Sine{

	public static void main(String[] args){
		int n=100;
		for(int i=1;i<=n;i++){
			double retVal = Math.sin(i)/i;
			System.out.println("i = " + i + "    C = " + retVal);
		}
	}

}
