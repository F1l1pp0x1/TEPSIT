package default_package;
/**
 * classe la cui istanza è utilizzata dai thread per prenotare i posti del teatro
 */
public class Prenotatore implements Runnable {
	
	private boolean[][] matrixTeatro; 	/**@brief: posti del teatro*/
	private int yTeatro;				/**@brief: numero file del teatro*/
	private int xTeatro;				/**@brief: numero posti per fila del teatro*/
	private boolean fermo=false;		/**@brief: indica se il thread deve fermare l'esecuzione*/
	private Posto[] vetPostiCentrali;	/**@brief: vettore dei posti del teatro dai più centrali ai meno centrali*/
	private int postiOccupati=0;		/**@brief: indica i posti occupati sul totale*/
	
	/**
	 * @brief: costruttore di Prenotatore
	 * @param matrixTeatro 
	 * @param yTeatro numero righe del teatro
	 * @param xTeatro numero colonne del teatro
	 */
	public Prenotatore ( boolean[][] matrixTeatro, int yTeatro, int xTeatro ){
		this.matrixTeatro=matrixTeatro;
		this.yTeatro=yTeatro;
		this.xTeatro=xTeatro;
		
		preparaPosto();
	}
	
	/**
	 * @brief: metodo run eseguito dal thread
	 */
	public void run() {
		/**
		 * @brief: finche ci sono posti liberi nel teatro o finche lo spettacolo non inizia
		 */
		while( fermo!=true && postiOccupati < (yTeatro*xTeatro) )
		{
			prenotaPosto();
		}
	}
	
	
	/**
	 * @brief: permette a un singolo thread alla volta di prenotare un posto nel teatro
	 */
	private synchronized void prenotaPosto() {
		/**
		 * @brief: se è presente un posto libero
		 */
		if( postiOccupati < (yTeatro*xTeatro) )
		{
			/**
			 * @brief: prendere il primo posto più centrale libero e mettelo occupato
			 */
			matrixTeatro[ vetPostiCentrali[postiOccupati].getYPosto() ] [ vetPostiCentrali[postiOccupati].getXPosto() ]=true;
			System.out.println("Posto prenotato a coordinate y= "+vetPostiCentrali[postiOccupati].getYPosto()+" x="+vetPostiCentrali[postiOccupati].getXPosto());
			
			postiOccupati++;
		}
	}
	
	/**
	 * @brief: prepara il vettore dei posti del teatro dal più centrale al meno centrale
	 */
	private void preparaPosto() {
		/**
		 * @brief: inizializza il vettore, creando tante celle quanti sono il numero di posti
		 */
	    vetPostiCentrali = new Posto[yTeatro * xTeatro];

	    /**
	     * @brief: Definizione del punto di partenza (il centro della matrice)
	     */
	    int yHalf = yTeatro / 2;
	    int xHalf = xTeatro / 2;

	    /**
	     * @brief: Direzioni per la spirale: dx, giù, sx, su (destra, giù, sinistra, su)
	     * 
	     * @details: dirX o dirY {destra, giù, sinistra, su}
	     */
	    int[] dirX = {1, 0, -1, 0};
	    int[] dirY = {0, 1, 0, -1};

	    /**
	     * @brief: Quanti passi fare in ogni direzione
	     * 
	     * @details: inizialmente si fa 1 passo solo
	     */
	    int steps = 1;
	    
	    /**
	     * @brief: indice del vetPostiCentrali
	     */
	    int vetIndex = 0;

	    /**
	     * @brief: Inserisce il posto centrale come primo elemento
	     */
	    vetPostiCentrali[vetIndex++] = new Posto(xHalf, yHalf);

	    /**
		* @brief: Ciclo che continua finché non riempie tutto il vettore dei posti centrali
		*/
		while (vetIndex < yTeatro * xTeatro) {
		    	
	        /**
	         * @brief: Alterna tra le direzioni (destra, giù, sinistra, su) muovendosi a spirale
	         */
	        for (int d = 0; d < 4; d++) {
	        	
	        	/**
	        	 * @brief: si muove nella direzione corrente per n° steps passi
	        	 */
	            for (int s = 0; s < steps; s++) {
	                xHalf += dirX[d];
	                yHalf += dirY[d];

	                /**
	                 * @brief: Verifica che sia dentro i limiti del teatro
	                 */
	                if (xHalf >= 0 && xHalf < xTeatro && yHalf >= 0 && yHalf < yTeatro) {
	                    vetPostiCentrali[vetIndex++] = new Posto(xHalf, yHalf);
	                }

	                /**
	                 * @brief: Se ha riempito tutto il vettore, esce
	                 */
	                if (vetIndex >= yTeatro * xTeatro) return;
	            }

	            /**
	             * @brief: Ogni due direzioni, aumenta il numero di passi
	             */
	            if (d == 1 || d == 3) {
	                steps++;
	            }
	        }
	    }
	}
	
	/**
	 * @brief: GETTER METHODS
	 */
	int getYTeatro() {
		return yTeatro;
	}
	int getXTeatro() {
		return xTeatro;
	}
	boolean getFermo() {
		return fermo;
	}
	int getPostiOccupati() {
		return postiOccupati;
	}
	
	/**
	 * @brief: SETTER METHODS
	 */
	void setYTeatro( int yTeatro ) {
		this.yTeatro=yTeatro;
	}
	void setXTeatro( int xTeatro ) {
		this.xTeatro=xTeatro;
	}
	void setFermo( boolean fermo) {
		this.fermo=fermo;
	}
	void setPostiOccupati( int postiOccupati ) {
		this.postiOccupati=postiOccupati;
	}
	
	
}
