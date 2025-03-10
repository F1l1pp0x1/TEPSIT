package it.miaazienda.hello;

import org.glassfish.jersey.server.ResourceConfig;
import javax.ws.rs.ApplicationPath;

@ApplicationPath("/api")
public class ApplicationConfig extends ResourceConfig {
    public ApplicationConfig() {
        packages("it.miaazienda.hello"); // Dove risiedono i tuoi servizi
    }
}
