package net;

import java.awt.Point;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.Socket;

import pong.gui.Pong;

public class Protocol extends Thread{
	private Pong pong;
	private Socket socket;
	private DataInputStream input;
	private DataOutputStream output;
	
	public Protocol(Socket socket, Pong pong){
		this.socket = socket;
		this.pong = pong;	
	}
	
public void run(){
	String[] resp = new String[3];
	
	while(true){
			try{
				output = new DataOutputStream(socket.getOutputStream());
				input = new DataInputStream(socket.getInputStream());
				
				sendData();
				Thread.sleep(10);
				resp = receiveData();

				if(resp.length == 3){ //racket only
					pong.getRacketOpp().setPosition(new Point( Integer.parseInt(resp[1]) + Pong.SIZE_PONG_X - pong.getRacket().getWidth() , Integer.parseInt(resp[2])));
				}
				if(resp.length == 6){ //ball and racket
					pong.getRacketOpp().setPosition(new Point( Integer.parseInt(resp[1]) + Pong.SIZE_PONG_X - pong.getRacket().getWidth() , Integer.parseInt(resp[2])));
					if(pong.getBall().getPosition().x < Pong.SIZE_PONG_X/2-10 || pong.getBall().getPosition().x > Pong.SIZE_PONG_X + 10) 
						pong.getBall().setPosition(new Point(Integer.parseInt(resp[4]), Integer.parseInt(resp[5])));
		
				}
		

			}catch (Exception e){
							}
			
		}
	}

private void sendData() throws IOException {
	String rck = new String("1;" + pong.getRacket().getPosition().x + ";" + pong.getRacket().getPosition().y);
	String bal = null;
	if(pong.isMySide()){
		Point p = pong.getBall().getPosition();
		bal = new String("0;" + p.x + ";" + p.y);
		output.writeUTF(rck + ";" + bal);
	}
	else output.writeUTF(rck);
	output.flush();
}

private String[] receiveData() throws IOException{
	String respS = input.readUTF();
	System.out.println(respS);
	String resp[] = respS.split(";");
	System.out.println("This : " + resp[0] + resp[1] + resp[2]);
	
	return resp;	
}


}
