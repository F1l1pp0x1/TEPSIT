package rest;

import javax.ws.rs.ApplicationPath; //per definire l' URL a cui sono disponibili le applicazioni REST
import org.glassfish.jersey.server.ResourceConfig; //per avviare e configurare applicazioni REST (cioè che usano http per comunicare) 

@ApplicationPath("api") //le applicazioni REST si trovano digitando /api nell'URL
public class LibraryApplication extends ResourceConfig {
    //Reindirizza 
	public LibraryApplication() {
        packages("api"); //rende le classi che usano metodi http dentro il package api prontea funzionare come applicazioni REST 
    }
}