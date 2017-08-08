package com.myexample2.Server;

import java.io.*;
import java.net.*;
import java.util.ArrayDeque;
import java.util.Queue;
import java.lang.*;

public class TCPServer {

    public static int waitQueueSize = 3;
    public static RecieveFromClientThread t[] = new RecieveFromClientThread[10];
    public static PrintStream outstream;
    public static Queue<Socket> queue = new ArrayDeque<Socket>();
    public static PrintStream os;
    public static Socket clientSocket = null;


    public static void main(String argv[]) throws Exception {
        String clientSentence;
        String capitalizedSentence;


        ServerSocket welcomeSocket = new ServerSocket();

        InetSocketAddress endPoint = new InetSocketAddress("localhost", 6789);

        System.out.println("Server Started and listening to the port 6789");
        welcomeSocket.bind(endPoint, waitQueueSize);

        while (true) {


            try {
                clientSocket = welcomeSocket.accept();
                System.out.println("Recieved connection from "
                        + clientSocket.getInetAddress() + " on port "
                        + clientSocket.getPort());
                int i = 0;

                for (i = 0; i < waitQueueSize; i++)


                {
                    if (t[i] == null)

                    {
                        (t[i] = new RecieveFromClientThread(clientSocket, t)).start();
                        break;
                    }
                }


                if (i == waitQueueSize) {
                    outstream = new PrintStream(clientSocket.getOutputStream());
                    outstream
                            .println("Request is added to the Queue. Please Wait");

                    queue.add(clientSocket);


                }

            } catch (IOException e) {
                System.out.println(e);
            }
        }

    }

}
