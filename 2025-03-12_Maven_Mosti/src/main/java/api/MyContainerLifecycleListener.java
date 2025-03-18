package api;

import javax.ws.rs.core.Application;
import org.glassfish.jersey.core.client; //per usare i container, che gestiscono le richieste e risposte http
import org.glassfish.jersey.server.ContainerLifecycleListener; //permette di accedere al ciclo di vita di un container

public class MyContainerLifecycleListener implements ContainerLifecycleListener {

    // Called when the container starts up
    @Override
    public void onStartup(Container container) {
        System.out.println("Container is starting up...");
    }

    // Called when the container is reloaded
    @Override
    public void onReload(Container container) {
        System.out.println("Container is being reloaded...");
    }

    // Called when the container shuts down
    @Override
    public void onShutdown(Container container) {
        System.out.println("Container is shutting down...");
    }
}
