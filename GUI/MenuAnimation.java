package GUI;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

/**
 *
 * @author brean
 */
class MenuAnimation extends Pane{
    public final double radius = 5;
    private double x = 50, y = radius;
    private double dy = 1;
    private ImageView imageV;
    private Timeline animation;
    private int cardChange = 0;
    
    public MenuAnimation(){
        this.imageV = new ImageView(new Image("Images/heart.png"));
        imageV.setX(x-20);
         imageV.setFitHeight(50);
         imageV.setFitWidth(40);
        Rectangle r = new Rectangle(100,100);
        r.setFill(Color.FORESTGREEN);
        getChildren().addAll(r,imageV);

        animation = new Timeline(
            new KeyFrame(Duration.millis(50), e -> movecard()));
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.play();
    }
    
    protected void movecard(){
            if (y < 0 || y > 50) {
            dy *= -1;
            
            if(cardChange == 0){
                imageV.setImage(new Image("Images/clubs.png"));
                cardChange++;
            }
            else if(cardChange == 1){
                imageV.setImage(new Image("Images/diamonds.png"));
                cardChange++;
            }
            else if(cardChange == 2){
                imageV.setImage(new Image("Images/spades.png"));
                cardChange++;
            }
            else if (cardChange == 3){
                imageV.setImage(new Image("Images/heart.png"));
                cardChange = 0;
            }
            
        }
        
        y += dy;
        imageV.setY(y);
    }
}
