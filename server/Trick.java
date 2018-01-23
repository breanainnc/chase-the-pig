package server;
import java.util.*;
import java.io.*;
/**
 *
 * @author brean
 */
public class Trick {
    
    private ArrayList<Integer> CardsPlayed;
    Player[] players;
    
    Trick(Player[] players){
        this.players = players;
    }
    public void playCard(int playerId, int card){
        int cardandId = playerId * 100 + card;
        CardsPlayed.add(cardandId);
        for(int i = 0; i < 4; i++){
            try{
            players[i].dos.write(card);
            }
            catch(IOException ex){
                System.err.print(ex);
            }
        }
        if(CardsPlayed.size() == 4){
            decideWin();
        }
        playerId++;
        if(playerId == 4){
            playerId = 0;
        }
        players[playerId].Turn = true;
    }
    public void decideWin(){
        
    }
}
