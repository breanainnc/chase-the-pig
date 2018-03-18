package Logic;

import GUI.HandPane;
import java.util.ArrayList;

/**
 *
 * @author brean
 */
public class GamePlayLogic {
    private HandPane handpane;
    private int pid;
    private boolean resetExpose = false;
    private ArrayList<Integer> Hand;
    private boolean Turn = false;
    private int firstCardOfTrick = -1;
    final private int ACEOFHEARTS = 26, ACEOFSPADES = 13, ACEOFDIAMONDS = 39, 
            ACEOFCLUBS = 52;
    
    public GamePlayLogic(HandPane handpane){
        this.handpane = handpane;
    }
    public void setFirstCardOfTrick(int card){
        this.firstCardOfTrick = card;
        System.out.println("Gameplay logic thinks first card is " +firstCardOfTrick); 
    }
    public void setResetExpose(){
        if(resetExpose == true) resetExpose = false;
        else resetExpose = true;
    }
    public boolean resetExpose(){
        return resetExpose;
    }
    
    public boolean validMove(int cardSelection){

        if(firstCardOfTrick != -1){
            return checkPlayerHasSameSuit(cardSelection);
        }
        else 
        System.out.println("firstCardOf trick has never been changed");            
            return true;
    }
    
    private boolean checkPlayerHasSameSuit(int cardSelection) {
        
        //DETERMINE THE RANGE OF CARDS
        int suitRangeLow = 0,suitRangeHigh;
        if(firstCardOfTrick <= ACEOFSPADES){
            suitRangeHigh = ACEOFSPADES;
        }
        else if(firstCardOfTrick <= ACEOFHEARTS){
            suitRangeLow = ACEOFSPADES + 1;
            suitRangeHigh = ACEOFHEARTS;
        }
        else if(firstCardOfTrick <= ACEOFDIAMONDS){
            suitRangeLow = ACEOFHEARTS + 1;
            suitRangeHigh = ACEOFDIAMONDS;
        }
        else{
            suitRangeLow = ACEOFDIAMONDS + 1;
            suitRangeHigh = ACEOFCLUBS;
        }
        System.out.println("Range high:" + suitRangeHigh + " Range Low: "+ suitRangeLow);
        //CHECK IF ANY CARD IS IN RANGE
        for(int i = 0; i < handpane.cardCount(); i++){
            
            int cardInt = handpane.getCardItem(i).getCardNumber();
            System.out.print(cardInt + " Card in hand ");
            
            if(cardInt >= suitRangeLow && cardInt <= suitRangeHigh){
                if(cardSelection >= suitRangeLow && cardSelection <= suitRangeHigh){
                    return true;
                }
                System.out.println("is in range");
                return false;
            }
        }
        return true;
    }
    
    public void getHand(){
        Hand = handpane.getCardList();
    }
    public void setTurn(){
        Turn = true;
    }
    public void TurnOver(){
        Turn = false;
    }
    public boolean isTurn(){
        return Turn;
    }
    public void setTrickWinner(int winner){
        if(winner == pid){
            Turn = true;
        }
    }
     public int getId() {
        return pid; 
    }
    public void setId(int pid) {
        this.pid = pid; 
    }


}
