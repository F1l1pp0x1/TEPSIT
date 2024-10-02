/**
 * D - Fare un programma che chiede all'utente di inserire N elementi in un vettore. Il programma deve creare 4 quattro thread ogniuno dei quali ordina
 * un quarto del vettore in maniera crescente e poi alla fine deve stampare il vettore ordinato. Per aspettare che un Thread termini usare il metodo
 * join() del Thread. Il programma NON deve fare una copia del vettore ma tutti i Thread DEVONO lavorare sullo stesso.
 * 
 * P.S.: Provare ad effettuare lo stesso esercizio con un array di 100 milioni di elementi e vedere se il tempo di ordinamento è migliore o peggiore
 * rispetto all'uso di un programma single Thread
 * 
 * @author Filippo Mosti
 * @class 5°AI
 * @date 23/09/2024
 * @warning con grandi numeri stampa solo l'ultimo pezzo di vettore; disattivando la stampa di esso stampa l'ultimo n/t (Risolto: la stampa è troppo lunga per cui stampa solo una riga alla volta; testato con delle pause ad ogni riga)
 * 
 * @warning scanner.nextLine è più lento di scanner.nextInt per cui se a riga 109 inserisco il primo viene saltato alla prima esecuzione 
*/
package esercizio4;

import java.util.ArrayList;
import java.util.Scanner;

public class Esercizio4 {
	
	public static void main( String[] args ) {
		Scanner scanner = new Scanner( System.in );
		
		int maxValue;										/**@brief: numero massimo contenibile in una cella*/
		int n;												/**@brief: dimensione vettore*/
		int t = 4;											/**@brief: numero thread*/
		ArrayList<Thread> listThread = new ArrayList<>();	/**@brief: lista thread utilizzati*/
		int[] vet;											/**@brief: vettore da ordinare*/
		int start=0;										/**@brief: inizio vettore da ordinare*/
		
		/**
		 * @brief: chiedere numero elementi
		 */
		do {
			System.out.println("\nQuanti elementi vuoi che l'array abbia (almeno "+t+")?: ");
			n = scanner.nextInt();
		}while(n<t);
		
		/**
		 * @brief: il vettore è ora grande n elementi
		 */
		vet = new int[n];
		
		/**
		 * @brief: chiedere il numero massimo che un elemento dell'array può contenere
		 */
		do {
			System.out.println("\nQuale è il numero massimo intero che un elemento dell'array può raggiungere (senza includerlo e minimo 1)?:");
			maxValue = scanner.nextInt();
		}while(maxValue<1);
		
		
		/**
		 * @brief: riempire vettore con valori tra 0 e maxValue escluso
		 */
		for( int i=0; i<n; i++ ) 
		{
			vet[i]= (int) ( Math.random() *maxValue);
		}
		
		/**
		 * @brief: creare i thread necessari
		 * @details: ogni thread può lavorare solo sul proprio n/t (dimensione diviso numero thread) di array. L'ultimo n/t non è inserito ora ma
		 * sarà costituito dalla rimanenza di elementi (può essere maggiore o inferiore a n/t)
		 */
		for( int i=0; i<t-1; i++) {
			
			listThread.add( new Thread( new OrdinaVettore( vet, start, (start+(n/t)) ) ) );
			
			start+=n/t;
		}
		/**
		 * @brief: ultimo thread che ordina l'ultima parte del vettore
		 */
		listThread.add( new Thread( new OrdinaVettore( vet, start, n ) ) );
		
		/**
		 * @brief: avviare i thread
		 */
		for( int i=0; i<t; i++) {
			listThread.get(i).start();
		}
		
		/**
		 * @brief: aspettare che i thread finiscano il lavoro
		 */
		for(Thread thread: listThread)
		{
			try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
		}
		
		/**
		 * @brief: stampa il vettore
		 */
		System.out.print( "[\n" );
		
		int indiceVettore = 0;
		/**
		 * @brief: per ogni thread escluso l'ultimo perchè la dimensione del suo pezzo di vettore è incerta
		 */
		for( int i=0; i<t-1; i++) {
			//scanner.nextInt();

			/**
			 * @brief: stampa n/t del vettore
			 */
			for (int j=0; j<n/t; j++)
			{
				System.out.print(vet[indiceVettore]+";\t");
				indiceVettore++;
			}
			
			/**
			 * @brief: lascia lo spazio tra un n/t e l'altro
			 */
			System.out.print("\n\n\n");
			
		}
		/**
		 * @brief: stampa il pezzo di vettore dell'ultimo thread
		 */
		while(indiceVettore<n)
		{
			System.out.print(vet[indiceVettore]+";\t");
			indiceVettore++;
		}
		
		
		
		/**
		 * @brief: close the scanner
		 */
		scanner.close();
	}
}
