package server;
import java.net.*;
import java.io.*;
import java.util.*;
/**
 *
 * @author brean
 */

class Player implements Runnable{

    Socket s;
    Game game;
    int playerId;
    PrintWriter out;
    BufferedReader in;

    
    
    public Player(Socket s, Game g ){
    this.s = s;
    this.game = g; 

}
    @Override
    public void run() {
        try{
        in = new BufferedReader(new InputStreamReader(s.getInputStream()));
        out = new PrintWriter(new OutputStreamWriter(s.getOutputStream()),true);
        out.println("Welcome player " + (playerId + 1) + "!!!");
        
        while(true){
            String request = in.readLine();
            
            if(request.contains("READY")){
                game.readyToDeal();
            }
            if(request.contains("EXPOSE")){
                String card = request.substring(6);
                int expose = Integer.parseInt(card);
                game.expose(playerId, expose);
            }
            if(request.contains("NOREVEAL")){
                game.expose(playerId, 0);
            }
            if(request.contains("PLAY")){
                String card = request.substring(4);
                int play = Integer.parseInt(card);
                game.playCard(playerId,play);
            }
        }
        }
        catch(IOException ex){
            System.err.println(ex);
        }
    }
    public void Tellplayer(String line){
        out.println(line);
    }
    public void Id(int id){
        playerId = id;
    }
    
}
