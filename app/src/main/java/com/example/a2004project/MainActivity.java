package com.example.a2004project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    DrawerLayout drawerLayout;
    ImageView menu;
    LinearLayout home,settings,share,logout;

    private CardView D1 = null;
    private CardView D2 = null;
    private CardView D3 = null;
    private CardView D4 = null;
    private CardView D5 = null;
    private CardView D6 = null;
    private CardView D7 = null;

    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        auth = FirebaseAuth.getInstance();

        D1 = findViewById(R.id.learnMonths);
        D2 = findViewById(R.id.learnDays);
        D3 = findViewById(R.id.learnWeather);
        D4 = findViewById(R.id.learnDirections);
        D5 = findViewById(R.id.learnSpelling);
        D6 = findViewById(R.id.learnMultiplication);
        D7 = findViewById(R.id.learnClock);






        D1.setOnClickListener(this);
        D2.setOnClickListener(this);
        D3.setOnClickListener(this);
        D4.setOnClickListener(this);
        D5.setOnClickListener(this);
        D6.setOnClickListener(this);
        D7.setOnClickListener(this);


        drawerLayout = findViewById(R.id.drawerLayout);
        menu = findViewById(R.id.menu);
        home = findViewById(R.id.home);
        logout = findViewById(R.id.logout);
        settings = findViewById(R.id.settings);
        share = findViewById(R.id.Share);

        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDrawer(drawerLayout);
            }
        });
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recreate();
            }
        });
        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                redirectActivity(MainActivity.this, PlayActivity.class);
            }
        });
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                redirectActivity(MainActivity.this,ShareActivity.class);
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Logout", Toast.LENGTH_SHORT).show();
                auth.signOut();
                redirectActivity(MainActivity.this,SignInActivity.class);
            }
        });
    }
    public static void openDrawer(DrawerLayout drawerLayout){
        drawerLayout.openDrawer(GravityCompat.START);
    }
    public static void closeDrawer(DrawerLayout drawerLayout){
        if(drawerLayout.isDrawerOpen(GravityCompat.START))
            drawerLayout.closeDrawer(GravityCompat.START);

    }
    public static void redirectActivity(Activity activity, Class secondActivity){
        Intent intent = new Intent(activity,secondActivity);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        activity.startActivity(intent);
        activity.finish();
    }
    @Override
    protected void onPause() {
        super.onPause();
        closeDrawer(drawerLayout);
    }

    @Override
    public void onClick(View v) {
        Intent i;
        switch (v.getId()) {
            case R.id.learnMonths:
                i = new Intent(this, LearnMonthsActivity.class);
                break;
            case R.id.learnDays:
                i = new Intent(this, LearnDaysActivity.class);
                break;
            case R.id.learnWeather:
                i = new Intent(this, LearnWeatherActivity.class);
                break;
            case R.id.learnDirections:
                i = new Intent(this, LearnDirectionsActivity.class);
                break;
            case R.id.learnClock:
                i = new Intent(this, LearnClockActivity.class);
                break;
            case R.id.learnSpelling:
                i = new Intent(this, LearnSpellingActivity.class);
                break;
            case R.id.learnMultiplication:
                i = new Intent(this, LearnMultiplicationActivity.class);
                break;
            default:
                throw new IllegalArgumentException("Unhandled view ID: " + v.getId());
        }
        startActivity(i);
    }
}