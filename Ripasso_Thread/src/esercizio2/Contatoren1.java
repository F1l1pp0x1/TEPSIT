package esercizio2;

/**
 * @brief: classe usata da Esercizio2 per contare da n a 1
*/
class Contatoren1 implements Runnable {
	
	private int n;		/**@brief: numero di thread*/
	private long d;		/**@brief: ms che ogni thread aspetterà*/
	
	
	/**
	 * @brief: constructor of Contatoren1
	 * @param n quanti numeri il thread conta
	 * @param d ms che il thread aspetta
	 */
	public Contatoren1( int n, long d ) {
		
		this.n=n;
		this.d=d;
	
	}
	
	
	/**
	 * @brief: run method from Runnable
	 */
    @Override
    public void run() {
        
    		
        /**
         * @brief: count from n to 1
         */
        for( int i=n; i>=1; i-- ) {
        	
        	System.out.println("Thread Name: "+Thread.currentThread().getName()+"\nThread N. "+Thread.currentThread().getId()+"\nfrom "+n+" to 1\nnumber: "+i+"\n---------------------------------------\n\n");
        
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