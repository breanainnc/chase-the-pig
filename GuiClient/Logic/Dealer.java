package Logic;
import GUI.HandPane;
import GUI.TrickPane;
import javafx.application.Platform;
/**
 *
 * @author brean
 */
public class Dealer {
    private HandPane handPane;
    private playerManager pm;
    private TrickPane trickpane;
    private GamePlayLogic gameplaylogic;
    
    public Dealer(HandPane PlayerHand,playerManager pm, TrickPane trickpane, 
            GamePlayLogic gamplaylogic){
        this.handPane = PlayerHand;
        this.pm = pm;
        this.trickpane = trickpane;
        this.gameplaylogic = gamplaylogic;
    }
    
    //SEND HANDS TO HANDPANE
    //TELLS GAMEPLAYLOGIC IF PLAYER HAS FIRST GO AND THIER HAND IN INT FORM
    public void sendHand(String hand) throws InterruptedException{

        int start = 0;
        int end = 2;
        for(int i = 0; i < 13; i++ ){
            int card = Integer.parseInt(hand.substring(start, end));

            handPane.addCard(card);
            start = start + 2;
            end = end + 2;
            if(card == 1){
                trickpane.updatePlayPrompt("Your Turn");
                gameplaylogic.setTurn();
            }
        }
        gameplaylogic.getHand();
    }

    // TELL PLAYER NUMBER 
    public void playerNumber(char number) {
        String playerNumber = "Player: " + number;
        handPane.setPlayerNumber(playerNumber);
        int n = (int)(number- '0');
        System.out.println(n);
        pm.setPlayers(n);
        gameplaylogic.setId(n);
    }
    
    public void playCardGUI(int pid, int card){
        pm.playACard(pid, card);
    }
    //RECIVES CARD AND ID INFO FROM STRING.
    //CALLS METHOD TO INFORM GUI ABOUT EXPOSED CARDS
    public void cardExposure(String cardsAndId) throws InterruptedException{
         int start = 0;
         int end = 3;
            for(int i = 0; i < 4; i++){
                int cardPlusId = Integer.parseInt(cardsAndId.substring(start, end));
                
                if(cardPlusId != 0){
                    int Id = cardPlusId % 10;
                    int card = cardPlusId / 10;
                    pm.addExCard(Id,card);
                }
                start += 3;
                end += 3;
            }
            
         //ONLY CALLED BY TASK
        //SO MUST HAPPEN ON APPLICATION THREAD
        Platform.runLater(new Runnable() {
            @Override
                public void run() {
                    trickpane.exposePrompt();
                    
                }
        });
           trickpane.setExposeSection();
           gameplaylogic.setResetExpose();
        }

    public void updatePlayerTurn(int nextTurn) {
        if(gameplaylogic.getId() == nextTurn){
            gameplaylogic.setTurn();
            trickpane.updatePlayPrompt("Your Turn!");
        }
        else{
            trickpane.updatePlayPrompt("Player: "  + nextTurn + "'s Turn!");
        }
    }
    public void updateTurnTrickWinner(int winner){
        gameplaylogic.TurnOver();
        gameplaylogic.setTrickWinner(winner);
        trickpane.updatePlayPrompt("Player:" + winner + " wins this hand!");
            
        }
    
    public void endTrickGUI() throws InterruptedException {
        trickpane.setDefaultCardBack();
    }

    public void updateFirstCardOfTrick(int firstCard) {

        gameplaylogic.setFirstCardOfTrick(firstCard);
        gameplaylogic.setTrickWinner(firstCard);
    }

    public void updateScores(String scores) {
        handPane.setScoreSheet(scores);
    }

    public void finishRound() {
        pm.removeCards();
        handPane.nextRound();
        trickpane.exposePrompt();
        trickpane.setExposeSection();
    }
}
