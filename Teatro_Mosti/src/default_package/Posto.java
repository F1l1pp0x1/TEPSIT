package default_package;
/**
 * classe Posto utilizzata per indicare le coordinate x e y di un posto all'interno del teatro
 */
public class Posto {
	
	private int xPosto;
	private int yPosto;
	
	public Posto( int xPosto, int yPosto ) {
		this.xPosto=xPosto;
		this.yPosto=yPosto;
	}
	
	/**
	 * @brief: GETTER METHODS
	 */
	int getXPosto()
	{
		return xPosto;
	}
	int getYPosto()
	{
		return yPosto;
	}
	
	/**
	 * @brief: SETTER METHODS
	 */
	void setXPosto( int xPosto )
	{
		this.xPosto=xPosto;
	}
	void setYPosto( int yPosto )
	{
		this.yPosto=yPosto;
	}
	
	
}
