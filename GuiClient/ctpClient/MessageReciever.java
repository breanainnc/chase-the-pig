/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ctpclient;

import Logic.Dealer;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

/**
 *
 * @author brean
 */
public class MessageReciever implements Runnable{
        BufferedReader input;
        Dealer d;
        PrintWriter output;
    
    public MessageReciever(BufferedReader input,PrintWriter output,Dealer d){
        this.input = input;
        this.d = d;
        this.output = output;
    }     
    @Override
    public void run() {
        try{
            output.println("READY");
            while(true){
                
                String Message = input.readLine();
                System.out.println(Message);
                /*
                if(Message.contains("PLAYER")){
                    char number = Message.charAt(6);
                    d.playerNumber(number);
                }
            */
                    d.sendHand(input.readLine());
                }
                
        }
        catch(IOException ex){
                    System.err.println(ex);
                }
        
    }
    
}
