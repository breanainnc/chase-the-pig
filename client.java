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
public class Client {
static Socket socket; 
static DataInputStream in;
static DataOutputStream out;
    /**
     * @param args the command line arguments
     * @throws java.lang.ClassNotFoundException
     */
    public static void main(String[] args) throws ClassNotFoundException {
        
        
       try{
           //sets up socket with server
           socket = new Socket("localhost", 8888);
           
           //starts thread for recieving data
           in = new DataInputStream(socket.getInputStream());
           
           
           input input = new input(in);
           Thread thread = new Thread(input);
           thread.start();
           
           //sets up output to send to server
           out = new DataOutputStream(socket.getOutputStream());
           Scanner i = new Scanner(System.in);
           System.out.print("what the hell is your name?");
           String name = i.nextLine();
           out.writeUTF(name);
           
           //play card
           while(true){
               String message = i.nextLine();
               out.writeUTF(message);
           }
           
       }
       catch(IOException ex){
           ex.printStackTrace();
       }
       
    }
    
}
class input implements Runnable{

    DataInputStream in;
    
    input(DataInputStream in){
        this.in = in;
    }
    @Override
    public void run() {
        
        while(true){
            try {
                String message = in.readUTF();
                System.out.println(message);
            } catch (IOException ex) {
                Logger.getLogger(input.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
