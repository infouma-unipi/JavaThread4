package Aeroporto;

public class ProvaAeroporto {

    public static void main(String[] args) {
        //creo l'oggetto buffer
        Pista pistaAeroporto = new Pista();
        //istanzio un oggetto della classe AddettoControllo e gli passo la pista appena creata
        AddettoControllo addettoAeroporto = new AddettoControllo(pistaAeroporto);

        int nRandom = ((int) (Math.random() * 20 + 1));

        System.out.println("Avvio del sistema di gestione dei voli...");
        System.out.println("---------------------------------------------");
        //parto da 1 per avere aerei con nome "Aereo-" + numero da 1 a 20
        for (int i = 1; i <= nRandom+1; i++) {
            //operazione di modulo per creare almeno un oggetto Aereo che sia in volo (true -> %2 == 0)
            // e uno a terra (false -> %2 != 0)
            if (i % 2 == 0) {
                Aereo aereo = new Aereo(pistaAeroporto, "Aereo-" + i, true);
                //avvio il thread
            } else {
                Aereo aereo = new Aereo(pistaAeroporto, "Aereo-" + i, false);
            }
        }
        //faccio partire il thread addetto
        addettoAeroporto.start();
    }
}
