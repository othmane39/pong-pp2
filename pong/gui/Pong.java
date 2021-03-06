package pong.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
//import java.awt.Point;
//import java.awt.Toolkit;
import java.awt.Dimension;
//import java.awt.event.KeyEvent;
//import java.awt.event.KeyListener;
//import javax.swing.ImageIcon;
//import java.util.ArrayList;
//import java.util.List;

import javax.swing.JPanel;

/**
 * An Pong is a Java graphical container that extends the JPanel class in
 * order to display graphical elements.
 */
public class Pong extends JPanel{

	private static final long serialVersionUID = 1L;

	/**
	 * Constant (c.f. final) common to all Pong instances (c.f. static)
	 * defining the background color of the Pong
	 */
	private static final Color backgroundColor = new Color(0xFF, 0x40, 0);
	private static final Color backgroundColor2 = new Color(0xAA, 0x40, 0);

	/**
	 * Width of pong area
	 */
	public static final int SIZE_PONG_X = 800;
	/**
	 * Height of pong area
	 */
	public static final int SIZE_PONG_Y = 600;
	/**
	 * Time step of the simulation (in ms)
	 */
	public static final int timestep = 10;
	/**
	 * Speed of ball (in pixels per second)
	 */
	public static final int BALL_SPEED = 2;
	/**
	 * Speed of racket (in pixels per second)
	 */
	public static final int RACKET_SPEED = 4;

	/**
	 * Pixel data buffer for the Pong rendering
	 */
	private Image buffer = null;
	/**
	 * Graphic component context derived from buffer Image
	 */
	private Graphics graphicContext = null;

	/**
	 * Ball to be displayed
	 */
	

	
	private Ball ball;
	

	private Racket racket;
	private Racket racket_opp;
	
	static public boolean mirror_player = false;

	public Pong() {

		ball = new Ball();
		racket = new Racket();
		racket_opp = new Racket();
		/////ICI FAUT FAIRE UN TRUC
		racket.setStartsPosition(1);
		racket_opp.setStartsPosition(2);
		
		this.setPreferredSize(new Dimension(SIZE_PONG_X, SIZE_PONG_Y));
		this.addKeyListener(racket);
	
	}

	/**
         * Proceeds to the movement of the ball and updates the screen
	 */
	public void animate() {
		//if(isMySide())
			ball.animate();
		//}
		racket.animate();
		//System.out.println("\t\t" + ball.getPosition());

		/* And update output */
		updateScreen();
	}
	
	public Ball getBall() {
		return ball;
	}


	public Racket getRacket(){
		return racket;
	}
	
	public Racket getRacketOpp(){
		return racket_opp;
	}
	/*
	 * (non-Javadoc) This method is called by the AWT Engine to paint what
	 * appears in the screen. The AWT engine calls the paint method every time
	 * the operative system reports that the canvas has to be painted. When the
	 * window is created for the first time paint is called. The paint method is
	 * also called if we minimize and after we maximize the window and if we
	 * change the size of the window with the mouse.
	 * 
	 * @see javax.swing.JComponent#paint(java.awt.Graphics)
	 */
	@Override
	public void paint(Graphics g) {
		g.drawImage(buffer, 0, 0, this);
	}

	/**
	 * Draw each Pong item based on new positions
	 */
	public void updateScreen() {
		if (buffer == null) {
			/* First time we get called with all windows initialized */
			buffer = createImage(SIZE_PONG_X, SIZE_PONG_Y);
			if (buffer == null)
				throw new RuntimeException("Could not instanciate graphics");
			else
				graphicContext = buffer.getGraphics();
		}
		/* Fill the area with blue */
		if(mirror_player){
			graphicContext.setColor(backgroundColor2);
		}
		else graphicContext.setColor(backgroundColor);
		graphicContext.fillRect(0, 0, SIZE_PONG_X, SIZE_PONG_Y);

		/* Draw items */
		graphicContext.drawImage(ball.getItem(), ball.getPosition().x, ball.getPosition().y, ball.getWidth(), ball.getHeight(), null);
		graphicContext.drawImage(racket.getItem(), racket.getPosition().x, racket.getPosition().y, racket.getWidth(), racket.getHeight(), null);
		graphicContext.drawImage(racket_opp.getItem(), racket_opp.getPosition().x, racket_opp.getPosition().y, racket_opp.getWidth(), racket_opp.getHeight(), null);

		this.repaint();
	}
	
	public boolean isColision(){
		if(ball.getPosition().x >= racket.getPosition().x && ball.getPosition().x <= racket.getPosition().x + racket.getWidth()){
			if (ball.getPosition().y >= racket.getPosition().y && ball.getPosition().y <= racket.getPosition().y+ racket.getHeight()/2) 
				return true;
		}
		return false;
	}
	
	public boolean isMySide(){
		if(getBall().getPosition().x < Pong.SIZE_PONG_X/2)
			return true;
		else return false;
	}
}
