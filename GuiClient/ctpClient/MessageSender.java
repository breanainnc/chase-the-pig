package ctpclient;

import java.io.PrintWriter;

/**
 *
 * @author brean
 */
public class MessageSender {
    PrintWriter out;
    
    public MessageSender(PrintWriter out){
        this.out = out;
    }
    
    public void expose(int card){
        if(card == 0){
            out.println("NOREVEAL");
        }
        else{
            out.println("EXPOSE" + card);
        }
    }
    public void playCard(int card){
        out.println("PLAY" + card);
    }
    
  public void ReadyMessage(){
      out.println("READY");
  }
}
