package GUI;
import Logic.Dealer;
import ctpclient.Connection;
import javafx.geometry.Pos;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author brean
 */
public class GamePlay extends Pane{
         HandPane hpane;
         Connection c;
         
    //SETS UP SCENE FOR GAME
    public GamePlay(){
        BorderPane b =  new BorderPane();
        b.setMinSize(600, 600);
        b.setMaxSize(600, 600);
        StackPane trickPane = new StackPane();
        trickPane.setAlignment(Pos.CENTER);
        hpane = new HandPane();
        

        c = new Connection();
        Dealer d = new Dealer(hpane);
        c.startReceiving(d);
        
        
        b.setBottom(hpane);
        b.setCenter(trickPane);
        getChildren().add(b);

    }
   
    //LEFT ARROW CONTROL
    public void controlLeft(int choice){
        hpane.getCardItem(choice).DisplayArrow(false);
        hpane.getCardItem(--choice).DisplayArrow(true);
    }
    //RIGHT ARROW CONTROL
    public void controlRight(int choice){
        hpane.getCardItem(choice).DisplayArrow(false);
        hpane.getCardItem(++choice).DisplayArrow(true);
    }
    //ENTER CONTROL
    public void controlEnter(int choice){
        
    }
    //CARD COUNT IN HAND
    public int cardCount(){
        return hpane.cardCount();
    }
    
}
