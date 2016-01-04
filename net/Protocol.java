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
				/*
				System.out.println("Ball x:" + pong.getBall().getPosition().x + " " + pong.getBall().getPosition().y);
				output.writeUTF(pong.getBall().getPosition().x + ";" + pong.getBall().getPosition().y);
				output.flush();
				Thread.sleep(10);
				String rs = input.readUTF();
				System.out.println(rs);
				*/
				//Thread.sleep(100);
				//
				//System.out.println("ISMIIIINE");
				//boolean side_changed_now;
				if(pong.isMySide()){
					//sendBallPos();
					//if(side_changed_now == false)
						//side_changed_now = true;
					//System.out.println("ISMIIIINE");
				}
				//sendRacketPos();
				sendData();
				
				Thread.sleep(10);
				resp = receiveData();
				/*if(resp.length == 3){
				System.out.println("THIS FINAL " + resp.length + " " + resp[0] + resp[1] + resp[2]);
				}
				else System.out.println("THIS FINAL " + resp.length + " " + resp[0] + resp[1] + resp[2]+ resp[3] + resp[4] + resp[5]);*/
				
				
				if(resp.length == 3){
					pong.getRacketOpp().setPosition(new Point( Integer.parseInt(resp[1]) + Pong.SIZE_PONG_X - pong.getRacket().getWidth() , Integer.parseInt(resp[2])));
				}
				if(resp.length == 6){
					pong.getRacketOpp().setPosition(new Point( Integer.parseInt(resp[1]) + Pong.SIZE_PONG_X - pong.getRacket().getWidth() , Integer.parseInt(resp[2])));
					//if(pong.getBall().getPosition().x - Integer.parseInt(resp[4]) < 0 && pong.getBall().getSpeed().x > 0){
						//pong.getBall().setPosition(new Point(Integer.parseInt(resp[4]), Integer.parseInt(resp[5])));
						//System.out.println("ISMIIIINE");
					//}else if (pong.getBall().getPosition().x - Integer.parseInt(resp[4]) > 0 && pong.getBall().getSpeed().x <0){
						//pong.getBall().setPosition(new Point(Integer.parseInt(resp[4]), Integer.parseInt(resp[5])));
						//System.out.println("ISMIIIINE");
					//if(pong.getBall().getSpeed().x > 0 && pong.getBall().getPosition().x > Integer.parseInt(resp[4])){
					if(pong.getBall().getPosition().x < Pong.SIZE_PONG_X/2-10 || pong.getBall().getPosition().x > Pong.SIZE_PONG_X + 10) 
						pong.getBall().setPosition(new Point(Integer.parseInt(resp[4]), Integer.parseInt(resp[5])));
						//System.out.println("ISMIIIINE");
					//}
					//if(pong.getBall().getSpeed().x < 0 && pong.getBall().getPosition().x > Integer.parseInt(resp[4])){
						
						//if (pong.getBall().getPosition().x - Integer.parseInt(resp[4]) > 0 && pong.getBall().getSpeed().x <0){
					
				}
				
				
				
				
				
				//pong.getRacketOpp().setPosition(receiveOpponentRacketPos());
					//break;
				//case 2:
					//pong.getBall().setPosition(receiveBallPos());
					//break;
				//}
				

			}catch (Exception e){
				//e.printStackTrace();
			}
			
		}
	}

private void sendRacketPos() throws IOException{
		
		output.writeUTF("1;" + pong.getRacket().getPosition().x + ";" + pong.getRacket().getPosition().y);
		//output.flush();	
}

private void sendBallPos() throws IOException{
	Point p = pong.getBall().getPosition();
	output.writeUTF("0;" + p.x + ";" + p.y);
	System.out.println("0;" + p.x + ";" + p.y);
	output.flush();
}

private Point receiveOpponentRacketPos() throws IOException{ //add int i parameters to get all player pos (if 4)
	String respS = input.readUTF();
	String[] resp = respS.split(";");
	System.out.println("OppRackPos " + respS);
	return new Point(Integer.parseInt(resp[1])+Pong.SIZE_PONG_X-pong.getRacket().getWidth(), Integer.parseInt(resp[2]));
	
}

private Point receiveBallPos() throws IOException{
	String respS = input.readUTF();
	String[] resp = respS.split(";");
	System.out.println("OppBallPos " + respS);
	return new Point(Integer.parseInt(resp[1]), Integer.parseInt(resp[2]));
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
	
	//p = new Point(Integer.parseInt(resp[1]), Integer.parseInt(resp[2]));
	return resp;
	
	
}


}
