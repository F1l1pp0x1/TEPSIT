/**
 * @brief: A2.0 Fare un programma in Java che chiede all'utente due numeri t ed
 * n, il programma deve creare t Thread ed l'insieme dei Thread creati deve contare da 1 a N 
 * (senza ripetere i valori, un conteggio condiviso)
 * 
 * @author Filippo Mosti
 * @class 5°AI
 * @date 18/09/2024
 * 
 * @warning se metto 10 thread a contare da 1 a 50, solo 3 contano e uno dopo l'altro
 */
package esercizio5;

import java.util.Scanner;
import java.util.ArrayList;


public class Esercizio5{
	
	/**
	 * @brief: MAIN function
	 */
	public static void main(String[] args) {
		
		int t;			/**@brief: quanti thread creare*/ 
		int N;			/**@brief: fino a quanto contare*/
		int numero=1; 	/**@brief: valore che incrementato da ogni thread, permette di stampare i valori da 1 a N*/
		
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
			System.out.println("Fino a quanto vuoi che i threads contino?(Da 1 a n):");
			N = scanner.nextInt();
		}while(N<2); 
		
		
		/**
		 * @brief: crea t nuovi threads, ognuno come istanza della classe Contatore5
		 */
		Contatore5 contatore5 = new Contatore5 (N, numero);
		
		for(int i=0; i<t;i++) {
			listThread.add( new Thread( contatore5 ) );
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