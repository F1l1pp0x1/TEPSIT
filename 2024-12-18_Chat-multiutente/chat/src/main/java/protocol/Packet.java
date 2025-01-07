package protocol;

import java.time.LocalDateTime;
import org.json.JSONObject;

public class Packet {
	
	/*
	 * Attributes
	 */
	
	private String source;
	private String destination;
	private String message;
	private boolean ack;
	private boolean log;
	private boolean crt;
	private boolean msg;
	private LocalDateTime time;
	
	public Packet() {
		source = "";
		destination = "";
		message = "";
		ack = false;
		log = false;
		crt = false;
		msg = false;
		time = LocalDateTime.now();
	}
	
	public Packet(String json) {
		readJson(json);
	}
	
	/*
	 * Conversione Json
	 */
	
	public String toJson() {
		JSONObject packet = new JSONObject();
		
		packet.put("source", source);
		packet.put("destination", destination);
		packet.put("message", message);
		
		packet.put("ACK", ack);
		packet.put("LOG", log);
		packet.put("CRT", crt);
		packet.put("MSG", msg);
		
		packet.put("time", time.toString());
		
		return packet.toString();
	}
	
	public void readJson(String json) {
		JSONObject packet = new JSONObject(json);
		
		source = packet.getString("source");
		destination = packet.getString("destination");
		message = packet.getString("message");
		
		ack = packet.getBoolean("ACK");
		log = packet.getBoolean("LOG");
		crt = packet.getBoolean("CRT");
		msg = packet.getBoolean("MSG");
		
		time = LocalDateTime.parse(packet.getString("time"));
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
	
	public String getMessage() {
		return message;
	}
	
	public boolean getACK() {
		return ack;
	}
	
	public boolean getLOG() {
		return log;
	}
	
	public boolean getCRT() {
		return crt;
	}
	
	public boolean getMSG() {
		return msg;
	}
	
	public LocalDateTime getTime() {
		return time;
	}
	
	/*
	 * Setter
	 */
	public void setAll(String source, boolean ack, boolean log, boolean crt, boolean msg, String destination, LocalDateTime time, String message) {
		setSource(source);
		setACK(ack);
		setLOG(log);
		setCRT(crt);
		setMSG(msg);
		setDestination(destination);
		setTime(time);
		setMessage(message);
	}
	
	public void setSource(String source) {
		this.source = source != null ? source : "";
	}
	
	public void setACK(boolean ack) {
		this.ack = ack;
	}
	
	public void setLOG(boolean log) {
		this.log = log;
	}
	
	public void setCRT(boolean crt) {
		this.crt = crt;
	}
	
	public void setMSG(boolean msg ) {
		this.msg = msg;
	}
	
	public void setDestination(String destination) {
		this.destination = destination != null ? destination : "";
	}
	
	public void setTime(LocalDateTime time) {
		this.time = time != null ? time : LocalDateTime.now();
	}
	
	public void setMessage(String message) {
		this.message = message != null ? message : "";
	}
	
	/*
	 * 
	 */
	public Packet clone() {
		Packet clone = new Packet();
		clone.setAll(source, ack, log, crt, msg, destination, time, message);
		return clone;
	}
}