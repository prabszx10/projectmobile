package com.example.projectmobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class Jwb6 extends AppCompatActivity {
    ImageButton kembali6;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jwb6);
        kembali6 = (ImageButton) findViewById(R.id.kembali6);
        kembali6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Jwb6.this, helpActivity.class);
                startActivity(intent);
            }
        });
    }
}