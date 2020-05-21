package com.example.projectmobile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.List;

public class FindRecipe extends AppCompatActivity {

    private RecyclerView mrecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_list);
        mrecyclerView = findViewById(R.id.recycleView);
        new FirebaseDatabaseHelper().readDb(new FirebaseDatabaseHelper.Datastatus() {
            @Override
            public void DataIsLoaded(List<Recipe> recipes, List<String> keys) {
                new RecyclerView_Config().setConfig(mrecyclerView, FindRecipe.this,recipes,keys);
            }

            @Override
            public void DataIsInserted() {

            }

            @Override
            public void DataIsUpdated() {

            }

            @Override
            public void DataIsDeleted() {

            }
        });
    }
}
