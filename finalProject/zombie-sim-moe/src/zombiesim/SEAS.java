package zombiesim;

import java.awt.Color;

/**
 * A class to represent a SEAS student.
 *
 */
public class SEAS extends Entity{

	public Boolean infect = false;

	public SEAS(int x, int y, int facing, DotPanel dp)
	{
		super(x,y,facing,dp);
		turnChance = 0.1;
		goalChance = 0.05;
		goalX = 110;
		goalY = 56;
		goalTime = 50000;
		drawColor = Color.ORANGE;
	}

	/**
	 * Update the SEAS's position following the rules above.
	 */
	public void update(Boolean isPaused)
	{
		super.update(isPaused);
	}
}
