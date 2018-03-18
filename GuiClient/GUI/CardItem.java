package GUI;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;


/**
 *
 * @author brean
 */
public class CardItem extends VBox{
        final private Image arrow = new Image("Images/arrow.png");
        final private Text exposePrompt = new Text("Expose?");
        final private int width = 40, height = 50;
        final private int PIG = 11, GOAT = 36, ACEH = 26, TENC = 48; 
        private ImageView pointer;
        private int CardInt;

        
    public CardItem(int i){
        CardInt = i;
        String pic = "Images/" + i + ".png";
        ImageView card = new ImageView(new Image(pic));
        card.setFitWidth(width);
        card.setFitHeight(height);
        
        pointer = new ImageView(arrow);
        DisplayArrow(false);
        
        getChildren().addAll(pointer,card);
        
        //CHECKS IF CARD IS A TYPE THAT CAN BE EXPOSED
        //THEN ADDS A PROMPT 
        if(i == PIG || i == GOAT || i == ACEH || i == TENC){
            getChildren().add(exposePrompt);
        }
    }
    
    //CALLED BY GAMEPLAY TO TELL THE USER IF THEY HAVE EXPOSED OR HIDDEN THIER 
    //CARD
    public void exposeQuestion(int cardNum){
        String text = exposePrompt.getText();
        
        //IF STATEMENT SO THE USER CANNOT CHANGE THE TEXT TWICE
        if (text.contains("?")){
            if(cardNum == 0 ){
                exposePrompt.setText("Hidden");
            }
            else{
                exposePrompt.setText("Exposed");
            }
        }
    }
  
    
    public void DisplayArrow(boolean c){
        pointer.setVisible(c);
    }
    public int getCardNumber(){
        return CardInt;
    }
}
