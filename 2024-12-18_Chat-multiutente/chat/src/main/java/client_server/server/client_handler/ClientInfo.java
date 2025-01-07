package client_server.server.client_handler;

import java.net.Socket;
import java.util.ArrayList;

import protocol.Archive;

public class ClientInfo {
	
	private static ArrayList<ClientInfo> clients;
	
	public static synchronized boolean exists(String ip) {
		boolean exists = false;
		for(int i = 0; i < clients.size(); i++) {
			exists = exists || clients.get(i).ip == ip;
		}
		return exists;
	}
	
	Socket userLink;
	Socket updateLink;
	
	String ip;
	String name;
	
	String username;
	String password;
	
	boolean isLogged;
	
	Archive archive;
	
	public ClientInfo(Socket userSocket) {
		userLink = userSocket;
		ip = userLink.getInetAddress().toString();
		name = ip;
		clients.add(this);
	}
	
	public void close() {
		clients.remove(this);
	}
	
	public void log(String username, String password) {
		this.username = username;
		this.password = password;
		isLogged = true;
	}
}

