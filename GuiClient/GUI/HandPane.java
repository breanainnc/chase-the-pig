/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;



/**
 *
 * @author brean
 */
public class HandPane extends Pane{
       public HBox Cards;
       public Text PlyrNum;
       
    //CONSTRUCTOR FOR HANDPANE
    public HandPane(){
        setPrefSize(725,150);
        Rectangle background = new Rectangle(725,150);
        background.setFill(Color.MAROON);
        Cards = new HBox(15);
        Cards.setTranslateX(15);
        
        
        
        getChildren().addAll(background,Cards);
       
    }
    //RETURNS CARDITEM
    public CardItem getCardItem(int i){
        return (CardItem)Cards.getChildren().get(i);
         
    }
    //SETS PLAYER NUMBER
    public void setPlayerNumber(String name){
        PlyrNum = new Text(name);
        PlyrNum.setFont(new Font(20));
        PlyrNum.setTranslateY(140);
        PlyrNum.setTranslateX(15);
        getChildren().add(PlyrNum);
    }
    //ADDS CARD TO HANDPANE
    public void addCard(int i){
        Cards.getChildren().add(new CardItem(i));
        getChildren().add(Cards);
    }
    //RETURNS AMOUNT OF CARDS
    public int cardCount(){
        return Cards.getChildren().size();
    }
}
