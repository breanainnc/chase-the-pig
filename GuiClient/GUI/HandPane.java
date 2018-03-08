package GUI;

import javafx.application.Platform;
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
        PlyrNum = new Text("NO SERVER AVAILABLE");                
        getChildren().addAll(background,Cards,PlyrNum);
       
    }
    
    
    //RETURNS CARDITEM
    public CardItem getCardItem(int i){
        return (CardItem)Cards.getChildren().get(i);
         
    }
    
    
    
    //SETS PLAYER NUMBER
    public void setPlayerNumber(String name){
        
        //ONLY CALLED BY TASK
        //SO MUST HAPPEN ON APPLICATION THREAD
        Platform.runLater(new Runnable() {
            @Override
                public void run() {
                    PlyrNum.setText(name);
                    PlyrNum.setFont(new Font(20));
                    PlyrNum.setTranslateY(140);
                    PlyrNum.setTranslateX(15);
                    
                }
        });
    }
    
 
    
    //ADDS CARD TO HANDPANE 
    public void addCard(int i) throws InterruptedException{
        
        //ONLY CALLED BY TASK
        //SO MUST HAPPEN ON APPLICATION THREAD
        Platform.runLater(new Runnable() {
            @Override
                public void run() {
                    Cards.getChildren().add(new CardItem(i));
                        }
                });
        Thread.sleep(250);

    }
    
    
    //RETURNS AMOUNT OF CARDS
    public int cardCount(){
        return Cards.getChildren().size();
    }
}
