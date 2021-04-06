package com.abhishek.educatorproject;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

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



    private AppBarConfiguration mAppBarConfiguration;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Intent intentus1=getIntent();
        check2=intentus1.getIntExtra("check2",0);
        phoneNumber=intentus1.getStringExtra("currentPhoneNumber");

        Intent intent5=getIntent();
        check=intent5.getIntExtra("check",0);
        userId=intent5.getStringExtra("userId");
        phoneNumber=intent5.getStringExtra("currentPhoneNumber");
        Name=intent5.getStringExtra("Name");


        SharedPreferences getShared1=getSharedPreferences("currentPhoneNumber",MODE_PRIVATE);
        phoneNumber=getShared1.getString("currentPhoneNumber","currentPhoneNumber");

        SharedPreferences getShared2=getSharedPreferences("check",MODE_PRIVATE);
        check=getShared2.getInt("check",0);



        Log.d("phyche32", userId+" "+phoneNumber+" "+Name+" "+check);




        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

       if(check == 0) {

           Log.d("phyche321", userId+" "+phoneNumber+" "+Name+" "+check);

           super.onCreate(savedInstanceState);
           getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
           Window window = getWindow();
           window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
           window.setStatusBarColor(Color.parseColor("#ffa012"));
           this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
           Log.d("phyche", check+""+phoneNumber);
           Intent intent1 = new Intent(MainActivity.this, MainLoginActivity.class);
           startActivity(intent1);
       }
       if(check == 1 && user != null) {
           super.onCreate(savedInstanceState);
           getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
           Window window = getWindow();
           window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
           window.setStatusBarColor(Color.parseColor("#FF7A5A"));
           this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
           if(check2==0 && TextUtils.isEmpty(userId)) {
               Intent intentus2 = new Intent(MainActivity.this, UserScreen1.class);
               intentus2.putExtra("currentPhoneNumber",phoneNumber);
               startActivity(intentus2);
           }
           setContentView(R.layout.activity_main);
           toolbar = findViewById(R.id.toolbar);
           setSupportActionBar(toolbar);
           DrawerLayout drawer = findViewById(R.id.drawer_layout);
           NavigationView navigationView = findViewById(R.id.nav_view);
           // Passing each menu ID as a set of Ids because each
           // menu should be considered as top level destinations.
           mAppBarConfiguration = new AppBarConfiguration.Builder(
                   R.id.home,R.id.profile, R.id.logout)
                   .setDrawerLayout(drawer)
                   .build();
           NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
           NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
           NavigationUI.setupWithNavController(navigationView, navController);

           phybut=(Button)findViewById(R.id.phybut);
           chembut=(Button)findViewById(R.id.chembut);
           biobut=findViewById(R.id.biobut);
           hombut=findViewById(R.id.hombut);
           notbut=findViewById(R.id.notbut);
           resbut=findViewById(R.id.resbut);
           testbut=findViewById(R.id.testbut);
           lecbut=findViewById(R.id.lecbut);
           homete=findViewById(R.id.homete);
           sub=findViewById(R.id.sub);
           noteste=findViewById(R.id.noteste);
           resultte=findViewById(R.id.resultte);
           testte=findViewById(R.id.testte);
           lecte=findViewById(R.id.lecte);

           /*phybut.setOnClickListener(new View.OnClickListener() {
               @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
               @Override
               public void onClick(View v) {
                   checkin=1;
                   phycheck=1;

                   phybut.setVisibility(View.INVISIBLE);
                   chembut.setVisibility(View.INVISIBLE);
                   biobut.setVisibility(View.INVISIBLE);
                   hombut.setVisibility(View.INVISIBLE);
                   notbut.setVisibility(View.INVISIBLE);
                   resbut.setVisibility(View.INVISIBLE);
                   testbut.setVisibility(View.INVISIBLE);
                   lecbut.setVisibility(View.INVISIBLE);
                   lecte.setVisibility(View.INVISIBLE);
                   testte.setVisibility(View.INVISIBLE);
                   resultte.setVisibility(View.INVISIBLE);
                   noteste.setVisibility(View.INVISIBLE);
                   homete.setVisibility(View.INVISIBLE);
                   sub.setVisibility(View.INVISIBLE);


                   toolbar.setBackgroundColor(Color.parseColor("#ffb85f"));

                   getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
                   Window window = getWindow();
                   window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                   window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                   window.setStatusBarColor(Color.parseColor("#ffb85f"));




                   yelnot1.setVisibility(View.VISIBLE);
                   yelnot1.setAlpha(0f);
                   yelnot1.animate()
                           .alpha(1f)
                           .setDuration(500);
                   yelnot2.setVisibility(View.VISIBLE);
                   yelnot2.setAlpha(0f);
                   yelnot2.animate()
                           .alpha(1f)
                           .setDuration(500);
                   yelnot3.setVisibility(View.VISIBLE);
                   yelnot3.setAlpha(0f);
                   yelnot3.animate()
                           .alpha(1f)
                           .setDuration(500);

               }
           });

           chembut.setOnClickListener(new View.OnClickListener() {
               @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
               @Override
               public void onClick(View v) {

                   phybut.setVisibility(View.INVISIBLE);
                   chembut.setVisibility(View.INVISIBLE);
                   biobut.setVisibility(View.INVISIBLE);
                   hombut.setVisibility(View.INVISIBLE);
                   notbut.setVisibility(View.INVISIBLE);
                   resbut.setVisibility(View.INVISIBLE);
                   testbut.setVisibility(View.INVISIBLE);
                   lecbut.setVisibility(View.INVISIBLE);
                   lecte.setVisibility(View.INVISIBLE);
                   testte.setVisibility(View.INVISIBLE);
                   resultte.setVisibility(View.INVISIBLE);
                   noteste.setVisibility(View.INVISIBLE);
                   homete.setVisibility(View.INVISIBLE);
                   sub.setVisibility(View.INVISIBLE);
                   checkin=1;
                   chemcheck=1;

                   toolbar.setBackgroundColor(Color.parseColor("#ffb85f"));

                   getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
                   Window window = getWindow();
                   window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                   window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                   window.setStatusBarColor(Color.parseColor("#ffb85f"));

                   yelnot1.setVisibility(View.VISIBLE);
                   yelnot1.setAlpha(0f);
                   yelnot1.animate()
                           .alpha(1f)
                           .setDuration(500);
                   yelnot2.setVisibility(View.VISIBLE);
                   yelnot2.setAlpha(0f);
                   yelnot2.animate()
                           .alpha(1f)
                           .setDuration(500);
                   yelnot3.setVisibility(View.VISIBLE);
                   yelnot3.setAlpha(0f);
                   yelnot3.animate()
                           .alpha(1f)
                           .setDuration(500);

               }
           });

           biobut.setOnClickListener(new View.OnClickListener() {
               @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
               @Override
               public void onClick(View v) {

                   phybut.setVisibility(View.INVISIBLE);
                   chembut.setVisibility(View.INVISIBLE);
                   biobut.setVisibility(View.INVISIBLE);
                   hombut.setVisibility(View.INVISIBLE);
                   notbut.setVisibility(View.INVISIBLE);
                   resbut.setVisibility(View.INVISIBLE);
                   testbut.setVisibility(View.INVISIBLE);
                   lecbut.setVisibility(View.INVISIBLE);
                   lecte.setVisibility(View.INVISIBLE);
                   testte.setVisibility(View.INVISIBLE);
                   resultte.setVisibility(View.INVISIBLE);
                   noteste.setVisibility(View.INVISIBLE);
                   homete.setVisibility(View.INVISIBLE);
                   sub.setVisibility(View.INVISIBLE);
                   checkin=1;
                   biocheck=1;

                   toolbar.setBackgroundColor(Color.parseColor("#ffb85f"));

                   getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
                   Window window = getWindow();
                   window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                   window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                   window.setStatusBarColor(Color.parseColor("#ffb85f"));

                   yelnot1.setVisibility(View.VISIBLE);
                   yelnot1.setAlpha(0f);
                   yelnot1.animate()
                           .alpha(1f)
                           .setDuration(500);
                   yelnot2.setVisibility(View.VISIBLE);
                   yelnot2.setAlpha(0f);
                   yelnot2.animate()
                           .alpha(1f)
                           .setDuration(500);
                   yelnot3.setVisibility(View.VISIBLE);
                   yelnot3.setAlpha(0f);
                   yelnot3.animate()
                           .alpha(1f)
                           .setDuration(500);


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
           });*/

           FirebaseFirestore fs=FirebaseFirestore.getInstance();
           fs.collection("REGISTERED NAME").whereEqualTo("UPI PHONE",phoneNumber).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
               @RequiresApi(api = Build.VERSION_CODES.KITKAT)
               @Override
               public void onComplete(@NonNull Task<QuerySnapshot> task) {
                   if(task.isSuccessful())
                   {
                       for(QueryDocumentSnapshot doc: Objects.requireNonNull(task.getResult()))
                       {
                           Name=(String)doc.get("Name");
                           userId=(String)doc.getId();
                       }
                   }
               }
           });


       }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onBackPressed() {

    }
}