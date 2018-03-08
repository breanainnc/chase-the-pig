package ctpserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

/**
 *
 * @author brean
 */
public class SocketServer {
       int portnumber = 8888;
       int clientNumber = 0;
       ServerSocket server = null;
       
       //BEGIN RUNNING THE SERVER
       public void runServer() {
        try{
            server = new ServerSocket(portnumber);
        }
        catch(IOException ex){
            
        }
        
        //TELLS USER SERVER HAS STARTED AND GAME OBJECT IS CREATED
        System.out.println("Chase the pig server has started");
        System.out.println("A Game had been created"+ "\n");
        Game game = new Game();
        
        //ENDLESS LOOP
        while(true){
          try{
              
            //WAITS FOR CLIENTS AND INCREMENTS COUNT
            Socket socket = server.accept();
            clientNumber++;
         
            
            //REVEALS DETAILS ABOUT THE USER
            System.out.println("Starting thread for Player " + clientNumber +" " + new Date() );
            InetAddress inetAddress = socket.getInetAddress();
            System.out.println("clients host name is " + inetAddress.getHostName());
            System.out.println("clients ip address is " + inetAddress.getHostName()+ "\n");
            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            
            
            //CREATES PLAYER THREAD AND PLACES THEM INTO GAME
            playerThread p = new playerThread(socket,game);
            game.initialisePlayer(p);
             
            
            //CREATES A NEW GAME ONCE FOUR PLAYERS HAVE JOINED
            if(clientNumber % 4 == 0){
                game = new Game();
                System.out.println("A Game had been created"+ "\n");
            }
            }
           catch(IOException ex){
            
            }
        }
       }
       
}
