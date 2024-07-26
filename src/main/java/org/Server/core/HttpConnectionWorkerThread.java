package org.Server.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class HttpConnectionWorkerThread extends Thread{
    private final Logger LOGGER =  LoggerFactory.getLogger(HttpConnectionWorkerThread.class);
    private Socket socket;
    public HttpConnectionWorkerThread(Socket socket){
        this.socket=socket;
    }
    @Override
    public void run() {

        try {

            InputStream inputStream =socket.getInputStream();
            OutputStream outputStream = socket.getOutputStream();
            int _byte;
            while ((_byte=inputStream.read())>=0){
                System.out.print((char)_byte);
            }
            LOGGER.info("thread is running on port :"+socket.getPort());
            LOGGER.info(" conection accepted  :"+socket.getInetAddress());
            String html="<h1>hello java http server  </h1>";
            final String CRLF="\n\r";
            String response="HTTP/1.1 200 OK"+ CRLF +
                    "Content-Length:"+html.getBytes().length+CRLF +
                    CRLF +
                    html +
                    CRLF + CRLF ;
            outputStream.write(response.getBytes());

            inputStream.close();
            outputStream.close();
            socket.close();
        }catch (Exception e){

        }
    }
}
