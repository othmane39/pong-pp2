package pong.gui;

import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;

public class Racket extends PongItem implements KeyListener{

	private int speed;
	
	public Racket(){
		super();
		ImageIcon icon;
		super.item = Toolkit.getDefaultToolkit().createImage(
				ClassLoader.getSystemResource("image/racket.png"));
		icon = new ImageIcon(item);
		super.width = icon.getIconWidth();
		super.height = icon.getIconHeight();
		
		
		//speed = 0;
		
		//speed = new Point(Pong.BALL_SPEED, Pong.BALL_SPEED);
	}
	
	
	
	public Image getItem(){
		return item;
	}
	
	public int getWidth(){
		return width;
	}
	
	public int getHeight(){
		return height;
	}
	
	public Point getPosition(){
		return position;
	}		
	
	public int getSpeed() {
		return speed;
	}
	
	
	
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	
	public void setStartsPosition(int i){
		switch(i){
			case 1:
				position = new Point(0 , 0);
				break;
			case 2:
				position = new Point(Pong.SIZE_PONG_X-width, 0);
				break;
			case 3:
				position = new Point(0, Pong.SIZE_PONG_Y);
				break;
			case 4:
				position = new Point(Pong.SIZE_PONG_X-width, Pong.SIZE_PONG_Y);
				break;
			default:
				System.err.println("setStrartPositionError i= " + i);
				break;
		}
	}
	

	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
			case KeyEvent.VK_UP:
			case KeyEvent.VK_KP_UP:
				speed = -Pong.RACKET_SPEED;
				break;
			case KeyEvent.VK_DOWN:
			case KeyEvent.VK_KP_DOWN:
				speed = Pong.RACKET_SPEED;
				break;
			default:
				System.out.println("got press "+e);
		}
	}
	public void keyReleased(KeyEvent e) {
		switch (e.getKeyCode()) {
			case KeyEvent.VK_UP:
			case KeyEvent.VK_KP_UP:
				speed = 0;
				break;
			case KeyEvent.VK_DOWN:
			case KeyEvent.VK_KP_DOWN:
				speed = 0;
				break;
			default:
				System.out.println("got release "+e);
		}
	}
	public void keyTyped(KeyEvent e) { }



	public void animate(){
		position.y += speed;
		if (position.y < 0)
			position.y = 0;
		if (position.y > Pong.SIZE_PONG_Y - height/2)
			position.y = Pong.SIZE_PONG_Y - height/2;

	}

}
