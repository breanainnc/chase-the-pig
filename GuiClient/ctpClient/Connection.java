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
         outputToServer.println("READY");
         
         }
         catch(IOException ex){
             System.err.println(ex);
         }
     }
     public MessageSender createMessageSender(){
         MessageSender messageSender = new MessageSender(outputToServer);
         return messageSender;
     }
     
     //STARTS BACKGROUND THREAD FOR USER RECIVING DATA, DAEMON SET SO STOPS WHEN USER STOPS.
     public void startReceiving(Dealer d){
        MessageReceiver incomingMessageThread = new MessageReceiver(inputFromServer,d);
        Thread MR = new Thread(incomingMessageThread);
        MR.setDaemon(true);
        MR.start();

     }
}
