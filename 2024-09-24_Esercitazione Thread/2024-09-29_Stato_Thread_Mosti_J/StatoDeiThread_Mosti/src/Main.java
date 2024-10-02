import java.util.Scanner;
import java.util.ArrayList;

/**
 * STATO DEI THREAD
 * 
 * Fare un programma che chiede all’utente due valori T ed N, dove T indica quanti Thread creare
 * ed ogni Thread conta i valori (SENZA STAMPARE) da 0 fino ad X dove X è un numero compreso tra 0 e N.
 * 
 * Ogni Thread dopo che ha stampato un valore aspetta 120ms
 * 
 * Il Thread principale stampa periodicamente (al massimo una volta al secondo) lo stato dei Thread e per i Thread
 * attivi stampa il valore a cui è arrivato a contare, mentre per quelli terminati stampa “COMPLETATO”, quando
 * tutti i Thread hanno raggiunto il loro valore X il programma deve stampare “TUTTI I THREAD COMPLETATI”
 * e terminare
 * 
 * SUGGERIMENTI
 * Per vedere se un Thread è attivo esiste il metodo isAlive(), mentre per aspettare il
 * completamento di un Thread esiste il metodo join()
 * 
 * @author Filippo Mosti
 * @class 5°AI
 * @date 29-09-24
 */
public class Main {
	
	public static void main ( String[] args )
	{
		int t; 														/**@brief: numero di thread da creare*/
		int n;														/**@brief: numero teorico massimo che ogni thread può conteggiare*/
		int x;														/**@brief: numero effettivo massimo che ogni thread conta da 0 a x, dove x è un numero random tra 0 e n*/
		ArrayList <Thread> listThread = new ArrayList <>();			/**@brief: lista dei thread*/
		ArrayList <Contatore> listContatore = new ArrayList <>();	/**@brief: lista delle istanze Contatore, ognuna delle quali passate come parametro a un thread*/ 
		int threadMorti=0;											/**@brief: indica quanti thread hanno finito di contare*/
		final int sleepDuration = 5000; 							/**@brief: quanti ms deve aspettare il thread principale per stampare lo stato dei thread*/
		/**
		 * @brief: Scanner for input actions
		 */
		Scanner scanner = new Scanner ( System.in );
		
		/**
		 * @brief: chiedere numero thread
		 */
		do {
			System.out.println("Quanti thread vuoi per il conteggio?: ");
			t = scanner.nextInt();
		}while( t<1 );
		
		/**
		 * @brief: chiedere n massimo teorico
		 */
		do {
			System.out.println("Inserisci n:");
			n = scanner.nextInt();
		}while(n<1);
		
		/**
		 * @brief: creare i thread
		 */
		for( int i=0; i<t; i++ )
		{
			x = (int) ( Math.random()*(n+1) );							/**@brief: calcolare x, compreso tra 0 e n inclusi*/
			System.out.print("X="+x);
			scanner.nextInt();
			
			listContatore.add( new Contatore (x) ); 				/**@brief: creare l'istanza di Contatore da passare come parametro al thread*/
			listThread.add( new Thread ( listContatore.get(i) ) ); 	/**@brief: creare un thread*/
		}
		
		/**
		 * @brief: avviare i thread
		 */
		for( int i=0; i<t; i++ )
		{
			listThread.get(i).start();
		}
		
		/**
		 * @brief: stampare lo stato dei thread
		 */
		while( threadMorti != t )
		{
			/**
			 * @brief: scorrere i thread per controllare quali sono vivi o morti
			 */
			for( int i=0; i<t; i++ )
			{
				/**
				 * @brief: id del thread
				 */
				System.out.println("Thread N."+listThread.get(i).getName());
				
				/**
				 * @brief: se il thread è vivo
				 */
				if( listThread.get(i).isAlive() == true )
				{
					/**
					 * @brief: stampa numero raggiunto e massimo da raggiungere
					 */
					System.out.println("raggiunto: "+listContatore.get(i).getNumero()
							+"\nsu "+listContatore.get(i).getX());
				}
				else
				{
					/**
					 * @brief: il thread è morto
					 * @details: stampare completato e massimo da raggiungere
					 */
					System.out.println("COMPLETATO"
							+ "\nmassimo: "+listContatore.get(i).getX());
					
					/**
					 * @brief: aumenta il numero dei thread morti
					 */
					threadMorti++;
				}
				
				/**
				 * @brief: stampare un divisore per leggibiliyà
				 */
				System.out.println("-----------------\n");
			}
				
			/**
			 * @brief: non tutti i thread sono morti
			 */
			if( threadMorti !=t )
			{
				threadMorti = 0;
			}
			
			/**
			 * @brief: il thread aspetta prima di ristampare lo stato dei thread
			 */
			try {
				Thread.sleep(sleepDuration);
			}catch(InterruptedException e){
				e.printStackTrace();
			}
			
			/**
			 * @brief: stampare un divisore per leggibiliyà
			 */				
			System.out.println("******************************************\n"
					+ "******************************************\n\n");
		}
		
		/**
		 * @brief: tutti i thread hanno completato il lavoro
		 */
		System.out.println("TUTTI I THREAD COMPLETATI");
			
	}
}
