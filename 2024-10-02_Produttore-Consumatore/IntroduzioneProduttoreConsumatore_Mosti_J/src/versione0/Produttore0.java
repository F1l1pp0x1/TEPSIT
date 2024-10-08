package versione0;

public class Produttore0 implements Runnable{
	private int numero; 			/**@brief: numero casuale che il thread produce genera ogni xms millisecondi */
	private final int maxNumero; 	/**@brief: massimo numero escluso che numero può assumere*/
	private final int minNumero;	/**@brief: minimo numero incluso che numero può assumere*/

	private int xms; 				/**@brief: indica ogni quanti ms il thread produttore genera il numero*/
	private final int maxAttesa;	/**@brief: massimo tempo che il produttore può aspettare prima di chiamare la funzione per immettere dati nel buffer*/
	private final int minAttesa;	/**@brief: minimo tempo che il produttore può aspettare prima di chiamare la funzione per immettere dati nel buffer*/
	
	private Buffer buffer;			/**@brief: istanza del buffer condivisa tra produttore e consumatore*/
	
	/**
	 * @brief: Costruttore
	 * 
	 * @param buffer istanza del buffer condivisa
	 * @param maxNumero massimo numero escluso che numero può assumere
	 * @param minNumero minimo numero incluso che numero può assumere
	 * @param maxAttesa massimo tempo che il produttore può aspettare prima di chiamare la funzione per immettere dati nel buffer
	 * @param minAttesa minimo tempo che il produttore può aspettare prima di chiamare la funzione per immettere dati nel buffer
	 */
	public Produttore0 (Buffer buffer, final int maxNumero, final int minNumero, final int maxAttesa, final int minAttesa) {
		this.buffer = buffer;
		this.maxNumero = maxNumero;
		this.minNumero = minNumero;
		this.maxAttesa = maxAttesa;
		this.minAttesa = minAttesa;
	}
	
	/**
	 * @brief: Thread execution
	 */
	public void run( ) {
		
		while(true) {
			/**
			 * @brief: il produttore scrive nel buffer
			 */
			produttoreBuffer();
		
			/**
			 * @brief: aspettare xms prima di poter scrivere nuovamente nel buffer
			 */
			xms = (int) (Math.random()*( maxAttesa-minAttesa )) +minAttesa;
			try{
				Thread.sleep(xms);
			}catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	/**
	 * @brief: il produttore controlla se il buffer è libero e nel caso vi scrive
	 */
	private void produttoreBuffer () {
		
		/**
		 * @brief: calcolare il numero che il produttore immette nel buffer
		 */
		numero = (int) (Math.random()*( maxNumero-minNumero ))+minNumero;
		
		/**
		 * @brief: accedere al metodo synchronized per immettere dati nel buffer
		 */
		buffer.produttoreBuffer(numero);
		
	}
	
}
