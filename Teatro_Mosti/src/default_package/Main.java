package default_package;
import java.util.ArrayList;
/**
 * A Teatro
 * 
 * Considerare uno scenario in cui in un cinema gli spettatori arrivano per comprare dei biglietti
 * per un film, il cinema è composto da 15 file ogniuna con 46 posti. 
 * 
 * Ogni spettatore prova a prenotare i posti centrali, e la biglietteria gli fornisce sempre i posti più vicini.
 * 
 * Definire quali sono le entità attiva e quali sono le risorse (entità passive) del sistema e creare
 * un programma che simula con la creazione di 7 Thread la prenotazione dei posti.
 * 
 * La simulazione deve prevedere che lo spettacolo può iniziare una volta passati 10 secondi e deve
 * mostrare il numero di posti disponibili rimasti nel cinema.
 * 
 * La simulazione deve garantire che lo stesso posto viene venduto a due spettatori diversi e che
 * non vengono venduti più posti di quelli disponibili.
 * 
 * La simulazione deve utilizzare delle stampe a video per far capire l’implementazione dello scenario
 * 
 * @author Filippo Mosti
 * @class 5°AI
 * @date 30/09/2024
 */
public class Main {

	public static void main (String[] args)
	{
		final int sleepDuration=10000;								/**@brief: quanto bisogna aspettare prima che lo spettacolo inizi*/
		final int yTeatro=15;										/**@brief: numero file del teatro*/
		final int xTeatro=46;										/**@brief: numero posti per fila del teatro*/
		final int numeroThread = 7;									/**@brief: numero di thread da creare*/
		boolean [][] matrixTeatro = new boolean [yTeatro][xTeatro]; /**@brief: posti del teatro*/
		ArrayList <Thread> listThread = new ArrayList<>();			/**@brief: lista dei thread*/
		
		/**
		 * @brief: istanza di Prenotatore utilizzata dai thread per prenotare una poltrona
		 */
		Prenotatore prenotatore = new Prenotatore ( matrixTeatro, yTeatro, xTeatro );
		
		/**
		 * @brief: creare i thread
		 */
		for (int i=0; i<numeroThread; i++)
		{			
			/**
			 * @brief: creare thread
			 */
			listThread.add( new Thread( prenotatore ) );
		}
		
		/**
		 * @brief: avviare i thread
		 */
		for( int i=0; i<numeroThread; i++ )
		{
			listThread.get(i).start();
		}
		
		/**
		 * @brief: lo spettacolo inizia tra sleepDuration ms
		 */
		try {
			Thread.sleep(sleepDuration);
		}catch( InterruptedException e ) {
			e.printStackTrace();
		}
		
		/**
		 * @brief: ferma l'esecuzione dei thread
		 */
		prenotatore.setFermo(true);
		
		/**
		 * @brief: stampa posti liberi e occupati del teatro
		 */
		System.out.println
				("POSTI DEL CINEMA"
				+ "\nPosti occupati: "+prenotatore.getPostiOccupati()
				+ "\nPosti liberi: "+(yTeatro*xTeatro-prenotatore.getPostiOccupati() ) 
				+ "\nPosti totali: "+(yTeatro*xTeatro)
				+ "\nX: posto occupato"
				+ "\nO: posto libero");
		/**
		 * @brief: scorrere la matrice e stampare X per posti occupati e O per posti liberi
		 */
		for( int i=0; i<yTeatro; i++ )
		{
			for( int j=0; j<xTeatro; j++ )
			{
				/**
				 * @brief: se il posto è occupato
				 */
				if( matrixTeatro[i][j] == true )
				{
					System.out.print("X ");
				}
				else {
					System.out.print("O ");
				}
			}
			System.out.print("\n");
		}

	}
}
