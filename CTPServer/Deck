package ctpserver;

class Deck{
    int[] cards;
    
    // DECK CONSTRUCTOR
    // +1 because images start at 1
    public Deck(){
    this.cards = new int[52];
    for(int i = 0; i < 52; i++){
        cards[i] = i+1;
    }
}
    
    //SHUFFLE CARDS
    public void shuffle(){
        for(int i = 0; i < 52; i++){
            int random = (int)(Math.random()*52);
            int temp = cards[i];
            cards[i] = cards[random];
            cards[random] = temp;
        }
    }
    
    //RETURNS DECK
    public int[] getDeck(){
        return cards;
    }
    
}
