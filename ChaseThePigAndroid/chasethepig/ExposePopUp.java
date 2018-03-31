package com.example.brean.chasethepig;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;

public class ExposePopUp extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceActivity){
        super.onCreate(savedInstanceActivity);

        setContentView(R.layout.activity_expose_popup);

        Button yesButton = (Button)findViewById(R.id.Yes_btn);
        yesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent();
                i.putExtra("response",1);
                setResult(1);
                finish();
            }
        });

        Button noButton = (Button)findViewById(R.id.No_btn);
        noButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent();
                i.putExtra("response",0);
                setResult(0);
                finish();
            }
        });

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout(width, (int)(height*.5));
    }
}
