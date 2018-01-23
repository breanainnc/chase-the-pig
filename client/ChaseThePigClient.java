
package client;
import java.net.*;
import java.io.*;
/*
author Breanainn
*/
public class ChaseThePigClient{
    public static void main(String[] args){
        
        try(Socket connect = new Socket("localhost",8888)){
         
            //creates the data streams
            System.out.println("you have connected successfully!");
            DataInputStream dis = new DataInputStream(connect.getInputStream());
            DataOutputStream dos = new DataOutputStream(connect.getOutputStream());
            
            //creates the play object with each reciveing an id
            int playerID = dis.readInt();
            Play play = new Play(playerID,dis,dos);
            play.start();
        }
        catch(IOException ex){
            System.err.println(ex);
        }
        
                

}
}
