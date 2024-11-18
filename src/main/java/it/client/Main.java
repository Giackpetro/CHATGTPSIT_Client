package it.client;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class Main {
    public static void main(String[] args) throws UnknownHostException, IOException {
        int port = 3000;
        Socket s = new Socket("localhost", port);
        System.out.println("Client connesso al server");
        Menu m = new Menu();
        Operation o = new Operation(s);
    }
}