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
    private View layoutToolsHub;
    private TextView toolsCategoryTitle;
    private TextView toolsListText;
    private TextView otherScreenTitle;
    private View emergencyStudyLayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        homeLayout = findViewById(R.id.layout_home_screen);
        layoutToolsHub = findViewById(R.id.layout_tools_hub);
        toolsCategoryTitle = findViewById(R.id.tools_category_title);
        toolsListText = findViewById(R.id.tools_list_text);
        otherScreenTitle = findViewById(R.id.other_screen_title);
        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        FloatingActionButton fabPanic = findViewById(R.id.fab_panic);
        emergencyStudyLayer = findViewById(R.id.emergency_study_layer);
        Button btnCloseEmergency = findViewById(R.id.btn_close_emergency);
        Button btnBackToHome = findViewById(R.id.btn_back_to_home);

        // Header VIP click
        View crownBtn = findViewById(R.id.btn_vip_crown);
        if (crownBtn != null) {
            crownBtn.setOnClickListener(v -> Toast.makeText(this, "VIP Plan: ₹9/day or ₹99/month", Toast.LENGTH_SHORT).show());
        }

        // Back to Home button from inside tools
        btnBackToHome.setOnClickListener(v -> showHomeScreen());

        // 1. AI Studio (1 - 25)
        findViewById(R.id.card_home_ai).setOnClickListener(v -> openCategory(
            "🤖 AI Studio (1 – 25)",
            "1. AI Text Writer & Chat\n2. AI Image Generator\n3. AI Background Remover\n4. AI Voice & Speech Clone\n5. AI Code Explainer\n6. AI Essay & Homework Solver\n7. AI Photo Enhancer\n8. AI Face Swap\n9. AI Video Script Generator\n10. AI Grammar Checker\n...aur 15 aur AI tools!"
        ));

        // 2. Offline Tools (26 - 50)
        findViewById(R.id.card_home_offline).setOnClickListener(v -> openCategory(
            "📶 Offline Tools (26 – 50)",
            "26. Flashlight Strobe & SOS\n27. Offline Unit Converter\n28. Compass & Leveler\n29. Scientific Calculator\n30. Offline Notepad & Notes\n31. Stop Watch & Timer\n32. Sound Meter (dB)\n33. Offline Counter\n34. Morse Code Flasher\n...aur 16 bina internet wale tools!"
        ));

        // 3. Privacy Tools (51 - 70)
        findViewById(R.id.card_home_privacy).setOnClickListener(v -> openCategory(
            "🎭 Privacy Tools (51 – 70)",
            "51. Screen Peep Blocker (Black filter)\n52. Intruder Selfie (Chori-chhupe kholne wale ki photo)\n53. Fake Call Generator\n54. App Locker\n55. Fake GPS Spoof Info\n56. EXIF Metadata Cleaner\n57. Cam/Mic Spy Detector\n...aur 13 aur privacy rakshak tools!"
        ));

        // 4. Reels (Direct Bottom Navigation)
        findViewById(R.id.card_home_reels).setOnClickListener(v -> bottomNav.setSelectedItemId(R.id.nav_reels));

        // 5. Movies (Direct Bottom Navigation)
        findViewById(R.id.card_home_movies).setOnClickListener(v -> bottomNav.setSelectedItemId(R.id.nav_movies));

        // 6. Chat (Direct Bottom Navigation)
        findViewById(R.id.card_home_chat).setOnClickListener(v -> bottomNav.setSelectedItemId(R.id.nav_chat));

        // 7. Social Savers (71 - 90)
        findViewById(R.id.card_home_savers).setOnClickListener(v -> openCategory(
            "📥 Social Savers (71 – 90)",
            "71. Insta HD DP Viewer\n72. Status & Story Saver\n73. Direct WhatsApp Chat without saving number\n74. Hashtag Generator\n75. Caption Creator\n76. Video Audio Extractor\n77. Thumbnail Downloader\n...aur 13 social media savers!"
        ));

        // 8. Office & PDF (91 - 110)
        findViewById(R.id.card_home_office).setOnClickListener(v -> openCategory(
            "📑 Office & PDF Tools (91 – 110)",
            "91. Images to PDF Converter\n92. PDF Compressor\n93. PDF Password Protector\n94. Resume & Bio-Data Maker\n95. QR Code Generator & Scanner\n96. OCR Image-to-Text Reader\n97. Digital Signature Maker\n...aur 13 zaroori office tools!"
        ));

        // 9. Desi Life (111 - 130)
        findViewById(R.id.card_home_desi).setOnClickListener(v -> openCategory(
            "₹ Desi Life & Utilities (111 – 130)",
            "111. Bijli Bill Calculator\n112. EMI & Interest Byaj Ganit\n113. Gold Price & GST Calculator\n114. Vehicle Challan & RTO Finder\n115. PNR Status & Train Live Info\n116. Bhulekh & Khasra-Khatauni Guide\n117. Rashan Card Search\n...aur 13 desi daily life tools!"
        ));

        // Hero Banner & View All Click
        findViewById(R.id.hero_tools_banner).setOnClickListener(v -> openCategory(
            "🪐 AstraX 165+ Master Hub",
            "AstraX ke sabhi 165 powerful tools categories ke andar active hain. Kisi bhi category card par click karke uske tools ko direct access karo!"
        ));
        findViewById(R.id.btn_view_all).setOnClickListener(v -> openCategory(
            "🪐 All 165+ Tools Categories",
            "• AI Studio (1 - 25)\n• Offline Tools (26 - 50)\n• Privacy Tools (51 - 70)\n• Social Savers (71 - 90)\n• Office & PDF (91 - 110)\n• Desi Life (111 - 130)\n• Media & Audio (131 - 150)\n• Extra Pro Tools (151 - 165+)"
        ));

        // Bottom Navigation Tabs
        bottomNav.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if (itemId == R.id.nav_home) {
                showHomeScreen();
                return true;
            } else {
                homeLayout.setVisibility(View.GONE);
                layoutToolsHub.setVisibility(View.GONE);
                otherScreenTitle.setVisibility(View.VISIBLE);

                if (itemId == R.id.nav_reels) {
                    otherScreenTitle.setText("🎬 Reels Player");
                } else if (itemId == R.id.nav_movies) {
                    otherScreenTitle.setText("🍿 Movies & Cinema Hub");
                } else if (itemId == R.id.nav_chat) {
                    otherScreenTitle.setText("💬 Chat & Secret Vault");
                } else if (itemId == R.id.nav_profile) {
                    otherScreenTitle.setText("👤 VIP & Profile");
                }
                return true;
            }
        });

        // Panic Mode
        fabPanic.setOnClickListener(v -> emergencyStudyLayer.setVisibility(View.VISIBLE));
        btnCloseEmergency.setOnClickListener(v -> emergencyStudyLayer.setVisibility(View.GONE));
    }

    private void openCategory(String title, String toolsList) {
        homeLayout.setVisibility(View.GONE);
        otherScreenTitle.setVisibility(View.GONE);
        layoutToolsHub.setVisibility(View.VISIBLE);

        toolsCategoryTitle.setText(title);
        toolsListText.setText(toolsList);
    }

    private void showHomeScreen() {
        homeLayout.setVisibility(View.VISIBLE);
        layoutToolsHub.setVisibility(View.GONE);
        otherScreenTitle.setVisibility(View.GONE);
    }

    @Override
    public void onBackPressed() {
        if (emergencyStudyLayer.getVisibility() == View.VISIBLE) {
            emergencyStudyLayer.setVisibility(View.GONE);
        } else if (layoutToolsHub.getVisibility() == View.VISIBLE) {
            showHomeScreen();
        } else {
            super.onBackPressed();
        }
    }
}
