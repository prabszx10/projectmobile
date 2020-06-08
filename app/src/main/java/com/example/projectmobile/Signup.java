package com.example.projectmobile;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class Signup extends AppCompatActivity {
    EditText username,password,reenter,emaill,fullname;
    Button signup, backbutton;
    FirebaseDatabase database;
    DatabaseReference reference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        username = findViewById(R.id.username);
        fullname = findViewById(R.id.fullname);
        password = findViewById(R.id.password);
        reenter = findViewById(R.id.reenter);
        emaill = findViewById(R.id.email);
        signup = findViewById(R.id.signup);
        backbutton=findViewById(R.id.backbutton);

        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(Signup.this, Menu_login.class);
                startActivity(intent);
            }
        });


        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                database = FirebaseDatabase.getInstance();
                reference = database.getReference("user");
                final String usernamee = username.getText().toString();
                final String fullnamee = fullname.getText().toString();
                final String passwordd = password.getText().toString();
                final String repasswordd = reenter.getText().toString();
                final String email = emaill.getText().toString();
                String nowhiteSpace = "\\A\\w{4,20}\\z";

                if (!isConnected()) {
                    new SweetAlertDialog(Signup.this, SweetAlertDialog.ERROR_TYPE).setTitleText("no internet connection").show();
                } else {
                    if (usernamee.isEmpty() || passwordd.isEmpty() || repasswordd.isEmpty() || email.isEmpty() || fullnamee.isEmpty()) {
                        new SweetAlertDialog(Signup.this, SweetAlertDialog.ERROR_TYPE).setTitleText("Field cannot be empty").show();
                    } else if (usernamee.length() >= 15) {
                        new SweetAlertDialog(Signup.this, SweetAlertDialog.ERROR_TYPE).setTitleText("Username must be less than 15 character").show();
                    } else if (!passwordd.equals(repasswordd)) {
                        new SweetAlertDialog(Signup.this, SweetAlertDialog.ERROR_TYPE).setTitleText("Please enter the same password").show();
                        password.setError("Use the same password");
                        reenter.setError("Use the same password");
                    } else if (password.length() < 6) {
                        new SweetAlertDialog(Signup.this, SweetAlertDialog.ERROR_TYPE).setTitleText("Password Must use atleast 6 character ").show();
                    } else {
                        final Loading_PopUp loading_popUp = new Loading_PopUp(Signup.this);
                        loading_popUp.startLoadingDialog();

                        final String userUsername = username.getText().toString().trim();

                        final DatabaseReference reference = FirebaseDatabase.getInstance().getReference("user");
                        Query checkUser = reference.orderByChild("username").equalTo(userUsername);

                        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                if (dataSnapshot.exists()) {
                                    Handler handler = new Handler();
                                    handler.postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            loading_popUp.dissmissDialog();
                                        }
                                    }, 0);
                                    new SweetAlertDialog(Signup.this, SweetAlertDialog.ERROR_TYPE).setTitleText("Username has been used").show();
                                } else {
                                    UserHelper user = new UserHelper(usernamee, fullnamee, passwordd, repasswordd, email);
                                    reference.child(usernamee).setValue(user);
                                    Handler handler = new Handler();
                                    handler.postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            loading_popUp.dissmissDialog();
                                        }
                                    }, 0);
                                    Intent i = new Intent(Signup.this, Menu_login.class);
                                    startActivity(i);
                                    Toast.makeText(getApplicationContext(), "Successfully added data", Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });

                    }
                }
            }
        });
    }

    private boolean isConnected(){

        ConnectivityManager connectivityManager=(ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo=connectivityManager.getActiveNetworkInfo();
        return  networkInfo !=null && networkInfo.isConnected();
    }
}