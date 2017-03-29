public class SkyDiver{

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
        SkyDiver mySkyDiver = new SkyDiver(0.01, 0);
        mySkyDiver.solve(-40);
    }

    public SkyDiver(double newStep, double schuteTime){
        step = newStep;
        deployTime = schuteTime;
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
        double timeOfSchute = 0;
        do{
            timeOfSchute+=step;
            possibleVel = run(timeOfSchute);
        }while(possibleVel > endVel);

        //At this point in time, the possibleVel is probably higher than the desired velocity. Better safe than sorry, we better go with the final velocity one timestep earlier. While we could just subtract from the timestep, we'll rerun so we can print out our final y and velocity too.
        run(timeOfSchute - step);
        System.out.println(yDiver);
        System.out.println(vDiver);
        System.out.println("The paraschute should be opened at time: " + (timeOfSchute - step));
    }

    //Run the simulator once, deploying parachute at given time
    public double run(double timeOfSchute){
        g = -32.174;
        time = 0;
        deployTime = timeOfSchute;
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
