package com.abhishek.educatorproject;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;

import java.util.HashMap;
import java.util.Objects;

public class RegistrationSuccessfullActivity extends AppCompatActivity {

    private String Name;
    private String phoneNumber;
    private FirebaseAuth auth;
    private String userId;
    private int check=1;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        Objects.requireNonNull(getSupportActionBar()).hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_registration_successfull);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Window window = getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(Color.parseColor("#ffa012"));

        Intent intent5=getIntent();
        Name=intent5.getStringExtra("currentName");
        phoneNumber=intent5.getStringExtra("currentPhoneNumber");
        userId=intent5.getStringExtra("userId");

        HashMap<String, Object> fullname = new HashMap<>();
        fullname.put("Name", Name);
        HashMap<String, Object> upiPhone = new HashMap<>();
        upiPhone.put("UPI PHONE", phoneNumber);

        FirebaseFirestore clientinfo = FirebaseFirestore.getInstance();
        clientinfo.collection("REGISTERED NAME").document(userId).set(fullname);
        clientinfo.collection("REGISTERED NAME").document(userId).set(upiPhone, SetOptions.merge());

        SharedPreferences checksend = getSharedPreferences("check", MODE_PRIVATE);
        SharedPreferences.Editor edit2 = checksend.edit();

        SharedPreferences up = getSharedPreferences("currentPhoneNumber", MODE_PRIVATE);
        SharedPreferences.Editor edit3 = up.edit();


        edit2.putInt("check",check);
        edit2.apply();
        edit3.putString("currentPhoneNumber", phoneNumber);
        edit3.apply();

        Handler mHandler = new Handler();
        mHandler.postDelayed(new Runnable() {

            @Override
            public void run() {
                Intent intentus1=new Intent(RegistrationSuccessfullActivity.this,MainActivity.class);
                intentus1.putExtra("currentName",Name);
                intentus1.putExtra("currentPhoneNumber",phoneNumber);
                intentus1.putExtra("userId",userId);
                intentus1.putExtra("check",check);
                startActivity(intentus1);
            }

        }, 2000L);

    }
    @Override
    public void onBackPressed() {
        this.moveTaskToBack(true);
    }
}