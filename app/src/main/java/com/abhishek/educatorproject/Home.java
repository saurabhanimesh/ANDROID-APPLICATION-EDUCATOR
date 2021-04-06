package com.abhishek.educatorproject;

import androidx.annotation.RequiresApi;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProviders;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

import static android.content.Context.MODE_PRIVATE;

public class Home extends Fragment {

    private int check=0;
    private String Name;
    private String phoneNumber;
    private FirebaseAuth auth;
    private String userId;
    private int check2=0;
    private Button phybut;
    private Button chembut;
    private Button biobut;
    private Button hombut;
    private Button notbut;
    private Button resbut;
    private Button testbut;
    private Button lecbut;
    private Button yelnot1;
    private Button yelnot2;
    private Button yelnot3;
    private ImageView sub;
    private ImageView homete;
    private ImageView noteste;
    private ImageView resultte;
    private ImageView lecte;
    private ImageView testte;
    private int checkin=0;
    private int phycheck=0;
    private int chemcheck=0;
    private int biocheck=0;
    Toolbar toolbar;

    private HomeViewModel mViewModel;

    public static Home newInstance() {
        return new Home();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.home_fragment, container, false);

        phybut=(Button)view.findViewById(R.id.phybut);
        chembut=(Button)view.findViewById(R.id.chembut);
        biobut=view.findViewById(R.id.biobut);
        hombut=view.findViewById(R.id.hombut);
        notbut=view.findViewById(R.id.notbut);
        resbut=view.findViewById(R.id.resbut);
        testbut=view.findViewById(R.id.testbut);
        lecbut=view.findViewById(R.id.lecbut);
        homete=view.findViewById(R.id.homete);
        sub=view.findViewById(R.id.sub);
        noteste=view.findViewById(R.id.noteste);
        resultte=view.findViewById(R.id.resultte);
        testte=view.findViewById(R.id.testte);
        lecte=view.findViewById(R.id.lecte);
        toolbar = view.findViewById(R.id.toolbar);


        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
        // TODO: Use the ViewModel

        phybut.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),PhysicsActivityMain.class);
                startActivity(intent);
            }
        });

        chembut.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(getActivity(),ChemistryActivityMain.class);
                startActivity(intent);


            }
        });

        biobut.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(getActivity(),BioActivityMain.class);
                startActivity(intent);

            }
        });



        notbut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });

        lecbut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });

        testbut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });

        resbut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }




}