public class jshapiro314TrooperSimulator{

    public double step = 0;
    public double time = 0;
    public double xTrooper = 0;
    public double vTrooper = 0;
    public double aTrooper = 12;

    public double xSpeeder = 0;
    public double vSpeeder = 100;
    public double aSpeeder = 0;

    public static void main(String[] args){
        jshapiro314TrooperSimulator myTrooper = new jshapiro314TrooperSimulator(0.001);
        myTrooper.solve();
    }

    public jshapiro314TrooperSimulator(double newStep){
        step = newStep;
        //System.out.println(step);
    }

    //Calculate the new values for one timestep.
    public void move(){
        vTrooper = vTrooper + aTrooper*step;
        xTrooper = xTrooper + vTrooper*step;

        xSpeeder = xSpeeder + vSpeeder*step;
        time += step;

        //System.out.println(xTrooper);
        //System.out.println(xSpeeder);
    }

    //Figure out when the cop catches the speeder
    public void solve(){
        xTrooper = 0;
        vTrooper = 0;
        aTrooper = 12;

        xSpeeder = 0;
        vSpeeder = 100;
        aSpeeder = 0;
        time = 0;

        move();
        while(xTrooper < xSpeeder){
            move();
        }

        System.out.println("The distance where the cop catches the speeder is: " + xTrooper);
        System.out.println("The distance where the speeder is caught by the cop is: " + xSpeeder);
        System.out.println("The time when the cop catches the speeder is: " + time);
    }

}
