public class ChaseThePigServer{
    public static void main(String[] args) throws IOException{
        
        ServerSocket server = new ServerSocket(8888);
        Socket s;
        int playerID = 0;
        Player[] players = new Player[4];
        
        while(true){
        //accepts clients
        s = server.accept();
       
        //sets up dataStreams
        DataInputStream dis = new DataInputStream(s.getInputStream());
        DataOutputStream dos = new DataOutputStream(s.getOutputStream());
        
        //creates player objects
        Player player = new Player(s,dis,dos,playerID );
        player.dos.writeInt(playerID);
        players[playerID] = player;
        
        System.out.println("Connection Successful " + playerID);
        
        
        
        if(playerID == 3){
            System.out.println("Begin game!!");
            Game game = new Game(players);
            game.begin();
            playerID = -1;
        }
         playerID++;
        }

}
}
