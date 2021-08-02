package Aeroporto;

public class Aereo extends Thread {
    private Pista pista;
    private String nome;
    public boolean tipoAereo;

    public Aereo(Pista pista, String nome, boolean tipoAereo) {
        this.pista = pista;
        this.nome = nome;
        this.tipoAereo = tipoAereo;
        start();
    }


    public void run() {
        //ciclo all'infinito
        while (true) {
            //se true l'aereo deve atterrare
            if (this.tipoAereo) {
                try {
                    //attendo che l'aereo si avvicini all'aeroporto
                    sleep(15000);
                    //prova l'atterraggio
                    pista.atterra(this);
                    //segnalo che l'aereo dovrà decollare
                    this.tipoAereo = false;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else if (!this.tipoAereo) { //gestisco il caso tipo aereo == false (il decollo)
                try {
                    //attendo che l'aereo compia le operazioni precedenti al volo
                    sleep(10000);
                    //prova il decollo
                    pista.decolla(this);
                    //l'aereo diventa un aereo che dovrà atterrare
                    this.tipoAereo = true;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    public String getNome() {
        return this.nome;
    }
}
