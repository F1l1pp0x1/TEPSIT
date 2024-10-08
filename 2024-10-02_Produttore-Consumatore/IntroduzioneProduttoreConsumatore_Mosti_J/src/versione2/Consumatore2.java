package versione2;
import java.util.ArrayDeque;

public class Consumatore2 implements Runnable{
	private int pari;
	private int dispari;
	private Dato datoConsumato;
	
	private Buffer2 buffer; /**@bief: istanza del buffer condiviso*/
	
	ArrayDeque <Dato> codaDatiConsumati = new ArrayDeque <>(); /**@brief: coda dei numeri consumati dal consumatore*/
	
	/**
	 * @brief: Costruttore
	 * 
	 * @param buffer istanza del buffer condiviso
	 */
	public Consumatore2 (Buffer2 buffer) {
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
			consumatoreBuffer2();
		}
	}
	
	/**
	 * @brief: il consumatore controlla se il buffer è libero e nel caso vi preleva i dati
	 */
	private void consumatoreBuffer2 () {
		
		/**
		 * @brief: accedere al metodo synchronized per prelevare dati dal buffer
		 */
		buffer.consumatoreBuffer( codaDatiConsumati );
		
		System.out.println("Numeri prelevati: ");
				
		
		/**
		 * @brief: finchè la coda non è vuota
		 */
		while( codaDatiConsumati.isEmpty() == false ) {
			
			datoConsumato = codaDatiConsumati.removeFirst();
			
			/**
			 * @if: il numero è pari
			 */
			if(datoConsumato.getNumero()%2==0) {
				pari++;
			}
			else {
				dispari++;
			}

			
			
			/**
			 * @brief: stampa numero prelevato e la sua vita in ms
			 */
			System.out.println(datoConsumato.getNumero()+";\tvita(ms): "+(datoConsumato.getFineTempo()-datoConsumato.getInizioTempo()));
			
		}
		/**
		 * @brief: statistiche numeri pari e dispari
		 */
		System.out.println("Numeri pari: "+pari
						+"\nNumeri dispari: "+dispari);
		
	}
}
