public class DataExample {
		public static void main(String[] argv){
				DataGenerator dGen = new DataGenerator();
				int K = 30;
				int class0 = 0;
				int class1 = 0;

				System.out.printf("Data  Class\n");
				for (int k = 0; k < K; k++) {
						dGen.generateSample();
						System.out.printf("%d     %d\n", dGen.getX(), dGen.getClassNum());
						if (dGen.getClassNum() == 0)
								class0++;
						else
								class1++;
				}

				System.out.println("Probability of class 0: "+(double)class0/K);
				System.out.println("Probability of class 1: "+(double)class1/K);
		}
}
