package esercizio4;

import java.util.Arrays;

public class OrdinaVettore implements Runnable{
	
	private int[]vet;	/**@brief: vettore*/
	private int start;	/**@brief: indice inizio vettore*/
	private int end;	/**@brief: indice fine vettore*/
	
	public OrdinaVettore ( int[]vet, int start, int end )
	{
		/**
		 * @brief: passaggio del vettore per reference
		 */
		this.vet=vet;
		this.start=start;
		this.end=end;
	}
	
	/**
	 * @brief: run method from Runnable
	 */
    public void run() 
    {
    	/**
    	 * @brief: Il thread ordina la sua parte di vettore
    	 */
    	Arrays.sort(vet, start, end);
    }
}
