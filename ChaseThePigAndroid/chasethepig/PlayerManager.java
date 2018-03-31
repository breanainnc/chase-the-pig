package com.example.brean.chasethepig;

import android.widget.TextView;

public class PlayerManager {
    GameActivity gameActivity;
    int LEFT,TOP,RIGHT;
    TextView leftText, rightText, topText;

    public PlayerManager(GameActivity gameActivity){
        this.gameActivity = gameActivity;
        leftText = gameActivity.findViewById(R.id.left_Player_id);
        rightText = gameActivity.findViewById(R.id.right_Player_id);
        topText = gameActivity.findViewById(R.id.top_Player_id);
    }
    public void setPlayers(int pid){
        if(pid == 1){
            LEFT = 2;
            TOP = 3;
            RIGHT = 4;
        }
        if(pid == 2){
            LEFT = 3;
            TOP = 4;
            RIGHT = 1;
        }
        if(pid == 3){
            LEFT = 4;
            TOP = 1;
            RIGHT = 2;
        }
        if(pid == 4){
            LEFT = 1;
            TOP = 2;
            RIGHT = 3;
        }
        gameActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                leftText.setText("Player: " + LEFT);
                rightText.setText("Player: " + RIGHT);
                topText.setText("Player: " + TOP);
            }
        });

    }
}
