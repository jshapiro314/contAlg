
public class LabTestExample {

    public static void main (String[] argv)
    {
    double positive = 0;
    double sick = 0;
    double well = 0;
	double numTrials = 1000000;
	LabTest lab = new LabTest (0.05, 0.99, 0.03);
	for (int n=0; n<numTrials; n++) {
	    lab.nextPatient();
	    if (lab.testedPositive()) {
                // INSERT YOUR CODE HERE
            positive ++;
            if(lab.isSick()){
                //To figure out Pr[sick|positive]
                sick++;
            }else{
                //To figure out Pr[well|positive]
                well++;
            }


	    }
	}
        // AND HERE
        System.out.println("The probability that if a test is positive, the person is infected is: " + sick/positive);
        System.out.println("The probability that if a test is positive, the person is well is: " + well/positive);
    }

}
