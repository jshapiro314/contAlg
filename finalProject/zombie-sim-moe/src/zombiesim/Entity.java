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
	protected int facing;
	protected int turnChance;
	protected Color drawColor;
	protected Boolean infect = false;	//	set to true to remove a human when safe
	
	private DotPanel dp;
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
		
		//	check if entity is viewed and react appropriately
		//	if a reaction occurs, standard movement should not
		if( viewEntity() )
			return;
		
		//	check if facing should change
		if( Helper.nextInt(turnChance)==0 )
			facing = Helper.nextInt(MAXDIR);
		
		move(facing,1);
	}
	
	/**
	 * Check if an entity of Class is up to 10 spaces away from facing direction
	 * Entity should not be able to see through walls
	 */
	public Boolean viewEntity()
	{
		//	setup
		ArrayList<? extends Entity> a = City.getArrayList(this instanceof Human);
		
		//	check facing step-by-step to prevent seeing through walls (INLINE IF FUN)
		for( Entity e:a )
		{
			for( int i=1; i<=10; i++ )
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
							if( this instanceof Human )
								move(DOWN,2);
							else
								move(UP,1);
							return true;
						}
						break;
					
					case RIGHT:
						if( City.checkBoundary(x, y, facing, i) )
							return false;
						
						if( x+i==e.x&&y==e.y )
						{
							if( this instanceof Human )
								move(LEFT,2);
							else
								move(RIGHT,1);
							return true;
						}
						break;
						
					case DOWN:
						if( City.checkBoundary(x, y, facing, i) )
							return false;
						
						if( x==e.x&&y+i==e.y )
						{
							if( this instanceof Human )
								move(UP,2);
							else
								move(DOWN,1);
							return true;
						}
						break;
						
					case LEFT:
						if( City.checkBoundary(x, y, facing, i) )
							return false;
						
						if( x-i==e.x&&y==e.y )
						{
							if( this instanceof Human )
								move(RIGHT,2);
							else
								move(LEFT,1);
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