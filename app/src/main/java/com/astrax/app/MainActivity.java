package com.astrax.app;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private TextView screenTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        screenTitle = findViewById(R.id.screen_title);
        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);

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
    }
}
