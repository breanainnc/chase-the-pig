
package client;
import java.net.*;
import java.io.*;
import java.util.*;
/**
 *
 * @author brean
 */
public class Play {
    int playerID;
    DataInputStream dis;
    DataOutputStream dos;
    boolean gameover = false;
    ArrayList<Integer> hand;
    boolean turn = false; 
    
    public Play(int playerID, DataInputStream dis, DataOutputStream dos){
        this.playerID = playerID;
        this.dis = dis;
        this.dos = dos;
        this.hand = new ArrayList<Integer>();
    }
    public void start(){
        try{
            while(gameover == false){
                String handStr = dis.readUTF();
                createHand(handStr);
                beginRound();
                for(int i = 0; i < 13; i++){
                    displayHand();
                    for(int j = 0; j < 4; j++){   
                    play();
                 }
                }
            }
        }
        catch(IOException ex){
            System.err.println(ex);
        }
    }
    
    public void beginRound(){
        for(int i = 0; i < hand.size(); i++){
            if(hand.get(i) == 0){
                turn = true;
            }
        }
        }
    public void play() throws IOException{
        if(turn == true){
            Scanner input = new Scanner(System.in);
            System.out.println("Enter a a choice:");
            int card = input.nextInt();
            if(card > -1 && card < hand.size()){
                dos.write(hand.get(card));
                hand.remove(card);
                turn = false;
            }
            else{
                System.out.println("Invalid choice");
                play();
            }
        }
        else{
            int card = dis.readInt();
            if(card == 52){
                turn = true;
                play();
            }
            else{
            displaycard(card);
            }
        }
    }
    /*
    public void playCard(){
        System.out.print("Your Turn: ");
        Scanner input = new Scanner(System.in);
        int card = input.nextInt();
        if(card < 0 || card > hand.size()){
            System.out.println("That number is out of range");
            playCard();
        }
        else{
            try{
                int PlyIdentifer = playerNum * 100; 
                card = card + PlyIdentifer;
                dos.write(card);
            }
            catch(IOException ex){
                System.err.print(ex);
            }
        }
    }
    */
    public void displayHand(){
        for(int i = 0; i < hand.size(); i++){
            System.out.print("Choice Num " + i + ": ");
            displaycard(hand.get(i));
        }
    }
    public void displaycard(int Card){
           String card = "";
           int CardNum = Card % 13;
           switch(CardNum){
               case 0: card += "2 of ";break;
               case 1: card += "3 of ";break;
               case 2: card += "4 of ";break;
               case 3: card += "5 of ";break;
               case 4: card += "6 of ";break;
               case 5: card += "7 of ";break;
               case 6: card += "8 of ";break;
               case 7: card += "9 of ";break;
               case 8: card += "10 of ";break;
               case 9: card += "Jack of ";break;
               case 10: card += "Queen of ";break;
               case 11: card += "King of ";break;
               case 12: card += "Ace of ";break;
           }
           if(Card < 13)
               card += "Spades";
           else if (Card < 26)
               card += "Clubs";
           else if (Card < 39)
               card += "Diamonds";
           else
               card += "Hearts";
           
           
           System.out.println(card);
        
    }
   
    public void createHand(String handStr){
        /*
        * This method takes the string recieved by the player
        * and turns them into an arrayList
        */
         
        int start = 0;
        int end = 2;
        for(int i = 0; i < 13; i++ ){
            int card = Integer.parseInt(handStr.substring(start, end));
            hand.add(card);
            start = start + 2;
            end = end + 2;
        }
    }
}
