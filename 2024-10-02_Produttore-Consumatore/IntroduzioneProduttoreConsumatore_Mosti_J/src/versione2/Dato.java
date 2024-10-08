package versione2;

public class Dato {
	
	private int numero; /**@brief: numero del dato*/
	private int inizioTempo; /**@brief: tempo in ms di inizio vita valore*/
	private int fineTempo;	/**@brief: tempo in ms di fine vita valore*/

	public Dato( int numero ) {
		this.numero=numero;
		this.inizioTempo=(int) System.currentTimeMillis();
	}
	/**
	 * @brief: getter
	 */
	public int getNumero () {
		return numero;
	}
	public int getInizioTempo () {
		return inizioTempo;
	}
	public int getFineTempo () {
		return fineTempo;
	}
	
	/**
	 * @brief: setter
	 */
	public void setNumero (int numero) {
		this.numero=numero;
	}
	public void setInizioTempo (int inizioTempo) {
		this.inizioTempo=inizioTempo;
	}
	public void setFineTempo () {
		this.fineTempo=(int) System.currentTimeMillis();
	}
}
