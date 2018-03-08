package GUI;
import Logic.Dealer;
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
         HandPane hpane;
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
        hpane = new HandPane();
        
        leftPlayer = new SidePlayer(); 
        rightPlayer = new SidePlayer(); 
        
        topPlayer = new TopPlayer();
        b.setTop(topPlayer);
        
        b.setRight(rightPlayer);
        b.setLeft(leftPlayer);  
        
        b.setBottom(hpane);
        b.setCenter(trickPane);

        getChildren().add(b);

    }
    
    //CONNECTS TO SERVER
    public void connect(){
        c = new Connection();
        playerManager pm = new playerManager(leftPlayer,rightPlayer,topPlayer);
        Dealer d = new Dealer(hpane,pm);
        c.startReceiving(d);
        
        outMessenger = c.createMessageSender();
    }
   
    //LEFT ARROW CONTROL
    public void controlLeft(int choice){
        hpane.getCardItem(choice).DisplayArrow(false);
        hpane.getCardItem(--choice).DisplayArrow(true);
    }
    //RIGHT ARROW CONTROL
    public void controlRight(int choice){
        hpane.getCardItem(choice).DisplayArrow(false);
        hpane.getCardItem(++choice).DisplayArrow(true);
    }
    
    //ENTER CONTROL
    public void controlEnter(int choice){
        CardItem cardSelection = hpane.getCardItem(choice);
        int cardNum = cardSelection.getCardNumber();
        
        //METHOD THAT LETS USER MAKE A CHOICE FOR EXPOSURE OF CARDS
        if(trickPane.getExposeSection() == true){
            if(cardNum == PIG || cardNum == GOAT || cardNum == ACEH || cardNum == TENC){
                hpane.getCardItem(choice).exposeQuestion(cardNum);
                outMessenger.expose(cardNum);
            }
        }
        else{
        trickPane.cardPlayed(cardNum);
        }
    }
    
    //SPACEBAR CONTROL
    public void controlSpaceBar(int choice){
        CardItem cardSelection = hpane.getCardItem(choice);
        int cardNum = cardSelection.getCardNumber();
        
        //METHOD THAT LETS USER MAKE CHOICE FOR EXPOSURE OF CARDS
        if(trickPane.getExposeSection() == true){
            if(cardNum == PIG || cardNum == GOAT || cardNum == ACEH || cardNum == TENC){
                hpane.getCardItem(choice).exposeQuestion(0);
                outMessenger.expose(0);
            }
        }
    }
    
    //CARD COUNT IN HAND
    public int cardCount(){
        return hpane.cardCount();
    }
    
}
