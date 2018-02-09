/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author brean
 */
public class ChaseThePigServer {
    
    public static void main(String[] args) throws Exception{
        
        //set up ServerSocket
        ServerSocket listener = new ServerSocket(8888);
        System.out.println("Server is listening. . .");
    
        while(true){
            Game game = new Game();
            
            for(int i = 0; i < 4; i++){
                game.players[i] = game.new Player(listener.accept());
            }
            
            game.players[0].setNextPlayer(game.players[1]);
            game.players[1].setNextPlayer(game.players[2]);
            game.players[2].setNextPlayer(game.players[3]);
            game.players[3].setNextPlayer(game.players[0]);
            
            game.deal();
            
            for(int j = 0; j < 4; j++){
                game.players[j].start();
            }
            
        }
    }
}

class Game{
    
    Player currentPlayer;
    Player[] players = new Player[4];
    
    public void deal(){
        int[] deck = new int[52];
        for(int i = 0; i < 52; i++){
            deck[i] = i;
        }
        for(int j = 0; j < 52; j++){
            int random = (int)(Math.random() * 52);
            int temp = deck[j];
            deck[j] = deck[random];
            deck[random] = temp;
        }
        
        
        for(int c = 0; c < 52; c++ ){
            if(c < 13){
                players[0].hand.add(deck[c]);
            }
            else if(c < 26){
                players[1].hand.add(deck[c]);
            }
            else if(c < 39){
                players[2].hand.add(deck[c]);
            }
            else{
                players[3].hand.add(deck[c]);
            }
        }
    }
    
    
    
    class Player extends Thread{
        Player nextTurn;
        List<Integer> hand = new ArrayList();
        Socket socket;
        PrintWriter output;
        ObjectOutputStream handoutput;
        
        
        Player(Socket socket){
            this.socket = socket;
            try{
                output = new PrintWriter(socket.getOutputStream(), true);
                handoutput = new ObjectOutputStream(socket.getOutputStream());
            }
            catch(IOException ex){
                System.out.print("error");
            }
        }
        
        public void setNextPlayer(Player nextTurn){
            this.nextTurn = nextTurn;
        }
        
        public void run(){
            output.println("Welcome All players have now joined!");
            try {
                handoutput.writeObject(hand);
            } catch (IOException ex) {
                Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
}
}
    
