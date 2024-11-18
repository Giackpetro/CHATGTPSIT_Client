package it.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class Receive extends Thread{
    //attributes
    BufferedReader in;

    //methods and constructions
    public Receive(Socket s) throws IOException{
        this.in = new BufferedReader(new InputStreamReader(s.getInputStream()));
    }

    public void errorUsername(String[] a){
        if (a[0].equals("KO")) {
            System.out.println("Errore, " + a[1]);
        } else {
            System.out.println("ti sei collegato al server");
        }
    }

    @Override
    public void run() {
        try {
            
        } catch (Exception e) {
            System.out.println("Errore");
        }
    }
}
