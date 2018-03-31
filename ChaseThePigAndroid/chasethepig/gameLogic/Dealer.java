package com.example.brean.chasethepig.gameLogic;

import com.example.brean.chasethepig.Handpane;
import com.example.brean.chasethepig.PlayerManager;

public class Dealer {
    private Handpane handpane;
    private PlayerManager playerManager;

    public Dealer(Handpane h,PlayerManager p) {
        this.handpane = h;
        this.playerManager = p;

    }

    public void receiveHand(String hand) {
        int start = 0;
        int end = 2;
        for (int i = 0; i < 13; i++) {
            int card = Integer.parseInt(hand.substring(start, end));

            handpane.addCard(card);
            start = start + 2;
            end = end + 2;
            /*
            if(card == 1){
                trickpane.updatePlayPrompt("Your Turn");
                gameplaylogic.setTurn();
            }
        }
        gameplaylogic.getHand();*/
        }
    }

    public void playerNumber(char number) {
        String playerNumber = "Player: " + number;
        handpane.setPlayerNumber(playerNumber);
        int n = (int)(number- '0');
        playerManager.setPlayers(n);
        //gameplaylogic.setId(n);
    }
}
