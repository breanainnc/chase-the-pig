package ctpclient;

import Logic.Dealer;
import java.io.BufferedReader;
import java.io.IOException;
import javafx.application.Platform;
import javafx.concurrent.Task;

/**
 *
 * @author brean
 */
public class MessageReceiver extends Task{
        BufferedReader input;
        Dealer dealer;
    
    public MessageReceiver(BufferedReader input,Dealer d){
        this.input = input;
        this.dealer = d;

    }     
  
    @Override
    protected Void call() throws Exception {
         try{
            while(true){
                String Message = input.readLine();
                System.out.println(Message);
                
                if(Message.contains("PLAYER")){
                    char number = Message.charAt(6);
                    dealer.playerNumber(number);
                }
                
                if(Message.contains("HAND")){
                    String hand = Message.substring(4);
                    dealer.sendHand(hand);
                }
                if(Message.contains("PLYREX")){
                    System.out.println(Message);
                    String exposedCards = Message.substring(6);
                    dealer.cardExposure(exposedCards);
                }
                //TELL GPLYLOGIC THAT FIRSTCARD........
                if(Message.contains("FIRSTC")){
                    char playerchar = Message.charAt(6);
                    int plyrnum = playerchar - '0';
                    String card = Message.substring(7);
                    int cardPlayed = Integer.parseInt(card);
                    dealer.playCardGUI(plyrnum, cardPlayed);
                    dealer.updateFirstCardOfTrick(cardPlayed);
                    System.out.println(plyrnum + " " + cardPlayed);
                }
                if(Message.contains("CARD")){
                    char playerchar = Message.charAt(4);
                    int plyrnum = playerchar - '0';
                    String card = Message.substring(5);
                    int cardPlayed = Integer.parseInt(card);
                    dealer.playCardGUI(plyrnum,cardPlayed);
                    System.out.println(plyrnum + " " +cardPlayed);
                }
                if(Message.contains("ENDTRICK")){
                   dealer.endTrickGUI();
                   char playerchar = Message.charAt(8);
                   int pidwinner = playerchar - '0';
                   dealer.updateFirstCardOfTrick(-1);
                   dealer.updateTurnTrickWinner(pidwinner);
                }
                if(Message.contains("TURN")){
                    String turnString = Message.substring(4);
                    int nextTurn = Integer.parseInt(turnString);
                    dealer.updatePlayerTurn(nextTurn);
                    
                }
                if(Message.contains("ENDROUND")){
                    dealer.updateScores(input.readLine());
                    
                    
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run(){
                            dealer.finishRound();
                        }
                    });
                        
                }
                 
                }
                
        }
        catch(IOException ex){
                    System.err.println(ex);
                }
        
        return null;
       
    }
    
}
