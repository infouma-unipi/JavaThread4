package Aeroporto;

public class AddettoControllo extends Thread {
    private Pista pista;

    public AddettoControllo(Pista pista) {
        this.pista = pista;
    }

    public void run() {
        while (true) {
            //se la pista è piena, do l'ok per le operazioni di decollo di un aereo e di atterraggio dell'altro
            if (pista.controllaPista()) {
                pista.liberaPista();
            }
        }
    }
}
