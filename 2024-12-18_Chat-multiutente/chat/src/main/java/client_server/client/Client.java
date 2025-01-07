package client_server.client;

import java.net.Socket;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.Scanner;
import java.time.*;

import protocol.Archive;
import protocol.Packet;

public class Client {
	/**Indirizzo IP del server*/
	private static final String serverAddress = "127.0.0.1";
	/**Porta del server*/
	private static final int serverPort = 1234; 		
	/**Connessione con server non ancora instaurata*/
	private static Socket link = null;				
	
	/**Utilizzare un PrintWriter per inviare i dati al server*/
	private static PrintWriter writer=null;				
	/**Utilizzare un BufferReader per leggere i dati ricevuti dal server*/
    private static BufferedReader reader = null;		
    
    /**Username client*/
    private static String usernameClient = null;
    /**Password client*/
    private static String passwordClient = null;
    /**Massimo numero di tentativi per accedere all'account*/
    private final static int MAXTENTATIVI = 10;
    /**Archivio locale del client*/
    private static Archive archive = null;
    
    /**Tempo in ms che un utente, dopo aver esaurito i tentativi di accesso, deve aspettare prima di poter tentare nuovamente l'accesso*/
    private static final long waitTime =  (1*60)*1000;
    
    /**
	 * Metodo Main della classe Host
	 * @param args
	 */
	public static void main(String[] args) {
		/**Permette l'input dall'utente*/
		Scanner scanner = new Scanner (System.in);
		
		try {
			
			/**
			 * Crea una connessione con il server
			 */
			creaConnessione();
			
			/**Se la connessione al server è fallita*/
			if (link == null ) {
				System.out.println("Impossibile collegarsi al server");
				scanner.close();
				return;
			}
			/**Se la connessione al server è riuscita*/
			else {
				System.out.println("Connessione con il server eseguita");
				
				/**
				 * Creare il writer e reader della connessione con il server
				 */
				creaWriterReader();
					
				/**
				 * Accedere all'account del client o crearne uno
				 */
				accediCreaAccount(scanner);
	            		
				/**
				 * Crea un thread che aggiorna l'archivio del client
				 */
				AggiornatoreArchivio aggiornatoreArchivio = new AggiornatoreArchivio(usernameClient, passwordClient, archive, serverAddress, serverPort);
				Thread threadArchivio = new Thread ( aggiornatoreArchivio );
				threadArchivio.start();
				
				/**
				 * Permette di accedere al menu con le opzioni che può fare l'utente
				 */
				menuOpzioni(scanner);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	
	
	/**
	 * metodo che crea una connessione con il server
	 */
	private static void creaConnessione () {
		try {
			/**
			 *  Crea con successo una connessione al server
			 */
			link = new Socket(serverAddress, serverPort);
		}catch (Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * Metodo che permette di accedere o creare l'account del client
	 */
	private static void accediCreaAccount (Scanner scanner) {
		
		int scelta;
		
		/**
		 * Ripete la schermata di home se l'utente ha esaurito i tentativi di accesso
		 */
		do{
			/**
	         * Menu scelta accesso o creazione account client
	         */
	        do {
	        	puliziaSchermo();
	        	System.out.println(
	    			"\n----------------------------------------"
	    			+ "\nCHAT MULTIUTENTE      -Schermata Home"
	    			+"\n----------------------------------------"
	    			+ "\n"
	    			+ "\n(0)Termina il programma"
	    			+ "\n(1)Accedi"
	    			+ "\n(2)Non hai un account? Creane uno"
	        	);
	        	scelta= scanner.nextInt();
	        			
	        }while(scelta<0 || scelta >2);
	        
	        /**
	         * Il programma viene terminato
	         */
	        if( scelta == 0 ) {
	        	cleanup();
	        }
	        
	        /**
	         * Accesso all'acount
	         */
	        if( scelta == 1 ) {
	        	
	        	if ( accediAccount(scanner) == false) {
	        		/**Accesso all'acccount fallito*/
	        		
	        		/**L'utente torna alla schermata home*/
	        		scelta = -1;
	        		
	        		/**Aspettare il tempo prestabilito prima di tentare nuovamente l'accesso*/
	        		try{
	        			Thread.sleep(waitTime);
	        		}catch(Exception e) {
	        			e.printStackTrace();
	        		}
	        		
	        	}
	        	
	        }
	        
	        /**
	         * Crea l'account
	         */
	        if( scelta == 2 ) {
	        	creaAccount(scanner);
	        }
		}while(scelta == -1);
	}
	
	/**
	 * Metodo che permette all'utente di scegliere che azione eseguire nel programma
	 */
	private static void menuOpzioni (Scanner scanner) {
		int scelta;
		
		do {
			/**
			 * Stampare le opzioni tra cui può scegliere l'utente
			 */
			do {
				puliziaSchermo();
				System.out.println(
		    			"\n----------------------------------------"
		    			+ "\nCHAT MULTIUTENTE"
		    			+"\n----------------------------------------"
		    			+ "\n"
		    			+ "\n(0)Termina il programma"
		    			+ "\n(1)Cerca destinatario"
		    			+ "\n(2)Mostra chat già esistenti");
				
				scelta= scanner.nextInt();
			}while(scelta<0 || scelta>2);
			
			/**
			 * L'utente vuole terminare il programma
			 */
			if(scelta == 0) {
				cleanup();
				return;
			}
			
			/**
			 * L'utente vuole cercare il destinatario digitando il suo username
			 */
			if( scelta == 1) {
				/**Destinatario che l'utente sta cercando*/
				String destinatario= scanner.nextLine();
				
				/**Il destinatario è già noto al client*/
				if( archive.exists(destinatario) == true) {
					accediChat(destinatario, scanner);
				}
				/**Il destinatario è nuovo al client*/
				else {
					nuovaChat(destinatario, scanner);
				}
			}
			/**
			 * L'utente vuole accedere ad una chat di  quelle che ha già digitando l'indice 
			 */
			else {
				
				/**
				 * Stampa il nome delle persone con cui abbiamo iniziano una chat
				 */
 				for (int i=0; i< archive.getChatList().length; i++) {
 					System.out.print("\n("+(i+2)+")Continua con "+ archive.getChatList()[i]);
				}
				
				/**
				 * L'utente sceglie con chi comunicare
				 */
				do {
					System.out.println("Con chi vuoi comunicare? (digita il suo indice)");
					scelta = scanner.nextInt();
				}while(scelta<0 || scelta> archive.getChatList().length);
				
				/**
				 * L'utente accede a quella determinata chat
				 */
				accediChat(archive.getChatList()[scelta], scanner);
			}
		}while(scelta!=0);
	}
	
	/**
	 * Metodo che inizializza il writer e reader del client. Permette di inviare flussi di
	 * pacchetti testuali in output e input
	 */
	private static void creaWriterReader () {
		try {
			/**
	         * Utilizzare un PrintWriter per inviare i dati con protocollo testuale invece di binario
	         */
	        writer = new PrintWriter( link.getOutputStream() , true );
		
	        /**
	         * Utilizzare un BufferReader per leggere i dati con protocollo testuale invece di binario
	         */
	        reader = new BufferedReader(new InputStreamReader( link.getInputStream() ));
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Metodo che crea l'account dell'host sul server
	 */
	private static void creaAccount (Scanner scanner) {
		puliziaSchermo();
		
		String username = null;
		String password = null;
		Packet pacchetto = new Packet();
		
		try {
			System.out.println("CREAZIONE ACCOUNT");
			
			/**
			 * Creazione username
			 */
			do {
				System.out.println("Hai già un account? Accedi digitando 'accedi' come Username ");
				
				System.out.print("\nUsername: ");
				username = scanner.next();
				
				switch (username) {
					case "accedi":{
						accediAccount(scanner);
					break;
					}
					case "crea":{
						System.out.println("Username non disponibile");
					break;
					}
					default:{
						/** 
						* Invia un pacchetto con lo username scelto finchè il server non
						* conferma che lo username è stato accettato
						*/
						pacchetto.setAll(username, false, false, true, false, null, LocalDateTime.now(), null);
						
						/**Invia il pacchetto come stringa JSON al server*/
						writer.println(pacchetto.toJson());
						/**Attende la risposta dal server come stringa JSON*/
						pacchetto.readJson(reader.readLine());
						
						if (pacchetto.getACK() == false) {
							System.out.println("Username già utilizzato");
						}
					}
				}
				
			}while(pacchetto.getACK() == false && username != "accedi");
			
			
			/**
			 * Creazione password
			 * 
			 * Invia un pacchetto con la password scelto finchè il server non
			 * conferma che la password è accettabile
			 */
			do {
				System.out.print("\nPassword: ");
				password = scanner.next();
				pacchetto.setAll(username, false, false, true, false, null, LocalDateTime.now(), password);
				
				/**Invia il pacchetto come stringa JSON al server*/
				writer.println(pacchetto.toJson());
				/**Attende la risposta dal server come stringa JSON*/
				pacchetto.readJson(reader.readLine());
				
				if (pacchetto.getACK() == false) {
					System.out.println("Password non accettabile");
				}
			}while(pacchetto.getACK() == false);
			
			/**Accedere alla pagina di login all'account*/
			accediAccount (scanner);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Metodo che permette al client di accedere al proprio account
	 */
	private static boolean accediAccount (Scanner scanner) {
		puliziaSchermo();
		
		/**Username e password*/
		String username = null;
		String password = null;
		/**Pacchetto con le credenziali da inviare al server*/
		Packet pacchetto = new Packet();
		/**Numero di tentativi di accesso per il client*/
		int tentativi = MAXTENTATIVI;
		/**Indica se il tentativo di inserimento username/password è valido (credenziali corrette) o non*/
		boolean valido = false;
		
		try {
			System.out.println("ACCESSO ALL'ACCOUNT ");
			System.out.println("Non hai ancora un account? Creane uno digitando 'crea' come username");
			/**
			 * accesso
			 * 
			 * Invia un pacchetto con le credenziali dell'utente finchè il server non
			 * conferma che le credenziali siano corrette o finchè non si raggiunge il limite di tentativi
			 */
			
			do {
				System.out.print("\nUsername: ");
				username = scanner.next();
				

				switch (username) {
					case "accedi":{
						System.out.println("Username non disponibile");
					break;
					}
					
					case "crea":{
						creaAccount(scanner);
						puliziaSchermo();
						System.out.println("ACCESSO ALL'ACCOUNT ");
						System.out.println("Non hai ancora un account? Creane uno digitando 'crea' come username");	
					break;
					}
					
					default:{
						System.out.print("\nPassword: ");
						password = scanner.next();
						pacchetto.setAll(username, false, true, false, false, null, LocalDateTime.now(), password);
						
						/**Invia il pacchetto come stringa JSON al server*/
						writer.println(pacchetto.toJson());
						/**Attende la risposta dal server come stringa JSON*/
						pacchetto.readJson(reader.readLine());
						
						if(pacchetto.getACK() == false) {
							System.out.println("Credenziali errate");
							tentativi--;
						}
						else {
							valido = true;
						}
					}
				}
			}while(valido == false && tentativi>0);
			
			/**
			 * L'utente ha errato troppe volte le credenziali
			 */
			if(tentativi == 0)
			{
				System.out.println("Hai esaurito il numero di tentativi"
						+ "\nSiamo spiacenti ma dovrai aspettare "+msToHour(waitTime)+" ore prima di tentare nuovamente l'accesso");
				
				/**Restituisce che l'accesso all'account non è stato validato*/
				return valido;
			}
			else {
				usernameClient = username;
				passwordClient = password;
			
				/**Restituisce che l'accesso all'account è stato validato*/
				return valido;
			}
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	
	}
	
	
	/**
	 * Metodo per accedere alla chat con un destinatario specifico
	 * @param destinatario
	 * @param scanner
	 */
	private static void accediChat(String destinatario,Scanner scanner) {
		Packet pacchetto = new Packet();
		String messaggio = null;
		do {
			puliziaSchermo();
			System.out.println("CHAT CON+" + destinatario);
			
			/**
			 * Stampa la chat che è avvenuta finora con il destinatario
			 */
			archive.getChat(destinatario);
			
			/**
			 * Preparare il pacchetto da inviare
			 */
			pacchetto.setAll(usernameClient, false, false, false, true, destinatario, LocalDateTime.now(), null);
			
			/**
			 * Inserire il messaggio da inviare
			 */
			System.out.print("Messaggio (Inserisci -Exit per uscire dalla chat ): ");
			messaggio = scanner.nextLine();
			
			if(messaggio !="-Exit") {
				/**
				 * Inviare il pacchetto
				 */
				pacchetto.setMessage(messaggio);
				writer.print(pacchetto.toJson());
			}
				
		}while(pacchetto.getMessage() != "-Exit");
	}

	/**
	 * Metodo per creare una nuova chat con un destinatario prima sconosciuto all'utente
	 * @param destinatario
	 * @param scanner
	 * @return indica se il destinatario esiste (true) o no (false)
	 */
	private static boolean nuovaChat (String destinatario, Scanner scanner) {
		boolean returnValue = false;
		
		Packet pacchetto = new Packet();
		
		try{
			pacchetto.setAll(usernameClient, false, false, false, true, destinatario, LocalDateTime.now(), null);
		
			
			writer.print(pacchetto.toJson());
			
			pacchetto.readJson(reader.readLine());
			
			/**Il destinatario cercato esiste*/
			if(pacchetto.getACK()==true) {
				
				returnValue = true;
			}
			
			return returnValue;
			
		}catch(Exception e) {
			
			e.printStackTrace();
			return false;
		
		}
	}
	
	/**
	 * Metodo che chiude connessione con il server
	 */
	private static void cleanup() {
		try {
			writer.close();							/**Chiudere il flusso in output di dati*/
			reader.close();							/**Chiudere il flusso in input di dati*/
			link.close();							/**Chiudere la connessione*/
		} catch (IOException e) {}
	}
	
	private static void cleanup(AggiornatoreArchivio aggiornatoreArchivio) {
		aggiornatoreArchivio.setInFunzione(false); /**Interrompe l'operato del thread che aggiorna l'archvio dell'host*/
		
		cleanup();	
	}
	
	/**
	 * Metodo che simula la pulizia della console.
	 * Aiuta a migliorare la leggibilità dello schermo per l'utente
	 */
	private static void puliziaSchermo () {
		
		for( int i=0; i<5; i++ ) {
			
			System.out.print("\n");
		
		}
	
	}
	
	/**
	 * Metodo che trasforma un tempo da millisecondi a ore
	 * @param msValue
	 * @return
	 */
	public static long msToHour (long msValue) {
		return (msValue/1000/60/60);
	}
	
}