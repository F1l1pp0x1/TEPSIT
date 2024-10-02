package esercizio1;

/**
 * @brief: classe usata da Esercizio1 per contare da 1 a n
*/
class Contatore1 implements Runnable {
	
	private int n; /**@brief: fino a quale numero vuoi contare*/
	
	/**
	 * @brief: constructor of Contatore1
	 * @param n massimo numero contato partendo da 1
	 */
	public Contatore1(int n) {
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
    	
    }
}