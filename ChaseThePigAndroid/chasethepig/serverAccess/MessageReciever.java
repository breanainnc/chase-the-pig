package com.example.brean.chasethepig.serverAccess;

import com.example.brean.chasethepig.gameLogic.Dealer;

import java.io.BufferedReader;
import java.io.IOException;


public class MessageReciever {
    BufferedReader input;
    Dealer dealer;

    public MessageReciever(BufferedReader input,Dealer d){
        this.input = input;
        this.dealer = d;

    }
    public void startRecieving(){
        try {
            while (true) {
                String Message = input.readLine();
                System.out.println(Message);
                if(Message.contains("PLAYER")){
                    char number = Message.charAt(6);
                    dealer.playerNumber(number);
                }
                if(Message.contains("HAND")){
                    String hand = Message.substring(4);
                    dealer.receiveHand(hand);
                }
            }

        }
        catch (IOException ex){

        }
    }
}
