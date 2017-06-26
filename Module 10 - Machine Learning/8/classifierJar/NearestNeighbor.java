import java.util.*;
import java.text.*;

public class NearestNeighbor extends NullClassifier {
		ArrayList<Vector<Double> >[] trainingData;

		public String train(int numClasses, boolean isFixedDimension, ArrayList<Vector<Double> >[] trainingData){
				this.numClasses = numClasses;
				this.trainingData = trainingData;
				if (isFixedDimension) {
						System.out.println("NN: fixed");
						dim = trainingData[0].get(0).size();
				} else {
						this.trainingData = makeFixedDimension(numClasses, trainingData);
				}

				// No training at all.

				return null;
		}

		public int classify(Vector<Double> V){
				Vector<Double> v = makeFixedDimension(V);

				int bestClass = -1;
				double leastDistance = Double.MAX_VALUE;
				for (int c = 0; c < numClasses; c++) {
						for (int m = 0; m < trainingData[c].size(); m++) {
								// Get m-th training vector.
								Vector x = trainingData[c].get(m);
								// NOTE: the method distance (x,v) has been inherited from NullClassifier
								// INSERT YOUR CODE HERE
								double currentDist = distance(x, v);
								if (currentDist < leastDistance) {
										leastDistance = currentDist;
										bestClass = c;
								}
						}
				}

				return bestClass;
		}
}
