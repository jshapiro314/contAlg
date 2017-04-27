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
	 * Create a new City representing GW and fill it with buildings and people.
	 * @param w width of city
	 * @param h height of city
	 * @param numP number of people
	 * @param numI number of infected people
	 */
	public City( DotPanel dp, int w, int h, int numP, int numI)
	{
		width = w;
		height = h;
		rate = START_RATE;
		end = safeEnd = false;
		walls = new boolean[w][h];
		this.dp = dp;

		//randomBuildings(numB);
		createGWBuildings();
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
	 * Generates the GW campus for the simulator. We are mapping from 19th to 24th and E to I st.
	 * This assumes the map is 350x252 for the buildings to be placed properly.
	 *
	 */
	private void createGWBuildings()
	{
		//Generate block 0 (50x50)
		//(7,7)-(57,57)
		makeBlocker0();

		//Generate block 1 (50x50)
		//(64,7)-(114,57)
		makeSEH();
		makeFullBright();
		makeKennedy();
		makeMunson();

		//Generate block 2 (80x50)
		//(121,7)-(201,57)
		makeAcademicCenter();
		makeRomePhilips();
		makeDistrict();
		makeMarvin();
		makeLafayette();

		//Generate block 3 (65x50)
		//(208,7)-(273,57)
		make2000Penn();
		makeSMPA();
		makeBlocker3();

		//Generate block 4 (63x50)
		//(280,7)-(343,57)
		makeBlocker4();

		//Generate block 5 (50x60)
		//(7,64)-(57,124)
		makeAmsterdam();
		makeHealWell();
		makeBlocker5();

		//Generate block 6 (50x60)
		//(64,64)-(114,124)
		makeTompkins();
		makeMadison();
		makeDuquesAndFunger();

		//Generate block 7 (80x60)
		//(121,64)-(201,124)
		makeGelman();
		makeLisner();
		makeStaughton();
		makeMonroe();

		//Generate block 8 (65x60)
		//(208,64)-(273,124)
		makeCorcoran();
		makeLawSchool();
		makeLisnerHall();
		makeTextileMuseum();

		//Generate block 9 (63x60)
		//(280,64)-(343,124)
		makeBlocker9();

		//Generate block 10 (50x50)
		//(7,131)-(57,181)
		makeShenkman();
		makeBlocker10();

		//Generate block 11 (50x50)
		//(64,131)-(114,181)
		makeTownHouses();
		makeSmithCenter();

		//Generate block 12 (80x50)
		//(121,131)-(201,181)
		makeSouthHall();
		makeGuthridge();
		makeBlocker12();

		//Generate block 13 (65x50)
		//(208,131)-(273,181)
		makeBlocker13();
		makeFSK();
		makePatomac();

		//Generate block 14 (63x50)
		//(280,131)-(343,181)
		makeBlocker14();

		//Generate block 15 (50x57)
		//(7,188)-(57,245)
		makeBlocker15();

		//Generate block 16 (50x57)
		//(64,188)-(114,245)
		makeIHouse();
		makeBlocker16();

		//Generate block 17 (80x57)
		//(121,188)-(201,245)
		makeBlocker17();
		makeDakota();

		//Generate block 18 (65x57)
		//(208,188)-(273,245)
		makeBlocker18();

		//Generate block 19 (63x57)
		//(280,188)-(343,245)
		makeBlocker19();
		makeElliot();
		makeThurston();
	}
	private void makeBlocker0(){
		for(int x=7;x<57;x++){
			for(int y=7;y<57;y++){
				walls[x][y] = true;
			}
		}
	}
	private void makeThurston(){
		for(int x=317;x<343;x++){
			walls[x][188] = true;
			walls[x][218] = true;
		}
		for(int y=188;y<219;y++){
			walls[317][y] = true;
			walls[342][y] = true;
		}
		for(int i=327;i<333;i++){
			walls[i][188] = false;
		}
	}
	private void makeElliot(){
		for(int x=280;x<343;x++){
			walls[x][222] = true;
			walls[x][244] = true;
		}
		for(int y=222;y<245;y++){
			walls[280][y] = true;
			walls[342][y] = true;
		}
		for(int i=308;i<314;i++){
			walls[i][244] = false;
		}
	}
	private void makeBlocker19(){
		for(int x=280;x<316;x++){
			for(int y=188;y<216;y++){
				walls[x][y] = true;
			}
		}
	}
	private void makeBlocker18(){
		for(int x=208;x<273;x++){
			for(int y=188;y<245;y++){
				walls[x][y] = true;
			}
		}
	}
	private void makeDakota(){
		for(int x=185;x<201;x++){
			walls[x][188] = true;
			walls[x][202] = true;
		}
		for(int y=188;y<203;y++){
			walls[185][y] = true;
			walls[200][y] = true;
		}
		for(int i=190;i<196;i++){
			walls[i][188] = false;
		}
	}
	private void makeBlocker17(){
		for(int x=121;x<183;x++){
			for(int y=188;y<245;y++){
				walls[x][y] = true;
			}
		}
		for(int x=183;x<201;x++){
			for(int y=205;y<245;y++){
				walls[x][y] = true;
			}
		}
	}
	private void makeBlocker16(){
		for(int x=64;x<114;x++){
			for(int y=216;y<245;y++){
				walls[x][y] = true;
			}
		}
	}
	private void makeIHouse(){
		for(int x=77;x<114;x++){
			walls[x][188] = true;
			walls[x][211] = true;
		}
		for(int y=188;y<212;y++){
			walls[77][y] = true;
			walls[113][y] = true;
		}
		for(int i=197;i<203;i++){
			walls[113][i] = false;
		}
	}
	private void makeBlocker15(){
		for(int x=7;x<57;x++){
			for(int y=188;y<245;y++){
				walls[x][y] = true;
			}
		}
	}
	private void makeBlocker14(){
		for(int x=280;x<343;x++){
			for(int y=131;y<181;y++){
				walls[x][y] = true;
			}
		}
	}
	private void makePatomac(){
		for(int x=241;x<253;x++){
			walls[x][154] = true;
			walls[x][180] = true;
		}
		for(int y=154;y<181;y++){
			walls[241][y] = true;
			walls[252][y] = true;
		}
		for(int i=244;i<250;i++){
			walls[i][180] = false;
		}
	}
	private void makeFSK(){
		for(int x=255;x<273;x++){
			walls[x][154] = true;
			walls[x][180] = true;
		}
		for(int y=154;y<181;y++){
			walls[255][y] = true;
			walls[272][y] = true;
		}
		for(int i=261;i<267;i++){
			walls[i][180] = false;
		}
	}
	private void makeBlocker13(){
		for(int x=208;x<273;x++){
			for(int y=131;y<150;y++){
				walls[x][y] = true;
			}
		}
		for(int x=208;x<240;x++){
			for(int y=154;y<181;y++){
				walls[x][y] = true;
			}
		}
	}
	private void makeBlocker12(){
		for(int x=156;x<201;x++){
			for(int y=131;y<149;y++){
				walls[x][y] = true;
			}
		}
	}
	private void makeGuthridge(){
		for(int x=152;x<181;x++){
			walls[x][170] = true;
			walls[x][180] = true;
		}
		for(int y=170;y<181;y++){
			walls[152][y] = true;
			walls[180][y] = true;
		}
		for(int i=164;i<170;i++){
			walls[i][180] = false;
		}
	}
	private void makeSouthHall(){
		for(int x=121;x<144;x++){
			walls[x][158] = true;
			walls[x][180] = true;
		}
		for(int y=158;y<181;y++){
			walls[121][y] = true;
			walls[143][y] = true;
		}
		for(int i=130;i<136;i++){
			walls[i][180] = false;
		}
	}
	private void makeSmithCenter(){
		for(int x=85;x<114;x++){
			walls[x][139] = true;
			walls[x][176] = true;
		}
		for(int y=139;y<177;y++){
			walls[85][y] = true;
			walls[113][y] = true;
		}
		for(int i=155;i<161;i++){
			walls[113][i] = false;
		}
	}
	private void makeTownHouses(){
		for(int x=64;x<80;x++){
			walls[x][131] = true;
			walls[x][180] = true;
		}
		for(int y=131;y<181;y++){
			walls[64][y] = true;
			walls[79][y] = true;
		}
		for(int i=153;i<159;i++){
			walls[64][i] = false;
		}
	}
	private void makeBlocker10(){
		for(int x=7;x<34;x++){
			for(int y=131;y<181;y++){
				walls[x][y] = true;
			}
		}
	}
	private void makeShenkman(){
		for(int x=39;x<57;x++){
			walls[x][131] = true;
			walls[x][180] = true;
		}
		for(int y=131;y<181;y++){
			walls[39][y] = true;
			walls[56][y] = true;
		}
		for(int i=153;i<159;i++){
			walls[56][i] = false;
		}
	}
	private void makeBlocker9(){
		for(int x=280;x<343;x++){
			for(int y=64;y<124;y++){
				walls[x][y] = true;
			}
		}
	}
	private void makeTextileMuseum(){
		for(int x=208;x<224;x++){
			walls[x][98] = true;
			walls[x][123] = true;
		}
		for(int y=98;y<124;y++){
			walls[208][y] = true;
			walls[223][y] = true;
		}
		for(int i=108;i<114;i++){
			walls[208][i] = false;
		}
	}
	private void makeLisnerHall(){
		for(int x=227;x<273;x++){
			walls[x][114] = true;
			walls[x][123] = true;
		}
		for(int y=114;y<124;y++){
			walls[227][y] = true;
			walls[272][y] = true;
		}
		for(int i=116;i<122;i++){
			walls[272][i] = false;
		}
	}
	private void makeLawSchool(){
		for(int x=256;x<273;x++){
			walls[x][64] = true;
			walls[x][111] = true;
		}
		for(int y=64;y<112;y++){
			walls[256][y] = true;
			walls[272][y] = true;
		}
		for(int i=85;i<91;i++){
			walls[256][i] = false;
		}
	}
	private void makeCorcoran(){
		for(int x=208;x<225;x++){
			walls[x][64] = true;
			walls[x][97] = true;
		}
		for(int y=64;y<98;y++){
			walls[208][y] = true;
			walls[224][y] = true;
		}
		for(int i=78;i<84;i++){
			walls[208][i] = false;
			walls[224][i] = false;
		}
	}
	private void makeMonroe(){
		for(int x=164;x<186;x++){
			walls[x][111] = true;
			walls[x][123] = true;
		}
		for(int x=186;x<201;x++){
			walls[x][98] = true;
			walls[x][123] = true;
		}
		for(int y=111;y<124;y++){
			walls[164][y] = true;
			walls[200][y] = true;
		}
		for(int y=98;y<111;y++){
			walls[185][y] = true;
			walls[200][y] = true;
		}
		for(int i=172;i<178;i++){
			walls[i][111] = false;
			walls[i][123] = false;
		}
	}
	private void makeStaughton(){
		for(int x=121;x<144;x++){
			walls[x][101] = true;
			walls[x][107] = true;
		}
		for(int y=101;y<108;y++){
			walls[121][y] = true;
			walls[143][y] = true;
		}
		for(int i=130;i<136;i++){
			walls[i][101] = false;
		}
	}
	private void makeLisner(){
		for(int x=176;x<201;x++){
			walls[x][64] = true;
			walls[x][82] = true;
		}
		for(int y=64;y<83;y++){
			walls[176][y] = true;
			walls[200][y] = true;
		}
		for(int i=71;i<77;i++){
			walls[200][i] = false;
		}
	}
	private void makeGelman(){
		for(int x=121;x<144;x++){
			walls[x][64] = true;
			walls[x][83] = true;
		}
		for(int y=64;y<84;y++){
			walls[121][y] = true;
			walls[143][y] = true;
		}
		for(int i=71;i<77;i++){
			walls[143][i] = false;
		}
	}
	private void makeTompkins(){
		for(int x=64;x<80;x++){
			walls[x][71] = true;
			walls[x][92] = true;
		}
		for(int y=71;y<93;y++){
			walls[64][y] = true;
			walls[79][y] = true;
		}
		for(int i=79;i<85;i++){
			walls[64][i] = false;
		}
	}
	private void makeMadison(){
		for(int x=95;x<114;x++){
			walls[x][64] = true;
			walls[x][75] = true;
		}
		for(int y=64;y<76;y++){
			walls[95][y] = true;
			walls[114][y] = true;
		}
		for(int i=102;i<108;i++){
			walls[i][64] = false;
		}
	}
	private void makeDuquesAndFunger(){
		for(int x=64;x<88;x++){
			walls[x][110] = true;
			walls[x][123] = true;
		}
		for(int x=88;x<114;x++){
			walls[x][87] = true;
			walls[x][123] = true;
		}
		for(int y=87;y<110;y++){
			walls[87][y] = true;
			walls[114][y] = true;
		}
		for(int y=110;y<124;y++){
			walls[64][y] = true;
			walls[114][y] = true;
		}
		for(int i=73;i<79;i++){
			walls[i][123] = false;
		}
		for(int i=96;i<102;i++){
			walls[114][i] = false;
		}
	}
	private void makeAmsterdam(){
		for(int x=7;x<38;x++){
			walls[x][64] = true;
			walls[x][77] = true;
		}
		for(int y=64;y<78;y++){
			walls[7][y] = true;
			walls[37][y] = true;
		}
		for(int i=20;i<26;i++){
			walls[i][64] = false;
		}
	}
	private void makeHealWell(){
		for(int x=21;x<48;x++){
			walls[x][109] = true;
			walls[x][124] = true;
		}
		for(int y=109;y<124;y++){
			walls[21][y] = true;
			walls[47][y] = true;
		}
		for(int i=32;i<38;i++){
			walls[i][124] = false;
		}
	}
	private void makeBlocker5(){
		for(int x=7;x<57;x++){
			for(int y=81;y<105;y++){
				walls[x][y] = true;
			}
		}

		for(int x=7;x<17;x++){
			for(int y=105;y<124;y++){
				walls[x][y] = true;
			}
		}

		for(int x=42;x<57;x++){
			for(int y=64;y<81;y++){
				walls[x][y] = true;
			}
		}
	}
	private void makeBlocker4(){
		for(int x=280;x<343;x++){
			for(int y=7;y<57;y++){
				walls[x][y] = true;
				walls[x][y] = true;
			}
		}

	}
	private void make2000Penn(){
		for(int x=208;x<273;x++){
			walls[x][7] = true;
			walls[x][24] = true;
		}
		for(int y=7;y<25;y++){
			walls[208][y] = true;
			walls[273][y] = true;
		}
		for(int i=238;i<244;i++){
			walls[i][7] = false;
			walls[i][24] = false;
		}
	}
	private void makeSMPA(){
		for(int x=208;x<236;x++){
			walls[x][35] = true;
			walls[x][56] = true;
		}
		for(int y=35;y<57;y++){
			walls[208][y] = true;
			walls[236][y] = true;
		}
		for(int i=220;i<226;i++){
			walls[i][56] = false;
		}
	}
	private void makeBlocker3(){
		for(int x=239;x<273;x++){
			for(int y=31;y<57;y++){
				walls[x][y] = true;
				walls[x][y] = true;
			}
		}

	}
	private void makeLafayette(){
		for(int x=192;x<201;x++){
			walls[x][7] = true;
			walls[x][28] = true;
		}
		for(int y=7;y<29;y++){
			walls[192][y] = true;
			walls[200][y] = true;
		}
		for(int i=15;i<21;i++){
			walls[200][i] = false;
		}
	}
	private void makeMarvin(){
		for(int x = 177; x < 190; x++){
			walls[x][7] = true;
			walls[x][56] = true;
		}
		for(int x = 190; x < 201; x++){
			walls[x][32] = true;
			walls[x][56] = true;
		}
		for(int y =7;y<32;y++){
			walls[177][y] = true;
			walls[190][y] = true;
		}
		for(int y =32;y<57;y++){
			walls[177][y] = true;
			walls[200][y] = true;
		}
		for(int i=181;i<187;i++){
			walls[i][56] = false;
		}
		for(int i=42;i<48;i++){
			walls[177][i] = false;
			walls[200][i] = false;
		}
	}
	private void makeDistrict(){
		for(int x = 161; x < 174; x++){
			walls[x][7] = true;
			walls[x][56] = true;
		}
		for(int y =7;y<57;y++){
			walls[161][y] = true;
			walls[173][y] = true;
		}
		for(int i=163;i<171;i++){
			walls[i][7] = false;
			walls[i][56] = false;
		}
	}
	private void makeRomePhilips(){
		for(int x = 145; x < 156; x++){
			walls[x][14] = true;
			walls[x][56] = true;
		}
		for(int y =14;y<57;y++){
			walls[145][y] = true;
			walls[155][y] = true;
		}
		for(int i=148;i<154;i++){
			walls[i][56] = false;
		}
	}
	private void makeAcademicCenter(){
		for(int x = 121; x < 140; x++){
			walls[x][24] = true;
			walls[x][56] = true;
		}
		for(int y =24;y<57;y++){
			walls[121][y] = true;
			walls[139][y] = true;
		}
		for(int i=38;i<44;i++){
			walls[121][i] = false;
		}
	}
	private void makeMunson(){
		for(int x = 88; x < 99; x++){
			walls[x][7] = true;
			walls[x][18] = true;
		}
		for(int y =7;y<19;y++){
			walls[88][y] = true;
			walls[98][y] = true;
		}
		for(int i=91;i<97;i++){
			walls[i][7] = false;
		}
	}

	private void makeKennedy(){
		for(int x = 74; x < 84; x++){
			walls[x][7] = true;
			walls[x][23] = true;
		}
		for(int y =7;y<24;y++){
			walls[74][y] = true;
			walls[83][y] = true;
		}
		for(int i=76;i<82;i++){
			walls[i][7] = false;
		}
	}

	private void makeFullBright(){
		for(int x = 74; x < 81; x++){
			walls[x][36] = true;
			walls[x][56] = true;
		}
		for(int y =36;y<57;y++){
			walls[74][y] = true;
			walls[80][y] = true;
		}
		for(int i=44;i<50;i++){
			walls[80][i] = false;
		}
	}

	private void makeSEH(){
		for(int x = 64; x < 87; x++){
			walls[x][26] = true;
			walls[x][33] = true;
		}
		for(int x  = 87;x<101;x++){
			walls[x][24] = true;
			walls[x][56] = true;
		}
		for(int x  = 101;x<114;x++){
			walls[x][7] = true;
			walls[x][56] = true;
		}
		for(int y = 27;y<33;y++){
			walls[64][y] = true;
			walls[113][y] = true;
		}
		for(int y = 33;y<57;y++){
			walls[87][y] = true;
			walls[113][y] = true;
		}
		for(int y=24;y<27;y++){
			walls[87][y] = true;
			walls[113][y] = true;
		}
		for(int y=7;y<25;y++){
			walls[101][y] = true;
			walls[113][y] = true;
		}
		for(int i=42;i<48;i++){
			walls[87][i] = false;
			walls[113][i] = false;
		}
		for(int i=105;i<111;i++){
			walls[i][56] = false;
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
