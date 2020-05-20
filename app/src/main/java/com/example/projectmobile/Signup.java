package com.example.projectmobile;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Signup extends AppCompatActivity {
    EditText username,password,reenter,email,phone;
    Button signup;
    FirebaseAuth fAuth;
    ProgressBar pb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        reenter = findViewById(R.id.reenter);
        email = findViewById(R.id.email);
        phone = findViewById(R.id.phone);
        signup = findViewById(R.id.signup);

        fAuth = FirebaseAuth.getInstance();
        pb = findViewById(R.id.pb);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usernamee = username.getText().toString().trim();
                String passwordd = password.getText().toString().trim();
                String repasswordd = reenter.getText().toString().trim();

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
                if(passwordd != repasswordd){
                    reenter.setError("Password Must be atleast six character");
                }

                pb.setVisibility(View.VISIBLE);
                fAuth.createUserWithEmailAndPassword(usernamee,passwordd).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(Signup.this , "User Created", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    }
                    else {
                        Toast.makeText(Signup.this, "Error" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                    }
                });
            }
        });
    }
}
