package com.example.brean.chasethepig;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.brean.chasethepig.gameLogic.Dealer;
import com.example.brean.chasethepig.serverAccess.Connection;
import com.example.brean.chasethepig.serverAccess.MessageSender;

import java.io.PrintWriter;

public class GameActivity extends AppCompatActivity {
    private LinearLayout hand = null;
    private int cardToBeExposed;
    private MessageSender messageSender;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        hand = findViewById(R.id.hand_layout);
        Handpane handpane = new Handpane(GameActivity.this);
        PlayerManager playerManager = new PlayerManager(GameActivity.this);
        final Dealer dealer = new Dealer(handpane,playerManager);


        new Thread(new Runnable() {
            @Override
            public void run() {
                Connection c = new Connection(dealer);
                setUpOutputToServer(c.getOutputStream());
                c.startRecieving();
            }
        }).start();

    }
    public void setUpOutputToServer(final PrintWriter outputToServer){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                messageSender = new MessageSender(outputToServer);
            }
        });
    }

    //TOAST FOR USE BY BACKGROUND THREAD TO INFORM PLAYER
    //OF TURNS
    public void makeToast(String s){
        final String st = s;
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(GameActivity.this,st,Toast.LENGTH_LONG).show();
            }
        });

    }
    //METHOD FOR ADDING CARDS TO PLAYERS HAND
    public void addCard(final String cardImageResId, final int cardValue){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                final ImageButton cardButton = new ImageButton(GameActivity.this);
                cardButton.setPadding(0,0,0,0);
                cardButton.setImageResource(getResources().getIdentifier(cardImageResId,"mipmap",
                        "com.example.brean.chasethepig"));
                cardButton.setBackgroundColor(android.R.drawable.btn_default);
                if(cardValue == 11 || cardValue == 26 || cardValue == 36 || cardValue == 48){
                    cardButton.setBackgroundColor(getResources().getColor(R.color.colorExpose));
                }
                hand.addView(cardButton);

                cardButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(GameActivity.this,"Button Press "+cardValue, Toast.LENGTH_LONG).show();
                        cardButton.setBackgroundColor(android.R.drawable.btn_default);
                        if(cardValue == 11 || cardValue == 26 || cardValue == 36 || cardValue == 48){
                            GameActivity.this.cardToBeExposed = cardValue;
                            Intent intent = new Intent(GameActivity.this, ExposePopUp.class);
                            startActivityForResult(intent,1);
                        }
                    }
                 });

            }
        });
    }

    //GETS RESULT FROM EXPOSE POP-UP AND SEND IT TO THE SERVER
    @Override
    public void onActivityResult(int requestCode,int resultCode, Intent data){
        if(requestCode == 1 && resultCode == 0){
            String card = "No" + cardToBeExposed;
            Toast.makeText(GameActivity.this,card, Toast.LENGTH_LONG).show();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    messageSender.expose(0);
                }
            }).start();

        }
        if(requestCode == 1 && resultCode == 1){
            String card = "Yes" + cardToBeExposed;
            Toast.makeText(GameActivity.this,card, Toast.LENGTH_LONG).show();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    messageSender.expose(cardToBeExposed);
                }
            }).start();
        }
    }

    //EDITS THE TEXT FOR PLAYER
    public void editText(final String text){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                TextView textview = (TextView)findViewById(R.id.Player_id);
                textview.setText(text);
            }
        });
    }
}
