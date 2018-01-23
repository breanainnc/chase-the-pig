package server;

//import java.net.*;
import java.io.*;
/**
 *
 * @author brean
 */
   class Game {
    Player[] players;
    Thread[] playthread;
    Deck deck;
    Trick trick;
    
    public Game(Player[] players){
        this.players = players;
        this.deck = new Deck();
        this.playthread = new Thread[4];
        this.trick = new Trick(players);
        for(int i = 0; i < 4; i++){
           Thread player = new Thread(players[i]);
           playthread[i] = player;
           players[i].CreateTrick(trick);
        }
        
    }
    
    public void begin(){
        deal();
        
    }
    public void deal(){
        /*
        * This method splits the deck into four hands.
        * It then places them into String and sends them to
        * the clients where they will interpret them.
        * 
        */
        String hand1 = "";
        String hand2 = "";
        String hand3 = "";
        String hand4 = "";
        
        deck.shuffle();
        int[] cards = deck.getDeck();
        for(int i = 0; i < 52; i++){
            if(i < 13){
                if(cards[i] < 10){
                    hand1 += "0" + cards[i];
                }
                else
                hand1 += cards[i];
            }
            else if(i < 26){
                if(cards[i] < 10){
                    hand2 += "0" + cards[i];
                }
                else
                hand2 += cards[i];
            }
            else if(i < 39){
                if(cards[i] < 10){
                    hand3 += "0" + cards[i];
                }
                else
                hand3 += cards[i];
            }
            else{
                if(cards[i] < 10){
                    hand4 += "0" + cards[i];
                }
                else
                hand4 += cards[i];
            }
        }
        try{
        players[0].dos.writeUTF(hand1);
        players[1].dos.writeUTF(hand2);
        players[2].dos.writeUTF(hand3);
        players[3].dos.writeUTF(hand4);
        }
        catch(IOException ex){
            System.err.print(ex);
        }
    }
}
