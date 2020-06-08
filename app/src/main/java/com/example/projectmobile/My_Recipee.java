package com.example.projectmobile;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.HashMap;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class My_Recipee extends AppCompatActivity {

    private static final int REQUEST_CODE_IMAGE=101;
    String usernameedit;
    EditText idname, idingredients, idstep;
    Button button, backbutton;
    ImageButton imagebutton;
    ProgressBar progressBar;
    TextView textViewProgress;

    Uri imageUri;
    boolean isImageAdded=false;

    FirebaseDatabase database;
    DatabaseReference myRef,myRef2;
    StorageReference storageref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my__recipee);

        idname= findViewById(R.id.idName);
        button= findViewById(R.id.button);
        idingredients=findViewById(R.id.ingredients);
        idstep= findViewById(R.id.step);
        imagebutton=findViewById(R.id.imageViewAdd);
        progressBar=findViewById(R.id.progressBar);
        textViewProgress=findViewById(R.id.textViewProgress);

        usernameedit =getIntent().getStringExtra("usernameedit");

        textViewProgress.setVisibility(View.GONE);
        progressBar.setVisibility(View.GONE);

        backbutton=findViewById(R.id.backbutton);
        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        insertData();

    }
    private boolean isConnected(){

        ConnectivityManager connectivityManager=(ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo=connectivityManager.getActiveNetworkInfo();
        return  networkInfo !=null && networkInfo.isConnected();
    }

    private void insertData() {
        database = FirebaseDatabase.getInstance();
        myRef= database.getReference("myrecipe");
        myRef2=database.getReference().child("user").child(usernameedit).child("ownrecipe");
        storageref= FirebaseStorage.getInstance().getReference().child("Recipe_images");


        imagebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent,REQUEST_CODE_IMAGE);
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!isConnected()) {
                    new SweetAlertDialog(My_Recipee.this, SweetAlertDialog.ERROR_TYPE).setTitleText("no internet connection").show();
                } else {

                    final String name = idname.getText().toString().toUpperCase();
                    final String ingredients = idingredients.getText().toString();
                    final String step = idstep.getText().toString();
                    final String username = usernameedit;
                    if (isImageAdded != false && name != null && ingredients != null && step != null && username != null) {
                        uploadImage(name,ingredients,step,username);

                    }
                    else{
                        new SweetAlertDialog(My_Recipee.this, SweetAlertDialog.ERROR_TYPE).setTitleText("All fields are required!").show();
                    }
                }
            }
        });
    }
    private void uploadImage(final String name, final String ingredients,final String step, final String username) {

        textViewProgress.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.VISIBLE);


        final String key = myRef.push().getKey();
        storageref.child(key+".jpg").putFile(imageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                storageref.child(key+".jpg").getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        HashMap hashMap= new HashMap();
                        hashMap.put("name",name);
                        hashMap.put("step",step);
                        hashMap.put("ingredients",ingredients);
                        hashMap.put("id",key);
                        hashMap.put("username",username);
                        hashMap.put("recipeimage",uri.toString());


                        myRef.child(key).setValue(hashMap).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                finish();
                                 Toast.makeText(My_Recipee.this,"Data Successfully Uploaded", Toast.LENGTH_SHORT).show();
                            }
                        });

                        myRef2.child(key).setValue(hashMap).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                finish();
                                Toast.makeText(My_Recipee.this,"Data Successfully Uploaded", Toast.LENGTH_SHORT).show();
                            }
                        });

                    }
                });
            }

        }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                double progress=(taskSnapshot.getBytesTransferred()*100)/taskSnapshot.getTotalByteCount();
                progressBar.setProgress((int)progress);
                textViewProgress.setText(progress+" %");
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==REQUEST_CODE_IMAGE && data!=null)
        {
            imageUri=data.getData();
            isImageAdded=true;
        }
    }
}