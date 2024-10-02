package Singolo;

public class Persona extends Thread {
    private int minTempo;
    private int maxTempo;
    private Discoteca discoteca;

    public Persona(Discoteca discoteca, int minTempo, int maxTempo) {
        this.discoteca = discoteca;
        this.minTempo = minTempo;
        this.maxTempo = maxTempo;
    }

    @Override
    public void run() {
        while (true) {
            try {
                // Entra nella discoteca
                entra();

                // Resta nella discoteca per un tempo casuale
                Thread.sleep((int) (Math.random() * (maxTempo - minTempo)) + minTempo);

                // Esce dalla discoteca
                esci();

                // Resta fuori per un tempo casuale
                Thread.sleep((int) (Math.random() * (maxTempo - minTempo)) + minTempo);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void entra() {
        discoteca.entra();
    }

    public void esci() {
        discoteca.esci();
    }
}
