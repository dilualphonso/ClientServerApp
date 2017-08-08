package com.myexample2.Server;

import java.io.*;
import java.net.*;
import java.lang.*;


class RecieveFromClientThread extends Thread {
    Socket clientSocket = null;
    BufferedReader brBufferedReader = null;
    RecieveFromClientThread t[];

    public RecieveFromClientThread(Socket clientSocket, RecieveFromClientThread[] t) {
        this.clientSocket = clientSocket;
        this.t = t;
    }

    public void run() {
        TCPServer obj = new TCPServer();

        try {
            brBufferedReader = new BufferedReader(new InputStreamReader(
                    this.clientSocket.getInputStream()));

            String messageString;
            while (true) {
                while ((messageString = brBufferedReader.readLine()) != null) {


                    if (messageString.equals("EXIT")) {

                        for (int i = 0; i < obj.waitQueueSize; i++) {
                            if (t[i] == this) {
                                t[i] = null;
                            }
                        }
                        break;

                    }

                    System.out.println("From Client: " + messageString);

                }

                if (obj.queue.isEmpty() == false) {
                    clientSocket = obj.queue.peek();
                    for (int i = 0; i < obj.waitQueueSize; i++) {
                        if (t[i] == null) {
                            obj.outstream
                                    .println("You are connection is made from queue");
                            obj.outstream
                                    .println("Type your message to send to server..type 'EXIT'");
                            (t[i] = new RecieveFromClientThread(clientSocket,
                                    obj.t)).start();

                            obj.outstream.close();


                            break;
                        }
                    }
                }


            }

        } catch (Exception ex) {
        }
    }
}