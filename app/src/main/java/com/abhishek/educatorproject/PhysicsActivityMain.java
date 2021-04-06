package com.abhishek.educatorproject;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.Objects;

public class PhysicsActivityMain extends AppCompatActivity {

    private Button yelnot;
    private Button yelnot2;
    private Button yelnot3;
    private Toolbar toolbar;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        Objects.requireNonNull(getSupportActionBar()).hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_physics_main);
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Window window = getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(Color.parseColor("#ffa012"));

        Intent intent=getIntent();

        toolbar=findViewById(R.id.toolbar2);
        yelnot=findViewById(R.id.yelnot);
        yelnot2=findViewById(R.id.yelnot2);
        yelnot3=findViewById(R.id.yelnot3);





        yelnot.setVisibility(View.INVISIBLE);
        yelnot2.setVisibility(View.INVISIBLE);
        yelnot3.setVisibility(View.INVISIBLE);


        yelnot.setVisibility(View.VISIBLE);
        yelnot.setAlpha(0f);
        yelnot.animate()
                .alpha(1f)
                .setDuration(700);

        yelnot2.setVisibility(View.VISIBLE);
        yelnot2.setAlpha(0f);
        yelnot2.animate()
                .alpha(1f)
                .setDuration(1100);

        yelnot3.setVisibility(View.VISIBLE);
        yelnot3.setAlpha(0f);
        yelnot3.animate()
                .alpha(1f)
                .setDuration(1500);
    }
}