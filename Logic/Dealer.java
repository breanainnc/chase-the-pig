/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logic;
import GUI.HandPane;
/**
 *
 * @author brean
 */
public class Dealer {
    private HandPane PlayerHand;
    
    public Dealer(HandPane PlayerHand){
        this.PlayerHand = PlayerHand;
    }
    public void sendHand(String hand){
                    System.out.println(hand);
        int start = 0;
        int end = 2;
        for(int i = 0; i < 5; i++ ){
            int card = Integer.parseInt(hand.substring(start, end));

            PlayerHand.addCard(card);
            start = start + 2;
            end = end + 2;
        }
    }

    public void playerNumber(char number) {
        String playerNumber = "Player: " + number;
        PlayerHand.setPlayerNumber(playerNumber);
    }
}
