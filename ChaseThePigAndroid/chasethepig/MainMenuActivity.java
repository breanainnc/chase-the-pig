package com.example.brean.chasethepig;

import android.content.Intent;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Icon;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

public class MainMenuActivity extends AppCompatActivity {

    private Button mFindGameButton;
    private Button mRulesButton;
    private Button mQuitButton;
    private ImageView mCardIcon;

    private int windowWidth;
    private int screenCenter;
    private int cardType = 1;

    private float cardIconX;
    private float speed = 10;

    private Handler handler = new Handler();
    private Timer timer = new Timer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        mCardIcon = (ImageView) findViewById(R.id.cardIcon);


        WindowManager wm = getWindowManager();
        Display display = wm.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        windowWidth = size.x;
        screenCenter = windowWidth / 2;

        mCardIcon.setX(screenCenter);
        cardIconX = mCardIcon.getX();


        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        moveIcon();
                    }
                });
            }
        },0,40);


        mFindGameButton = (Button) findViewById(R.id.findgame_button);
        mFindGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainMenuActivity.this, GameActivity.class);
                startActivity(intent);
            }
        });

        mRulesButton = (Button) findViewById(R.id.rules_button);
        mRulesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainMenuActivity.this, rulesActivity.class);
                startActivity(intent);
            }
        });

        mQuitButton = (Button) findViewById(R.id.quit_button);
        mQuitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void moveIcon(){

        cardIconX += speed;
        mCardIcon.setX(cardIconX);
        if(cardIconX < screenCenter - 300 || cardIconX > screenCenter+ 200) {
            speed *= -1;
            cardType++;
            if (cardType > 4) {
                cardType = 1;
            }
            if(cardType == 1){
                mCardIcon.setImageResource(R.mipmap.heart);
            }
            else if(cardType == 2){
                mCardIcon.setImageResource(R.mipmap.clubs);
            }
            else if(cardType == 3){
                mCardIcon.setImageResource(R.mipmap.diamonds);
            }
            else if(cardType == 4){
                mCardIcon.setImageResource(R.mipmap.spades);
            }
        }

    }
}
