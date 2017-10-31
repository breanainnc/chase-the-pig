/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;
import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author brean
 */
public class ChaseThePigClient {
    
    private BufferedReader in;
    private Socket socket;
    private ObjectInputStream handin;
    List<Integer> hand = new ArrayList();
    
    public ChaseThePigClient() throws Exception {
        socket = new Socket("localhost",8888);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        handin = new ObjectInputStream(socket.getInputStream());
    }
    public void play() throws ClassNotFoundException{
        try{
            System.out.println(in.readLine());
            hand = (List<Integer>) handin.readObject();
            for(int i = 0;  i < hand.size(); i++){
                System.out.println(hand.get(i));
            }
        }
        catch(IOException ex){
            System.out.println("Error 1");
        }
    }
    
    public static void main(String[] args) throws Exception{
        ChaseThePigClient client = new ChaseThePigClient();
        client.play();
    }

}
