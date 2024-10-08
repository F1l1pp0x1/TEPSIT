package versione0;
/**
 * Fare un programma che crea un Produttore ed un Consumatore con il seguente ruolo:
	• Produttore: genera all’infinito un numero casuale tra 0 e 1023 ogni Xms, dove X è un
	numero casuale tra 100 e 1000.
	
	• Consumatore: deve consumare i numeri generati dal Produttore e ogni volta che li
	consuma stampa il numero letto e stampa una statistica dei numeri pari e dei numeri
	dispari letti

	Il programma deve garantire che i dati prodotti vengo consumati in ordine FIFO e che non vi
	devono essere perdite dei dati prodotti dal Consumatore.
	Suggerimenti
	Utilizzare quattro classi una per Produttore, una per Consumatore, una classe Buffer ed il Main
	per avviare tutto il sistema
	
	@author Filippo Mosti
	@class 5°AI
	@date 07/10/2024
 */
public class Main0 {

	public static void main(String[] args) {
		final int maxNumero = 1024; /**@brief: massimo numero escluso che numero prodotto dal produttore può assumere*/
		final int minNumero = 100;	/**@brief: minimo numero incluso che numero prodotto dal produttore può assumere*/
		final int maxAttesa = 1000;	/**@brief: massimo tempo che il produttore può aspettare prima di chiamare la funzione per immettere dati nel buffer*/
		final int minAttesa = 0;	/**@brief: minimo tempo che il produttore può aspettare prima di chiamare la funzione per immettere dati nel buffer*/
		final int maxCoda = 10; 	/**@brief: massima dimensione della coda*/
		
		/**
		 * @brief: creare il buffer
		 */
		Buffer buffer = new Buffer ( maxCoda );
		
		/**
		 * @brief: creare thread produttore
		 */
		Thread produttore = new Thread( new Produttore0( buffer, maxNumero, minNumero, maxAttesa, minAttesa ) );
		
		/**
		 * @brief: creare thread consumatore
		 */
		Thread consumatore = new Thread( new Consumatore0( buffer ) );
		
		/**
		 * @brief: avviare il thread produttore
		 */
		produttore.start();
		
		/**
		 * @brief: avviare il thread consumatore
		 */
		consumatore.start();
		
	}

}
