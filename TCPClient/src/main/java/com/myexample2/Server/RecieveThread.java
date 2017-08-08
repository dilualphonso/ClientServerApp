package com.myexample2.Server;

import java.io.*;
import java.net.*;

class RecieveThread implements Runnable {
    Socket sock = null;
    BufferedReader recieve = null;

    public RecieveThread(Socket sock) {
        this.sock = sock;
    }

    public void run() {
        try {
            recieve = new BufferedReader(new InputStreamReader(this.sock.getInputStream()));
            String msgRecieved = null;
            while ((msgRecieved = recieve.readLine()) != null) {
                System.out.println("From Server: " + msgRecieved);

            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}