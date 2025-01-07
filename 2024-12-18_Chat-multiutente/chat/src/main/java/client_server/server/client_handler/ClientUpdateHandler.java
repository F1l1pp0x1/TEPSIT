package client_server.server.client_handler;

import java.net.Socket;

public class ClientUpdateHandler implements Runnable {

	Socket link;
	String name;
	
	public ClientUpdateHandler(Socket socket) {
		link = socket;
		name = link.getRemoteSocketAddress().toString();
	}
	
	public void run() {

	}

}
