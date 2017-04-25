package zombiesim;

import java.awt.Color;
import java.util.ArrayList;

public class City
{
	private static boolean walls[][];
	private static int width, height;
	
	public static final Boolean IS_HUMAN	= true;
	public static final Boolean IS_ZOMBIE	= false;
	public static final int START_RATE 		= 33;	//	30fps
	
	//	our arraylists
	private static ArrayList<Human>		humans;
	private static ArrayList<Zombie>	zombies;
	private ArrayList<Human>	humanQueue;
	private ArrayList<Zombie>	zombieQueue;
	
	//	the end of the world Booleans
	private Boolean end, safeEnd;
	
	//	counts for output
	private int zc = 0;		//	zombie count
	private int hc = 0;		//	human count
	
	//	for determining motion of humans and zombies
	private Boolean humansPaused = false;
	private Boolean zombiesPaused = false;
	
	//	for graphics
	private DotPanel dp;
	private int rate;
	
	/**
	 * Create a new City and fill it with buildings and people.
	 * @param w width of city
	 * @param h height of city
	 * @param numB number of buildings
	 * @param numP number of people
	 */
	public City(int w, int h, int numB, int numP, DotPanel dp)
	{
		width = w;
		height = h;
		rate = START_RATE;
		end = safeEnd = false;
		walls = new boolean[w][h];
		this.dp = dp;
		
		randomBuildings(numB);
		populate(numP);
		
		humanQueue = new ArrayList<Human>();
		zombieQueue = new ArrayList<Zombie>();
	}
	
	/**
	 * Create numPeople Human
	 * Create numPeople Zombie
	 * All Human must be on the screen at any given time
	 * All but one Zombie will be drawn, the other Zombie will be at {-1,-1}
	 * When an infect happens, the Human will move to {-1,-1} and the Zombie will replace
	 * 
	 * @param numPeople the number of people to generate
	 */
	private void populate(int numPeople)
	{
		int eX, eY, eDir;
		
		//	create humans, do not draw where walls are
		//	we do not care about overlaps between humans and zombies
		humans = new ArrayList<Human>(numPeople);
		for( int p=0; p<numPeople; p++ )
		{
			do
			{
				eX = Helper.nextInt(width);
				eY = Helper.nextInt(height);
			}while( walls[eX][eY] );
			
			eDir = Helper.nextInt(Entity.MAXDIR);
			humans.add(new Human(eX, eY, eDir,dp));
		}
		
		
		//	create zombies, start with 1
		zombies = new ArrayList<Zombie>(1);
		/*
		do
		{
			eX = Helper.nextInt(width);
			eY = Helper.nextInt(height);
		}while( walls[eX][eY] );

		eDir = Helper.nextInt(Entity.MAXDIR);
		zombies.add(new Zombie(eX,eY,eDir,dp));
		*/
	}
	
	/**
	 * Replace infected humans with new zombies
	 * This occurs during a safe point in execution
	 */
	public void infect()
	{
		//	use a traditional for loop here
		int maxHuman = humans.size();
		for( int i=0; i<maxHuman; i++ )
		{
			if( humans.get(i).getInfect() )
			{
				//	add a zombie
				zombies.add( new Zombie(humans.get(i).x,humans.get(i).y,humans.get(i).facing,dp) );
				humans.remove(i);
				
				//	handle indexes correctly to avoid a fault
				maxHuman--;
				i--;
			}
		}
		
	}
	
	/**
	 * Creates an entity at the given {x,y}
	 * Entity cannot be made atop a building
	 */
	public void createEntity(int x, int y, Boolean b)
	{
		int eX = x;
		int eY = y;
		int eDir = Helper.nextInt(Entity.MAXDIR);
		
		//	check if overlapping walls, bounds check must occur first
		if( eX<0 || eX>=width )
			return;
		if( eY<0 || eY>=height )
			return;
		if( walls[eX][eY] )
			return;
		
		if(b==IS_HUMAN)
			humanQueue.add( new Human(eX,eY,eDir,dp));
		else
			zombieQueue.add( new Zombie(eX,eY,eDir,dp) );
	}
	
	/**
	 * Pauses and unpauses the human/zombie
	 */
	private void pause(Boolean b)
	{
		if(b==IS_HUMAN)
			humansPaused = !humansPaused;
		else
			zombiesPaused = !zombiesPaused;
	}
	
