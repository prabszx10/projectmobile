package com.example.projectmobile;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class topFragment extends Fragment {
    private Button mButton1, mButton2;
    private RecyclerView recyclerView, recyclerView1;
    private ItemAdapter adapter;
    private List<Internasional> items;
    private Itemlocal adapterlocal;
    private ItemInternasional adapterint;
    private List<Local> items1;
    private DatabaseReference reference, reference2;


    public topFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_top, container, false);
        recyclerView = root.findViewById(R.id.recycleint);
        recyclerView1 = root.findViewById(R.id.recyclelocal);
        recyclerView.setHasFixedSize(true);
        recyclerView1.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        RecyclerView.LayoutManager layoutManager1 = new LinearLayoutManager(getContext());
        recyclerView1.setLayoutManager(layoutManager1);
        items = new ArrayList<>();
        items1 = new ArrayList<>();
        displaylocal();
        displayint();

        return root;
    }

    private void displayint() {
        reference = FirebaseDatabase.getInstance().getReference("topinternasional");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                items.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Internasional internasional = snapshot.getValue(Internasional.class);
                    items.add(internasional);
                }
                adapterint = new ItemInternasional(items);
                recyclerView.setAdapter(adapterint);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void displaylocal() {
        reference2 = FirebaseDatabase.getInstance().getReference("toplocal");
        reference2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                items1.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Local local = snapshot.getValue(Local.class);
                    items1.add(local);
                }
                adapterlocal = new Itemlocal(items1);
                recyclerView1.setAdapter(adapterlocal);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

    }
}
