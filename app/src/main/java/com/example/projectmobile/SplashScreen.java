package com.example.projectmobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class SplashScreen extends AppCompatActivity {
    String usernameedit,fullnameedit, passwordedit,emailedit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        usernameedit =getIntent().getStringExtra("usernameedit");
        fullnameedit =getIntent().getStringExtra("fullnameedit");
        passwordedit =getIntent().getStringExtra("passwordedit");
        emailedit =getIntent().getStringExtra("emailedit");

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(SplashScreen.this, SecondActivity.class);
                i.putExtra("usernameedit",usernameedit);
                i.putExtra("fullnameedit",fullnameedit);
                i.putExtra("passwordedit",passwordedit);
                i.putExtra("emailedit",emailedit);
                startActivity(i);
                finish();
            }
        },2000);
    }
}