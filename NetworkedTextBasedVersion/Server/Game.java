package server;

//import java.net.*;
//import java.io.*;
/**
 *
 * @author brean
 */
   class Game {
    Deck deck;
    Trick trick;
    Score score;
    String exposed;
    int expose = 0;
    int playercount = 0;
    int readyToDeal = 0;
    Player[] players;
    
    public Game(){
        this.deck = new Deck();
        this.score = new Score();
        this.trick = new Trick(score);
        this.players = new Player[4];
        this.exposed = "";
    }
    
    //WHEN THE FOUR PLAYERS ANNOUNCE THEY ARE READY THIS METHOD DEALS
    public void readyToDeal(){
        readyToDeal++;
        if(readyToDeal == 4){
            deal();
            readyToDeal = 0;
        }
        
    }
    
    //WHEN A CARD IS PLAYED
    public void playCard(int p, int card){
        if(trick.emptyTrick() == true){
            for(int i = 0; i < 4; i++){
                players[i].Tellplayer("FIRSTCARD" + card);
            }
         }
        else{
            for(int i = 0; i < 4; i++){
                players[i].Tellplayer("CARD" + card);
           }
        }
        trick.addCard(card,p);
       
        
        if(trick.endtrick() == true){
            int winner = trick.winner();
            for(int i = 0; i < 4; i++){
                players[i].Tellplayer("ENDTRICK" + winner);
            }
            
            //CHECKS IF ROUND IS OVER
            if(trick.roundOver() != true)
                players[winner].Tellplayer("YOURTURN");
            else{
                String finalScore = score.calculateScore();
                for(int i = 0; i < 4; i++){
                players[i].Tellplayer("ENDROUND" + winner);
                players[i].Tellplayer(finalScore);
                }
            }
                
        }
        else{
            int nextPlayer = p + 1;
            if (nextPlayer > 3){
                nextPlayer = 0;
            }
            players[nextPlayer].Tellplayer("YOURTURN");
        }
        
    }
    
    
    //EXPOSE CARDS TO PLAYERS AND TELLS SCORE OBJECT
    public void expose(int pid, int card){
        expose++;
        System.out.println(expose);
        if(card == 0){
            exposed += "000";
        }
        else{
        score.addExCard(card);
        exposed += card;
        exposed += (pid+1);
        }
        if(expose == 4){
            System.out.println(exposed);
            for(int i = 0; i < 4; i++){
                players[i].Tellplayer(exposed);
            }
        }
    }
    //PLACES PLAYERS INTO ARRAY AND TELLS EACH PLAYER THEIR ID
    public void initialisePlayer(Player p){
        p.Id(playercount);
        players[playercount] = p;
        playercount++;
    }
    
    //DEALS THE CARDS TO THE PLAYERS
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
        players[0].Tellplayer(hand1);
        players[1].Tellplayer(hand2);
        players[2].Tellplayer(hand3);
        players[3].Tellplayer(hand4);
    }
}
