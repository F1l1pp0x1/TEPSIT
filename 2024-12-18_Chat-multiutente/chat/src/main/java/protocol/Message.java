package protocol;

import java.time.LocalDateTime;

import org.json.JSONObject;

public class Message {
	private String source;
	private String destination;
	private String text;
	private LocalDateTime time;
	
	/*
	 * Constructor
	 */
	
	public Message() {
		this("", "", "", LocalDateTime.now());
	}
	
	public Message(String source, String destination, String text) {
		this(source, destination, text, LocalDateTime.now());
	}
	
	public Message(String source, String destination, String text, LocalDateTime time) {
		this.source = source;
		this.destination = destination;
		this.text = text;
		this.time = time;
	}
	
	public Message(JSONObject jsonObject) {
		readJSONObject(jsonObject);
	}
	
	public Message(Packet packet) {
		readPacket(packet);
	}
	
	/*
	 * Conversione JSONObject
	 */
	
	public JSONObject toJSONObject() {
		JSONObject json = new JSONObject();
		json.put("source", source);
		json.put("destination", destination);
		json.put("text", text);
		json.put("time", time.toString());
		return json;
	}
	
	public void readJSONObject(JSONObject json) {
		source = json.getString("source");
		destination = json.getString("destination");
		text = json.getString("text");
		time = LocalDateTime.parse(json.getString("time"));
	}
	
	/*
	 * Conversione Packet
	 */
	
	public Packet toPacket() {
		Packet packet = new Packet();
		packet.setSource(source);
		packet.setDestination(destination);
		packet.setMessage(text);
		packet.setTime(time);
		packet.setMSG(true);
		return packet;
	}
	
	public void readPacket(Packet packet) {
		source = packet.getSource();
		destination = packet.getDestination();
		text = packet.getMessage();
		time = packet.getTime();
	}
	
	/*
	 * toString
	 */
	
	public String toString() {
		return source + " (" + time.getDayOfMonth() + "/" + time.getMonthValue() + "/" + time.getYear() + " " + time.getHour() + ":" + time.getMinute() + "): " + text;
	}
	
	/*
	 * Getter
	 */
	
	public String getSource() {
		return source;
	}
	
	public String getDestination() {
		return destination;
	}
	
	public String getText() {
		return text;
	}
	
	public LocalDateTime getTime() {
		return time;
	}
	
	/*
	 * Setter
	 */
	
	public void setSource(String source) {
		this.source = source;
	}
	
	public void setDestination(String destination) {
		this.destination = destination;
	}
	
	public void setText(String text) {
		this.text = text;
	}
	
	public void setTime(LocalDateTime time) {
		this.time = time;
	}
}
