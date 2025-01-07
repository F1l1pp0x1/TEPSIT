package protocol;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;

import org.json.JSONArray;
import org.json.JSONObject;

public class Archive {
	
	private static final String archivePath = "";
	
	private JSONObject data;
	private String path;
	
	public Archive() {
		data = new JSONObject();
		path = "";
	}
	
	public static boolean existsArchive(String username, boolean isServer) {
		File file = new File(archivePath + (isServer ? "server/" : "client/") + username + "Data.json");
		return file.exists();
	}
	
	public synchronized boolean isUsed() {
		return path != "";
	}
	
	public synchronized boolean changeUser(String username, String password, boolean isServer) {
		path = archivePath + (isServer ? "server/" : "client/") + username + "Data.json";
		if(fileExists()) {
			try {
				File file = new File(path);
				file.createNewFile();
				data = new JSONObject();
				data.put("username", username);
				data.put("password", password);
				return true;
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			readAllFile();
			if(data.getString("username") == username && data.getString("password") == password) {
				return true;
			}
		}
		path = "";
		data.clear();
		data = new JSONObject();
		return false;
	}
	
	public synchronized String getUsername() {
		if(path == "") throw new IllegalStateException("no one is logged in this archive");
		return data.getString("username");
	}
	
	public synchronized Message[] getChat(String chatName) {
		if(path == "") throw new IllegalStateException("no one is logged in this archive");
		if(!exists(chatName)) {
			return null;
		}
		JSONArray chatData = data.getJSONObject("chat").getJSONArray(chatName);
		Message[] chat = new Message[chatData.length()];
		for(int i = 0; i < chatData.length(); i++) {
			chat[i] = new Message(chatData.getJSONObject(i));
		}
		return chat;
	}
	
	public synchronized Message[] getLastMessages(String chatName, int n) {
		if(path == "") throw new IllegalStateException("no one is logged in this archive");
		if(!exists(chatName)) {
			return null;
		}
		JSONArray chatData = data.getJSONObject("chat").getJSONArray(chatName);
		Message[] chat = new Message[n];
		for(int i = chatData.length() - n; i < chatData.length(); i++) {
			chat[i-chatData.length()+n] = new Message(chatData.getJSONObject(i));
		}
		return chat;
	}
	
	public synchronized Message getLastMessage(String chatName) {
		if(path == "") throw new IllegalStateException("no one is logged in this archive");
		if(!exists(chatName)) {
			return null;
		}
		JSONArray chatData = data.getJSONObject("chat").getJSONArray(chatName);
		return new Message(chatData.getJSONObject(chatData.length()-1));
	}
	
	public synchronized void storeMessage(Message message) {
		if(path == "") throw new IllegalStateException("no one is logged in this archive");
		String chatName;
		JSONArray chat;
		if(message.getSource() == data.getString("username")) {
			chatName = message.getDestination();
		} else {
			chatName = message.getSource();
		}
		
		if(exists(chatName)) {
			chat = data.getJSONObject("chat").getJSONArray(chatName);
			data.getJSONObject("chat").remove(chatName);
		} else {
			chat = new JSONArray();
		}
		chat.put(message.toJSONObject());
		data.getJSONObject("chat").put(chatName, chat);
		writeAllFile();
	}
	
	public synchronized void newChat(String chatName) {
		if(path == "") throw new IllegalStateException("no one is logged in this archive");
		if(exists(chatName)) {
			return;
		}
		JSONObject chatList = data.getJSONObject("chat");
		data.remove("chat");
		chatList.put(chatName, new JSONArray());
		data.put("chat", chatList);
	}
 	
	public synchronized String[] getChatList() {
		if(path == "") throw new IllegalStateException("no one is logged in this archive");
		Iterator<String> it = data.getJSONObject("chat").keys();
		String[] chatList = new String[data.getJSONObject("chat").length()];
		for(int i = 0; i < chatList.length; i++) {
			chatList[i] = it.next();
		}
		return chatList;
	}
	
	public synchronized boolean exists(String chatName) {
		if(path == "") throw new IllegalStateException("no one is logged in this archive");
		return data.getJSONObject("chat").has(chatName);
	}
	
	public synchronized Message removeFirstMessage(String chatName) {
		if(path == "") throw new IllegalStateException("no one is logged in this archive");
		if(!exists(chatName)) {
			return null;
		}
		Message message = new Message(data.getJSONObject("chat").getJSONArray(chatName).getJSONObject(0));
		data.getJSONObject("chat").getJSONArray(chatName).remove(0);
		return message;		
	}
	
	public synchronized Message[] clearChat(String chatName) {
		if(path == "") throw new IllegalStateException("no one is logged in this archive");
		if(!exists(chatName)) {
			return null;
		}
		Message[] chat = getChat(chatName);
		data.getJSONObject("chat").getJSONArray(chatName).clear();
		return chat;
	}
	
	public synchronized Message[] removeChat(String chatName) {
		if(path == "") throw new IllegalStateException("no one is logged in this archive");
		if(!exists(chatName)) {
			return null;
		}
		Message[] chat = getChat(chatName);
		data.getJSONObject("chat").remove(chatName);
		return chat;
	}
	
	synchronized boolean fileExists() {
		if(path == "") throw new IllegalStateException("no one is logged in this archive");
		File file = new File(path);
		return file.exists();
	}
	
	synchronized void writeAllFile() {
		if(path == "") throw new IllegalStateException("no one is logged in this archive");
		try {
			FileWriter writer = new FileWriter(path);
			writer.write(data.toString());
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	synchronized void readAllFile() {
		if(path == "") throw new IllegalStateException("no one is logged in this archive");
		BufferedReader reader;
		String line = "";
		String fileData = "";
		
		try {
			reader = new BufferedReader(new FileReader(path));
			do {
				line = reader.readLine();
				if(line != null) {
					fileData += line;
				}
			} while(line != null);
			reader.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		data.clear();
		data = new JSONObject(fileData);
	}
}