	/**
	 * public functions that call the private function properly
	 */
	public void pauseHumans()
	{
		pause(IS_HUMAN);
	}
	
	public void pauseZombies()
	{
		pause(IS_ZOMBIE);
	}
	
	/**
	 * Add from queues to lists at a safe point of execution
	 */
	private void addFromQueue()
	{
		while( humanQueue.size()>0 )
			humans.add(humanQueue.remove(0));
		while( zombieQueue.size()>0 )
			zombies.add(zombieQueue.remove(0));
	}
	
	/**
	 * Generates a random set of numB buildings.
	 * 
	 * @param numB the number of buildings to generate
	 */
	private void randomBuildings(int numB)
	{
		/* Create buildings of a reasonable size for this map */
		int bldgMaxSize = width/6;
		int bldgMinSize = width/50;
		
		/* Produce a bunch of random rectangles and fill in the walls array */
		for(int i=0; i < numB; i++)
		{
			int tx, ty, tw, th;
			tx = Helper.nextInt(width);
			ty = Helper.nextInt(height);
			tw = Helper.nextInt(bldgMaxSize) + bldgMinSize;
			th = Helper.nextInt(bldgMaxSize) + bldgMinSize;
			
			for(int r = ty; r < ty + th; r++)
			{
				if(r >= height)
					continue;
				for(int c = tx; c < tx + tw; c++)
				{
					if(c >= width)
						break;
					walls[c][r] = true;
				}
			}
		}
	}
	
	/**
	 * return the requested type of ArrayList
	 * if b==true, instance is Human, return Zombie
	 * else, instance is Zombie, return Human
	 */
	public static ArrayList<? extends Entity> getArrayList(Boolean b)
	{
		if(b==IS_HUMAN)
			return zombies;
		return humans;
	}
	
	/**
	 * Check if a wall exists
	 * @param x
	 * @param y
	 * @param f facing
	 * @param i offset (default should be 1)
	 * @return true if wall or boundary, false if no wall
	 */
	public static Boolean checkBoundary(int x, int y, int f, int i)
	{
		Boolean b = false;
		switch( f )
		{
			case Entity.UP:
				if( y-i>=0 )
					b = walls[x][y-i];
				else
					b = true;
				break;
			case Entity.RIGHT:
				if( x+i<width )
					b = walls[x+i][y];
				else
					b = true;
				break;
			case Entity.DOWN:
				if( y+i<height )
					b = walls[x][y+i];
				else
					b = true;
				break;
			case Entity.LEFT:
				if( x-i>=0 )
					b = walls[x-i][y];
				else
					b = true;
				break;
		}
		
		return b;
	}
	
	/**
	 * End the world
	 */
	public void end()
	{
		end = true;
	}
	
	/**
	 * Return the value of safeEnd, needed by the above to end safely
	 */
	public Boolean safeEnd()
	{
		return safeEnd;
	}
	
	/**
	 * Updates the state of the city for a time step.
	 */
	public void update()
	{
		//	THE WORLD HAS ENDED DO NOT EXECUTE FURTHER
		if( end )
		{
			safeEnd = true;
			return;
		}
		
		//	safe point to manage human/zombie ratios
		infect();
		addFromQueue();
		
		//	update all entities
		for( Zombie z: zombies )
		{
			z.update(zombiesPaused);
		}
		for( Human h: humans )
		{
			h.update(humansPaused);
		}
		
		if( zc!=zombies.size() || hc!=humans.size() )
		{
			zc = zombies.size();
			hc = humans.size();
			System.out.println(zc+"/"+hc);
		}
	}
	
	/**
	 * update the framerate
	 */
	public void changeRate(int rate)
	{
		this.rate = rate;
	}
	
	/**
	 * Draw the buildings and all humans. Only clears
	 * the screen before drawing if traceMode is false
	 * @param traceMode set to true to leave trails
	 */
	public void draw()
	{
		drawWalls();
		
		for( Zombie z: zombies )
		{
			z.draw();
		}
		for( Human h: humans )
		{
			h.draw();
		}
		
		dp.repaintAndSleep(rate);
	}

	/**
	 * Draw the buildings.
	 */
	private void drawWalls()
	{
		dp.setPenColor(Color.DARK_GRAY);
		for(int r = 0; r < height; r++) 
		{
			for(int c = 0; c < width; c++) 
			{
				if(walls[c][r])
					dp.drawDot(c, r);
			}
		}
	}
	
}
