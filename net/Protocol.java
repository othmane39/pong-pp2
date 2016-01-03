package net;

import java.awt.Point;
import java.io.DataInputStream;
import java.io.DataOutputStream;
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
	while(true){
			try{
				output = new DataOutputStream(socket.getOutputStream());
				input = new DataInputStream(socket.getInputStream());
				/*
				System.out.println("Ball x:" + pong.getBall().getPosition().x + " " + pong.getBall().getPosition().y);
				output.writeUTF(pong.getBall().getPosition().x + ";" + pong.getBall().getPosition().y);
				output.flush();
				Thread.sleep(10);
				String rs = input.readUTF();
				System.out.println(rs);
				*/
				sendRacketPos();
				
				/*if(isMySide()){
					sendBallPos();
				}*/
				
				
				pong.getRacketOpp().setPosition(receiveOpponentRacketPos());
					//break;
				//case 2:
					//pong.getBall().setPosition(receiveBallPos());
					//break;
				//}

			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}

private void sendRacketPos() throws IOException{
		
		output.writeUTF(pong.getRacket().getPosition().x + ";" + pong.getRacket().getPosition().y);
		output.flush();	
}

private void sendBallPos() throws IOException{
	
	output.writeUTF(pong.getBall().getPosition().x + ";" + pong.getBall().getPosition().y);
	output.flush();
}

private Point receiveOpponentRacketPos() throws IOException{ //add int i parameters to get all player pos (if 4)
	String respS = input.readUTF();
	String[] resp = respS.split(";");
	//System.out.println("OppRackPos " + respS);
	return new Point(Integer.parseInt(resp[0])+Pong.SIZE_PONG_X-pong.getRacket().getWidth(), Integer.parseInt(resp[1]));
	
}

private Point receiveBallPos() throws IOException{
	String respS = input.readUTF();
	String[] resp = respS.split(";");
	//System.out.println("OppBallPos " + respS);
	return new Point(Integer.parseInt(resp[0]), Integer.parseInt(resp[1]));
}

private boolean isMySide(){
	if(pong.getBall().getPosition().x < Pong.SIZE_PONG_X)
		return true;
	return false;
}


}
