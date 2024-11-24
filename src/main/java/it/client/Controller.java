package it.client;

import java.io.IOException;
import java.net.Socket;

public class Controller {
    // attributes
    private Sender sender;
    private Receive receive;

    // methods and constructions
    public Controller(Socket s) throws IOException {
        this.sender = new Sender(s);
        this.receive = new Receive(s, this);
    }

    // funzione che avvia il Sender e i Receive
    public void start() {
        sender.start();
        receive.start();
    }

    // funzione richiamata dal Receive, se bisogna far rinserire l'Username perchè gia usato/non disponibile
    public void alertUsernameError() throws IOException {
        System.out.println("Inserire un'altro nome utente:");
        sender.getUsername();
    }
}
