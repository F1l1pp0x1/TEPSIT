package client_server.client;

import java.net.Socket;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.time.*;

import protocol.Archive;
import protocol.Message;
import protocol.Packet;

public class AggiornatoreArchivio implements Runnable{
	
	/**Indica che il thread aggiornatore dell'archivio è in funzione*/
	private boolean inFunzione = true;
	
	/**Indirizzo IP del server*/
	private static String serverAddress;
	/**Porta del server*/
	private static int serverPort; 		
	/**Connessione con server non ancora instaurata*/
	private static Socket link = null;				
	
	/**writer per scrivere i dati verso il server*/
	private static PrintWriter writer = null;
	/** reader per leggere i dati ricevuti dal server*/
    private static BufferedReader reader = null;		
    
    /**Username del client di cui il thread aggiorna l'archivio*/
    private static String usernameClient = null;
    /**Password del client di cui il thread aggiorna l'archivio*/
    private static String passwordClient = null;

    /**Archivio locale del client di cui il thread aggiorna l'archivio*/
    private static Archive archive = null;
	
	/**
	 * Costruttore di AggiornatoreArchivio
	 * @param archive archivio del client che il thread aggiorna
	 */
	public AggiornatoreArchivio (String usernameClient, String passwordClient, Archive archive, String serverAddress, int serverPort) {
		this.serverAddress = serverAddress;
		this.serverPort = serverPort+1;
		this.usernameClient = usernameClient;
		this.passwordClient = passwordClient;
		this.archive = archive;
	}
	
	public void run() {
		/**Pacchetto che il thread riceve dal server*/
		Packet pacchetto = new Packet();
		/**Messaggio che il threa aggiunge alla coda della chat specificata*/
		Message messaggioNuovo = new Message();
		/**Nome della chat da cui il thread ha ricevuto il messaggio*/
		String nomeChat = null;
		
		
		try {
			/**
			 * Crea la connessione
			 */
			link = new Socket(serverAddress, serverPort);
			
			/**
			 * Crea il writer
			 */
			writer = new PrintWriter ( link.getOutputStream() ) ;
	        /**
	         * Crea il reader
	         */
	        reader = new BufferedReader(new InputStreamReader( link.getInputStream() ));
	        
	        /**
	         * Si autentica sul server destinatario
	         * 
	         * Il server riconosce che il login è da parte del thread di AggiornatoreArchivio e non dal
	         * thread main perchè comunica su una porta diversa del server
	         */
	        pacchetto.setAll(usernameClient, false, true, false, false, null, LocalDateTime.now(), null);
		
	        /**Invia il pacchetto come stringa JSON al server*/
			writer.println(pacchetto.toJson());
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		/**
		 * Il thread legge il pacchetto ricevuto dal server che contiene l'ultimo messaggio con cui aggiorna una chat del client
		 */
		while(inFunzione == true) {
			
			try {
				/**
				 * Il thread riceve il pacchetto inviato dal server
				 */
				pacchetto.readJson(reader.readLine());
				
				/**
				 * Se è stato ricevuto un messaggio
				 */
				if( pacchetto.getMessage() !=null ) {
					
					/**
					 * Converte il pacchetto nel messaggio da aggiungere alla chat
					 */
					messaggioNuovo.readPacket(pacchetto);
					
					/**
					 * Individuare la chat a cui corrisponde il messaggio
					 */
					if( messaggioNuovo.getSource() == usernameClient ) {
						/**
						 * Il destinatario del messaggio permette di individuare in quale chat
						 * sta comunicando il client
						 */
						nomeChat = messaggioNuovo.getDestination();
					}
					else {
						/**
						 * Il mittente del messaggio permette di individuare in quale chat
						 * sta comunicando il client
						 */
						nomeChat = messaggioNuovo.getSource();
					}
					
					/**
					 * Aggiunge il nuovo messaggio alla chat corrispondente
					 */
					archive.storeMessage(messaggioNuovo);

				}
				
				/**
				 * Aggiornare la vecchia lista delle chat con quella aggiornata ricevuta dal server
				 */
				
			}catch(Exception e) {
				e.printStackTrace();
			}
			
		}
		
	}

	/**
	 * Getter di inFunzione
	 */
	public boolean getInFunzione () {
		return inFunzione;
	}
	/**
	 * Setter di inFunzione
	 */
	public void setInFunzione (boolean valore) {
		this.inFunzione = valore;
	}
}
