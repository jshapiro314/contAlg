package zombiesim;

import java.awt.Color;

/**
 * A class to represent a SMPA student.
 *
 * 		if (SMPA sees Zombie within 10 spaces of facing direction):
 * 			face opposite direction and move 2 spaces if not blocked by a wall
 *		else:
 * 			if (10% chance):
 * 				turn to face a random direction (up/down/left/right)
 * 			Move in the current direction one space if not blocked by a wall
 */
public class SMPA extends Entity{

	public Boolean infect = false;

	public SMPA(int x, int y, int facing, DotPanel dp)
	{
		super(x,y,facing,dp);
		turnChance = 0.1;
		goalChance = 0.05;
		goalX = 223;
		goalY = 56;
		goalTime = 100000;
		drawColor = Color.RED;
		type = "SMPA";
	}

	/**
	 * Update the SMPA's position following the rules above.
	 */
	public void update(Boolean isPaused)
	{
		super.update(isPaused);
	}
}
