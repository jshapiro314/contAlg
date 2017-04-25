package zombiesim;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

class DrawGUI extends JFrame
{
	//	constants
	private static final long serialVersionUID	= 1L;
	private static final int AFRICANPURPLE		= 0xB284BE;
	private static final int ATOMICTANGERINE	= 0xFF9966;
	private static final int FUZZYWUZZY			= 0xCC6666;
	
	protected DotPanel dp;
	protected int width;
	protected int height;

	private static final int dotSize = 10;
	
	/**
	 * Event handler for all mouse clicks
	 */
	class FrameMouseHandler extends MouseAdapter implements MouseListener
	{
		public void mouseClicked(MouseEvent m)
		{
			if( SwingUtilities.isLeftMouseButton(m) )
			{
				dp.drawDot(m.getX()/dotSize,m.getY()/dotSize);
			}
			else if( SwingUtilities.isRightMouseButton(m) )
			{
				Random rand = new Random();
				float r = rand.nextFloat();
				float g = rand.nextFloat();
				float b = rand.nextFloat();
				dp.setPenColor(new Color(r,g,b));
				//System.out.println("{"+r+","+g+","+b+"}");
			}
		}
		
		public void mouseDragged(MouseEvent m)
		{
			mouseClicked(m);
		}
	}
	
	/**
	 * Draw a picture of Dot Pixis and Eren Jaeger (Shingeki no Kyojin)
	 */
	private void drawErenJaeger(ImageIcon img)
	{
		//	intialize a blank BufferedImage
		BufferedImage _img = new BufferedImage(
				img.getIconWidth(),
				img.getIconHeight(),
				BufferedImage.TYPE_INT_RGB
		);
		
		//	paint ImageIcon to BufferedImage
		Graphics g = _img.createGraphics();
		img.paintIcon(null, g, 0, 0);
		g.dispose();
		
		//	read BufferedImage pixel-by-pixel and recreate in Dot Pixel
		for( int j=0; j<height;j++ )
		{
			for( int i=0; i<width;i++ )
			{
				int buf = _img.getRGB(i, j);
				Color c = new Color(buf,true);
				dp.setPenColor(c);
				dp.drawDot(i,j);
			}
		}
		
		//	reset color of the brush to default (red)
		dp.setPenColor(Color.RED);
	}

	/**
	 * This fills the frame with a "DotPanel", a type of drawing canvas that
	 * allows you to easily draw squares to the screen, and a "JPanel" which
	 * sits at the bottom of the screen and will hold the buttons used for your
	 * interface.
	 */
	public DrawGUI(int w, int h)
	{
		//	gui setup
		this.setSize(w * dotSize, h * dotSize);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setTitle("Pixel Art");
		width = w;
		height = h;
		
		//	default background for panel as well as icon
		final ImageIcon img = new ImageIcon( DrawGUI.class.getResource("icon40x40.png") );

		//	create and set the size of the panel
		dp = new DotPanel(w, h, dotSize);
		dp.setAutoShow(true);

		//	add the panel to the frame
		Container cPane = this.getContentPane();
		cPane.add(dp);
		
		//	add event listeners to the drawing panel
		cPane.addMouseListener(new FrameMouseHandler());
		cPane.addMouseMotionListener(new FrameMouseHandler());
		
		//	create a panel for holding buttons
		JPanel buttonP = new JPanel();
		buttonP.setBackground(Color.DARK_GRAY);
		buttonP.setPreferredSize(new Dimension(width,67));
		
		//	african purple button
		JButton button = new JButton("African Purple");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				dp.setPenColor(new Color(AFRICANPURPLE));
			}
		});
		buttonP.add(button);
		
		//	atomic tangerine button
		button = new JButton("Atomic Tangerine");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				dp.setPenColor(new Color(ATOMICTANGERINE));
			}
		});
		buttonP.add(button);
		
		//	fuzzy wuzzy button
		button = new JButton("Fuzzy Wuzzy");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				dp.setPenColor(new Color(FUZZYWUZZY));
			}
		});
		buttonP.add(button);
		
		//	black button
		button = new JButton("Black");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				dp.setPenColor(Color.BLACK);
			}
		});
		buttonP.add(button);
		
		//	eren jaeger and dot pixis button
		button = new JButton("Eren Jaeger and Dot Pixis");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				drawErenJaeger(img);
			}
		});
		buttonP.add(button);
		
		//	clear button
		button = new JButton("Clear");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				Color c = dp.getPenColor();
				dp.clear();
				dp.setPenColor(Color.BLACK);
				dp.drawDot(0, 0);
				dp.setPenColor(c);
			}
		});
		buttonP.add(button);
		
		//	add the button panel to the content pane
		cPane.add(buttonP, BorderLayout.SOUTH);
		
		/* Initialize the DotPanel canvas:
		 * 
		 * You CANNOT draw to the panel BEFORE this code is called.
		 * You CANNOT add new widgets to the frame AFTER this is called.
		 * 
		 */
		this.pack();
		dp.init();
		dp.clear();
		dp.setPenColor(Color.red);
		this.setTitle("Dot Pixel ft. Eren Jaeger and Dot Pixis");
		this.setIconImage(img.getImage());
		this.setLocationByPlatform(true);
		this.setVisible(true);
		
		//	print out additional instructions
		System.out.println("original art by 054art.tumblr.com");
		System.out.println("http://054art.tumblr.com/private/51823635602/tumblr_mnoiatvfyC1rd6udk");
		System.out.println("press LEFT MOUSE to place a pixel");
		System.out.println("press RIGHT MOUSE to change to a random color");
	}

	public static void main(String[] args)
	{
		// Create a new GUI window holding a 40x40 DotPanel
		new DrawGUI(40, 40);
	}

}