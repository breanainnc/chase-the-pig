package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author brean
 */
public class Round {
    BufferedReader in;
    PrintWriter out;
    ArrayList<Integer> hand;
    ArrayList<Integer> exposedCards;
    int firstCardPlayed = -1;
    
    public Round(BufferedReader in, PrintWriter out){
        this.in = in;
        this.out = out;
        this.hand = new ArrayList<Integer>();
        this.exposedCards = new ArrayList<Integer>();
    }
    
    //METHOD THAT STARTS THE ROUND AND RECEIVES HAND
    public void begin(){
        out.println("READY");
        try{
            String cards = in.readLine();
            createHand(cards); 
            revealCards();
            displayExposed();
            displayHand();
            
            
        }
        catch(IOException ex){
            System.err.println(ex);
        }
    }
    

      public void play(){
          while(true){
              try{
                  String instruction = in.readLine();
                  if(instruction.contains("YOURTURN")){
                      ChooseCard();
                  }
                  if(instruction.contains("ENDTRICK")){
                      firstCardPlayed = -1;
                  }
                  if(instruction.contains("CARD")){
                     // receiveCard();
                  }
                  if(instruction.contains("END")){
                      break;
                  }
              }
              catch(IOException ex){
                  System.err.println(ex);
              }
          }
          
          
      }
      public void ChooseCard(){
          
          Scanner input = new Scanner(System.in);
          System.out.print("Play a card: ");
          int card = input.nextInt();
          if(card > 0 && card < hand.size()){
              if(firstCardPlayed == -1){
                 out.println("CARD" + card);
              }
              else if(ifPlayable(card) == true){
                  out.println("CARD" + card);
              }
              else{
                  System.out.println("YOU MUST PLAY THE SAME SUIT");
                  ChooseCard();
              }
              
          }
          else{
              ChooseCard();
          }
          
      }
      public boolean ifPlayable(int card){
         if(anyPlayableCards() == true){
            if(firstCardPlayed < 13){
                return card < 13;
            }
            else if(firstCardPlayed < 26){
                return card > 12 && card < 26;
            }
            else if(firstCardPlayed < 39){
                return card > 25 && card < 39;
            }
            else{
                return card > 38;
            }
         }
         return true;
         
      }
      public boolean anyPlayableCards(){
          int rangeStart =0;
          int rangeEnd = 0;
          if(firstCardPlayed < 13){
              rangeStart = 0;
              rangeEnd = 12;
          }
          else if(firstCardPlayed < 26){
              rangeStart = 13;
              rangeEnd = 25;
          }
          else if(firstCardPlayed < 39){
              rangeStart = 26;
              rangeEnd = 38;
          }
          else{
              rangeStart = 39;
              rangeEnd = 51;
          }
          
          for(int i = 0; i < hand.size(); i++){
              if(hand.get(i) >= rangeStart && hand.get(i) <= rangeEnd){
                  return true;
              }
          }
          return false;
      }
      //CHECKS IF THE USERS HAND CONTAINS THE TWO OF SPADES
      public boolean haveTwoOfSpades(){
          if(hand.size() < 13){
              return false;
          }
          for(int i = 0; i < 13; i++){
              if(hand.get(i) == 0){
                  return true;
              }
          }
          return false;
      }
      
      //CONVERTS THE HAND FROM A STRING TO AN ARRAYLIST
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
      
      //DISPLAYS THE EXPOSED HAND
      public void displayExposed(){
          System.out.println("Exposed Cards: ");
          for(int i = 0; i < exposedCards.size(); i++){
              int player = exposedCards.get(i) % 10;
              int card = exposedCards.get(i) / 10;
              System.out.print("   Player " + player + ":");
              displaycard(card);
          }
      }
      
      //DISPLAYS THE USERS HAND
       public void displayHand(){
        for(int i = 0; i < hand.size(); i++){
            System.out.print("Choice Num " + i + ": ");
            displaycard(hand.get(i));
        }
    }
       
       //DISPLAYS INDIVIDUAL CARDS
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
        
    //METHOD THAT LETS PLAYERS EXPOSE CARDS
    public void revealCards(){
        if(hand.contains(10)){
            System.out.print("Would you like to expose the ");
            displaycard(10);
            System.out.print("type y for yes.. anything else for no: ");
            Scanner input = new Scanner(System.in);
            char ans = input.next().charAt(0);
            if(ans == 'y' || ans == 'Y'){
                out.println("EXPOSE10");
            }
            else{
                System.out.println("your card was not revealed");
                out.println("NOREVEAL");
            }
        }
        if(hand.contains(21)){
           System.out.print("Would you like to expose the ");
            displaycard(21);
            System.out.print("type y for yes.. anything else for no: ");
            Scanner input = new Scanner(System.in);
            char ans = input.next().charAt(0);
            if(ans == 'y' || ans == 'Y'){
                out.println("EXPOSE21");
            }
            else{
                System.out.println("your card was not revealed");
                out.println("NOREVEAL");
            }
        }
        if(hand.contains(35)){
            System.out.print("Would you like to expose the ");
            displaycard(35);
            System.out.print("type y for yes.. anything else for no: ");
            Scanner input = new Scanner(System.in);
            char ans = input.next().charAt(0);
            if(ans == 'y' || ans == 'Y'){
                out.println("EXPOSE35");
            }
            else{
                System.out.println("your card was not revealed");
                out.println("NOREVEAL");
            }
        }
        if(hand.contains(51)){
            System.out.print("Would you like to expose the ");
            displaycard(51);
            System.out.print("type y for yes.. anything else for no: ");
            Scanner input = new Scanner(System.in);
            char ans = input.next().charAt(0);
            if(ans == 'y' || ans == 'Y'){
                out.println("EXPOSE51");
            }
            else{
                System.out.println("your card was not revealed");
            }
        }
        recieveExposed();
    }
    
    //RECIEVES CARDS THAT HAVE BEEN EXPOSED BY PLAYERS
    public void recieveExposed(){
        try{
            String read = in.readLine();
            
            int start = 0;
            int end = 3;
            for(int i = 0; i < 4; i++){
                int card = Integer.parseInt(read.substring(start, end));
                exposedCards.add(card);
                start += 3;
                end += 3;
            }
        }
        catch(IOException ex){
            System.err.println(ex);
        }
    }
}
