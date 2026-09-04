package com.astrax.app;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    private View homeLayout;
    private TextView otherScreenTitle;
    private View emergencyStudyLayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        homeLayout = findViewById(R.id.layout_home_screen);
        otherScreenTitle = findViewById(R.id.other_screen_title);
        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        FloatingActionButton fabPanic = findViewById(R.id.fab_panic);
        emergencyStudyLayer = findViewById(R.id.emergency_study_layer);
        Button btnCloseEmergency = findViewById(R.id.btn_close_emergency);

        // Header VIP Click
        View crownBtn = findViewById(R.id.btn_vip_crown);
        if (crownBtn != null) {
            crownBtn.setOnClickListener(v -> Toast.makeText(this, "VIP Plan: ₹9/day or ₹99/month", Toast.LENGTH_SHORT).show());
        }

        // Home Screen Shortcuts to other tabs
        View cardReels = findViewById(R.id.card_home_reels);
        if (cardReels != null) {
            cardReels.setOnClickListener(v -> bottomNav.setSelectedItemId(R.id.nav_reels));
        }
        View cardMovies = findViewById(R.id.card_home_movies);
        if (cardMovies != null) {
            cardMovies.setOnClickListener(v -> bottomNav.setSelectedItemId(R.id.nav_movies));
        }
        View cardChat = findViewById(R.id.card_home_chat);
        if (cardChat != null) {
            cardChat.setOnClickListener(v -> bottomNav.setSelectedItemId(R.id.nav_chat));
        }

        // Bottom Navigation Tab Switching
        bottomNav.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if (itemId == R.id.nav_home) {
                homeLayout.setVisibility(View.VISIBLE);
                otherScreenTitle.setVisibility(View.GONE);
                return true;
            } else {
                homeLayout.setVisibility(View.GONE);
                otherScreenTitle.setVisibility(View.VISIBLE);

                if (itemId == R.id.nav_reels) {
                    otherScreenTitle.setText("Reels Player");
                } else if (itemId == R.id.nav_movies) {
                    otherScreenTitle.setText("Movies & Cinema Hub");
                } else if (itemId == R.id.nav_chat) {
                    otherScreenTitle.setText("Chat & Secret Vault");
                } else if (itemId == R.id.nav_profile) {
                    otherScreenTitle.setText("VIP & Profile");
                }
                return true;
            }
        });

        // 1-Tap Panic Trigger
        fabPanic.setOnClickListener(v -> emergencyStudyLayer.setVisibility(View.VISIBLE));

        // Exit Study Mode
        btnCloseEmergency.setOnClickListener(v -> emergencyStudyLayer.setVisibility(View.GONE));
    }

    @Override
    public void onBackPressed() {
        if (emergencyStudyLayer.getVisibility() == View.VISIBLE) {
            emergencyStudyLayer.setVisibility(View.GONE);
        } else {
            super.onBackPressed();
        }
    }
}
