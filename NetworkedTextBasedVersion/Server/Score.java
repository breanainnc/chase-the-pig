package server;

import java.util.ArrayList;

/**
 *
 * @author brean
 */
class Score {
    ArrayList<Integer> exposedCards;
    int[] playerScores;
    
    public Score(){
        this.exposedCards = new ArrayList<>();
        this.playerScores = new int[4];
        for(int i = 0; i < 4; i++){
            playerScores[i] = 0;
        }
    }
    public void addExCard(int card){
        exposedCards.add(card);
    }
}
