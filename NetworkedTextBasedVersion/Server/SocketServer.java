package server;
import java.io.IOException;
import java.net.*;
/**
 *
 * @author brean
 */
public class SocketServer {
    int portnumber = 8888;
    ServerSocket serverSocket = null;
    
    public void runServer(){
        
        // CREATES A GAME OBJECT AND SETS UP SERVERSOCKET
        Game game = new Game();
        try{
        serverSocket = new ServerSocket(portnumber);
        }
        catch(IOException ex){
            System.err.println(ex);
        }
        
        //KEEPS TRACK OF CONNECTIONS,CREATES PLAYER THREADS AND PASSES THEM GAME OBJECT
        //ONCE FOUR PLAYERS HAVE JOINED A NEW GAME IS MADE 
        int connections = 0;
        while(true){
            try{
            Socket client = serverSocket.accept();
            Player p = new Player(client, game);
            new Thread(p).start();
            game.initialisePlayer(p);
            }
            catch(IOException ex){
                System.err.println(ex);
            }
            connections++;
            if(connections % 4 == 0){
                game = new Game();
            }
        }
    }
}
