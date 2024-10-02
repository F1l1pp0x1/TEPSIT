package esercizio3;

/**
 * @brief: classe usata da Esercizio3 per contare da 1 a n stampando solo i multipli del tid del thread
*/
class Contatore3 implements Runnable {
	
	private int n; /**@brief: fino a quale numero vuoi contare*/
	
	/**
	 * @brief: constructor of Contatore3
	 * @param n massimo numero contato partendo da 1
	 */
	public Contatore3(int n) {
		this.n=n;
	}
	
	
	/**
	 * @brief: run method from Runnable
	 */
    @Override
    public void run() {
        
        /**
         * @brief: conta da 1 a n
         */
    	for( int i=1; i<=n; i++) {
    		
    		/**
    		 * @if: se il numero corrente è multiplo del TID del thread
    		 */
    		if( i%(Thread.currentThread().getId()) == 0 ) 
    		{
    			
    			/**
    			 * @brief: stampa il numero
    			 */
    			System.out.println("Thread Name: "+Thread.currentThread().getName()+"\nThread N. "+Thread.currentThread().getId()+"\nda 1 a "+n+"\nnumero: "+i+"\n---------------------------------------\n\n");
    		
    		}
    		else
    		{
    			/**
    			 * @brief: non stampa il numero
    			 */
    		}
    	}
    	
    }
}