package GUI;

import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
/**
 *
 * @author brean
 */
public class MainMenu extends Pane{

        public VBox menuList;
        private MenuAnimation icon2;
        
    //SETS UP MAIN MENU SCENE 
    public MainMenu(){

        //SET SIZE
        setPrefSize(490, 490);
        
        //SETS BACKGROUND
        BackGround backGround = new BackGround();
        
        //SETS UP GAME IMAGES
        ImageView icon1 = new ImageView(new Image("Images/pig.png"));
        icon2 = new MenuAnimation();
        ImageView icon3 = new ImageView(new Image("Images/goat.png"));
        
        icon1.setFitHeight(100);
        icon3.setFitHeight(100);
        icon1.setFitWidth(100);
        icon3.setFitWidth(100);
 
        HBox icons = new HBox(15,icon1,icon2,icon3);
        icons.setTranslateX(85);
        icons.setTranslateY(50);
        
        
        //GAME LOGO
        ImageView logo = new ImageView(new Image("Images/title.png"));
        logo.setY(150);
        logo.setX(200);
                                   
        //LIST OF MENU 
        menuList = new VBox(10,
                        new MenuItem("FIND A GAME"),
                        new MenuItem("RULES"),
                        new MenuItem("EXIT")    
                        ); 
        
        menuList.setAlignment(Pos.CENTER);
        menuList.setTranslateX(195);
        menuList.setTranslateY(280);
        getMenuItem(0).setActive(true);
        
                                            
        getChildren().addAll(backGround,icons,menuList,logo);
        
    }
    
    //STOPS ANIMATION WHEN GAME IS STARTED
    public void stopAnimation(){
        icon2.stopAnimation();
    }
    
    //STATIC CLASS FOR BACKGROUND DESIGN FOR MAIN MENU
    private static class BackGround extends Parent {
        public BackGround() {
        Rectangle backGround = new Rectangle(500, 500);
        Rectangle backGDesign1 = new Rectangle(100,500);
        Rectangle backGDesign2 = new Rectangle(100,500);
        
        backGDesign1.setTranslateX(85);
        backGDesign2.setTranslateX(315);
        
        backGDesign1.setFill(Color.WHITE);
        backGDesign2.setFill(Color.WHITE);
        backGround.setFill(Color.FORESTGREEN);
        
        getChildren().addAll(backGround,backGDesign1,backGDesign2);
    }
 }
   
    public MenuItem getMenuItem(int index) {
      return (MenuItem)menuList.getChildren().get(index);
}
}

