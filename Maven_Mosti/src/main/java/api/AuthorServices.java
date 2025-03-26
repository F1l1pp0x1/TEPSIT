package api;

import javax.ws.rs.GET; //Libreria usata per rispondere a richieste GET HTTP
import javax.ws.rs.Path; //Fa in modo di scrive @Path("test")
import javax.ws.rs.PathParam; //permette di scrive @Path("{name}")
import java.util.List;
import java.util.ArrayList;

//raggiunto con localhost:8080/api/books/1/authors e restituisce gli autori del libro
@Path("books/{book_id}/authors")
public class AuthorServices {
	@GET
	public List<Author> list(@PathParam("book_id") long bookId) {
		Author author = new Author();
		author.setId(1);
		author.setName("Joanne");
		author.setSurname("Rowling");
		List<Author> authors = new ArrayList();
		authors.add(author);
		
		return authors;
	}
}
