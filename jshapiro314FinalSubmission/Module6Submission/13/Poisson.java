public class Poisson {
		public static void main(String[] argv){
				double prob = poisson(2, 3);

				System.out.println("Pr[X=3] = "+prob);

				//Drawing the graph of the distribution when gamma is 2...
				Function f = new Function("Poisson Distribution");
				for (int i = 1; i < 20; i++) {
						double y = poisson(2, i);
						//System.out.println("X = "+i+", Y = "+y);
						f.add(i, y);
				}
				f.show();
		}

		static double poisson(double gamma, int k){
				// INSERT YOUR CODE HERE.
				double kFact = k;

				for (int i = k-1; i > 1; i--) {
						kFact *= i;
				}

				double retVal = Math.pow(Math.E, gamma* -1)*Math.pow(gamma, k)/kFact;

				return retVal;
		}
}
