package Logic;
import GUI.HandPane;
/**
 *
 * @author brean
 */
public class Dealer {
    private HandPane PlayerHand;
    private playerManager pm;
    
    public Dealer(HandPane PlayerHand,playerManager pm){
        this.PlayerHand = PlayerHand;
        this.pm = pm;
    }
    
    //SEND HANDS TO HANDPANE
    public void sendHand(String hand) throws InterruptedException{

        int start = 0;
        int end = 2;
        for(int i = 0; i < 13; i++ ){
            int card = Integer.parseInt(hand.substring(start, end));

            PlayerHand.addCard(card);
            start = start + 2;
            end = end + 2;
        }
    }

    // TELL PLAYER NUMBER 
    public void playerNumber(char number) {
        String playerNumber = "Player: " + number;
        PlayerHand.setPlayerNumber(playerNumber);
        int n = (int)(number- '0');
        System.out.println(n);
        pm.setPlayers(n);
        
    }
    
    public void cardExposure(String cardsAndId) throws InterruptedException{
         int start = 0;
         int end = 3;
            for(int i = 0; i < 4; i++){
                int cardPlusId = Integer.parseInt(cardsAndId.substring(start, end));
                
                if(cardPlusId != 0){
                    int Id = cardPlusId % 10;
                    int card = cardPlusId / 10;
                    //WORKS
                    pm.addExCard(Id,card);
                }
                start += 3;
                end += 3;
            }
        }
        
    
    public void setPlayerPositions(int pid){
        
    }
}
