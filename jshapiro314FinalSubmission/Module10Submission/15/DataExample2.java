public class DataExample2 {
		public static void main(String[] argv){
				DataGenerator dGen = new DataGenerator();

				double numTrials = 100000;
				double numMistakes = 0;

				for (int n = 0; n < numTrials; n++) {
						dGen.generateSample();
						int x = dGen.getX();
						int c = dGen.getClassNum();     // The true class that x belongs to.
						int algResult = classify(x);    // What the algorithm classifies x as.
						if (algResult != c)
								numMistakes++;
				}
				double probError = numMistakes/numTrials;
				System.out.println("Pr[E]="+probError);
		}

		static int classify(int x){
				// INSERT YOUR CODE HERE
				if (x == 0)
						return 0;
				else if (x == 1)
						return 0;
				else if (x == 2)
						return 0;
				else
						return 1;
		}
}
