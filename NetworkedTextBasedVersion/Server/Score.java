package server;

import java.util.ArrayList;

/**
 *
 * @author brean
 */
class Score {
    ArrayList<Integer> exposedCards;
    int[] playerScores;
    ArrayList<Integer> player1Cards;
    ArrayList<Integer> player2Cards;
    ArrayList<Integer> player3Cards;
    ArrayList<Integer> player4Cards;
    
    public Score(){
        this.exposedCards = new ArrayList<>();
        this.playerScores = new int[4];
        for(int i = 0; i < 4; i++){
            playerScores[i] = 0;
        }
        this.player1Cards = new ArrayList<>();
        this.player2Cards = new ArrayList<>();
        this.player3Cards = new ArrayList<>();
        this.player4Cards = new ArrayList<>();
    }
    public void addExCard(int card){
        exposedCards.add(card);
    }
    public void addWonCards(int pid, ArrayList<Integer> cards){
        if(pid == 0){
            for(int i = 0; i < 4; i++){
                player1Cards.add(cards.get(i));
            }
        }
        else if(pid == 1){
            for(int i = 0; i < 4; i++){
                player2Cards.add(cards.get(i));
            }
        }
        else if(pid == 2){
            for(int i = 0; i < 4; i++){
                player3Cards.add(cards.get(i));
            }
        }
        else{
            for(int i = 0; i < 4; i++){
                player4Cards.add(cards.get(i));
            }
        }
    }
    public String calculateScore(){
        String score = "";
        score += calScore(player1Cards, 0);
        score += calScore(player2Cards, 1);
        score += calScore(player3Cards, 2);
        score += calScore(player4Cards, 3);
        return score;
    }
    public String calScore(ArrayList <Integer> cards, int p){
        int scr = 0;
        boolean multiply = false;
        int pig = -100;
        int goat = 100;
        int aceH = -50;
        if(exposedCards.contains(10)){
            pig = -200;
        }
        if(exposedCards.contains(35)){
            goat = 200;
        }
        if(exposedCards.contains(51)){
            aceH = -100;
        }
        System.out.println(">>>>>>>PLAYER<<<<<<<");
        for(int i = 0; i < cards.size(); i++){
            switch (cards.get(i)){
                    case 10: scr += pig;break;
                    case 21: multiply = true;break;
                    case 35: scr += goat;break;
                    case 42: scr += -10;break;
                    case 43: scr += -10;break;
                    case 44: scr += -10;break;
                    case 45: scr += -10;break;
                    case 46: scr += -10;break;
                    case 47: scr += -10;break;
                    case 48: scr += -20;break;
                    case 49: scr += -30;break;
                    case 50: scr += -40;break;
                    case 51: scr += aceH;break;
                    default:break;
            }
            System.out.println("Card: " + cards.get(i) + " Current score" + scr);
        }
        if(multiply == true){
            scr = scr * 2;
            if(exposedCards.contains(21)){
                scr = scr * 2;
            }
        }
        playerScores[p] += scr;
        System.out.println(playerScores[p]);
        String score = "Player " + p + ": " + playerScores[p] + "   ";
        return score;
        }
    }
