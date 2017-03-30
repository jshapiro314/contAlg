public class jshapiro314SkyDiverSimulator{

    public double step = 0;
    public double time = 0;
    public double deployTime = 0;
    public double g = -32.174;
    public double yDiver = 1200;
    public double vDiver = 0;
    public double aDiver = g;
    public double k = 0;
    public double kFall = 0.268;
    public double kDeployed = 1.342;


    public static void main(String[] args){
        jshapiro314SkyDiverSimulator mySkyDiver = new jshapiro314SkyDiverSimulator(0.001, 0);
        mySkyDiver.solve(-40);
    }

    public jshapiro314SkyDiverSimulator(double newStep, double chuteTime){
        step = newStep;
        deployTime = chuteTime;
        //System.out.println(step);
    }

    //Calculate the new values for one timestep.
    public void move(){
        if(time < deployTime){
            k = kFall;
        }else{
            k = kDeployed;
        }
        aDiver = g - k*vDiver;
        vDiver = vDiver + aDiver*step;
        yDiver = yDiver + vDiver*step;

        time += step;

        //System.out.println(xTrooper);
        //System.out.println(xSpeeder);
    }

    //Figure out when the cop catches the speeder
    public void solve(double endVel){
        double possibleVel = 0;
        double timeOfchute = 0;
        do{
            timeOfchute+=step;
            possibleVel = run(timeOfchute);
        }while(possibleVel > endVel);

        //At this point in time, the possibleVel is probably higher than the desired velocity. Better safe than sorry, we better go with the final velocity one timestep earlier. While we could just subtract from the timestep, we'll rerun so we can print out our final y and velocity too.
        run(timeOfchute - step);
        //System.out.println(yDiver);
        System.out.println("Diver's final velocity: " + vDiver);
        System.out.println("The parachute should be opened at time: " + (timeOfchute - step));
    }

    //Run the simulator once, deploying parachute at given time
    public double run(double timeOfchute){
        g = -32.174;
        time = 0;
        deployTime = timeOfchute;
        yDiver = 1200;
        vDiver = 0;
        aDiver = g;

        do{
            move();
        }
        while(yDiver > 0);


        //output the final velocity
        return vDiver;

    }

}
