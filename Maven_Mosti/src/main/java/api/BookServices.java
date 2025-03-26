
package api;

//BANANAJOE import java.io.FileWriter;
//BANANAJOE import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;		//usato per scrivere @Path("books")
import javax.ws.rs.PathParam;	//usato per scrivere @Path("{id}")
//BANANAJOE import javax.ws.rs.Produces;
//BANANAJOE import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
//BANANAJOE import com.google.gson.Gson;

//raggiunto con localhost:8080/books permette di avere la lista di tutti i libri
@Path("books")
public class BookServices {
	@GET //in caso di richiesta GET
	//stampa la lista di tutti i libri con le info di ogni libro
	public List<Book> list() {
		//lista libri e autori
		List<Book> books = new ArrayList();
		List<Author> authors = new ArrayList();
		
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
	//restituisce il libro con l'id specificato
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
	
	@POST //in caso di richieste POST, aggiunge un libro
    public Response add(Book book) throws URISyntaxException {
        long newId = 3;  // In un caso reale, il nuovo ID sarebbe generato automaticamente.
        
       //BANANAJOE
//        // Serializza l'oggetto Book in formato JSON e salvalo in un file
//        Gson gson = new Gson();
//        try (FileWriter writer = new FileWriter("book" + newId + ".json")) {
//            gson.toJson(book, writer);  // Serializza e scrivi nel file
//            System.out.println("Book salvato nel file book" + newId + ".json");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        // Restituisci una risposta con URI per la risorsa appena creata
        return Response.created(new URI("books/" + newId)).build();
    }
	
	@PUT//in caso di richieste PUT, si aggiorna il libro specifificato
	@Path("{id}") //digitando localhost:8080/api/BookServices/2
	public Response update(@PathParam("{id}") long id, Book book) {
		return Response.noContent().build();
	}
	
	@DELETE //in caso di richieste delete, cancella il libro specificato
	@Path("{id}") //digitando localhost:8080/api/BookServices/2
	public Response delete(@PathParam("{id}") long id) {
		//cancella il libro specificato
		return Response.noContent().build();
	}
}