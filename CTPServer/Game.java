package ctpserver;

/**
 *
 * @author brean
 */
public class Game {
    private playerThread players[];
    private int playercount = 0;
    private int readyToDeal = 0;
    private Deck deck;
    private int expose = 0;
    private String exposed;
    
    public Game(){
        this.players = new playerThread[4];
        this.deck = new Deck();
        this.exposed = "";
    }
    
    // PLACES PLAYERS INTO ARRAY,TELLS EACH PLAYER THEIR ID
    // AND STARTS PLAYER THREAD
    public void initialisePlayer(playerThread p){
        p.setId(playercount);
        players[playercount] = p;
        playercount++;
        new Thread(p).start();
    }
    
        //WHEN THE FOUR PLAYERS ANNOUNCE THEY ARE READY THIS METHOD DEALS
    public void readyToDeal(){
        readyToDeal++;
        if(readyToDeal == 4){
            deal();
            readyToDeal = 0;
        }
        
    }
       //EXPOSE CARDS TO PLAYERS AND TELLS SCORE OBJECT
    public void expose(int pid, int card){
        expose++;
        if(card == 0){
            exposed += "000";
        }
        else{
        //score.addExCard(card);
        exposed += card;
        exposed += (pid);
        }
        if(expose == 4){
            for(int i = 0; i < 4; i++){
                players[i].Tellplayer("PLYREX" + exposed);
            }
        }
    }
        //DEALS THE CARDS TO THE PLAYERS
    public void deal(){
        /*
        * This method splits the deck into four hands.
        * It then places them into String and sends them to
        * the clients where they will interpret them.
        * 
        */
        String hand1 = "";
        String hand2 = "";
        String hand3 = "";
        String hand4 = "";
        
        deck.shuffle();
        int[] cards = deck.getDeck();
        for(int i = 0; i < 52; i++){
            if(i < 13){
                if(cards[i] < 10){
                    hand1 += "0" + cards[i];
                }
                else
                hand1 += cards[i];
            }
            else if(i < 26){
                if(cards[i] < 10){
                    hand2 += "0" + cards[i];
                }
                else
                hand2 += cards[i];
            }
            else if(i < 39){
                if(cards[i] < 10){
                    hand3 += "0" + cards[i];
                }
                else
                hand3 += cards[i];
            }
            else{
                if(cards[i] < 10){
                    hand4 += "0" + cards[i];
                }
                else
                hand4 += cards[i];
            }
        }
        players[0].Tellplayer("HAND" + hand1);
        players[1].Tellplayer("HAND" + hand2);
        players[2].Tellplayer("HAND" + hand3);
        players[3].Tellplayer("HAND" + hand4);
    }

}
