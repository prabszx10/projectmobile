package com.example.projectmobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class helpActivity extends AppCompatActivity {
    Button jwb1, jwb2, jwb3, jwb4, jwb5, jwb6, jwb7;;
    ImageButton helpback;
    TextView anotherquestion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
        anotherquestion= findViewById(R.id.anotherquestion);
        helpback=findViewById(R.id.helpback);
        helpback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();

            }
        });

        anotherquestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain"); // send email as plain text
                intent.putExtra(Intent.EXTRA_EMAIL, new String[] { "MasakKuy2020@gmail.com" });
                intent.putExtra(Intent.EXTRA_SUBJECT, "Feedback for MasakKuy App");
                intent.putExtra(Intent.EXTRA_TEXT, "Terdapat hal/sejumlah hal yang hendak saya tanyakan namun tidak terdapat di FAQ pertanyaan saya adalah");
                startActivity(Intent.createChooser(intent, "MasakKuy2020"));
            }
        });

        jwb1 = (Button) findViewById(R.id.jwb1);
        jwb1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(helpActivity.this, Jwb1.class);
                startActivity(intent);
            }
        });

        jwb2 = (Button) findViewById(R.id.jwb2);
        jwb2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(helpActivity.this, Jwb2.class);
                startActivity(intent);
            }
        });

        jwb3 = (Button) findViewById(R.id.jwb3);
        jwb3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(helpActivity.this, Jwb3.class);
                startActivity(intent);
            }
        });

        jwb4 = (Button) findViewById(R.id.jwb4);
        jwb4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(helpActivity.this, Jwb4.class);
                startActivity(intent);
            }
        });

        jwb5 = (Button) findViewById(R.id.jwb5);
        jwb5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(helpActivity.this, Jwb5.class);
                startActivity(intent);
            }
        });

        jwb6 = (Button) findViewById(R.id.jwb6);
        jwb6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(helpActivity.this, Jwb6.class);
                startActivity(intent);
            }
        });

        jwb7 = (Button) findViewById(R.id.jwb7);
        jwb7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(helpActivity.this, Jwb7.class);
                startActivity(intent);
            }
        });

    }
}
