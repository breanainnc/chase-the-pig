package Logic;
import ctpclient.MessageSender;
/**
 *
 * @author brean
 */
public class LegalCommand {
    private MessageSender messageSender;
    private GamePlayLogic gameplaylogic;
    
    final private int PIG = 11, GOAT = 36, ACEH = 26, TENC = 48; 
    private boolean pigSent = false, goatSent= false, acehSent = false, tencSent = false;
    private int exposeCount = 0;
    
    boolean yourturn = false;
    boolean firstTrick = true;
    
    public LegalCommand(MessageSender messageSender, GamePlayLogic gameplaylogic){
        this.messageSender = messageSender;
        this.gameplaylogic = gameplaylogic;
    }
    
    //CALLED BY ENTER COMMAND
    public boolean playCard(int card){
         if(gameplaylogic.isTurn()){
             if(gameplaylogic.validMove(card)){
                messageSender.playCard(card);
                gameplaylogic.TurnOver();
                return true;
                }
             else return false;        
            }
         else return false;   
    }
    
    //USED BY DEALER TO TELL WHOSE TURN IT IS
    public void setTurn(){
        yourturn = true;
    }
    
    public void exposeCard(int cardNum, boolean send){
        //RESETS THE SENT VARIABLES FOR LATER ROUNDS
        if(gameplaylogic.resetExpose()){
            pigSent = false;
            goatSent = false;
            acehSent= false; 
            tencSent = false;
            gameplaylogic.setResetExpose();
        }
        //CHECKS IF CARD HASNT BEEN EXPOSED ALREADY
        //THEN SENDS CARD/NO CARD TO MESSAGE SENDER WHO CREATES
        //MESSAGE FOR SERVER
        if(cardNum == PIG && pigSent == false){
            if(send)messageSender.expose(cardNum);
            else messageSender.expose(0);
        } 
        if(cardNum == GOAT && goatSent == false){
            if(send)messageSender.expose(cardNum);
            else messageSender.expose(0);
        }
        if(cardNum == ACEH && acehSent == false){
            if(send)messageSender.expose(cardNum);
            else messageSender.expose(0);
        }
        if(cardNum == TENC && tencSent == false){
            if(send)messageSender.expose(cardNum);
            else messageSender.expose(0);
        }
            
        switch(cardNum){
            case PIG: pigSent = true; 
            case GOAT: goatSent = true; break;
            case ACEH: acehSent = true; break;
            case TENC: tencSent = true; break;
        }
        
        exposeCount++;
        
        
    }
}
