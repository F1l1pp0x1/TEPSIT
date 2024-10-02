package esercizio5;

/**
 * @brief: classe usata da Esercizio5 per contare da 1 a N senza ripetizioni del solito numero
*/
class Contatore5 implements Runnable {
	
	private int N; 		/**@brief: fino a quale numero vuoi contare*/
	private int numero;	/**@brief: valore che incrementato da ogni thread, permette di stampare i valori da 1 a N*/
	private boolean raggiuntoN=false; /**@brief indica se è stato raggiunto il valore N da stampare*/
	/**
	 * @brief: constructor of Contatore5
	 * @param N massimo numero contato partendo da 1
	 */
	public Contatore5(int N, int numero) {
		this.N=N;
		this.numero=numero;
	}
	
	
	/**
	 * @brief: run method from Runnable
	 */
    @Override
    public void run() {
        /**
         * @brief: controllare se non sono stati stampati tutti e N i numeri
         */
    	while( raggiuntoN == false ) {
    		/**
    		 * @brief: incrementa numero e stampa il suo valore
    		 */
    		incrementa();
    	}
    	
    }
    
    /**
     * @brief: incrementa numero e stampa il suo valore
     * @details: essendo di tipo synchronized può essere acceduto da un solo thread alla volta
     */
    private synchronized void incrementa ()
    {
    	if( numero<=N )
    	{
    		/**
    		 * @brief: stampa il numero
    		 */
    		System.out.println("Thread Name: "+Thread.currentThread().getName()+"\nThread N. "+Thread.currentThread().getId()+"\nda 1 a "+N+"\nnumero: "+numero+"\n---------------------------------------\n\n");
    
    		/**
    		 * @brief: incrementa il numero
    		 */
    		numero++;
    	}
    	else
    	{
    		/**
    		 * @brief: raggiunto il valore N, non si procede più a stampare numero 
    		 */
    		raggiuntoN=true;
    	}
    }
}