package GUI;

import javafx.application.Platform;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
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
    private ImageView yourCard,leftPlayerCard,rightPlayerCard,topPlayerCard;
    private exposePrompt exposeprompt;
    private boolean exposeSection = true;
    private Text playPrompt;
    
    public TrickPane(){
        Rectangle r = new Rectangle(250,250);
        r.setFill(Color.FORESTGREEN);
        getChildren().add(r);
        
        //SETS UP CARD BACKS
        yourCard = new ImageView(cardBack);
        leftPlayerCard = new ImageView(cardBack);
        rightPlayerCard = new ImageView(cardBack);
        topPlayerCard = new ImageView(cardBack);
        
        //SETS FIT SIZE OFF CARDBACK IMAGES
        yourCard.setFitHeight(cardHeight);
        yourCard.setFitWidth(cardWidth);
        leftPlayerCard.setFitHeight(cardHeight);
        leftPlayerCard.setFitWidth(cardWidth);
        rightPlayerCard.setFitHeight(cardHeight);
        rightPlayerCard.setFitWidth(cardWidth);
        topPlayerCard.setFitHeight(cardHeight);
        topPlayerCard.setFitWidth(cardWidth);
        
        //SETS THE POSITIONS OF EACH CARD
        yourCard.setX(105);
        yourCard.setY(175);
        leftPlayerCard.setX(30);
        leftPlayerCard.setY(100);
        rightPlayerCard.setX(180);
        rightPlayerCard.setY(100);
        topPlayerCard.setX(105);
        topPlayerCard.setY(25);
  
        playPrompt = new Text("Not Your Turn");
        playPrompt.setFill(Color.GOLDENROD);
        gameplayPrompt  gpp = new gameplayPrompt(playPrompt);
        gpp.setTranslateY(230);


        
        
        getChildren().addAll(yourCard,leftPlayerCard,rightPlayerCard,topPlayerCard,gpp);
        
        exposeprompt = new exposePrompt();
        
        exposePrompt();
        
        
    }
    public void leftcardPlayed(int i){
        String card = "Images/" + i + ".png";
        leftPlayerCard.setImage(new Image(card));
        
    }
     public void rightcardPlayed(int i){
        String card = "Images/" + i + ".png";
        rightPlayerCard.setImage(new Image(card));
        
    }
    public void topcardPlayed(int i){
        String card = "Images/" + i + ".png";
        topPlayerCard.setImage(new Image(card));
        
    }
    public void yourcardPlayed(int i){
        String card = "Images/" + i + ".png";
        yourCard.setImage(new Image(card));
    }
    public void setDefaultCardBack() throws InterruptedException {
        Thread.sleep(2000);
         //ONLY CALLED BY TASK
        //SO MUST HAPPEN ON APPLICATION THREAD
        Platform.runLater(new Runnable() {
            @Override
                public void run() {
                topPlayerCard.setImage(cardBack);
                yourCard.setImage(cardBack);
                leftPlayerCard.setImage(cardBack);
                rightPlayerCard.setImage(cardBack);
                }});
    }
    
    public void updatePlayPrompt(String message){
        playPrompt.setText(message);
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
    public void setExposeSection(){
        if(exposeSection == true){
            exposeSection = false;
        }
        else if(exposeSection == false){
            exposeSection = true;
        }
    }


    
    
    private static class gameplayPrompt extends StackPane {
        private Text prompt;
        public gameplayPrompt(Text prompt){
            this.prompt = prompt;
            Rectangle base = new Rectangle(250, 20);
            base.setArcHeight(25);
            base.setArcWidth(25);
            
            
            getChildren().addAll(base,prompt);
        }
        public void setText(String prompt){
            this.prompt.setText(prompt);
        }
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
