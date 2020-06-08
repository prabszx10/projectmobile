package com.example.projectmobile;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class Itemlocal extends RecyclerView.Adapter<Itemlocal.ViewHolder> {
    private Context context;
    private List<Local> items;
    View v;

    public Itemlocal(List<Local> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item,parent,false);
        return new Itemlocal.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        final Local item =items.get(position);
        holder.dis_name.setText(item.name);
        holder.dis_publishBy.setText("Published By: "+item.username);
        Picasso.get().load(item.recipeimage).into(holder.imageFood1);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(v.getContext(), DetailLocal.class);
                intent.putExtra("keys",item.getId().toString());
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView dis_name, dis_publishBy;
        private ImageView imageFood1;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            dis_name= itemView.findViewById(R.id.dis_name);
            dis_publishBy =itemView.findViewById(R.id.dis_publishBy);
            imageFood1= itemView.findViewById(R.id.imageFood1);
        }
    }

}