package Logic;

import GUI.SidePlayer;
import GUI.TopPlayer;
import GUI.TrickPane;
import javafx.application.Platform;

/**
 *
 * @author brean
 */
public class playerManager {
    private SidePlayer left,right;
    private TopPlayer top;
    private TrickPane trickPane;
    int LEFT,RIGHT,TOP;
    
    public playerManager(SidePlayer left,SidePlayer right,TopPlayer top, TrickPane trickPane){
        this.left = left;
        this.right = right;
        this.top = top;
        this.trickPane = trickPane;
    }
    
    public void setPlayers(int pid){
        if(pid == 1){
            LEFT = 2;            
            TOP = 3;
            RIGHT = 4;
        }
        if(pid == 2){
            LEFT = 3;           
            TOP = 4;
            RIGHT = 1;
        }
        if(pid == 3){
            LEFT = 4;            
            TOP = 1;
            RIGHT = 2;
        }
        if(pid == 4){
            LEFT = 1;            
            TOP = 2;
            RIGHT = 3;
        }
        left.setText(LEFT);
        right.setText(RIGHT);
        top.setText(TOP);
    }
    public void playACard(int pid, int card){
        if(pid == LEFT){
            trickPane.leftcardPlayed(card);
        }
        else if(pid == RIGHT){
            trickPane.rightcardPlayed(card);
        }
        else if(pid == TOP){
            trickPane.topcardPlayed(card);
        }
        else{
            trickPane.yourcardPlayed(card);
        }
    }
    public void addExCard(int pid, int card) throws InterruptedException{
        if(pid == LEFT){
            left.addCard(card);
            
        }
        else if(pid == RIGHT){
            right.addCard(card);
           
        }
        else if(pid == TOP){
            top.addCard(card);
            
        }
        
    }

    public void removeCards() {
        left.removeCards();
        top.removeCards();
        right.removeCards();
    }

    }
   

