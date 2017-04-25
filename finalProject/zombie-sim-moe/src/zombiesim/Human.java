package zombiesim;

import java.awt.Color;

/**
 * A class to represent a Human.
 * 
 * 		if (Human sees Zombie within 10 spaces of facing direction):
 * 			face opposite direction and move 2 spaces if not blocked by a wall
 *		else:
 * 			if (10% chance):
 * 				turn to face a random direction (up/down/left/right)
 * 			Move in the current direction one space if not blocked by a wall
 */
public class Human extends Entity{
	
	public Boolean infect = false;
	
	public Human(int x, int y, int facing, DotPanel dp)
	{
		super(x,y,facing,dp);
		turnChance = 100/10;
		drawColor = Color.ORANGE;
	}
	
	/**
	 * Update the Human's position following the rules above.
	 */
	public void update(Boolean isPaused)
	{
		super.update(isPaused);
	}
}
