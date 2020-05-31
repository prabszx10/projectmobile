package com.example.projectmobile;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import cn.pedant.SweetAlert.SweetAlertDialog;

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

        username.setText("");
        password.setText("");


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
                String usernamee= username.getText().toString();
                String passwordd= password.getText().toString();

                if(usernamee.isEmpty()&&passwordd.isEmpty()){
                    new SweetAlertDialog(Menu_login.this, SweetAlertDialog.ERROR_TYPE).setTitleText("Username and Password must be filled").show();
                    username.setError("Field cannot be empty");
                    password.setError("Field cannot be empty");
                }

                else if(usernamee.isEmpty()){
                    new SweetAlertDialog(Menu_login.this, SweetAlertDialog.ERROR_TYPE).setTitleText("Username must be filled").show();
                    username.setError("Field cannot be empty");
                }
                else if(passwordd.isEmpty()){
                    new SweetAlertDialog(Menu_login.this, SweetAlertDialog.ERROR_TYPE).setTitleText("Password must be filled").show();
                    password.setError("Field cannot be empty");
                }
                else{

                    isUser();
                }
            }
        });
    }




    private void isUser() {
        final Loading_PopUp loading_popUp= new Loading_PopUp(Menu_login.this);
        loading_popUp.startLoadingDialog();
        final String userUsername= username.getText().toString().trim();
        final String userPassword= password.getText().toString().trim();

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("user");
        Query checkUser = reference.orderByChild("username").equalTo(userUsername);

        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {


            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){

                    String passwordDb = dataSnapshot.child(userUsername).child("password").getValue(String.class);
                    if(passwordDb.equals(userPassword)){

                        Intent i= new Intent(Menu_login.this,SecondActivity.class);
                        startActivity(i);
                        finish();
                        Handler handler= new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                loading_popUp.dissmissDialog();
                            }
                        },0);

                    }
                    else {
                        Handler handler= new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                loading_popUp.dissmissDialog();
                            }
                        },0);

                        new SweetAlertDialog(Menu_login.this, SweetAlertDialog.ERROR_TYPE).setTitleText("Incorect Username or Password").show();

                       }
                }
                else{
                    Handler handler= new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            loading_popUp.dissmissDialog();
                        }
                    },0);
                    new SweetAlertDialog(Menu_login.this, SweetAlertDialog.ERROR_TYPE).setTitleText("Incorect Username or Password").show();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

}

