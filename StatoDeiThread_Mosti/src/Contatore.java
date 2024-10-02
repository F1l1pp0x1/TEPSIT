/**
 * Classe Contatore utilizzata da ogni thread per contare da 1 a x
 */
public class Contatore implements Runnable{

	private final int sleepDuration = 120; /**@brief: how many ms the thread will wait for each increment*/
	private int x;		/**@brief: numero massimo da raggiungere*/
	private int numero;	/**@brief: numero raggiunto al momento*/
	
	/**
	 * @brief: inizializza il numero massimo da raggiungere
	 * @param x
	 */
	public Contatore ( int x )
	{
		this.x=x;
	}
	
	/**
	 * @brief: metodo run
	 */
	public void run()
	{
		/**
		 * @brief: incrementare da 0 a x inclusi
		 */
		for( numero=0; numero<=x; numero++ )
		{
			try {
				Thread.sleep( sleepDuration );
			}
			catch ( InterruptedException e ) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * @brief: getter of x
	 */
	public int getX () {
		return x;
	}
	
	/**
	 * @brief: getter of numero
	 */
	public int getNumero () {
		return numero;
	}
	
	
}
