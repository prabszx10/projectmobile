package com.example.projectmobile;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.List;

public class DetailInternasional extends AppCompatActivity {
    private TextView namaResep,publisher,ingredients, steps;
    Button backbutton;
    ImageView imageBig, imageSmall;
    DatabaseReference ref;
    private List<RecipeInput> items;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_internasional);
        namaResep = findViewById(R.id.namaResep);
        backbutton= findViewById(R.id.backbutton);
        publisher= findViewById(R.id.publisher);
        ingredients = findViewById(R.id.ingredients);
        steps = findViewById(R.id.steps);
        imageBig =findViewById(R.id.imageBig);
        imageSmall =findViewById(R.id.imageSmall);
        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        ref= FirebaseDatabase.getInstance().getReference().child("topinternasional");
        Bundle dataExtra = getIntent().getExtras();
        String keys= dataExtra.getString("keys");

        ref.child(keys).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    String name=dataSnapshot.child("name").getValue().toString();
                    String publishBy= dataSnapshot.child("username").getValue().toString();
                    String recipeImage =dataSnapshot.child("recipeimage").getValue().toString();
                    String ingredient = dataSnapshot.child("ingredients").getValue().toString();
                    String step = dataSnapshot.child("step").getValue().toString();

                    Picasso.get().load(recipeImage).into(imageBig);
                    Picasso.get().load(recipeImage).into(imageSmall);
                    publisher.setText("Publish by :"+publishBy);
                    namaResep.setText(name);
                    ingredients.setText(ingredient);
                    steps.setText(step);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }
}