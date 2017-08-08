package com.myexample2.Server;

import java.io.*;
import java.net.*;

public class TCPClient {
    public static void main(String[] args) {
        try {
            Socket sock = new Socket("localhost", 2222);
            SendThread sendThread = new SendThread(sock);
            Thread thread = new Thread(sendThread);
            thread.start();
            RecieveThread recieveThread = new RecieveThread(sock);
            Thread thread2 = new Thread(recieveThread);
            thread2.start();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
