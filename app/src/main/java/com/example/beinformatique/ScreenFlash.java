package com.example.beinformatique;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class ScreenFlash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_flash);
        //code pour rediriger vers la page principale
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                //demarrer un page
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
                finish();
            }
        };
        //handler post delay
        new Handler().postDelayed(runnable, 2000);
    }
}