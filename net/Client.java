package net;

import java.io.IOException;
import java.net.Socket;

public class Client {
	private Socket socket;
	private int port;
	
	public Client(String ip){
		try{
		port = 4444;
		System.out.println("Connecting to " + ip);
		socket = new Socket(ip, port);
		System.out.println("Connected");
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
