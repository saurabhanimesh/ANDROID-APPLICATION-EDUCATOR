package com.abhishek.educatorproject;

import androidx.annotation.NonNull;
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
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignUp1 extends AppCompatActivity {

    private ImageView e1;
    private ImageView acc1;
    private ImageView phs1;
    private ImageView phi1;
    private ImageView b1;
    private Button login1;
    private Button sign1;
    private EditText ph1;
    private EditText ph2;
    private ImageView b;
    private ConstraintLayout c1;
    private TextView info;
    private FirebaseAuth auth;
    private String Name;
    private String phoneNumber;
    private int checkcorrectl = 0;


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        Objects.requireNonNull(getSupportActionBar()).hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_sign_up1);
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Window window = getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(Color.parseColor("#ffa012"));

        Intent intent2=getIntent();

        auth=FirebaseAuth.getInstance();




        e1=findViewById(R.id.e1);
        acc1=findViewById(R.id.acc1);
        phs1=findViewById(R.id.phs1);
        phi1=findViewById(R.id.phi1);
        b1=findViewById(R.id.b1);
        sign1=findViewById(R.id.sign1);
        ph1=(EditText)findViewById(R.id.usercode);
        login1=findViewById(R.id.login1);
        ph2=(EditText)findViewById(R.id.ph2);
        b=findViewById(R.id.b);
        info=findViewById(R.id.info);
        c1=findViewById(R.id.c1);

        login1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Name = ph2.getText().toString();
                phoneNumber = ph1.getText().toString();
                Pattern p = Pattern.compile("[^A-Za-z0-9. ]");
                Matcher m = p.matcher(Name);
                boolean b = m.find();


                if (TextUtils.isEmpty(Name) || TextUtils.isEmpty(phoneNumber) || phoneNumber.length() < 10) {
                    ph2.setText(null);
                    ph1.setText(null);
                    if (phoneNumber.length() < 10) {
                        info.setText("PLEASE ENTER A VALID NUMBER");
                    }
                    if(TextUtils.isEmpty(Name) || TextUtils.isEmpty(phoneNumber))
                    {
                        info.setText("ENTER VALID INPUTS");
                    }
                } else {

                    FirebaseFirestore check = FirebaseFirestore.getInstance();
                    if (b) {
                        ph1.setText(null);
                        ph2.setText(null);
                        if (b) {
                            info.setText("INVALID CHARACTER/NUMBER");
                        }
                    } else {

                        checkcorrectl = 0;


                        check.collection("REGISTERED NAME").whereEqualTo("UPI PHONE", phoneNumber).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                if (task.isSuccessful()) {
                                    for (QueryDocumentSnapshot doc : Objects.requireNonNull(task.getResult())) {
                                        info.setText("PHONE NUMBER ALREADY REGISTERD");
                                        checkcorrectl = 1;
                                    }
                                    checkProceed(Name, checkcorrectl);
                                }
                            }
                        });
                    }

                }

            }
        });



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
            public void onClick(View v) {
                Intent intentmain=new Intent(SignUp1.this,MainLoginActivity.class);
                startActivity(intentmain);
            }
        });




    }

    public void checkProceed(String currentName,int value)
    {
        if(value!=1) {
            Log.d("HIBOSE", Name + " " + phoneNumber );
            Intent intent4 = new Intent(SignUp1.this, SaveInformationActivity.class);
            intent4.putExtra("currentPhoneNumber", phoneNumber);
            intent4.putExtra("currentName", Name);
            startActivity(intent4);
        }
    }
}