package pong.gui;

import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;

import javax.swing.ImageIcon;

public class Ball extends PongItem{
	
	private Point speed;
	
	public Ball(){
		super();
		
		ImageIcon icon;
		super.item = Toolkit.getDefaultToolkit().createImage(
				ClassLoader.getSystemResource("image/ball.png"));
		icon = new ImageIcon(item);
		super.width = icon.getIconWidth();
		super.height = icon.getIconHeight();
		speed = new Point(Pong.BALL_SPEED, Pong.BALL_SPEED);
		position = new Point(40, 30);

		
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
		return super.position;
	}
	public Point getSpeed() {
		return speed;
	}



	public void animate() {
		/* Update ball position */
		position.translate(speed.x, speed.y);
		if (position.x < 0)
		{
			position.x = 0;
			speed.x = -speed.x;
		}
		if (position.y < 0)
		{
			position.y = 0;
			speed.y = -speed.y;
		}
		if (position.x > Pong.SIZE_PONG_X - width)
		{
			position.x = Pong.SIZE_PONG_X - width;
			speed.x = -speed.x;
		}
		if (position.y > Pong.SIZE_PONG_Y - height)
		{
			position.y = Pong.SIZE_PONG_Y - height;
			speed.y = -speed.y;
		}
	}

}
