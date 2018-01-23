
package server;

class Deck{
    int[] cards;
    
    public Deck(){
    this.cards = new int[52];
    for(int i = 0; i < 52; i++){
        cards[i] = i;
    }
}
    public void shuffle(){
        for(int i = 0; i < 52; i++){
            int random = (int)(Math.random()*52);
            int temp = cards[i];
            cards[i] = cards[random];
            cards[random] = temp;
        }
    }
    public int[] getDeck(){
        return cards;
    }
    
}
