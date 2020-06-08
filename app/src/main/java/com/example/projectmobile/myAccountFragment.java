package com.example.projectmobile;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class myAccountFragment extends Fragment {
    String usernameedit,fullnameedit, passwordedit,emailedit;
    ImageView profile_image;
    private TextView username1;
    DatabaseReference ref;
    private Button mButton1, mButton2, mButton3, mButton4, mButton5, out;


    public myAccountFragment() {

        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_my_account, container, false);

        usernameedit =getActivity().getIntent().getStringExtra("usernameedit");
        fullnameedit =getActivity().getIntent().getStringExtra("fullnameedit");
        passwordedit =getActivity().getIntent().getStringExtra("passwordedit");
        emailedit =getActivity().getIntent().getStringExtra("emailedit");

        return root;
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        username1= view.findViewById(R.id.username1);
        username1.setText(fullnameedit);

        ref= FirebaseDatabase.getInstance().getReference().child("user");

        ref.child(usernameedit).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    String name=dataSnapshot.child("fullname").getValue().toString();
                    username1.setText(name);

                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });

        mButton1 = (Button) view.findViewById(R.id.button_edit);
        mButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                passData.onDataReceived("test");
                Intent i = new Intent(getActivity(), accountEditActivity.class);
//                Intent i=new Intent(, accountEditActivity.class);
                i.putExtra("usernameedit",usernameedit);
                i.putExtra("fullnameedit",fullnameedit);
                i.putExtra("passwordedit",passwordedit);
                i.putExtra("emailedit",emailedit);
                startActivity(i);
            }
        });

        mButton2 = (Button) view.findViewById(R.id.button_cp);
        mButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain"); // send email as plain text
                intent.putExtra(Intent.EXTRA_EMAIL, new String[] { "MasakKuy2020@gmail.com" });
                intent.putExtra(Intent.EXTRA_SUBJECT, "Feedback for MasakKuy App");
                intent.putExtra(Intent.EXTRA_TEXT, "Kritik dan Saran untuk MasakKuy App adalah...");
                startActivity(Intent.createChooser(intent, "MasakKuy2020"));
            }
        });


        mButton3 = (Button) view.findViewById(R.id.button_pp);
        mButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ppActivity.class);
                startActivity(intent);
            }
        });

        mButton4 = (Button) view.findViewById(R.id.button_help);
        mButton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), helpActivity.class);
                startActivity(intent);
            }
        });

        mButton5 = (Button) view.findViewById(R.id.button_aboutUs);
        mButton5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), about_us2.class);
                startActivity(intent);
            }
        });

        out = view.findViewById(R.id.button_out);
        out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Menu_login.class);
                startActivity(intent);
                getActivity().finish();
            }
        });
        profile_image=view.findViewById(R.id.profile_image);
    }




}

