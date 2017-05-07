package zombiesim;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.SwingUtilities;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


public class ZombieSim extends JFrame
{
	private static final long serialVersionUID = 1L;
	private static final int MAX_X   = 350;    //	window size in tiles
	private static final int MAX_Y   = 252;    //	window size in tiles
	private static final int DOT_SIZE = 3;    //	zoom in N times

	private static final String HUMAN_LABEL = "Pause Humans?";
	private static final String ZOMBIE_LABEL = "Pause Zombies?";

	private DotPanel dp;
	private City world;

	private JCheckBox jcbh;    //	humans
	private JCheckBox jcbz;    //	zombies
	private JSlider js;


	/**
	* event handler for all mouse clicks
	*/
	private class FrameMouseHandler extends MouseAdapter implements MouseListener
	{
		public void mouseClicked(MouseEvent e)
		{
			//	must scale down all coordinates by DOT_SIZE
			if( SwingUtilities.isLeftMouseButton(e) )
			{
				world.createEntity(e.getX()/DOT_SIZE, e.getY()/DOT_SIZE,City.IS_ZOMBIE);
			}
			else if( SwingUtilities.isRightMouseButton(e) )
			{
				world.createEntity(e.getX()/DOT_SIZE, e.getY()/DOT_SIZE,City.IS_HUMAN);
			}
		}

		public void mouseReleased(MouseEvent e)
		{
			//	check if the trigger was a JCheckBox
			if( e.getComponent() instanceof JCheckBox )
			{
				JCheckBox jcb = (JCheckBox)e.getComponent();

				if( jcb.getText()==HUMAN_LABEL)
				world.pauseHumans();
				else if( jcb.getText()==ZOMBIE_LABEL )
				world.pauseZombies();
			}
		}

		public void mouseDragged(MouseEvent e)
		{
			mouseClicked(e);
		}
	}

	/**
	* create a new instance of the zombie simulator
	*
	* @param w	width (in tiles)
	* @param h	height (in tiles)
	* @throws InterruptedException
	*/
	public ZombieSim(final int w, final int h) throws InterruptedException
	{
		//	gui setup
		this.setSize(w*DOT_SIZE,h*DOT_SIZE);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setTitle("Zombie Simulation");

		//	create and set the size of the panel
		dp = new DotPanel(w, h, DOT_SIZE);
		dp.setAutoShow(false);

		//	add the panel to the frame
		Container cPane = this.getContentPane();
		cPane.add(dp);

		//	add event listeners to the drawing panel
		cPane.addMouseListener(new FrameMouseHandler());
		cPane.addMouseMotionListener(new FrameMouseHandler());

		//	add panel for checkboxes, buttons, and sliders
		JPanel jp = new JPanel();
		jp.setBackground(Color.DARK_GRAY);
		//jp.setPreferredSize(new Dimension(w*DOT_SIZE,67));

		jcbh = new JCheckBox(HUMAN_LABEL);
		jcbh.addMouseListener(new FrameMouseHandler());
		jcbh.setBackground(Color.DARK_GRAY);
		jcbh.setForeground(Color.WHITE);
		jp.add(jcbh);

		jcbz = new JCheckBox(ZOMBIE_LABEL);
		jcbz.addMouseListener(new FrameMouseHandler());
		jcbz.setBackground(Color.DARK_GRAY);
		jcbz.setForeground(Color.WHITE);
		jp.add(jcbz);

		//	we want our slider to work like this: left-slow, right-fast
		//	we will translate 10 values from 0-10 to our final values
		//	our default will be 30fps, if max is 60fps then we start at half
		js = new JSlider(1,10,5);
		js.setBackground(Color.DARK_GRAY);
		js.setForeground(Color.WHITE);
		js.setMinorTickSpacing(1);
		js.setPaintTicks(true);
		js.addChangeListener(new ChangeListener(){
			public void stateChanged(ChangeEvent e)
			{
				JSlider source = (JSlider)e.getSource();
				int rate = (int)source.getValue();

				rate *= 6; //	our desired fps (default: 5*6=30fps)
				rate = 1000/rate; //	our desired ms delay

				world.changeRate(rate);
			}
		});
		jp.add(js);

		//	we want our slider to work like this: left-slow, right-fast
		//	we will translate 7 values from 1-7 to our final values
		//	our default will be 1 second steps, 2 is minute, 3, hour, etc
		js = new JSlider(1,7,1);
		js.setBackground(Color.DARK_GRAY);
		js.setForeground(Color.WHITE);
		js.setMinorTickSpacing(1);
		js.setPaintTicks(true);
		js.addChangeListener(new ChangeListener(){
			public void stateChanged(ChangeEvent e)
			{
				JSlider source = (JSlider)e.getSource();
				int rate = (int)source.getValue();

				//Put in switch statement to convert from 1-7 to day, week, month, etc
				switch (rate) {
					//second
					case 1:  rate = 1;
					break;
					//minute
					case 2:  rate = 60;
					break;
					//hour
					case 3:  rate = 3600;
					break;
					//day
					case 4:  rate = 86400;
					break;
					//week
					case 5:  rate = 604800;
					break;
					//month
					case 6:  rate = 2629746;
					break;
					//year
					case 7:  rate = 31556952;
					break;
					//second
					default: rate = 1;
					break;
				}
				world.changeTimePeriod(rate);
			}
		});
		jp.add(js);

		//	reset button
		JButton jb = new JButton("Reset");
		jb.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				//	reset everything we need reset
				js.setValue(5); //	default from above
				jcbh.setSelected(false);
				jcbz.setSelected(false);

				//	wait until it is safe for the world to end
				world.end();
				//while( !world.safeEnd() );

				//world = new City(w,h,60,200,dp);
				world = new City(dp,w,h,200,200);
				dp.clear();

			}
		});
		jp.add(jb);

		cPane.add(jp,BorderLayout.SOUTH);

		//	finish initialization
		this.pack();
		dp.init();
		dp.clear();
		dp.setPenColor(Color.red);
		//this.setIconImage(img.getImage());
		this.setLocationByPlatform(true);
		this.setVisible(true);

		//	standard output
		System.out.println("Left click to add zombie, right click to add human");

		//	begin simulation
		//world = new City(w,h,60,200,dp);
		world = new City(dp,w,h,200,200);
		while(true)
		{
			world.draw();
			//To control drawing every minute instead of every second
			for(int i=0; i<world.getTimePeriod(); i++) {
				world.update();
			}
		}
	}

	/**
	* run a zombie infestation simulation
	* @throws InterruptedException
	*/
	public static void main(String[] args) throws InterruptedException
	{
		new ZombieSim(MAX_X,MAX_Y);
	}

}
