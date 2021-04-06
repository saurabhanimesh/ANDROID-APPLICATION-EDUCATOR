package com.abhishek.educatorproject;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainLoginActivity extends AppCompatActivity {

    private ImageView e1;
    private ImageView acc1;
    private ImageView phs1;
    private ImageView phi1;
    private ImageView b1;
    private Button login1;
    private Button sign1;
    private EditText ph1;
    private ConstraintLayout c1;
    private TextView info;
    private FirebaseAuth auth;
    private String phoneNumber;
    private int check=0;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        Objects.requireNonNull(getSupportActionBar()).hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main_login);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Window window = getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(Color.parseColor("#ffa012"));

        Intent intent1=getIntent();

        auth=FirebaseAuth.getInstance();

        e1=findViewById(R.id.e1);
        acc1=findViewById(R.id.acc1);
        phs1=findViewById(R.id.phs1);
        phi1=findViewById(R.id.phi1);
        b1=findViewById(R.id.b1);
        sign1=findViewById(R.id.sign1);
        ph1=(EditText)findViewById(R.id.usercode);
        login1=findViewById(R.id.login1);
        c1=findViewById(R.id.c1);
        info=findViewById(R.id.info);






        ph1.setAlpha(0f);
        ph1.setVisibility(View.VISIBLE);

        b1.setAlpha(0f);
        b1.setVisibility(View.VISIBLE);


        phi1.setAlpha(0f);
        phi1.setVisibility(View.VISIBLE);

        phs1.setAlpha(0f);
        phs1.setVisibility(View.VISIBLE);

        login1.setAlpha(0f);
        login1.setVisibility(View.VISIBLE);

        acc1.setAlpha(0f);
        acc1.setVisibility(View.VISIBLE);

        sign1.setAlpha(0f);
        sign1.setVisibility(View.VISIBLE);



            Handler mHandler = new Handler();
            mHandler.postDelayed(new Runnable() {

                @Override
                public void run() {


                    ph1.animate()
                            .alpha(1f)
                            .setDuration(500);

                    b1.animate()
                            .alpha(1f)
                            .setDuration(550);



                    phi1.animate()
                            .alpha(1f)
                            .setDuration(600);




                    phs1.animate()
                            .alpha(1f)
                            .setDuration(650);




                    login1.animate()
                            .alpha(1f)
                            .setDuration(700);




                    acc1.animate()
                            .alpha(1f)
                            .setDuration(750);

                    sign1.animate()
                            .alpha(1f)
                            .setDuration(800);


                }

            }, 1300L);

        c1.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View view) {
                try {
                    InputMethodManager imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(Objects.requireNonNull(getCurrentFocus()).getWindowToken(), 0);
                }
                catch (Exception p)
                {
                    p.getStackTrace();
                }

            }
        });

            sign1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent2=new Intent(MainLoginActivity.this,SignUp1.class);
                    startActivity(intent2);
                }
            });

            login1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    FirebaseFirestore client = FirebaseFirestore.getInstance();
                    phoneNumber = (String) ph1.getText().toString();

                    if (TextUtils.isEmpty(phoneNumber)) {
                        info.setText("Please Enter your number");
                    } else {

                        client.collection("REGISTERED NAME").whereEqualTo("UPI PHONE", phoneNumber).get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                            @Override
                            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                                if (queryDocumentSnapshots.isEmpty()) {
                                    info.setText("Phone number not registered");
                                } else {
                                    Intent intent6=new Intent(MainLoginActivity.this,LoginOtpActivity.class);
                                    intent6.putExtra("currentPhoneNumber",phoneNumber);
                                    startActivity(intent6);
                                }
                            }
                        });

                    }

                }
            });


    }
    @Override
    public void onBackPressed() {
        this.moveTaskToBack(true);
    }
}