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
import com.example.a2004project.databinding.ActivitySettingsBinding;
public class PlayActivity extends AppCompatActivity implements View.OnClickListener {

    DrawerLayout drawerLayout;
    ImageView menu;
    LinearLayout home,settings,share,logout;

    private ActivitySettingsBinding binding;

    private CardView D1 = null;
    private CardView D2 = null;
    private CardView D3 = null;
    private CardView D4 = null;
    private CardView D5 = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        binding = ActivitySettingsBinding.inflate(getLayoutInflater());

        D1 = findViewById(R.id.followBall);
        D2 = findViewById(R.id.rememberNumbers);
        D3 = findViewById(R.id.multiplication);
        D4 = findViewById(R.id.matchingGame);
        D5 = findViewById(R.id.rememberBackwards);





        D1.setOnClickListener(this);
        D2.setOnClickListener(this);
        D3.setOnClickListener(this);
        D4.setOnClickListener(this);
        D5.setOnClickListener(this);

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
                redirectActivity(PlayActivity.this,MainActivity.class);
            }
        });
        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recreate();
            }
        });
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                redirectActivity(PlayActivity.this,ShareActivity.class);
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(PlayActivity.this, "Logout", Toast.LENGTH_SHORT).show();
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
            case R.id.followBall:
                i = new Intent(this, FollowBallActivity.class);
                break;
            case R.id.rememberNumbers:
                i = new Intent(this, RememberNumbersActivity.class);
                break;
            case R.id.multiplication:
                i = new Intent(this, PlayMultiplicationActivity.class);
                break;
            case R.id.matchingGame:
                i = new Intent(this, PlayMatchingCardActivity.class);
                break;
            case R.id.rememberBackwards:
                i = new Intent(this, RememberBackwardsActivity.class);
                break;
            default:
                throw new IllegalArgumentException("Unhandled view ID: " + v.getId());
        }
        startActivity(i);
    }
}