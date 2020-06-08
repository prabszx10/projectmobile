package com.example.projectmobile;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.projectmobile._sliders.FragmentSlider;
import com.example.projectmobile._sliders.SliderIndicator;
import com.example.projectmobile._sliders.SliderPagerAdapter;
import com.example.projectmobile._sliders.SliderView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link homeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class homeFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private RecyclerView recyclerView,recyclerView1;
    private ItemAdapter adapter;
    private List<RecipeInput> items,items1;
    private DatabaseReference reference;
    EditText searchRecipe;
    TextView noResult;
    private String mParam1;
    private String mParam2;
    LinearLayout linerlayout;

    private SliderPagerAdapter mAdapter;
    private SliderIndicator mIndicator;

    private SliderView sliderView;
    private LinearLayout mLinearLayout;

    public homeFragment() {
        // Required empty public constructor
    }

    public static homeFragment newInstance(String param1, String param2) {
        homeFragment fragment = new homeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }


    }

    private void DisplayData() {
        reference = FirebaseDatabase.getInstance().getReference("myrecipe");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                items.clear();
                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    RecipeInput recipeInput= snapshot.getValue(RecipeInput.class);
                    items.add(recipeInput);
                }
                adapter= new ItemAdapter(items);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_home_fragment, container, false);
        // Inflate the layout for this fragment
        sliderView = (SliderView) rootView.findViewById(R.id.sliderView);
        mLinearLayout = (LinearLayout) rootView.findViewById(R.id.pagesContainer);
        recyclerView= rootView.findViewById(R.id.recycleView);
        recyclerView1= rootView.findViewById(R.id.recycleView1);
        recyclerView.setHasFixedSize(true);
        recyclerView1.setHasFixedSize(true);
        searchRecipe = rootView.findViewById(R.id.searchRecipe);
        noResult= rootView.findViewById(R.id.noResult);
        linerlayout = rootView.findViewById(R.id.searchVisible);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        RecyclerView.LayoutManager layoutManager1 = new LinearLayoutManager(getContext());
        recyclerView1.setLayoutManager(layoutManager1);


        items = new ArrayList<>();
        items1 = new ArrayList<>();
        linerlayout.setVisibility(View.GONE);
        setUpSliderOffline();
        DisplayData();

        searchRecipe.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                filter(s.toString());
            }
        });
            return rootView;
    }


    private void filter(String text) {
        reference = FirebaseDatabase.getInstance().getReference("myrecipe");
        final String namerecipe= searchRecipe.getText().toString().trim();
        final String sensitivecase= text.toUpperCase();
        final Query checkUser = reference.orderByChild("name").startAt(sensitivecase).endAt(sensitivecase+"\uf8ff");
        final Query checkPublisher = reference.orderByChild("username").startAt(text).endAt(text+"\uf8ff");


        if(!namerecipe.isEmpty()){
            linerlayout.setVisibility(View.VISIBLE);

            checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if(dataSnapshot.exists()){
                        recyclerView1.setVisibility(View.VISIBLE);
                        noResult.setVisibility(View.GONE);
                        checkUser.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                items1.clear();
                                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                                    RecipeInput recipeInput= snapshot.getValue(RecipeInput.class);
                                    items1.add(recipeInput);
                                }
                                adapter= new ItemAdapter(items1);
                                recyclerView1.setAdapter(adapter);
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });
                    }
                    else {
                        noResult.setVisibility(View.VISIBLE);
                        recyclerView1.setVisibility(View.GONE);
                        noResult.setText("No Result For "+searchRecipe.getText());
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });}
        else {
            linerlayout.setVisibility(View.GONE);
        }
    }

    private void setupSlider() {

        sliderView.setDurationScroll(1500);
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(FragmentSlider.newInstance("https://images.pexels.com/photos/3060255/pexels-photo-3060255.jpeg?cs=srgb&dl=makanan-gelap-kelam-hitam-3060255.jpg&fm=jpg"));
        fragments.add(FragmentSlider.newInstance("https://images.pexels.com/photos/1266741/pexels-photo-1266741.jpeg?cs=srgb&dl=berair-beri-buah-fotografi-makanan-1266741.jpg&fm=jpg"));
        fragments.add(FragmentSlider.newInstance("https://images.pexels.com/photos/3217151/pexels-photo-3217151.jpeg?cs=srgb&dl=minuman-kaca-lemon-rumahan-3217151.jpg&fm=jpg"));
        mAdapter = new SliderPagerAdapter(getFragmentManager(), fragments);
        sliderView.setAdapter(mAdapter);
        mIndicator = new SliderIndicator(getActivity(), mLinearLayout, sliderView, R.drawable.indicator_circle);
        mIndicator.setPageCount(fragments.size());
        mIndicator.show();
    }

    private void setUpSliderOffline() {

        List<Fragment> fragments = new ArrayList<>();
        fragments.add(FragmentSlider.newInstance("img1"));
        fragments.add(FragmentSlider.newInstance("img2"));
        fragments.add(FragmentSlider.newInstance("img3"));

        mAdapter = new SliderPagerAdapter(getFragmentManager(), fragments);
        sliderView.setAdapter(mAdapter);
        mIndicator = new SliderIndicator(getActivity(), mLinearLayout, sliderView, R.drawable.indicator_circle);
        mIndicator.setPageCount(fragments.size());
        mIndicator.show();
    }

}