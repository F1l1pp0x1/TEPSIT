package client_server.server.client_handler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import protocol.Archive;
import protocol.Packet;

public class ClientHandler implements Runnable {
	
	ClientInfo client;
	Socket link;

	BufferedReader reader; 
	PrintWriter writer;
	
	public ClientHandler(ClientInfo client) {
		this.client = client;
		link = client.userLink;
		reader = null;
		writer = null;
	}
	
	public void run() {
		try {
			reader = new BufferedReader(new InputStreamReader(link.getInputStream()));
			writer = new PrintWriter(link.getOutputStream(), true);
		}catch(IOException ex) {
			ex.printStackTrace();
			System.out.println(client.name + ": Client died too early, cleaning up the mess!");
			cleanup();
			return;
		}
		
		
		try {
			boolean kill = false;
			int phase = 0;
			String usernameTemp = "";
			Archive archive = client.archive;
			
			while(!kill) {
				Packet input = new Packet(reader.readLine());
				Packet output = input.clone();
				if(input.getCRT()) {
					switch(phase) {
						case 0:
							if(Archive.existsArchive(input.getSource(), true)) {
								output.setACK(false);
							} else {
								output.setACK(true);
								usernameTemp = input.getSource();
								phase++;
							}
							writer.write(output.toJson());
						break;
						case 1:
							if(input.getMessage() != "" && input.getSource() == usernameTemp) {
								output.setACK(true);
								archive.changeUser(input.getSource(), input.getMessage(), true);
								phase = 0;
							} else {
								output.setACK(false);
								phase = 0;
							}
						break;
					}
				} else if(input.getLOG()) {
					if(Archive.existsArchive(input.getSource(), true)) {
						if(archive.changeUser(input.getSource(), input.getMessage(), true)) {
							output.setACK(true);
							archive.changeUser(input.getSource(), input.getMessage(), true);
							client.log(input.getSource(), input.getMessage());
							client.name = input.getSource() + " (" + client.name + ")";
						} else {
							output.setACK(false);
						}
					} else {
						output.setACK(false);
					}
				} else if(input.getMSG() && client.isLogged) {
					
				} else {
					System.out.print(client.name + ": Violation of the protocoll");
					kill = true;
				}
			}
		} catch (IOException e) {
			System.out.println(client.name + ": Disconnected");
			e.printStackTrace();
		}
		cleanup();
	}
	
	public void cleanup() {
		try {
			reader.close();
			writer.close();
			link.close();
		} catch (IOException e) {
		}
	}
}
