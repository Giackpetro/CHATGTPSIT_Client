package it.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class Receive extends Thread {
    // attributes
    private BufferedReader in;
    private Controller c;

    // methods and constructions
    public Receive(Socket s, Controller controller) throws IOException {
        this.in = new BufferedReader(new InputStreamReader(s.getInputStream()));
        this.c = controller;
    }

    // funzione che controlla le risposte del server, guardando anche se si trattano
    // o no di errori
    public void check(String[] a) throws IOException {
        switch (a[0]) {
            case "KO":
                System.out.println("Errore, " + a[1]);
                if (a[1].equals("user-not-available")) {
                    c.alertUsernameError();
                }
                break;
            
            case "JOIN":
                System.out.println("Client connesso al server");
                break;
            
            case "ACCEPT":
                System.out.println("Nuovo nome utente <" + a[1] + "> accettato");
                break;
            case "USERS":
                System.out.println("Lista utenti attivi:");
                for (int i = 1; i < a.length; i++) {
                    System.out.println(a[i]);
                }
                break;

            default:
                System.out.println(a[1] + " si è collegato alla chat");
                break;
        }
    }


    // funzione di start che è la parte centrale della classe Receive e del
    // funzionamento di essa. Inoltre questa sta sempre in ascolto per prendere in
    // input i messaggi/risposte del server
    @Override
    public void run() {
        try {
            while (true) {
                String input = in.readLine();
                String[] answer = input.split(" ");
                check(answer);
            }
        } catch (Exception e) {
            System.out.println("Errore");
        }
    }
}
