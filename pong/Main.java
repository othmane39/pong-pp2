package pong;

import pong.gui.Window;
import net.Client;
import net.Server;
import pong.gui.Pong;

/**
 * Starting point of the Pong application
 */
public class Main  {
	
	static Object machine;
	public static void main(String[] args) {
		try{
		Pong pong = new Pong();
		machine = (args.length == 0) ? (Server) new Server() : (Client) new Client(args[0]); 
		Window window = new Window(pong);
		window.displayOnscreen();
		}
		finally{
			
		}
	}
	
	
}
