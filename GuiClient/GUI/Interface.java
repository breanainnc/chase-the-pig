package GUI;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

/**
 *
 * @author brean
 */
public class Interface extends Application {
    
    Scene MainMenuScene, GamePlayScene;
    Stage primaryStage;    
    int currentMenuChoice = 0; 
    GamePlay GAMEPLAY;
    MainMenu MAINMENU;
    

    @Override
    public void start(Stage primaryStage) {
        
        this.primaryStage = primaryStage; 
        this.primaryStage.setResizable(false);
        MAINMENU = new MainMenu();
        GAMEPLAY = new GamePlay();
        
        MainMenuScene= new Scene(MAINMENU);
        GamePlayScene= new Scene(GAMEPLAY);
        
       
        
        MainMenuScene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.UP) {
                if (currentMenuChoice > 0) {
                    MAINMENU.getMenuItem(currentMenuChoice).setActive(false);
                    MAINMENU.getMenuItem(--currentMenuChoice).setActive(true);
                }
            }

            if (event.getCode() == KeyCode.DOWN) {
                if (currentMenuChoice < MAINMENU.menuList.getChildren().size() - 1) {
                    MAINMENU.getMenuItem(currentMenuChoice).setActive(false);
                    MAINMENU.getMenuItem(++currentMenuChoice).setActive(true);
                }
            }
            if (event.getCode() == KeyCode.ENTER) {
               sceneChanger();
            }
        });
        
        GamePlayScene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.LEFT) {
                if (currentMenuChoice > 0) {
                    GAMEPLAY.controlLeft(currentMenuChoice--);
                }
            }

            if (event.getCode() == KeyCode.RIGHT) {
                if (currentMenuChoice < GAMEPLAY.cardCount() - 1) {
                    GAMEPLAY.controlRight(currentMenuChoice++);
                }
            }
            if (event.getCode() == KeyCode.ENTER) {
                //GAMEPLAY.addCard(hpane.Cards.getChildren().size() + 1);
            }
        });
        
        
        
        primaryStage.setTitle("CHASE THE PIG");
        primaryStage.setScene(MainMenuScene);
        primaryStage.show();
    }
    
    
        private void sceneChanger(){
        
        if(currentMenuChoice == 0){
            primaryStage.setScene(GamePlayScene);
            //GAMEPLAY.connectToServer();
            MAINMENU.stopAnimation();
        }
        else if(currentMenuChoice == 1){
            MAINMENU.stopAnimation();
        }
        else if(currentMenuChoice == 2){
            System.exit(0);
        }
        else
            System.out.println("Button press");
            
    }
       
    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
