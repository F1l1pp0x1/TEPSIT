//digita localhost:8080/books

package api;

import javax.ws.rs.GET; //Libreria usata per rispondere a richieste GET HTTP
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.DELETE;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.net.URISyntaxException;
import javax.ws.rs.Path; //Fa in modo di scrive @Path("test")
import javax.ws.rs.PathParam; //permette di scrive @Path("{name}")
import java.util.List;
import java.util.ArrayList;

@Path("books")
public class BookServices {
	@GET //in caso di richiesta GET
	public List list() {
		//lista libri e autori
		List books = new ArrayList();
		List authors = new ArrayList();
		
		//Crea un autore di libri
		Author author = new Author();
		author.setId(1);
		author.setName("Joanne");
		author.setSurname("Rowling");
		
		//Aggiunge l'autore alla lista degli autori
		authors.add(author);
		
		//Crea il primo libro
		Book book1 = new Book();
		book1.setId(1);
		book1.setTitle("Harry Potter and the Philosopher's Stone");
		book1.setLanguage("english");
		book1.setAuthors(authors);
		
		//aggiunge il primo libro alla lista dei libri
		books.add(book1);
		
		//Crea il secondo libro
		Book book2 = new Book();
		book2.setId(2);
		book2.setTitle("Harry Potter and the Chamber of Secrets");
		book2.setLanguage("english");
		book2.setAuthors(authors);
		
		//aggiunge il secondo libro alla lista dei libri
		books.add(book2);
		
		return books;
	}
	
	@GET //in caso di richiesta GET
	@Path("{id}") //digitando localhost:8080/api/BookServices/2
	public Book get(@PathParam("{id}") long id) {
		//lista autori
		List authors = new ArrayList();
		
		//crea autore
		Author author = new Author();
		author.setId(1);
		author.setName("Joanne");
		author.setSurname("Rowling");
		
		//aggiunge autore a lista autori
		authors.add(author);
		
		//crea libro
		Book book = new Book();
		book.setId(1);
		book.setTitle("Harry Potter and the Philosopher's Stone");
		book.setLanguage("english");
		book.setAuthors(authors);
		
		return book;
	}
	
	@POST //in caso di richieste POST
	public Response add(Book book) throws URISyntaxException {
		long newId = 3;
		return Response.created(new URI("api/books/" + newId)).build();
	}
	
	@PUT//in caso di richieste PUT
	@Path("{id}") //digitando localhost:8080/api/BookServices/2
	public Response update(@PathParam("{id}") long id, Book book) {
		return Response.noContent().build();
	}
	
	@DELETE //in caso di richieste delete
	@Path("{id}") //digitando localhost:8080/api/BookServices/2
	public Response delete(@PathParam("{id}") long id) {
		//cancella il libro specificato
		return Response.noContent().build();
	}
}