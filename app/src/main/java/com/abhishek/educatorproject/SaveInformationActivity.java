package com.abhishek.educatorproject;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
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
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class SaveInformationActivity extends AppCompatActivity {

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


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        Objects.requireNonNull(getSupportActionBar()).hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_save_information);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Window window = getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(Color.parseColor("#ffa012"));

        Intent intent4=getIntent();


        Name=(String)intent4.getStringExtra("currentName");
        phoneNumber=(String)intent4.getStringExtra("currentPhoneNumber");

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




        enterUpi = "+91"+phoneNumber;

        Log.d("fail222", "onCreasdste8888 ");

        PhoneAuthOptions options =
                PhoneAuthOptions.newBuilder(auth)
                        .setPhoneNumber(enterUpi)       // Phone number to verify
                        .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                        .setActivity(this)                 // Activity (for callback binding)
                        .setCallbacks(new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                            @Override
                            public void onCodeSent(String s,
                                                   PhoneAuthProvider.ForceResendingToken forceResendingToken) {

                                super.onCodeSent(s, forceResendingToken);
                                Log.d("checkuser1", "onCreate: " + s + " " + codesent);
                                codesent = s;
                                Log.d("HIBOSE2", codesent );
                                // Save the verification id somewhere
                                // ...

                                // The corresponding whitelisted code above should be used to complete sign-in.

                            }

                            @Override
                            public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {

                                Log.d("fail222", "onCreate: ");
                                Log.d("HIB", Name + "  " + enterUpi );
                                // Sign in with the credential
                                // ...
                            }

                            @Override
                            public void onVerificationFailed(FirebaseException e) {
                                // ...
                            }
                        })          // OnVerificationStateChangedCallbacks
                        .build();
        PhoneAuthProvider.verifyPhoneNumber(options);




                login1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        uc = (String) usercode.getText().toString();

                        if (TextUtils.isEmpty(uc)) {
                            info.setText("Please enter otp");
                        } else {

                            if (codesent != null) {
                                PhoneAuthCredential credential = PhoneAuthProvider.getCredential(codesent, uc);
                                signInWithPhoneAuthCredential(credential);
                            } else {
                                info.setText("Invalid Otp");
                            }
                        }
                    }
                });


                againcode.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        recallcode(phoneNumber);
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



    }

    private void signInWithPhoneAuthCredential(final PhoneAuthCredential credential) {
        auth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            userId = Objects.requireNonNull(auth.getCurrentUser()).getUid().toString();
                            try {

                                // Sign in success, update UI with the signed-in user's information
                                Intent intent5 = new Intent(SaveInformationActivity.this, RegistrationSuccessfullActivity.class);
                                intent5.putExtra("currentPhoneNumber", phoneNumber);
                                intent5.putExtra("currentName", Name);
                                intent5.putExtra("userId", userId);
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
    public boolean hasInternetConnectivity() {
        ConnectivityManager cm =
                (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return (activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting());
    }
    @Override
    public void onBackPressed() {
        this.moveTaskToBack(true);
    }
}