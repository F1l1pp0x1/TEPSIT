package api;

import javax.ws.rs.container.ContainerRequestContext; //per richiesta HTTP client
import javax.ws.rs.container.ContainerResponseContext;//per risposte HTTP dal server
import javax.ws.rs.container.ContainerRequestFilter; //per filtrare le richieste
import javax.ws.rs.container.ContainerResponseFilter;//per filtrare le risposte
import java.io.IOException;

public class RequestResponse implements ContainerRequestFilter, ContainerResponseFilter {
    
    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
    	//filtra le richieste
    }

    @Override
    public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) throws IOException {
        //filtra le risposte
    }
    
}
