package ctpclient;

import Logic.Dealer;
import java.io.*;
import java.net.*;

/**
 *
 * @author brean
 */
public class Connection {
    Socket socket;
    BufferedReader inputFromServer;
    PrintWriter outputToServer;
     public Connection(){
         try{
         socket = new Socket("localhost",8888);
         inputFromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
         outputToServer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()),true);
         outputToServer.println("hello");
         //startReceiving();
         }
         catch(IOException ex){
             System.err.println(ex);
         }
     }
     public void startReceiving(Dealer d){
         new Thread(new MessageReciever(inputFromServer,outputToServer,d)).start();

     }
}
