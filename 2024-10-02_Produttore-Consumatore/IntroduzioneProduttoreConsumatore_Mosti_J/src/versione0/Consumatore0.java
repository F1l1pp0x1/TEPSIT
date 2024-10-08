package versione0;
import java.util.ArrayDeque;

public class Consumatore0 implements Runnable{
	private int pari;
	private int dispari;
	private int numeroConsumato;
	
	private Buffer buffer; /**@bief: istanza del buffer condiviso*/
	
	ArrayDeque <Integer> codaNumeriConsumati = new ArrayDeque <>(); /**@brief: coda dei numeri consumati dal consumatore*/
	
	/**
	 * @brief: Costruttore
	 * 
	 * @param buffer istanza del buffer condiviso
	 */
	public Consumatore0 (Buffer buffer) {
		this.buffer = buffer;
	}
	
	/**
	 * @brief: Thread execution
	 */
	public void run( ) {
		
		while(true) {
			
			/**
			 * @brief: il consumatore consuma il buffer
			 */
			consumatoreBuffer();
		}
	}
	
	/**
	 * @brief: il consumatore controlla se il buffer è libero e nel caso vi preleva i dati
	 */
	private void consumatoreBuffer () {
		
		/**
		 * @brief: accedere al metodo synchronized per prelevare dati dal buffer
		 */
		buffer.consumatoreBuffer( codaNumeriConsumati );
		
		System.out.println("Numeri prelevati: ");
				
		
		/**
		 * @brief: finchè la coda non è vuota
		 */
		while( codaNumeriConsumati.isEmpty() == false ) {
			
			numeroConsumato = (int) codaNumeriConsumati.removeFirst();
			
			/**
			 * @if: il numero è pari
			 */
			if(numeroConsumato%2==0) {
				pari++;
			}
			else {
				dispari++;
			}

			/**
			 * @brief: stampa numero prelevato
			 */
			System.out.println(numeroConsumato+";\t");
			
		}
		/**
		 * @brief: statistiche numeri pari e dispari
		 */
		System.out.println("Numeri pari: "+pari
						+"\nNumeri dispari: "+dispari);
		
	}
}
