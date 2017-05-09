package zombiesim;

import java.util.*;
import java.awt.Color;

public class Disease {

	// disease constants for mumps, obtained from CDC
	// The height of the map is 252 pixels. This means it takes a total of 252 seconds for an entity to move all the way down the map.
	// In reality, this journey takes about 10 minutes according to google maps. Therefore, these entities are moving at 2.4 times the rate
	// of normal human beings. This means they are experiencing 2.4 times the contact. All CDC data needs to be scaled by 2.4 to get accurate
	// results.
	public static double infectionNoVacRate = 0.9/2.4;
	public static double infectionVacRate = 0.12/2.4;
	public static double vaccinatedRate = 0.915;
	//public static double contagiousTime = 15.0;

	public static double contagiousTime = 15/2.4*0.01;

	public static double infectionTime = 28.0;

	public static double touchNum = 2;
	public static double touchTime = 10;

}
