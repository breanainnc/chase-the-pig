package GUI;

import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 *
 * @author brean
 */
public class SidePlayer extends StackPane{
    private Text t;
    private VBox exposedImgs;
    
    public SidePlayer(){
        Rectangle r = new Rectangle(238,600);
        r.setFill(Color.FORESTGREEN);
        
        t = new Text("no server");
        t.setFont(new Font(20));
        t.setTranslateY(-300);
        
        exposedImgs = new VBox();
        exposedImgs.setTranslateY(30);
        exposedImgs.setTranslateX(80);
        
        
        getChildren().add(r);
        getChildren().add(t);
        getChildren().add(exposedImgs);
    }
    public void setText(int i){
        String ply = "Player" + i;
        t.setText(ply);
    }
    
     public void addCard(int cardNumber) throws InterruptedException{
        String card ="Images/"+cardNumber+".png";
        ImageView cardImg = new ImageView(new Image(card));
        cardImg.setFitHeight(50);
        cardImg.setFitWidth(40);
        
        System.out.println(card);
        //ONLY CALLED BY TASK
        //SO MUST HAPPEN ON APPLICATION THREAD
        Platform.runLater(new Runnable() {
            @Override
                public void run() {
                    exposedImgs.getChildren().add(cardImg);
                    
                }
        });
        Thread.sleep(100);
        
    }
    public void removeCards(){
        exposedImgs.getChildren().clear();
    }
}
