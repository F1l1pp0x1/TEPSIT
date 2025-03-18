package api;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

//Questa eccezione è chiamata quando c'è un errore durante la richiesta HTTP del client
@Provider
public class MyExceptionMapper implements ExceptionMapper<Throwable> {
    
    @Override
    public Response toResponse(Throwable exception) {
        // stampa errore 500 problema al server con messaggio personalizzato
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity("An error : " + exception.getMessage())
                .build();
    }
}
