package net;

import java.io.IOException;
import java.net.ServerSocket;

public class Server {

	static int port;
	private static ServerSocket socket;
	
	
	public Server() {
		port = 4444;
		try{
			socket = new ServerSocket(port);
			System.out.println("Listn");
			socket.accept();
			System.out.println("Connectd");
		}catch(IOException e){
			e.printStackTrace();
		}
		
	}
	
	public void close(){
		try{
			socket.close();
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
}
