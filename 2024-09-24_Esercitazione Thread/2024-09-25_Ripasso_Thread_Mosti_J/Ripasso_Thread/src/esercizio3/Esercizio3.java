/**
 * @brief: C - Fare un programma che chiede all'utente due numeri t ed n, il programma deve creare t Thread ed ogni Thread deve contare da 1 ad n ,
 * ma deve stampare solo i numeri che sono multipli del proprio TID. Ogni thread deve indicare quando inizia a contare e quando finisce di contare.  
 * 
 * @author Filippo Mosti
 * @class 5°AI
 * @date 18/09/2024
 */
package esercizio3;

import java.util.Scanner;
import java.util.ArrayList;


public class Esercizio3{
	
	/**
	 * @brief: MAIN function
	 */
	public static void main(String[] args) {
		
		int t; /**@brief: quanti thread creare*/ 
		int n; /**@brief: fino a quanto contare*/
		
		/**
		 * @brief: creare scanner
		 */
		Scanner scanner = new Scanner (System.in);
		
		/**
		 * @brief: list of threads
		 */
		ArrayList<Thread> listThread = new ArrayList<>();
		
		
		/**
		 * @brief: chiedere numero thread
		 */
		do {	
			System.out.println("Quanti thread vuoi?:");
			t = scanner.nextInt();
		}while(t<1);
		
		/**
		 * @brief: chiedere fino a quanto contare
		 */
		do {	
			System.out.println("Fino a quanto vuoi che ogni thread conti?(Da 1 a n):");
			n = scanner.nextInt();
		}while(n<2); 
		
		
		/**
		 * @brief: crea t nuovi threads, ognuno come istanza della classe Contatore3
		 */
		for(int i=0; i<t;i++) {
			listThread.add( new Thread( new Contatore3(n) ) );
		}
		
		/**
		 * @brief: avviare t threads
		 */
		for( int i=0; i<t; i++) {
			( listThread.get(i) ).start();
		}
		
		/**
		 * @brief. close the scanner
		 */
		scanner.close();
		
	}
}