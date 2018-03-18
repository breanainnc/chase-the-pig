package GUI;

import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 *
 * @author brean
 */
public class TopPlayer extends StackPane{
    private Text t;
    private HBox exposedImgs;
    public TopPlayer(){
        Rectangle r = new Rectangle(725,200);
        r.setFill(Color.FORESTGREEN);
        
        t = new Text("no server");
        t.setFont(new Font(20));
        t.setTranslateY(-50);
        
        exposedImgs = new HBox();
        exposedImgs.setTranslateX(350);
        exposedImgs.setTranslateY(100);
        
        getChildren().add(r);
        getChildren().add(t);
        getChildren().add(exposedImgs);
    }
    public void setText(int i){
        String ply =  "Player " + i;
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
