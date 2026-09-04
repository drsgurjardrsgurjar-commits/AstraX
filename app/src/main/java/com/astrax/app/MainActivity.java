package com.astrax.app;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    private TextView screenTitle;
    private View emergencyStudyLayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        screenTitle = findViewById(R.id.screen_title);
        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        FloatingActionButton fabPanic = findViewById(R.id.fab_panic);
        emergencyStudyLayer = findViewById(R.id.emergency_study_layer);
        Button btnCloseEmergency = findViewById(R.id.btn_close_emergency);

        // Bottom Navigation Tabs
        bottomNav.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if (itemId == R.id.nav_home) {
                screenTitle.setText("Home Screen");
                return true;
            } else if (itemId == R.id.nav_reels) {
                screenTitle.setText("Reels Screen");
                return true;
            } else if (itemId == R.id.nav_movies) {
                screenTitle.setText("Movies Screen");
                return true;
            } else if (itemId == R.id.nav_chat) {
                screenTitle.setText("Chat Screen");
                return true;
            } else if (itemId == R.id.nav_profile) {
                screenTitle.setText("Profile Screen");
                return true;
            }
            return false;
        });

        // 1-Tap Instant Stealth Trigger
        fabPanic.setOnClickListener(v -> {
            emergencyStudyLayer.setVisibility(View.VISIBLE);
        });

        // Exit Study Mode
        btnCloseEmergency.setOnClickListener(v -> {
            emergencyStudyLayer.setVisibility(View.GONE);
        });
    }

    // Android back button support for panic layer
    @Override
    public void onBackPressed() {
        if (emergencyStudyLayer.getVisibility() == View.VISIBLE) {
            emergencyStudyLayer.setVisibility(View.GONE);
        } else {
            super.onBackPressed();
        }
    }
}
