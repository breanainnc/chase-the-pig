package GUI;

import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

/**
 *
 * @author brean
 */
public class TrickPane extends Pane{
    final private int cardWidth = 40, cardHeight = 50;
    final private Image cardBack = new Image("Images/blueBackground.png");
    private ImageView yourCard,leftPlayerCard,rightPlayerCard,frontPlayerCard;
    private exposePrompt exposeprompt;
    private boolean exposeSection = true;
    
    public TrickPane(){
        Rectangle r = new Rectangle(250,250);
        r.setFill(Color.FORESTGREEN);
        getChildren().add(r);
        
        //SETS UP CARD BACKS
        yourCard = new ImageView(cardBack);
        leftPlayerCard = new ImageView(cardBack);
        rightPlayerCard = new ImageView(cardBack);
        frontPlayerCard = new ImageView(cardBack);
        
        //SETS FIT SIZE OFF CARDBACK IMAGES
        yourCard.setFitHeight(cardHeight);
        yourCard.setFitWidth(cardWidth);
        leftPlayerCard.setFitHeight(cardHeight);
        leftPlayerCard.setFitWidth(cardWidth);
        rightPlayerCard.setFitHeight(cardHeight);
        rightPlayerCard.setFitWidth(cardWidth);
        frontPlayerCard.setFitHeight(cardHeight);
        frontPlayerCard.setFitWidth(cardWidth);
        
        //SETS THE POSITIONS OF EACH CARD
        yourCard.setX(105);
        yourCard.setY(175);
        leftPlayerCard.setX(30);
        leftPlayerCard.setY(100);
        rightPlayerCard.setX(180);
        rightPlayerCard.setY(100);
        frontPlayerCard.setX(105);
        frontPlayerCard.setY(25);
        
        getChildren().addAll(yourCard,leftPlayerCard,rightPlayerCard,frontPlayerCard);
        
        exposeprompt = new exposePrompt();
        
        exposePrompt();
        
        
    }
    public void cardPlayed(int i){
        String card = "Images/" + i + ".png";
        yourCard.setImage(new Image(card));
        
    }
    public void exposePrompt(){
        
        if (!getChildren().contains(exposeprompt)){
            
            getChildren().addAll(exposeprompt);
        }
        else{
            getChildren().remove(exposeprompt);
        }
}
    public boolean getExposeSection(){
        return exposeSection;
    }

        //STATIC CLASS FOR BACKGROUND DESIGN FOR MAIN MENU
    private static class exposePrompt extends Parent {
        public exposePrompt() {
            Rectangle exposePrompt = new Rectangle(250,250);
            exposePrompt.setFill(Color.FORESTGREEN);  
            Text line1 = new Text("Would you like to expose any of these cards?\n"
                    + "Press enter to expose and spaceBar to hide");
            line1.setY(125);
            line1.setX(5);

            ImageView pig = new ImageView(new Image("Images/11.png"));
            ImageView goat = new ImageView(new Image("Images/36.png"));   
            goat.setX(175);
            ImageView aceH = new ImageView(new Image("Images/26.png"));
            aceH.setY(155);
            ImageView tenC = new ImageView(new Image("Images/48.png"));
            tenC.setY(155);
            tenC.setX(175);
            

           
            getChildren().addAll(exposePrompt,line1,pig,goat,aceH,tenC);
    }
 }
}
