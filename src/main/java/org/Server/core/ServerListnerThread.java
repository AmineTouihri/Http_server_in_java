package org.Server.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerListnerThread extends Thread{
    private final Logger LOGGER =  LoggerFactory.getLogger(ServerListnerThread.class);
  private int port;
   private String webRoot;
   private HttpConnectionWorkerThread httpConnectionWorkerThread;
   private ServerSocket serverSocket;
    public ServerListnerThread(int port,String webRoot) throws IOException {
       this.port=port;
       this.webRoot=webRoot;

        serverSocket =new ServerSocket(port);
    }
    @Override
    public void run() {

        try {
            while (serverSocket.isBound() && !serverSocket.isClosed()){

                Socket socket= serverSocket.accept();
                httpConnectionWorkerThread=new HttpConnectionWorkerThread(socket);
                httpConnectionWorkerThread.start();
            }
//            serverSocket.close(); TODO handle the close methode later
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
