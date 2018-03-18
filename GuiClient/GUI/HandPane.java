package GUI;

import java.util.ArrayList;
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
       public Text ScoreSheet;
       public Text Round;
       private int roundInt = 1;
       
    //CONSTRUCTOR FOR HANDPANE
    public HandPane(){
        setPrefSize(725,150);
        Rectangle background = new Rectangle(725,150);
        background.setFill(Color.MAROON);
        Cards = new HBox(15);
        Cards.setTranslateX(15);
        PlyrNum = new Text("NO SERVER AVAILABLE"); 
        Text Score = new Text("Score:");
        Score.setTranslateY(140);
        Score.setTranslateX(190);
        Score.setFont(new Font(20));
        Round = new Text("Round: 1");
        Round.setTranslateY(140);
        Round.setTranslateX(600);
        Round.setFont(new Font(20));
        ScoreSheet = new Text("Player 1: 0 Player 2: 0 Player 3: 0 Player 4: 0");
        ScoreSheet.setTranslateY(140);
        ScoreSheet.setTranslateX(250);
        getChildren().addAll(background,Cards,PlyrNum,Score,ScoreSheet,Round);
       
    }
    
    
    //RETURNS CARDITEM
    public CardItem getCardItem(int i){
        return (CardItem)Cards.getChildren().get(i);
         
    }
    public ArrayList<Integer> getCardList(){
       ArrayList<Integer> hand = new ArrayList<Integer>(); 
       for(int i = 0; i < 13; i++){
           CardItem currentCard = getCardItem(i);
           hand.add(currentCard.getCardNumber());
       }
       return hand;
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
    
    public void setScoreSheet(String scores){
        Platform.runLater(new Runnable() {
            public void run(){
                ScoreSheet.setText(scores);
            }
        });
        ScoreSheet.setText(scores);
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

    public void removeCard(int card) {
        System.out.println(card +" to remove");
        Cards.getChildren().remove(card);
    }

    public void nextRound() {
        roundInt++;
        Round.setText("Round: " + roundInt);
    }
}
