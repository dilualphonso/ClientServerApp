package com.myexample2.Server;

import java.io.*;
import java.net.*;

class SendThread implements Runnable {
    Socket sock = null;
    PrintWriter print = null;
    BufferedReader brinput = null;

    public SendThread(Socket sock) {
        this.sock = sock;
    }

    public void run() {
        try {
            if (sock.isConnected()) {
                System.out.println("Client connected to " + sock.getInetAddress() + " on port " + sock.getPort());
                this.print = new PrintWriter(sock.getOutputStream(), true);
                while (true) {
                    System.out.println("Type your message to send to server..type 'EXIT' to exit");
                    brinput = new BufferedReader(new InputStreamReader(System.in));
                    String msgtoServerString = null;
                    msgtoServerString = brinput.readLine();
                    this.print.println(msgtoServerString);
                    this.print.flush();

                    if (msgtoServerString.equals("EXIT"))
                        break;
                }
                sock.close();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
