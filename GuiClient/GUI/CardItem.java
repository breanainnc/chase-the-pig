/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;


/**
 *
 * @author brean
 */
public class CardItem extends VBox{
        final private Image arrow = new Image("Images/arrow.png");
        ImageView pointer;
        int CardInt;
        
    public CardItem(int i){
        CardInt = i;
        String pic = "Images/" + i + ".png";
        ImageView card = new ImageView(new Image(pic));
        card.setFitWidth(40);
        card.setFitHeight(50);
        
        pointer = new ImageView(arrow);
        DisplayArrow(false);
        
        getChildren().addAll(pointer,card);
    }
    
    public void DisplayArrow(boolean c){
        pointer.setVisible(c);
    }
}
