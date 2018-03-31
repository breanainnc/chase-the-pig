package com.example.brean.chasethepig.serverAccess;

import com.example.brean.chasethepig.gameLogic.Dealer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;


public class Connection {
    private Socket socket;
    private BufferedReader inputFromServer;
    private PrintWriter outputToServer;
    private MessageReciever messageReciever;

    public Connection(Dealer d) {
        try {
            socket = new Socket("192.168.1.4", 8888);
            inputFromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            outputToServer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);
            outputToServer.println("READY");
            messageReciever = new MessageReciever(inputFromServer,d);

        }
        catch (IOException ex) {
            System.err.println(ex);
        }

    }
    public void startRecieving(){
        messageReciever.startRecieving();
    }
    public PrintWriter getOutputStream(){
        return outputToServer;
    }
}