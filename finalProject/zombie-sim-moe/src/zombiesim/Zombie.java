package zombiesim;

import java.awt.Color;
import java.util.ArrayList;

/**
 * A class to represent a Zombie.
 *
 * 		if( Zombie is adjacent to a Human non-diagonal):
 * 			delete Human and create Zombie in place
 *
 * 		if (Zombie sees Human within 10 spaces of facing direction):
 * 			move 1 space towards Human
 *		else:
 * 			if (20% chance):
 * 				turn to face a random direction (up/down/left/right)
 * 			Move in the current direction one space if not blocked by a wall
 */
public class Zombie extends Entity{

	public Zombie(int x, int y, int facing, DotPanel dp)
	{
		super(x,y,facing,dp);
		turnChance = 0.2;
		drawColor = Color.GREEN;
	}

	/**
	 * Update the Zombie's position following the rules above.
	 */
	public void update(Boolean isPaused)
	{
		//	check if all objects in City.entities are adjacent
		//	if they are adjacent, check if they are Human
		//	if they are human, make them a Zombie
		ArrayList<? extends Entity> a = City.getArrayList(false);
		for(Entity h: a)
		{
			if(((x==h.x)&&(y==h.y+1)) || ((x==h.x)&&(y==h.y-1)) || ((x==h.x+1)&&(y==h.y)) || ((x==h.x-1)&&(y==h.y))) {
				double prob = Helper.nextDouble();
				if (h.vaccinated == true) {
					if (prob < Disease.infectionVacRate) {
						h.infect = true;
					} else {
						System.out.println("hit but not infected");
					}
				} else {
					if (prob < Disease.infectionNoVacRate) {
						h.infect = true;
					} else {
						System.out.println("hit but not infected");
					}
				}
			}
		}

		super.update(isPaused);
	}
}
