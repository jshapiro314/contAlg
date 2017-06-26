public class DataExample {
		public static void main(String[] argv){
				DataGenerator dGen = new DataGenerator();
				int K = 30;
				int class0 = 0;
				int class1 = 0;

				int class03 = 0;
				int class13 = 0;

				System.out.printf("Data  Class\n");
				for (int k = 0; k < K; k++) {
						dGen.generateSample();
						System.out.printf("%d     %d\n", dGen.getX(), dGen.getClassNum());
						if (dGen.getClassNum() == 0) {
								class0++;
								if (dGen.getX() == 3)
										class03++;
						} else {
								class1++;
								if (dGen.getX() == 3)
										class13++;
						}
				}

				System.out.println("Pr[X = 3|C = 0]: "+(double)class03/class0);
				System.out.println("Pr[X = 3|C = 1]: "+(double)class13/class1);
		}
}
