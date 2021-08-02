import java.net.*;
import java.io.*;

public class Client extends Thread {
    public static void main(String[] args) throws IOException {
        try {
            System.out.println("Inizializzo il client");
            //127.0.0.1: localhost, creo la socket
            Socket socket = new Socket("127.0.0.1", 8080);
            System.out.println("Client inizializzato con successo");

            //stream per I/O e scanner
            InputStreamReader isr = new InputStreamReader(socket.getInputStream());
            //buffer per leggere gruppi di caratteri
            BufferedReader stdIn = new BufferedReader((new InputStreamReader(System.in)));
            BufferedReader in = new BufferedReader(isr);
            //gestione output
            OutputStreamWriter osw = new OutputStreamWriter(socket.getOutputStream());
            BufferedWriter bw = new BufferedWriter(osw);

            PrintWriter out = new PrintWriter(bw, true);//????

            String userInput;
            //loop all'infinito
            while (true) {
                //menu' che indica le possibili azioni che l'utente può compiere
                System.out.println("Cosa vuoi fare?");
                System.out.println("1 - visualizzare il numero di client online");
                System.out.println("2 - chiudere la connessione al server");
                //Leggo l'input dall'utente
                userInput = stdIn.readLine();
                out.println(userInput);
                //aggiungi gli altri casi
                if (userInput.equals("2")) {
                    break;
                } else if (userInput.equals("1")) {
                    System.out.println(in.readLine());
                } else {System.out.println("Inserire 1 o 2");}
            }
            stdIn.close();
            socket.close();
            out.close();
            in.close();
        } catch (UnknownHostException e) {
            System.out.println("Non ho trovato l'host...");
        } catch (IOException e) {
            System.err.println("Non riesco a effettuare operazioni di I/O");
        }
        System.out.println("Chiusura Client ");

    }
} //EchoClient