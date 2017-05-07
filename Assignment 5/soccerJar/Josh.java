import java.util.*;

public class Josh implements TeamController {

    SensorPack sensors;           // The sensor pack handed to us.
    int numPlayers;               // How many players/team.
    ArrayList<Player> players;    // List of our players.
    int myteam = -1;              // Our team number (0 or 1).
    boolean onLeft;               // Are we on the left or right?

    boolean debug = false;        // If we want to turn on debugging.

    String defensePlayerState = "moveBottom";
    String offensePlayerState = "followBall";
    String goalState = "rotate";


    public String getName ()
    {
	return "Josh";
    }


    public void init (SensorPack sensors, int numPlayers, int myteam, boolean onLeft)
    {
        this.sensors = sensors;
        this.numPlayers = numPlayers;
        this.myteam = myteam;
        this.onLeft = onLeft;
        players = new ArrayList<Player> ();
    }

    public void init (int playerNum, double initX, double initY, double initTheta)
    {
        Player p = new Player ();
        p.playerNum = playerNum;
        p.x = initX;
        p.y = initY;
        p.theta = initTheta;
        players.add (p);
    }


    public void move ()
    {
        updateLocations ();
        int t = (int) sensors.getCurrentTime ();

        //Player 1 is playing defense
        moveDefense(players.get(0));

        //Player 2 is offense
        //offense();

    }

    public void moveDefense(Player p){
        int x = 0;
        if(onLeft){
            x = 30;
        }else{
            x = 70;
        }
        switch(defensePlayerState){
            case "moveBottom":
                if(distance(p.x,p.y,x,10) < 2){
                    defensePlayerState = "moveTop";
                }
                moveToGoal(p,x,10);
                break;
            case "moveTop":
                if(distance(p.x,p.y,x,30) < 2){
                    defensePlayerState = "moveBottom";
                }
                moveToGoal(p,x,30);
                break;
            default:
                moveToGoal(p,x,30);
                break;
        }
    }

    public void moveToGoal(Player p,int x,int y){
        double angle = sensors.getAngle(p.x,p.y,x,y);
        double theta = p.theta;
        System.out.println(angle);
        switch(goalState){
            case "rotate":
                System.out.println("Rotate");
                if(Math.abs(angle-theta)>0.1){
                    p.vel = 0;
                    p.phi = 10;
                }else{
                    goalState = "move";
                    p.vel = 10;
                    p.phi = 0;
                }
                break;
            case "move":
                System.out.println("Move");
                if(Math.abs(angle-theta)<=0.1){
                    p.vel = 10;
                    p.phi = 0;
                }else{
                    goalState = "rotate";
                    p.vel = 0;
                    p.phi = 10;
                }
                break;
            default:
                break;



        }
    }

    public double getControl  (int p, int i)
    {
	Player player = getPlayer (p);
	if (i == 1) {
	    return player.vel;
	}
	else {
	    return player.phi;
	}
    }


    public boolean triesKick (int p)
    {
        return false;
    }


    public void kickSuccessful (int p)
    {
    }


    public boolean triesGrab (int p)
    {
        return false;
    }


    public void grabSuccessful (int p)
    {
    }


    void updateLocations ()
    {
	for (Player p: players) {
	    p.tryKick = false;
	    p.x = sensors.getX (myteam, p.playerNum);
	    p.y = sensors.getY (myteam, p.playerNum);
	    p.theta = sensors.getTheta (myteam, p.playerNum);
            p.distToBall = distance (sensors.getBallX(), sensors.getBallY(), p.x,p.y);
            if ( (sensors.isBallHeld()) && (sensors.ballHeldByTeam() == myteam) && p.playerNum == sensors.ballHeldByPlayer()) {
                p.hasBall = true;
            }
            else {
                p.hasBall = false;
            }
	}
    }

    Player getPlayer (int k)
    {
	for (Player player: players) {
	    if (player.playerNum == k) {
		return player;
	    }
	}
	return null;
    }


    double distance (double x1, double y1, double x2, double y2)
    {
	return Math.sqrt ( (x1-x2)*(x1-x2) + (y1-y2)*(y1-y2) );
    }

    public void startDebug ()
    {
        debug = true;
    }

}
