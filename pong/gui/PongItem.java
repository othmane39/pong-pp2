package pong.gui;

import java.awt.Image;
import java.awt.Point;

public abstract class PongItem {
	public Image item;
	public int width;
	protected int height;
	protected Point position;
	
	public PongItem(){
		position = new Point(0,0);
	}
	
	public abstract void animate();

	
	
	
}
