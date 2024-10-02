/**
 * @brief: B - Fare un programma in Java che chiede all'utente tre numeri t,
 * n e d il programma deve creare t Thread  ed meta dei Thread deve contare
 * da 1ad n ed aspettare d ms tempo ogni volta che conta. L'altra metà deve
 * contare da n ad 1 e aspettare d ms di tempo
 * 
 * @author Filippo Mosti
 * @class 5°AI
 * @date 18/09/2024
 */
package esercizio2;

import java.util.Scanner;
import java.util.ArrayList;

public class Esercizio2{
	
	/**
	 * @brief: MAIN function
	 * @param args
	 */
	public static void main(String[] args) {
		
		int t; /**@brief: numero thread*/ 
		int n; /**@brief: quanti numero ogni thread conta*/
		long d; /**@brief: quanti ms ogni thread aspetta*/
		
		/**
		 * @brief: create a scanner
		 */
		Scanner input = new Scanner (System.in);
		
		
		/**
		 * @brief: array of threads
		 */
		ArrayList<Thread> listThread = new ArrayList<>();
		
		
		/**
		 * @brief: chiedere numero di thread
		 */
		do {	
			
			System.out.println("Quanti thread vuoi?:");
			t = input.nextInt();
		
		}while(t<1 || t%2!=0);
		
		
		/**
		 * @brief: chiedere quanti numeri contare
		 */
		do {	
			
			System.out.println("Quanti numeri vuoi che ogni thread conti? La prima metà conta da 1 a n e l'altra metà da n a 1:");
			n = input.nextInt();
		
		}while(n<2); 
		
		
		/**
		 * @brief: chiedere quanti ms ogni thread aspetta
		 */
		do {
			
			System.out.println("Quanti ms ogni thread vuoi che aspetti?: ");
			d = input.nextLong();
	
		}while(d<1); 
		
		
		/**
		 * @brief: crea t nuovi threads
		 */
		for(int i=0; i<t;i++) {
			
			/**
			 * @brief: controlla se siamo nella prima o seconda metà dei threads
			 */
			if( i< t/2 ) {
		
				/**
				 * @brief: il thread conta da 1 a n
				 */
				listThread.add( new Thread(new Contatore1n(n,d)) );
			}
			else {
				/**
				 * @brief: il thread conta da n a 1
				 */
				listThread.add( new Thread(new Contatoren1(n,d)) );
			}
		
		}
		
		
		/**
		 * @brief: avvia t threads
		 */
		for( int i=0; i<t; i++) {
			
			( listThread.get(i) ).start();

		}
		
		
		/**
		 * @brief. close the scanner
		 */
		input.close();
		
	}
	
}