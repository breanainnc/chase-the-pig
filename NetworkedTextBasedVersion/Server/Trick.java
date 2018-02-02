package server;
import java.util.*;
import java.io.*;
/**
 *
 * @author brean
 */
public class Trick {
    ArrayList<Integer> playedCards;
    int currentWinner;
    int bestCard;
    
    public Trick(){
        this.playedCards = new ArrayList<>();
    }
    
    public void addCard(int card, int pid){
        playedCards.add(card);
        if(playedCards == null){
            currentWinner = pid;
            bestCard = card;
        }
        else{
            compareCards(card,pid);
        }
        
    }
    public void compareCards(int card, int pid){
        if(bestCard < 13){
            if(card < 13 && card > bestCard){
                bestCard = card;
                currentWinner = pid;
            }
        }
        else if(bestCard < 26){
            if(card < 26 && card > bestCard){
                bestCard = card;
                currentWinner = pid;
            }
        }
        else if(bestCard < 39){
            if(card < 39 && card > bestCard){
                bestCard = card;
                currentWinner = pid;
            }
        }
        else{
            if(card > bestCard){
                bestCard = card;
                currentWinner = pid;
            }
        }
    }

}
