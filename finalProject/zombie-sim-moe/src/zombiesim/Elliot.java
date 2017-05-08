package zombiesim;

import java.awt.Color;

/**
 * A class to represent a Elliot student.
 *
 * 		if (Elliot sees Zombie within 10 spaces of facing direction):
 * 			face opposite direction and move 2 spaces if not blocked by a wall
 *		else:
 * 			if (10% chance):
 * 				turn to face a random direction (up/down/left/right)
 * 			Move in the current direction one space if not blocked by a wall
 */
public class Elliot extends Entity{

	public Boolean infect = false;

	public Elliot(int x, int y, int facing, DotPanel dp)
	{
		super(x,y,facing,dp);
		turnChance = 0.1;
		goalChance = 0.05;
		goalX = 311;
		goalY = 244;
		goalTime = 50000;
		drawColor = Color.WHITE;
	}

	/**
	 * Update the Elliot's position following the rules above.
	 */
	public void update(Boolean isPaused)
	{
		super.update(isPaused);
	}
}
