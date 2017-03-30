
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.border.*;
import java.util.*;
import java.text.*;

public class jshapiro314CarChase extends JPanel {

    String statusStr = "";

    //scale the cars so they fit in the gui
    double scale = 0.3;

    double trooperX = 0;
    double speederX = 0;

    int time;                    // Track the current time.
    int sleepTime = 100;         // For animation.
    int timeStep = sleepTime;    // Advance the clock by this much.

    int tickSpacing = 30;        // Spacing of tick marks along incline.
    int tickSize = 4;            // Size of each tick mark.

    boolean stopped = true;      // Animation status.

    String terminateMsg = "";
    DecimalFormat df = new DecimalFormat ("##.##");

    void start ()
    {
        // Make a thread and run a simulation (whenever "Go" is clicked).
	Thread t = new Thread () {
		public void run ()
		{
		    simulate ();
		}
	    };
	t.start ();
    }


    void simulate ()
    {
	runSimulation (100000);
    }


    double runSimulation (double stopTime)
    {
	// Initialize trooper simulator.
    jshapiro314TrooperSimulator myTrooper = new jshapiro314TrooperSimulator((double)timeStep/1000);
	stopped = false;
	time = 0;

	while (! stopped) {

            // First pause the thread.
	    try {
		Thread.sleep (sleepTime);
	    }
	    catch (InterruptedException e) {
	    }

	    // Now we're past the sleeptime. Update clock.
	    time += timeStep;

        //Now call move on the trooper simulator
        myTrooper.move();
        trooperX = myTrooper.xTrooper;
        speederX = myTrooper.xSpeeder;
        //System.out.println(trooperX);
        //System.out.println(speederX);

	    if ((time >= stopTime) || trooperX >= speederX) {
		    stopped = true;
            repaint ();
            break;
	    }

	    // Redraw screen.
	    repaint ();
	}

	// Done.
        return trooperX;
    }


    public void paintComponent (Graphics g)
    {
	super.paintComponent (g);

	Dimension D = this.getSize ();

	// Background.
	g.setColor (Color.white);
	g.fillRect (0,0, D.width, D.height);

    // Draw the trooper
	g.setColor (Color.red);
        int x = (int) (trooperX*scale);
        int y = 20;
	g.fillOval (x-10, D.height-y-10, 20, 20);

    // Draw the speeder
	g.setColor (Color.blue);
        int xS = (int) (speederX*scale);
        int yS = 40;
	g.fillOval (xS-10, D.height-yS-10, 20, 20);


	// Status:
	g.setColor (Color.black);
	g.setFont (new Font ("Serif", Font.BOLD, 20));
	int seconds = time / 1000;
	int millis = time % 1000;
	String clockStr = "time: " + seconds + ":" + millis;
	g.drawString (clockStr, 300, 20);
        g.setColor (Color.red);
        g.drawString ("dist speeder: " + speederX, 300, 40);
        g.drawString ("dist cop: " + trooperX, 300, 60);
    }


    void go ()
    {
        try {
            stopped = false;
            // Fire off the simulation thread.
            start ();
        }
        catch (Exception e) {
        }
    }


    public void makeFrame ()
    {
        JFrame frame = new JFrame ();
        frame.setSize (500, 400);
        frame.setResizable (true);
        Container cPane = frame.getContentPane ();
        cPane.add (this, BorderLayout.CENTER);
        cPane.add (makeBottomPanel(), BorderLayout.SOUTH);
        frame.setVisible (true);
    }


    JPanel makeBottomPanel ()
    {
        JPanel panel = new JPanel ();
        // Go button.
        JButton button = new JButton ("Go");
        button.addActionListener (
           new ActionListener ()
           {
               public void actionPerformed (ActionEvent a)
               {
                   go ();
               }
           }
        );
        panel.add (button);

        // Quit button.
        panel.add (new JLabel ("       "));
        button = new JButton ("Quit");
        button.addActionListener (
           new ActionListener ()
           {
               public void actionPerformed (ActionEvent a)
               {
                   System.exit(0);
               }
           }
        );
        panel.add (button);

        return panel;
    }

    public static void main (String[] argv)
    {
        jshapiro314CarChase I = new jshapiro314CarChase ();
        I.makeFrame ();
    }

}
