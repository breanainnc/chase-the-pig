
package client;
import java.net.*;
import java.io.*;
/*
author Breanainn
*/
public class ChaseThePigClient{
    public static void main(String[] args){
        
       try{
           Socket s = new Socket("127.0.0.1",8888);
           BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
           PrintWriter out = new PrintWriter(new OutputStreamWriter(s.getOutputStream()),true);
           System.out.println(in.readLine());
           Round round = new Round(in,out);
           round.begin();
       }
       catch(UnknownHostException Hs){
           System.exit(1);
       }
       catch(IOException ex){
           System.err.println(ex);
       }
                

}
}
