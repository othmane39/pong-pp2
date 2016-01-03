package pong;

import pong.gui.Window;
import net.Client;
import net.Server;
import net.NetworkSocketInterface;
import net.Protocol;
import pong.gui.Pong;

/**
 * Starting point of the Pong application
 */
public class Main  {
	
	static NetworkSocketInterface network;
	
	public static void main(String[] args) {
		try{
		Pong pong = new Pong();
		
		network = (args.length == 0) ? new Server() : new Client(args[0]);
		if (network instanceof Client) {
			Pong.mirror_player = true;
		}
		Protocol prot = new Protocol(network.getSocket(), pong);
		prot.start();
		Window window = new Window(pong);
		window.displayOnscreen();
		
		}
		finally{
			network.close();
		}
	}
	
	
}
