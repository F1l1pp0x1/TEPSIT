package Singolo;

/**
 * Si va a ballare!
 * 
 * Versione – Ingresso Singolo
 * 
 * Creare un programma che simula una persona che entra ed esce da una discoteca,
 * ogni Thread rappresenta una persona che entra in discoteca, resta per un tempo
 * casuale, poi esce e poi rientra all’infinito.
 * 
 * Il sistema deve stampare ogni secondo il numero di persone all’interno della
 * discoteca.
 * 
 * @author Filippo Mosti
 * @class 5°AI
 * @date 01-10-2024
 */
import java.util.ArrayList;
import java.util.List;

public class Main {
	
    private static final int NUM_PERSONE = 10; /**@brief: Numero di persone nella simulazione*/
    private static final int MIN_TEMPO = 1200; /**@brief: tempo minimo in discoteca*/
    private static final int MAX_TEMPO = 5500; /**@brief: tempo massimo in discoteca*/

    public static void main(String[] args) {
        /**
         * @brief: crea una discoteca
         */
    	Discoteca discoteca = new Discoteca();
    	
    	/**
    	 * @brief: lista delle Persone nella discoteca
    	 */
        List<Persona> persone = new ArrayList<>();

        /**
         * @brief: Creiamo e avviamo i thread delle persone
         */
        for (int i = 0; i < NUM_PERSONE; i++) {
            Persona persona = new Persona(discoteca, MIN_TEMPO, MAX_TEMPO);
            persona.setName("Persona-" + i);
            persone.add(persona);
            persona.start();
        }

        /**
         * @brief: Monitoraggio del numero di persone nella discoteca ogni secondo
         */
        while (true) {
            try {
                Thread.sleep(1000);
                System.out.println("Numero di persone attualmente nella discoteca: " + discoteca.getNumeroPersone());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
