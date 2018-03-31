package com.example.brean.chasethepig.serverAccess;

import java.io.PrintWriter;

public class MessageSender {
    private PrintWriter outputToServer;
    public MessageSender(PrintWriter outputToServer){
        this.outputToServer = outputToServer;
    }
    public void expose(int card){
        if(card == 0){
            outputToServer.println("NOREVEAL");
        }
        else{
            outputToServer.println("EXPOSE" + card);
        }
    }
    public void playCard(int card){
        outputToServer.println("PLAY" + card);
    }
}
