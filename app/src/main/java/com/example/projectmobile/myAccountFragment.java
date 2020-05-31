package com.example.projectmobile;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class myAccountFragment extends Fragment {

    private Button mButton1, mButton2, mButton3, mButton4, out;

    public myAccountFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_my_account, container, false);
        return root;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        mButton1 = (Button) view.findViewById(R.id.button_edit);
        mButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), accountEditActivity.class);
                startActivity(intent);
            }
        });

        mButton2 = (Button) view.findViewById(R.id.button_dm);
        mButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), dmActivity.class);
                startActivity(intent);
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

        out = view.findViewById(R.id.button_out);
        out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Menu_login.class);
                startActivity(intent);
                getActivity().finish();
            }
        });
    }
}

