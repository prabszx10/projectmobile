package com.example.projectmobile;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Menu_login extends AppCompatActivity {
    EditText username,password;
    TextView display,signup;
    Button loginBUtton;
    FirebaseAuth fAuth;
    ProgressBar pb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_login);
        username =(EditText)findViewById(R.id.username);
        password= (EditText)findViewById(R.id.password);
        loginBUtton= (Button)findViewById(R.id.loginButton);
        display= (TextView)findViewById(R.id.display);
        signup=(TextView)findViewById(R.id.signup);

        fAuth = FirebaseAuth.getInstance();
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(Menu_login.this,Signup.class);
                startActivity(intent);
            }
        });

        loginBUtton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usernamee = username.getText().toString().trim();
                String passwordd = password.getText().toString().trim();

                if(TextUtils.isEmpty(usernamee)){
                    username.setError("Username Is Required");
                    return;
                }

                if(TextUtils.isEmpty(passwordd)){
                    password.setError("password Is Required");
                    return;
                }

                if(passwordd.length()<6){
                    password.setError("Password Must be atleast six character");
                }


                fAuth.signInWithEmailAndPassword(usernamee,passwordd).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(Menu_login.this , "Login Success", Toast.LENGTH_SHORT).show();
                            Intent i= new Intent(Menu_login.this,SecondActivity.class);
                            startActivity(i);
                        }
                        else {
                            display.setText("username or password is incorrect");
                        }
                    }
                });
            }
        });


    }



}

