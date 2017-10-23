package server;
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author brean
 */
public class Server {
    static ServerSocket server;
    static Socket socket;
    static DataOutputStream out;
    static DataInputStream in;
    static players[] player = new players[4];
    static int deck[]  = new int[52];
    
    public static void main(String[] args) throws IOException {
        server = new ServerSocket(8888);
       while(true){
        socket = server.accept();
        for(int i = 0; i < 4; i++){
        System.out.println("Connection from " + socket.getInetAddress());
        out = new DataOutputStream(socket.getOutputStream());
        in = new DataInputStream(socket.getInputStream());
        int pid = i;
        if(player[i] == null){
            player[i] = new players(out,in,player,pid);
            Thread thread = new Thread(player[i]);
            thread.start();
            break;
        }
        }
       }
        
    }
}
class players implements Runnable{

    DataOutputStream out;
    DataInputStream in;
    players[] player = new players[4];
    String name;
    int pid;
    
    players(DataOutputStream out, DataInputStream in, players[] player, int pid){
    this.out = out;
    this.in = in;
    this.player = player;
    this.pid = pid;
}
    @Override
    public void run() {
        try {
           
             name = in.readUTF();
        } catch (IOException ex) {
            
        }
        while(true){
            try {
                String message = in.readUTF();
                for(int i = 0; i < 4; i++){
                    if(player[i] != null){
                        if(i != pid)
                        player[i].out.writeUTF(name + ":" + message);
                    }
                }
            } catch (IOException ex) {
                in = null;
                out = null;
            }
        }
    }
    
}
