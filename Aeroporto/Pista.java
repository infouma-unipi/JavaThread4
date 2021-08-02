package Aeroporto;

public class Pista {
    private boolean disponibileAtterraggio;
    private boolean disponibileDecollo;

    public Aereo aereoAtterraggio;
    public Aereo aereoDecollo;

    public Pista() {
        disponibileAtterraggio = true;
        disponibileDecollo = true;
    }

    public synchronized void atterra(Aereo a) {
        //controllo se l'aereo deve atterrare
        if (a.tipoAereo) {
            while (!disponibileAtterraggio) {
                System.out.println("La pista (atterraggio) è occupata...");
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("\nLa parte di pista per l'atterraggio è libera");
            aereoAtterraggio = a;
            //tengo bloccato il posto della pista per l'atterraggio
            this.disponibileAtterraggio = false;
            System.out.println("La parte di pista per l'atterraggio sara' occupata dall'aereo " + aereoAtterraggio.getNome());
            notifyAll();
        }
    }

    public synchronized void decolla(Aereo a) {
        //se l'aereo deve decollare
        if (!a.tipoAereo) {
            //se la parte di pista per il decollo è piena, aspetto
            while (!disponibileDecollo) {
                System.out.println("La pista (decollo) è occupata...");
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            //tengo bloccati i posti in attesa che arrivi un aereo che vuole decollare
            System.out.println("\nLa parte di pista per il decollo e' libera");
            //asssegno l'aereo corrente a un oggetto di tipo Aereo per usare la getNome per la stampa
            aereoDecollo = a;
            this.disponibileDecollo = false;
            System.out.println("La parte di pista per il decollo sara' occupata dall'aereo " + aereoDecollo.getNome());
            notifyAll();
        }
    }

    public synchronized boolean controllaPista() {
        //fino a quando la pista non è piena, aspetto
        while (disponibileAtterraggio || disponibileDecollo) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //quando la pista è piena restituisco true all'Addetto del controllo che invoca il metodo
        //per liberarla
        return true;
    }

    public synchronized void liberaPista() {
        System.out.println("\nL'aereo " + aereoDecollo.getNome() + " è decollato. L'aereo " + aereoAtterraggio.getNome() + " è atterrato");
        this.disponibileDecollo = true;
        this.disponibileAtterraggio = true;
        notifyAll();
    }
}