package versione2;

import java.util.ArrayDeque; /**@brief: coda a doppia estremità (inserimento e rimozione sia in testa sia coda)*/

public class Buffer2 {
	
	private final int maxCoda; 									/**@brief: massima dimensione della coda*/
	private ArrayDeque<Dato> codaBuffer = new ArrayDeque<>();		/**@brief: coda del buffer*/
	private Dato datoConsumato;									/**@brief: dato appena consumato, in modo da poter settare il tempo finale*/
	
	/**
	 * @brief: Costruttore
	 * 
	 * @param maxCoda massima dimensione della coda nel buffer che contiene i dati
	 */
	public Buffer2 ( final int maxCoda ) {
		this.maxCoda = maxCoda;
	}
	
	/**
	 * @brief: Produttore immette i dati nella coda
	 */
	public synchronized void produttoreBuffer ( Dato dato )  {
		
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
		 * @brief: inserisce un dato nella coda
		 */
		codaBuffer.addLast(dato);
		
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
	public synchronized void consumatoreBuffer ( ArrayDeque<Dato> codaNumeriConsumati ){
		
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
		 * @brief: preleva i numeri primi della coda in ordine FIFO
		 */
		while( codaBuffer.isEmpty() == false ) {
			/**
			 * @if: se il numero del dato è primo
			 */
			if( isPrime( codaBuffer.peekFirst().getNumero() ) == true ) {
				datoConsumato = codaBuffer.pollFirst();
				datoConsumato.setFineTempo();
				
				codaNumeriConsumati.addLast( datoConsumato ); /**@brief: versione dove il consumatore preleva in ordine FIFO*/
				
				//codaNumeriConsumati.addLast( datoConsumato ); /**@brief: versione dove il consumatore preleva in ordine LIFO*/

			}
			

		}
		
		/**
		 * @brief: preleva gli elementi della coda rimanenti in ordine FIFO
		 */
		while( codaBuffer.isEmpty() == false ) {
			datoConsumato = codaBuffer.pollFirst();
			datoConsumato.setFineTempo();
			
			codaNumeriConsumati.addLast( datoConsumato ); /**@brief: versione dove il consumatore preleva in ordine FIFO*/
			
			//codaNumeriConsumati.addLast( datoConsumato ); /**@brief: versione dove il consumatore preleva in ordine LIFO*/

			
		}
		
		/**
		 * @brief: risveglia eventuali produttori
		 */
		notifyAll();
		
	}
	
	/**
	 * @brief: calcola se un numero è primo o no
	 */
	private boolean isPrime (int num) {
		for(int i=2 ; i<num; i++) {
			if( num%i ==0 ) {
				return false;
			}
		}
		return true;
	}
	
}
