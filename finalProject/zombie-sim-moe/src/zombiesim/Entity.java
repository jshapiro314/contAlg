package zombiesim;

import java.util.ArrayList;
import java.awt.Color;

/**
 * Generic Entity class that Human and Zombie are built off of
 */
public class Entity
{

	//	constants
	public static final int UP		= 0;
	public static final int RIGHT	= 1;
	public static final int DOWN	= 2;
	public static final int LEFT	= 3;
	public static final int MAXDIR	= 4;	//	easy reference

	//	attributes
	protected int x, y;
	protected int goalX, goalY;
	protected int goalTime;
	protected int penalty;
	protected int facing;
	protected double turnChance;
	protected double goalChance;
	protected Color drawColor;
	protected Boolean infect = false;	//	set to true to remove a human when safe
	protected boolean vaccinated;
	public String type;

	public DotPanel dp;
	private int xdp, ydp;	//	for drawing

	private Boolean isPaused;

	public Entity(int x, int y, int facing, DotPanel dp)
	{
		this.x = x;
		this.y = y;
		this.facing = facing;
		this.dp = dp;
		xdp = ydp = -1;
		isPaused = false;
	}

	/**
	 * return infect boolean
	 */
	public Boolean getInfect()
	{
		return infect;
	}

	/**
	 * generic update function, used by Human and Zombie for similar behaviors
	 * call this during the update loops for Human and Zombie
	 */
	public void update(Boolean isPaused)
	{
		//	for motion
		this.isPaused = isPaused;

		//	for drawing
		xdp = x; ydp = y;

		//Check if entity is facing another entity and react appropriatetly (by turning 180 degrees and moving 1 space)
		//If a reaction occurs, standard movement should not
		if(viewEntity()){
			return;
		}


		//Check if entity should move towards its goal.
		if(Helper.nextDouble() < goalChance){
			if(moveToGoal()){
				return;
			}
		}

		//	check if facing should change
		if( Helper.nextDouble() < turnChance){
			facing = Helper.nextInt(MAXDIR);
		}
		move(facing,1);
	}

	/**
	 * If an entity is facing another entity and they are within 2 blocks of eachother, the entity will turn around and take 1 step forward.
	 */
	public Boolean viewEntity()
	{
		//	setup
		ArrayList<? extends Entity> a = City.getEntities();

		//	check facing step-by-step to prevent seeing through walls (INLINE IF FUN)
		for( Entity e:a )
		{
			for( int i=1; i<=2; i++ )
			{
				//	check if wall first, then check if opposing entity
				//	if wall, return false immediately
				switch( facing )
				{
					case UP:
						//	check boundaries
						if( City.checkBoundary(x, y, facing, i) )
							return false;	//	fail

						if( x==e.x&&y-i==e.y )
						{
							move(DOWN,1);
							return true;
						}
						break;

					case RIGHT:
						if( City.checkBoundary(x, y, facing, i) )
							return false;

						if( x+i==e.x&&y==e.y )
						{
							move(LEFT,1);
							return true;
						}
						break;

					case DOWN:
						if( City.checkBoundary(x, y, facing, i) )
							return false;

						if( x==e.x&&y+i==e.y )
						{
							move(UP,1);
							return true;
						}
						break;

					case LEFT:
						if( City.checkBoundary(x, y, facing, i) )
							return false;

						if( x-i==e.x&&y==e.y )
						{
							move(RIGHT,1);
							return true;
						}
						break;
				}
			}
		}

		//	default return
		return false;
	}

	/**
	 * Move the entity towards its goal. Ensure that the entity won't hit a wall. If the entity can't move without hitting a wall, return false, true if the entity does move. If the entity hits the goal, the next 1000 times this method is touched it will return false. This prevents the entities from jsut getting stuck in one location.
	 */
	public boolean moveToGoal(){
		//To begin, we need to check if we are already at the goal. If we are, we return false.
		if(x == goalX && y == goalY){
			penalty = goalTime;
			return false;
		}

		//Update the time since we've been at the goal.
		if(penalty > 0){
			penalty --;
			return false;
		}
		//We need to discover which directions we need to face to get to the goal.
		//There will be at most 2 directions, but possibly only one.
		//We will add these potential directions to a list, then choose one at random to move in
		ArrayList<Integer> potentialDirections = new ArrayList<Integer>();
		if(x > goalX){
			potentialDirections.add(LEFT);
		}else if(x < goalX){
			potentialDirections.add(RIGHT);
		}
		if(y > goalY){
			potentialDirections.add(UP);
		}else if(y < goalY){
			potentialDirections.add(DOWN);
		}

		//Randomly try the potential directions until one leads to a successful move.
		while(!potentialDirections.isEmpty()){
			int index = Helper.nextInt(potentialDirections.size());
			int direction = potentialDirections.get(index);
			potentialDirections.remove(index);

			int prevX = x;
			int prevY = y;
			move(direction,1);
			if(prevX != x || prevY != y){
				return true;
			}
		}
		//If none of the moves were successful, return false
		return false;
	}

	/**
	 * Move the Entity in a safe manner, recursively
	 */
	public void move(int dir,int count)
	{
		//	if we are paused do not move
		if( isPaused )
			return;

		facing = dir;

		//	check OOB before moving
		switch( facing )
		{
			case DOWN:
				if( !City.checkBoundary(x, y, facing, 1) )
					y++;
				break;

			case RIGHT:
				if( !City.checkBoundary(x, y, facing, 1) )
					x++;
				break;

			case UP:
				if( !City.checkBoundary(x, y, facing, 1) )
					y--;
				break;

			case LEFT:
				if( !City.checkBoundary(x, y, facing, 1) )
					x--;
				break;

			default:break;
		}

		if( count==1 )
			return;
		else
			move(facing,count-1);
	}

	/**
	 * Draw the Entity
	 */
	public void draw()
	{
		//	override draw with black if applicable
		if( xdp!=-1 && ydp!=-1 && !(xdp==x&&ydp==y) )
		{
			dp.setPenColor(Color.BLACK);
			dp.drawDot(xdp,ydp);
			xdp = ydp = -1;
		}

		dp.setPenColor(drawColor);
		dp.drawDot(x, y);
	}

}
