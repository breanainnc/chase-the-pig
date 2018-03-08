package ctpclient;

import Logic.Dealer;
import java.io.BufferedReader;
import java.io.IOException;
import javafx.concurrent.Task;

/**
 *
 * @author brean
 */
public class MessageReceiver extends Task{
        BufferedReader input;
        Dealer d;
    
    public MessageReceiver(BufferedReader input,Dealer d){
        this.input = input;
        this.d = d;

    }     
  
    @Override
    protected Void call() throws Exception {
         try{
            while(true){
                String Message = input.readLine();
                System.out.println(Message);
                
                if(Message.contains("PLAYER")){
                    char number = Message.charAt(6);
                    d.playerNumber(number);
                }
                
                if(Message.contains("HAND")){
                    String hand = Message.substring(4);
                    d.sendHand(hand);
                }
                if(Message.contains("PLYREX")){
                    System.out.println(Message);
                    String exposedCards = Message.substring(6);
                    d.cardExposure(exposedCards);
                }
                }
                
        }
        catch(IOException ex){
                    System.err.println(ex);
                }
        
        return null;
       
    }
    
}
