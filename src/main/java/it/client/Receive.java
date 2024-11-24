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

    //funzione che controlla le risposte del server, guardando se si trattano o no di errori
    public void check(String[] a) throws IOException {
        if (a[0].equals("KO")) {
            System.out.println("Errore, " + a[1]);
            if (a[1].equals("user-not-avaiable")) {
                c.alertUsernameError();
            }
        } else if (a[0].equals("JOIN")) {
            System.out.println("Client connesso al server");
        } else {
            System.out.println("OK");
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
