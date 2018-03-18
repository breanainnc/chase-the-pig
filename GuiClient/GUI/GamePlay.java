package GUI;
import Logic.Dealer;
import Logic.GamePlayLogic;
import Logic.LegalCommand;
import Logic.playerManager;
import ctpclient.Connection;
import ctpclient.MessageSender;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

/**
 *
 * @author brean
 */
public class GamePlay extends Pane{
         HandPane handpane;
         LegalCommand legalCommand;
         GamePlayLogic gameplayLogic;
         MessageSender outMessenger;
         TrickPane trickPane;
         Connection c;
         SidePlayer leftPlayer, rightPlayer;
         TopPlayer topPlayer;
         final int windowWidth = 725, windowHeight = 600;
         final private int PIG = 11, GOAT = 36, ACEH = 26, TENC = 48; 
         
    //SETS UP SCENE FOR GAME
    public GamePlay(){
        BorderPane b =  new BorderPane();
        b.setMinSize(windowWidth, windowHeight);
        b.setMaxSize(windowWidth, windowHeight);
        trickPane = new TrickPane();
        //trickPane.setAlignment(Pos.CENTER);
        handpane = new HandPane();
        
        gameplayLogic =  new GamePlayLogic(handpane);
        
        leftPlayer = new SidePlayer(); 
        rightPlayer = new SidePlayer(); 
        
        topPlayer = new TopPlayer();
        b.setTop(topPlayer);
        
        b.setRight(rightPlayer);
        b.setLeft(leftPlayer);  
        
        b.setBottom(handpane);
        b.setCenter(trickPane);

        getChildren().add(b);

    }
    
    //CONNECTS TO SERVER
    public void connect(){
        c = new Connection();
        playerManager pm = new playerManager(leftPlayer,rightPlayer,topPlayer,trickPane);
        Dealer d = new Dealer(handpane,pm,trickPane,gameplayLogic);
        c.startReceiving(d);
        
        outMessenger = c.createMessageSender();
        legalCommand = new LegalCommand(outMessenger,gameplayLogic);
    }
   
    //LEFT ARROW CONTROL
    public void controlLeft(int choice){
        handpane.getCardItem(choice).DisplayArrow(false);
        handpane.getCardItem(--choice).DisplayArrow(true);
    }
    //RIGHT ARROW CONTROL
    public void controlRight(int choice){
        handpane.getCardItem(choice).DisplayArrow(false);
        handpane.getCardItem(++choice).DisplayArrow(true);
    }
    
    //ENTER CONTROL
    public void controlEnter(int choice){
        CardItem cardSelection = handpane.getCardItem(choice);
        int cardNum = cardSelection.getCardNumber();
        
        //METHOD THAT LETS USER MAKE A CHOICE FOR EXPOSURE OF CARDS
        if(trickPane.getExposeSection() == true){
            if(cardNum == PIG || cardNum == GOAT || cardNum == ACEH || cardNum == TENC){
                handpane.getCardItem(choice).exposeQuestion(cardNum);
                legalCommand.exposeCard(cardNum, true);
            }
        }
        //CALLS ON LEGAL COMMAND TO CHECK IF IT IS THE USERS TURN 
        //AND IF THE CARD CHOSEN COMPLIES WITH GAME RULES
        else{
            if(legalCommand.playCard(cardNum)){
                handpane.removeCard(choice);
            }
        }
    }
    
    //SPACEBAR CONTROL
    public void controlSpaceBar(int choice){
        CardItem cardSelection = handpane.getCardItem(choice);
        int cardNum = cardSelection.getCardNumber();
        
        //METHOD THAT LETS USER MAKE CHOICE FOR EXPOSURE OF CARDS
        if(trickPane.getExposeSection() == true){
            if(cardNum == PIG || cardNum == GOAT || cardNum == ACEH || cardNum == TENC){
                handpane.getCardItem(choice).exposeQuestion(0);
                legalCommand.exposeCard(cardNum, false);
            }
        }
    }
    
    //CARD COUNT IN HAND
    public int cardCount(){
        return handpane.cardCount();
    }
    
}
