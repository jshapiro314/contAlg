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

	String type;
	double infectionTime;
	boolean overIt;

	public Zombie(int x, int y, int facing, DotPanel dp, String type)
	{
		super(x,y,facing,dp);
		turnChance = 0.2;
		goalChance = 0.2;
		goalX = 177;
		goalY = 45;
		goalTime = 10000;
		drawColor = Color.GREEN;
		this.type = type;
		infectionTime = Disease.contagiousTime*24*60*60; // convert days to seconds
		overIt = false;
	}

	/**
	 * Update the Zombie's position following the rules above.
	 */
	public void update(Boolean isPaused)
	{
		if (this.type == null) {
			this.type = "SEAS";
		}

		//	check if all objects in City.entities are adjacent
		//	if they are adjacent, check if they are Human
		//	if they are human, make them a Zombie
		ArrayList<? extends Entity> a = City.getArrayList(false);
		for(Entity h: a)
		{
			if(((x==h.x)&&(y==h.y+1)) || ((x==h.x)&&(y==h.y-1)) || ((x==h.x+1)&&(y==h.y)) || ((x==h.x-1)&&(y==h.y))) {
				double prob = Helper.nextDouble();
				if (h.immune){
					System.out.println("They are immune");
				}else if (h.vaccinated == true) {
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

				//Now that we've marked it infected, lets see if the entity has come in contact with enough infected people to be infected
				if(h.infect){
					if(h.infectionTime + Disease.touchTime >= City.time){
						h.infectionCount++;
					}else{
						h.infectionCount = 1;
					}
					h.infectionTime = City.time;

					if(h.infectionCount < Disease.touchNum){
						h.infect = false;
					}
				}
			}
		}
		if (this.infectionTime == 0) {
			// check studentType
			if (type.equals("Elliot")) {
				Elliot e = new Elliot(this.x, this.y, this.facing, dp);
				e.vaccinated = true;
				e.immune = true;
				City.humans.add(e);
			} else if (this.type.equals("SEAS")) {
				SEAS s = new SEAS(this.x, this.y, this.facing, dp);
				s.vaccinated = true;
				s.immune = true;
				City.humans.add(s);
			} else if (this.type.equals("Columbian")) {
				Columbian c = new Columbian(this.x, this.y, this.facing, dp);
				c.vaccinated = true;
				c.immune = true;
				City.humans.add(c);
			} else if (this.type.equals("SMPA")) {
				SMPA sm = new SMPA(this.x, this.y, this.facing, dp);
				sm.vaccinated = true;
				sm.immune = true;
				City.humans.add(sm);
			} else if (this.type.equals("Law")) {
				Law l = new Law(this.x, this.y, this.facing, dp);
				l.vaccinated = true;
				l.immune = true;
				City.humans.add(l);
			} else if (this.type.equals("Business")) {
				Business b = new Business(this.x, this.y, this.facing, dp);
				b.vaccinated = true;
				b.immune = true;
				City.humans.add(b);
			}
			overIt = true;
		}
		this.infectionTime--;
		super.update(isPaused);
	}
}
