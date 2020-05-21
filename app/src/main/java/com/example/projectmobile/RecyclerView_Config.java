package com.example.projectmobile;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerView_Config {
    private Context mContext;
    private RecipeAdapter mRecipeAdapter;

    public void setConfig(RecyclerView recyclerView, Context context,List<Recipe> recipes, List<String> keys){
        mContext= context;
        mRecipeAdapter= new RecipeAdapter(recipes, keys);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(mRecipeAdapter);
    }
    class RecipeItemView extends RecyclerView.ViewHolder{

        private TextView idNama, idAsal;
        private String key;
        public RecipeItemView(ViewGroup parent) {
            super(LayoutInflater.from(mContext).inflate(R.layout.recipe_list_item, parent,false));

            idNama= itemView.findViewById(R.id.idNama);
            idAsal= itemView.findViewById(R.id.idAsal);

        }

        public void bind(Recipe recipe,String key){
            idNama.setText(recipe.getName());
            idAsal.setText(recipe.getAsal());
            this.key=key;
        }
    }
    class RecipeAdapter extends  RecyclerView.Adapter<RecipeItemView>{
        private List<Recipe> mRecipeList;
        private List<String> mKeys;

        public RecipeAdapter(List<Recipe> mRecipeList, List<String> mKeys) {
            this.mRecipeList = mRecipeList;
            this.mKeys = mKeys;
        }

        @NonNull
        @Override
        public RecipeItemView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new RecipeItemView(parent);
        }

        @Override
        public void onBindViewHolder(@NonNull RecipeItemView holder, int position) {
            holder.bind(mRecipeList.get(position), mKeys.get(position));
        }

        @Override
        public int getItemCount() {
            return mRecipeList.size();
        }
    }


}
