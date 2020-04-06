package com.example.projectmobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private EditText username,password;
    private TextView display,signup;
    private Button loginBUtton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username =(EditText)findViewById(R.id.username);
        password= (EditText)findViewById(R.id.password);
        loginBUtton= (Button)findViewById(R.id.loginButton);
        display= (TextView)findViewById(R.id.display);
        signup=(TextView)findViewById(R.id.signup);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(MainActivity.this,Signup.class);
                startActivity(intent);
            }
        });
        loginBUtton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validation(username.getText().toString(), password.getText().toString());
            }
        });

    }

    private void validation(String username,String password){
        if(username.equals("username")&&password.equals("password")){
            Intent intent= new Intent(MainActivity.this,SecondActivity.class);
            startActivity(intent);
        }

        else {
            display.setText("Incorrect Username or Password");
        }
    }

}
