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
    private double x = 30, y = radius;
    private double dy = 1;
    private Timeline animation;
    private int cardChange = 0;
    private ImageView imageV;
    private Image image1,image2,image3,image4;
    
    //CONSTRUCTOR
    public MenuAnimation(){
        
        //SETS UP IMAGES, IMAGEVIEW AND SIZES
        this.image1 = new Image("Images/heart.png");
        this.image2 = new Image("Images/clubs.png");
        this.image3 = new Image("Images/diamonds.png");
        this.image4 = new Image("Images/spades.png");
        this.imageV = new ImageView(image1);
        imageV.setX(x);
        imageV.setFitHeight(50);
        imageV.setFitWidth(40);
        
        //SETBACKGROUND SQUARE
        Rectangle r = new Rectangle(100,100);
        r.setFill(Color.FORESTGREEN);
        getChildren().addAll(r,imageV);

        //ANIMATION DETAILS
        animation = new Timeline(
                new KeyFrame(Duration.millis(50), e -> movecard()));
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.play();
    }
    
    //ANIMATION METHOD
    protected void movecard(){
        
        //CHANGES DIRECTION AND PICTURE
            if (y < 0 || y > 50) {
            dy *= -1;
            
            if(cardChange == 0){
                imageV.setImage(image2);
                cardChange++;
            }
            else if(cardChange == 1){
                imageV.setImage(image3);
                cardChange++;
            }
            else if(cardChange == 2){
                imageV.setImage(image4);
                cardChange++;
            }
            else if (cardChange == 3){
                imageV.setImage(image1);
                cardChange = 0;
            }
        }
        
        //SPEED
        y += dy;
        imageV.setY(y);
    }
    
    //STOPANIMATION
    public void stopAnimation(){
        animation.stop();
    }
}

