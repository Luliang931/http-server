package com.lu.http.server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class HttpServer {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(9000);

        while (true){
            Socket socket = serverSocket.accept();
            System.out.println("a socket created.");

            DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(dataInputStream));

            StringBuffer requestBuffer = new StringBuffer();
            String line = "";
            // readline -> line end '\n'
            while (!(line = bufferedReader.readLine()).isEmpty()){
                requestBuffer.append(line);
            }

            String request = requestBuffer.toString();
            System.out.println(request);

            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            bufferedWriter.write("HTTP/1.1 200 ok\n\nHello World!\n");
            bufferedWriter.flush();

            socket.close();
        }
    }
}
