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
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class LoginOtpActivity extends AppCompatActivity {

    private String Name;
    private String phoneNumber;
    private FirebaseAuth auth;
    private String userId;
    private ImageView e1;
    private ImageView acc1;
    private ImageView phs1;
    private ImageView phi1;
    private ImageView b1;
    private Button login1;
    private Button sign1;
    private EditText usercode;
    private EditText ph2;
    private ImageView b;
    private ConstraintLayout c1;
    private TextView info;
    private String codesent;
    private String uc;
    private Button againcode;
    private String enterUpi;
    private String usercodes;
    private String currentName;
    private int check=1;


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        Objects.requireNonNull(getSupportActionBar()).hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login_otp);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Window window = getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(Color.parseColor("#ffa012"));

        Intent intent6=getIntent();
        phoneNumber=(String)intent6.getStringExtra("currentPhoneNumber");

        auth=FirebaseAuth.getInstance();

        e1=findViewById(R.id.e1);
        phs1=findViewById(R.id.phs1);
        b1=findViewById(R.id.b1);
        usercode=(EditText)findViewById(R.id.usercode);
        login1=findViewById(R.id.login2);
        b=findViewById(R.id.b);
        info=findViewById(R.id.info);
        c1=findViewById(R.id.c1);
        againcode=(Button)findViewById(R.id.againcode);

        enterUpi="+91"+phoneNumber;


        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                enterUpi,        // Phone number to verify
                60,                 // Timeout duration
                TimeUnit.SECONDS,   // Unit of timeout
                this,               // Activity (for callback binding)
                new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                    @Override
                    public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {

                    }

                    @Override
                    public void onVerificationFailed(@NonNull FirebaseException e) {
                        Log.d("fail222", "onCreate: ");
                        Log.d("HIB", Name + "  " + enterUpi );

                    }

                    @Override
                    public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                        super.onCodeSent(s, forceResendingToken);
                        Log.d("checkuser1", "onCreate: " + s + " " + codesent);
                        codesent = s;
                        Log.d("HIBOSE2", codesent );
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

        againcode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recallcode(phoneNumber);
            }
        });

        login1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                usercodes = (String) usercode.getText().toString();
                FirebaseFirestore client = FirebaseFirestore.getInstance();
                if (TextUtils.isEmpty(usercodes)) {
                    info.setText("Please Enter your number/otp");
                } else {
                    client.collection("REGISTERED NAME").whereEqualTo("UPI PHONE", phoneNumber).get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                        @Override
                        public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                            if (queryDocumentSnapshots.isEmpty()) {
                                info.setText("Please enter valid details");
                            } else {
                                FirebaseFirestore fs = FirebaseFirestore.getInstance();
                                fs.collection("REGISTERED NAME").whereEqualTo("UPI PHONE", phoneNumber).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                    @Override
                                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                        if (task.isSuccessful()) {
                                            for (QueryDocumentSnapshot doc : task.getResult()) {
                                                currentName = (String) doc.get("Name");
                                                userId = (String) doc.getId();
                                            }
                                        }
                                    }
                                });


                                if (true) {
                                    uc = usercode.getText().toString();
                                    PhoneAuthCredential credential = PhoneAuthProvider.getCredential(codesent, uc);
                                    signInWithPhoneAuthCredential(credential);
                                } else {
                                    info.setText("Please retrieve otp first");
                                }
                            }
                        }
                    });

                }
            }
        });

    }

    private void signInWithPhoneAuthCredential(final PhoneAuthCredential credential) {
        auth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            userId = Objects.requireNonNull(auth.getCurrentUser()).getUid().toString();
                            Log.d("phyche3", userId+" "+phoneNumber+" "+Name+" "+check);
                            try {
                                SharedPreferences checksend = getSharedPreferences("check", MODE_PRIVATE);
                                SharedPreferences.Editor edit2 = checksend.edit();

                                SharedPreferences up = getSharedPreferences("currentPhoneNumber", MODE_PRIVATE);
                                SharedPreferences.Editor edit3 = up.edit();

                                edit2.putInt("check",check);
                                edit2.apply();
                                edit3.putString("currentPhoneNumber", phoneNumber);
                                edit3.apply();

                                // Sign in success, update UI with the signed-in user's information
                                Intent intent5 = new Intent(LoginOtpActivity.this, MainActivity.class);
                                intent5.putExtra("currentPhoneNumber", phoneNumber);
                                intent5.putExtra("Name",Name);
                                intent5.putExtra("userId",userId);
                                intent5.putExtra("check",check);
                                startActivity(intent5);
                            }
                            catch (Exception e)
                            {

                                e.getStackTrace();
                            }
                        } else {
                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                // The verification code entered was invalid
                                info.setText("Incorrect OTP entered");
                            }
                        }
                    }
                });
    }

    private void recallcode(String number)
    {
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                "+91"+number,        // Phone number to verify
                60,                 // Timeout duration
                TimeUnit.SECONDS,   // Unit of timeout
                this,               // Activity (for callback binding)
                new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                    @Override
                    public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {

                    }

                    @Override
                    public void onVerificationFailed(@NonNull FirebaseException e) {
                        Log.d("HIBo", Name + "" + phoneNumber );
                    }

                    @Override
                    public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                        super.onCodeSent(s, forceResendingToken);
                        codesent=s;

                    }
                });        // OnVerificationStateChangedCallbacks

    }
    @Override
    public void onBackPressed() {
        this.moveTaskToBack(true);
    }


}