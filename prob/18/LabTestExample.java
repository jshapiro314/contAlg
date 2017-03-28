
public class LabTestExample {

    public static void main (String[] argv)
    {
	double numTrials = 1000000;
	LabTest lab = new LabTest (0.05, 0.99, 0.03);
	for (int n=0; n<numTrials; n++) {
	    lab.nextPatient();
	    if (lab.testedPositive()) {
                // INSERT YOUR CODE HERE
	    }
	}
        // AND HERE
    }
    
}
