package Singolo;

public class Discoteca {
	
	private int numeroPersone=0;
	
		public synchronized void entra() {
	        numeroPersone++;
		}

	    public synchronized void esci() {
	        numeroPersone--;
	    }

	    public synchronized int getNumeroPersone() {
	        return numeroPersone;
	    }
}
