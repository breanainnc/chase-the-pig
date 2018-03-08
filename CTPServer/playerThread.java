package ctpserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

/**
 *
 * @author brean
 */
public class playerThread implements Runnable{
        
    private PrintWriter out;
    private BufferedReader in;
    private Game game;
    private int pid;
    
    public playerThread(Socket socket,Game game) throws IOException{
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()),true);
        this.game = game;
    }
    @Override
    public void run() {
        String instruction;
        try{
            while(true){
                instruction = in.readLine();
                if(instruction.contains("READY")){
                    
                    game.readyToDeal();
                }
                if(instruction.contains("EXPOSE")){
                    String card = instruction.substring(6);
                    int expose = Integer.parseInt(card);
                    game.expose(pid, expose);
                }
                if(instruction.contains("NOREVEAL")){                 
                    game.expose(pid, 0);
                }
                
                instruction = "";
            }
        }
        catch(IOException ex){
            
        }

}
    //SETS THE PLAYER ID AND TELLS THE CLIENT WHAT PLAYER NUMBER THEY ARE
    public void setId(int pid){
        this.pid = (pid + 1);
        out.println("PLAYER" + this.pid);
        out.flush();
    }
    
    //FOR GAME TO TELL EACH PLAYER GAME CHANGES
    public void Tellplayer(String line){
        out.println(line);
        out.flush();
    }
}
