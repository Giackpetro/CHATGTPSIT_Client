package it.client;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Scanner;

public class Operation {
    //attributes
    BufferedReader in;
    DataOutputStream out;

    public Operation(Socket s) throws IOException {
        this.in = new BufferedReader(new InputStreamReader(s.getInputStream()));
        this.out = new DataOutputStream(s.getOutputStream());
    }

    public void getUserName(){
        String[] a;
        System.out.println("Inserisci un Username:");
        do {
            String username = "CONNECT" + this.getStringByUser();
            this.sendStringServer(username);
            String answer = this.getStringServer();
            a = answer.split(" ");
            this.errorUsername(a);
        } while (!(a[0].equals("JOIN")));
    }

    public void errorUsername(String[] a){
        if (a[0].equals("KO")) {
            System.out.println("Errore, " + a[1]);
        } else {
            System.out.println("ti sei collegato al server");
        }
    }

    public void changeUserName(){
        String[] a;
        System.out.println("Inserisci il nuovo Username:");
        do {
            String username = "CHANGE" + this.getStringByUser();
            this.sendStringServer(username);
            String answer = this.getStringServer();
            a = answer.split(" ");
            this.errorUsername(a);
        } while (!(a[0].equals("JOIN")));
    }

    public void showUsers(){
        String username = "USERS";
        this.sendStringServer(username);
        String answer = this.getStringServer();
        if (answer.equals(null)) {
            System.out.println("Utenti connessi:");
            String[] a = answer.split(" ");
            for (int i = 1; i < a.length; i++) {
                System.out.println("-" + a[i]);
            }
        } else {
            System.out.println("nessun utente connesso");
        }
    }

    public void sendPrivateMes(){
        System.out.println("Inserisci il destinatario");
        String dest = this.getStringByUser();
        System.out.println("-----------------------------------");
        System.out.println(dest + ":" );
        String mes = this.getStringByUser();
        String destMes = "PRIVATE " + dest + " " + mes;
        this.sendStringServer(destMes);
        
    }

    public void sendGlobalMes(){
        
    }

    public void disconnection(){
        
    }

    public String getStringByUser(){
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        return input;
    }

    public void sendStringServer(String s){
        try {
            out.writeBytes(s + "\n");
        } catch (Exception e) {
            System.out.println("Errore");
        }
    }

    public String getStringServer(){
        String input = "";
        try {
            input = in.readLine();
        } catch (Exception e) {
            System.out.println("Errore");
        }
        return input;
    }

    
}
