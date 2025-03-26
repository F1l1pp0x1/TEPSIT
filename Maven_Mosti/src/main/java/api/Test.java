//Per raggiungere la pagina digita localhost:8080/api/test/Johnny
//$ curl -v http://localhost:8080/api/test/eleonora nel prompt per analizzare la richiesta get
package api;

import javax.ws.rs.GET; //Libreria usata per rispondere a richieste GET HTTP
import javax.ws.rs.Path; //Fa in modo di scrive @Path("test")
import javax.ws.rs.PathParam; //permette di scrive @Path("{name}")

//@Path("test") fa si che la classe Test si trovi digitando nell'URL /test
@Path("test")
public class Test {
	@GET
	/*
	 * @Path("{name}") permette dopo /test di immettere il valore della variabile name (es: localhost:8080/api/test/Johnny)
	 * test(@PathParam("name") permette di trasformare il parametro Johnny scritto nell'URL nel valore della variabile name 
	 */
	@Path("{name}")
	public String test(@PathParam("name") String name) {
		return "Ciao " + name.toUpperCase(); //Ciao JOHNNY
	}
}