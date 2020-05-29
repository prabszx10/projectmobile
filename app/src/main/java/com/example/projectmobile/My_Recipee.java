package com.example.projectmobile;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class My_Recipee extends AppCompatActivity {

    EditText idname, idstep;
    Button button;

    FirebaseDatabase database;
    DatabaseReference myRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my__recipee);
        idname= findViewById(R.id.idName);
        button= findViewById(R.id.button);
        idstep= findViewById(R.id.step);

        insertData();

    }

    private void insertData() {
        database = FirebaseDatabase.getInstance();
        myRef= database.getReference("myrecipe");

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = idname.getText().toString();
                String step = idstep.getText().toString();
                long mDateTime = 999999999999L -System.currentTimeMillis();
                String mOrderTime = String.valueOf(mDateTime);

                RecipeInput recipeInput= new RecipeInput(step,name,mOrderTime);

                myRef.child(mOrderTime).setValue(recipeInput).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(getApplicationContext(),"Success",Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getApplicationContext(),"Fail",Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}
