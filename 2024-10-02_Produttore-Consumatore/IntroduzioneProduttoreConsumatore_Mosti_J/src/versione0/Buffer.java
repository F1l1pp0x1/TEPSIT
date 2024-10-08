package versione0;

import java.util.ArrayDeque; /**@brief: coda a doppia estremità (inserimento e rimozione sia in testa sia coda)*/

public class Buffer {
	
	private final int maxCoda; 									/**@brief: massima dimensione della coda*/
	private ArrayDeque<Integer> codaBuffer = new ArrayDeque<>();		/**@brief: coda del buffer*/
	
	/**
	 * @brief: Costruttore
	 * 
	 * @param maxCoda massima dimensione della coda nel buffer che contiene i dati
	 */
	public Buffer ( final int maxCoda ) {
		this.maxCoda = maxCoda;
	}
	
	/**
	 * @brief: Produttore immette i dati nella coda
	 */
	public synchronized void produttoreBuffer ( int numero )  {
		
		/**
		 * @brief: finchè la coda è piena
		 */
		while( codaBuffer.size() >= maxCoda )
		{
			
			try {
		
				wait();	/**@brief: blocca il produttore*/
			
			}catch(InterruptedException e) {}
		
		}
		
		/**
		 * @brief: inserisce un numero nella coda
		 */
		codaBuffer.addLast(numero);
		
		/**
		 * @brief: risveglia eventuali consumatori
		 */
		notifyAll();
		
	}
	
	/**
	 * @brief: Consumatore preleva il dato in coda
	 * 
	 * @return la testa di codaBuffer o null se la coda è vuota
	 */
	public synchronized void consumatoreBuffer ( ArrayDeque<Integer> codaNumeriConsumati ){
		
		/**
		 * @brief: finchè la coda è vuota
		 */
		while( codaBuffer.isEmpty() )
		{
			
			try {
			
				wait();		/**@brief: blocca il consumatore*/
			
			}catch(InterruptedException e) {}
		}
		
		
		/**
		 * @brief: preleva gli elementi della coda in ordine FIFO
		 */
		while( codaBuffer.isEmpty() == false ) {
			
			codaNumeriConsumati.addLast( codaBuffer.pollFirst() ); /**@brief: versione dove il consumatore preleva in ordine FIFO*/
			
			//codaNumeriConsumati.addLast( codaBuffer.pollLast() ); /**@brief: versione dove il consumatore preleva in ordine LIFO*/

		}
		
		/**
		 * @brief: risveglia eventuali produttori
		 */
		notifyAll();
		
	}
	
}
