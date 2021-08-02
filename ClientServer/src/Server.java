import java.io.*;
import java.net.*;

public class Server {
    //porta necessaria per la connessione tra client e server
    public static final int PORT = 8080;
    private static int contatore = 0;
    static Socket ServerSocket = null;

    //sottoclasse serverThread, estende Thread e effettua l'overriding del metodo di Thread run()
    static class serverThread extends Thread {
        //istanzio il lettore Buffered Reader e l'ouput writer per restituire l'output
        BufferedReader in = null;
        PrintWriter out = null;
        //istanzio la socket che verrà usata in run()
        Socket s1;

        //costruttore che assegna al Socket s il socket del client passato dal main
        public serverThread(Socket s) {
            //socket del client
            s1 = s;
        }

        //overriding di run()
        public void run() {
            try {
                //lettura dell'input
                InputStreamReader isr = new InputStreamReader(s1.getInputStream());
                in = new BufferedReader(isr);
                // creazione stream di output su clientSocket
                OutputStreamWriter osw = new OutputStreamWriter(s1.getOutputStream());
                BufferedWriter bw = new BufferedWriter(osw);
                out = new PrintWriter(bw, true);

                //loop all'infinito
                while (true) {
                    //leggo una stringa dall'utente
                    String str = in.readLine();
                    //Se ha valore 2, esco dall'if chiudendo lo stream e la connessione del client
                    if (str.equals("2")) {
                        break;
                    } else {
                        out.println("Client connessi: " + contatore);
                    }
                }
                out.close();
                in.close();
                s1.close();
                contatore--;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    //main
    public static void main(String[] args) throws IOException {
        //inizializzo una nuova socket del server passando la porta 8080 in comune tra Client e Server
        ServerSocket serverSocket = new ServerSocket(PORT);
        Socket clientSocket = null;

        //stampe di controllo
        System.out.println("Avvio il server ");
        System.out.println("Server Socket: " + serverSocket);
        try {
            //loop all'infinito
            while (true) {
                //accetto la connessione entrante dal client
                clientSocket = serverSocket.accept();
                System.out.println("Connessione del client accettata: " + clientSocket);
                //nuova istanza del server che gestirà le connessioni dal client
                serverThread threadServer = new serverThread(clientSocket);
                threadServer.start();
                //incremento il numero di visitatori
                contatore++;
            }
        } catch (IOException e) {
            System.err.println("Non è possibile inviare messaggi tra Client e Server");
        }
    }
}