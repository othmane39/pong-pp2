package net;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


public class Server implements NetworkSocketInterface {

	static int port;
	private static Socket socket;
	

	
	public Server() {
		port = 4444;
		try{
			
			ServerSocket socket_t = new ServerSocket(port);
			System.out.println("Listn");
			socket = socket_t.accept();
			System.out.println("Connectd");
		}catch(IOException e){
			e.printStackTrace();
		}
		
	}
	
	public Socket getSocket(){
		return socket;
	}
	
	public void close(){
		try{
			socket.close();
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	
}
