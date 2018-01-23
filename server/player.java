package server;
import java.net.*;
import java.io.*;
import java.util.*;
/**
 *
 * @author brean
 */

class Player implements Runnable{

    Socket s;
    DataInputStream dis;
    DataOutputStream dos;
    int playerId;
    ArrayList<Integer> cardsWon;
    boolean Turn = false;
    Trick trick;
    
    public Player(Socket s, DataInputStream dis, DataOutputStream dos, int playerId){
    this.s = s;
    this.dis = dis;
    this.dos = dos;
    this.playerId = playerId;
}
    @Override
    public void run() {
        while(true){
            try{
            int card = dis.readInt();
        
                trick.playCard(playerId,card);
               
            }
            catch(IOException ex){
                System.err.print(ex);    
                }
            if(Turn == true){
                try{
                dos.writeInt(52);
                }
                catch(IOException ex){
                    System.err.print(ex);
                }
            }
        }
    }
    public void CreateTrick(Trick trick){
        this.trick = trick;
    }
    
}
