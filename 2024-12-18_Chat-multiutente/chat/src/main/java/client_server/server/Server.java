package client_server.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import client_server.server.client_handler.ClientInfo;

public class Server {
	
	static final int UserPort = 1234;
	static final int UpdatePort = UserPort + 1;
	
	static ClientInfo clients;

	public static void main(String[] args) {
		try {
			ServerSocket userSocket = new ServerSocket(UserPort);
			UpdateHandler updateHandler = new UpdateHandler(UpdatePort);
			updateHandler.openSocket();
			Thread updateHandlerThread = new Thread(updateHandler);
			updateHandlerThread.start();
			System.out.println("Server avviato");
			
			while(true) {
				Socket client = userSocket.accept();
				System.out.println(client.getRemoteSocketAddress() + ": Nuovo client connesso sulla porta \"user\" (" + UserPort + ")");
				
				//Thread clientHanlder = new Thread(new ClientHandler());
				//clientHanlder.start();
			}
		} catch (IOException e) {
            System.err.println("Errore: " + e.getMessage());
			e.printStackTrace();
		}
	}
	
	static class UpdateHandler implements Runnable{
		int port;
		ServerSocket socket;
		
		public UpdateHandler(int port) {
			this.port = port;
			socket = null;
		}
		
		public void openSocket() throws IOException {
			socket = new ServerSocket(port);
		}
		
		public void run() {
			
		}
	}
	

	static class clientInfo {
		
		public clientInfo() {
			
		}
	}
}