package it.client;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Sender extends Thread {
    // attributes
    DataOutputStream out;

    // methods and constructions
    public Sender(Socket s) throws IOException {
        this.out = new DataOutputStream(s.getOutputStream());
    }

    // funzione per prendere un input da tastiera
    public String getStringByUser() {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        return input;
    }

    // menu di scelta dell'operazione che si desidera
    public String getChoose() {
        String choose = "";
        boolean control = false;
        do {
            System.out.println("----------------------------------------");
            System.out.println("/change per cambiare l'username");
            System.out.println("/users per vedere gli utenti connessi");
            System.out.println("/private per una chat privata");
            System.out.println("/global per una chat globale");
            System.out.println("/esc per uscire dalla chat");
            System.out.println("----------------------------------------");
            choose = this.getStringByUser();
            if ((choose.equals("/change") || choose.equals("/users") || choose.equals("/private")
                    || choose.equals("/global") || choose.equals("/esc"))) {
                control = true;
            } else {
                System.out.println("Comando inesistente");
            }
        } while (!(control == true));
        return choose;
    }

    // funzione che guarda cosa ha scelto l'utente e returna la stringa adeguata da
    // inviare al server
    public String operations(String c) {
        switch (c) {
            case "/change":
                System.out.println("Inserisci il nuovo username:");
                String newUsername = this.getStringByUser();
                return "CHANGE " + newUsername;

            case "/users":
                return "USERS";

            case "/private":
                System.out.println("Inserisci il destinatario: ");
                String dest = this.getStringByUser();
                System.out.println("--------------------------------");
                System.out.println(dest + ":");
                String mes = this.getStringByUser();
                return "PRIVATE " + dest + " " + mes;

            case "/global":
                System.out.println("Inserisci il messaggio");
                String mesGlobal = this.getStringByUser();
                return "GLOBAL " + mesGlobal;

            default:
                System.out.println("GOODBYE");
                return "ESC ";
        }
    }

    // funzione che ottiene il nome utente e lo invia al server
    public void getUsername() throws IOException {
        String username = this.getStringByUser();
        out.writeBytes("CONNECT " + username + "\n");
    }

    //funzione di start che è la parte centrale della classe Sender e del funzionamento di essa
    @Override
    public void run() {
        try {
            System.out.println("Inserisci un username:");
            getUsername();

            boolean control = false;
            do {
                String command = this.getChoose();
                String mes = this.operations(command.toLowerCase());
                out.writeBytes(mes + "\n");
                if (mes.equals("ESC")) {
                    control = true;
                }

            } while (!(control == true));

        } catch (Exception e) {
            System.out.println("Errore");
        }
    }
}
