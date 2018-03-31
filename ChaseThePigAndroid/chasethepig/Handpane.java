package com.example.brean.chasethepig;


public class Handpane {
    GameActivity gameActivity;
    public Handpane( GameActivity gameActivity){
        this.gameActivity = gameActivity;
    }
    public void addCard(int cardNum){
        String cardImageResId = "img_" + cardNum;
        gameActivity.addCard(cardImageResId,cardNum);
    }
    public void setPlayerNumber(String number){
        gameActivity.editText(number);
    }
}
