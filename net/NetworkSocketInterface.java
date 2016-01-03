package net;

import java.net.Socket;

public interface NetworkSocketInterface {
	public void close();
	public Socket getSocket();

}
