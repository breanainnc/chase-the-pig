
package chase.the.pig;

/**
 *
 * @author breanainn
 */

import java.util.*;

public class ChaseThePig {
boolean exPig, exGoat, exMulti, exAce = false;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Player [] players = new Player[4];
        for(int i = 0; i < 4; i++){
            players[i] = new Player();
        }
        
        game(players);   
        
       
    }
    
    public static void game(Player[] players){
        
        while(loser(players) == false){
        deal(players);
        round(players);
        score(players);
        }
        printScores(players);
        
    }
    public static void score(Player[] players){
        int[] score = new int[4];
        boolean multi = false;
        for(int i = 0; i < 4; i++){
            score[i] = 0;
        }
        // SCORES HEARTS
        for(int i = 0; i < 4; i++){
            for(int size = 0; size < players[i].WonCards.size(); size++){
                if(players[i].WonCards.get(size) == 0){
                    score[i] = score[i] - 50;
                }
                else if(players[i].WonCards.get(size) >= 4 && players[i].WonCards.get(size) <= 9){
                    score[i] = score[i] - 10;
                }
                else if(players[i].WonCards.get(size) == 10){
                    score[i] = score[i] - 20;
                }
                else if(players[i].WonCards.get(size) == 11){
                    score[i] = score[i] - 30;
                }
                else if(players[i].WonCards.get(size) == 12){
                    score[i] = score[i] - 40;
                }
                
            }
            if(score[i] == -200){
                score[i] = 200;
            }
        }
        // SCORES PIG AND GOAT
        for(int j = 0; j < 4; j++){
            for(int size = 0; size < players[j].WonCards.size(); size++){
                if(players[j].WonCards.get(size) == 24){
                    score[j] = score[j] + 100;
                }
                else if(players[j].WonCards.get(size) == 50){
                    score[j] = score[j] + 100;
                }
            }
        }
        
        // SCORES 10 OF CLUBS
        for(int d = 0; d < 4; d++){
            for(int size = 0; size < players[d].WonCards.size(); size++){
                if(players[d].WonCards.get(size) == 35){
                    if(score[d] == 0){
                        score[d] = score[d] + 50;
                    }
                    else{
                        score[d] = score[d] * 2;
                    }
                }
            }
        }
        for(int f = 0; f <4;f++){
            players[f].score = score[f];
        }
        
        
        
    }
    public static void expose(Player[] players){
        
    }
    public static void round(Player[] players){
        // decides whos turn it is
        int firstTurn = firstPlay(players);
        int turn = firstTurn;
        String suite = null;
        
        // lets players player their hand
        while(players[0].hand.isEmpty() == false){
            for(int i = 0; i < 4; i++){ 
                System.out.println("PLAYER " + (turn + 1));
                play(players[turn], turn, suite);
                if(i == 0){
                    suite = suite(players[firstTurn].trick);
                }
                turn++;
                if(turn > 3)
                    turn = 0;
                }
            //checks who won the trick
            turn = win(players,firstTurn);
            firstTurn = turn;
            //adds cards to to won cards list
            for(int j = 0; j < 4; j++){
                players[turn].WonCards.add(players[j].trick);
            }
            System.out.println("Winner is player " + (turn+ 1));
        }
    }
    public static int win(Player[] players, int firstTurn){
        String suite = suite(players[firstTurn].trick);
        int winner = firstTurn;
        int winningCard = players[firstTurn].trick;
        
            for(int i = 0; i < 4; i++){
                if(suite(players[i].trick).contains(suite) && players[i].trick > winningCard ){
                    winner = i;
                }
            }
            return winner;
    }
    public static int firstPlay(Player[] players){
        //locates the two of spades
        for(int plyer = 0; plyer < 4; plyer++){
            for(int list = 0; list < 13; list++){
              if(players[plyer].hand.get(list)== 39){
              return plyer;
              }
            }
        }
        return 0;
    }
    public static void play(Player player, int turn, String suite){
        Scanner input = new Scanner(System.in);
        
        for(int i = 0; i < player.hand.size(); i++){
            System.out.print("Choice " + i + " :");
            card(player.hand.get(i));
        }
        
        System.out.println("Cards you can play:");
        if(suite == null){
             System.out.println("Any");
        }
        else if(suite.matches("Hearts")){
            for(int i = 0; i < player.hand.size(); i++){
                
                if(player.hand.get(i) >= 0 && player.hand.get(i) <= 12){
                    System.out.print("Choice " + i + " :");
                    card(player.hand.get(i));
                }
            }
        }
        else if(suite.matches("Diamonds")){
            for(int i = 0; i < player.hand.size(); i++){
                
                if(player.hand.get(i) >= 13 && player.hand.get(i) <= 25){
                    System.out.print("Choice " + i + " :");
                    card(player.hand.get(i));
                }
            }    
        }
        else if(suite.matches("Clubs")){
            for(int i = 0; i < player.hand.size(); i++){
                
                if(player.hand.get(i) >= 26 && player.hand.get(i) <= 38){
                    System.out.print("Choice " + i + " :");
                    card(player.hand.get(i));
                }
            }
        }
        else if(suite.matches("Spades")){
            for(int i = 0; i < player.hand.size(); i++){
                
                if(player.hand.get(i) > 38 ){
                    System.out.print("Choice " + i + " :");
                    card(player.hand.get(i));
                }
            }
        }
        System.out.println("Choose your card: ");
        int cardChoice = input.nextInt();
        player.trick = player.hand.get(cardChoice);
        player.hand.remove(cardChoice);
    }
    public static void deal(Player[] players){
        //shuffle cards
         int[] deck = new int[52];
        for(int i = 0; i < 52; i++){
            deck[i] = i;
        }
        for(int j = 0; j < 50; j++){
            int random = (int)(52 * Math.random());
            int temp = deck[j];
            deck[j] = deck[random];
            deck[random] = temp;
            
        }
        //deal cards
        for(int j = 0; j < 52;j++){
           if(j < 13){
               players[0].hand.add(deck[j]);
           }
           else if(j < 26){
               players[1].hand.add(deck[j]);
           }
           else if(j < 39){
               players[2].hand.add(deck[j]);
           }
           else {
               players[3].hand.add(deck[j]);
           }
        }
    }
    public static void card(int number){
        
        //CARD TYPE
        int type = (number + 1) % 13;
         
        if(type == 0){
            System.out.print("Ace of ");
        }
        else if(type >= 1 && type <= 9){
            System.out.print((type + 1) + " of ");
        }
        else if(type == 10){
            System.out.print("Jack of ");
        }
        else if(type == 11){
            System.out.print("Queen of ");
        }
        else if(type == 12){
            System.out.print("King of ");
        }
        
        //CARD SUITE
        
        if(number >= 0 && number <= 12){
            System.out.println("Hearts");
        }
        else if(number >= 13 && number <= 25){
            System.out.println("Diamonds");
        }
        else if(number >= 26 && number <= 38){
            System.out.println("Clubs");
        }
        else{
            System.out.println("Spades");
        }
    }
    public static String suite(int number){
        String suite;
        if(number >= 0 && number <= 12){
            suite = "Hearts";
        }
        else if(number >= 13 && number <= 25){
            suite = "Diamonds";
        }
        else if(number >= 26 && number <= 38){
            suite = "Clubs";
        }
        else{
            suite = "Spades";
        }
        return suite;
    }
    public static boolean loser(Player[] players){
        for(int i = 0; i < 4; i++){
            if(players[i].score > 1000)
                return true;
        }
        return false;
    }
   
    public static void printScores(Player[] players){
        System.out.println("END GAME_-_-_-_-_-_-_-_");
        for(int i = 0; i < 4; i++){
            System.out.println("Player Scores: " + (i + 1) + " " + players[i].score);
        }
    }
}
