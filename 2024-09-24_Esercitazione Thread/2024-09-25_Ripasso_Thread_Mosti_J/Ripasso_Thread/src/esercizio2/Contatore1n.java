package esercizio2;

/**
 * @brief: classe usata da Esercizio2 per contare da 1 a n
*/
class Contatore1n implements Runnable {
	
	private int n; /**@brief: fino a quale numero vuoi contare*/
	private long d; /**@brief: quanti ms ogni thread aspetterà*/
	
	/**
	 * @brief: constructor of Contatore1
	 * @param n massimo numero contato partendo da 1
	 * @param d ms che il thread aspetta
	 */
	public Contatore1n(int n, long d) {
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
    		System.out.println("Thread Name: "+Thread.currentThread().getName()+"\nThread N. "+Thread.currentThread().getId()+"\nda 1 a "+n+"\nnumero: "+i+"\n---------------------------------------\n\n");
    	}
    	
    	/**
         * @brief: let the thread stop for d ms
         */
    	try {
    		
        	Thread.sleep(d);
        	
    	}
    	catch (InterruptedException e) {
    		
    		e.printStackTrace();
    		
    	}
    	
    }
}