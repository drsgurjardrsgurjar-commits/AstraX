package com.astrax.app;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private View homeLayout;
    private View layoutToolsHub;
    private TextView toolsCategoryTitle;
    private TextView toolsListText;
    private TextView otherScreenTitle;
    private View emergencyStudyLayer;
    private BottomNavigationView bottomNav;

    // 165+ Tools Search Database
    private final String[] allToolsDatabase = {
        "🤖 AI Studio: AI Text Writer & Chat",
        "🤖 AI Studio: AI Image Generator",
        "🤖 AI Studio: AI Background Remover",
        "🤖 AI Studio: AI Voice & Speech Clone",
        "🤖 AI Studio: AI Code Explainer",
        "🤖 AI Studio: AI Photo Enhancer",
        "🤖 AI Studio: AI Face Swap",
        "🤖 AI Studio: AI Video Script Generator",
        "📶 Offline: Flashlight Strobe & SOS",
        "📶 Offline: Offline Unit Converter",
        "📶 Offline: Compass & Spirit Level",
        "📶 Offline: Scientific Calculator",
        "📶 Offline: Offline Notepad Vault",
        "📶 Offline: Sound Meter (dB)",
        "🎭 Privacy: Screen Peep Blocker",
        "🎭 Privacy: Intruder Selfie Catcher",
        "🎭 Privacy: Fake Incoming Call",
        "🎭 Privacy: App Locker & Vault",
        "🎭 Privacy: EXIF Photo Metadata Cleaner",
        "🎬 Entertainment: Reels Player",
        "🎬 Entertainment: Cinema Movies Hub",
        "💬 Social: Encrypted Chat & PIN Vault",
        "📥 Savers: Insta HD DP & Status Saver",
        "📥 Savers: WhatsApp Direct Message",
        "📥 Savers: Video to MP3 Audio Extractor",
        "📑 Office & PDF: Images to PDF Converter",
        "📑 Office & PDF: PDF Compressor",
        "📑 Office & PDF: QR Code Generator & Scanner",
        "📑 Office & PDF: Digital Signature Maker",
        "₹ Desi Life: Bijli Bill Calculator",
        "₹ Desi Life: Loan EMI & Byaj Ganit",
        "₹ Desi Life: Gold Price & GST Calculator",
        "₹ Desi Life: Vehicle RC & Challan Status",
        "₹ Desi Life: Train PNR & Live Running Info"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        homeLayout = findViewById(R.id.layout_home_screen);
        layoutToolsHub = findViewById(R.id.layout_tools_hub);
        toolsCategoryTitle = findViewById(R.id.tools_category_title);
        toolsListText = findViewById(R.id.tools_list_text);
        otherScreenTitle = findViewById(R.id.other_screen_title);
        bottomNav = findViewById(R.id.bottom_navigation);
        FloatingActionButton fabPanic = findViewById(R.id.fab_panic);
        emergencyStudyLayer = findViewById(R.id.emergency_study_layer);
        Button btnCloseEmergency = findViewById(R.id.btn_close_emergency);
        Button btnBackToHome = findViewById(R.id.btn_back_to_home);

        // Header VIP Button
        bindClick(R.id.btn_vip_crown, v -> 
            Toast.makeText(this, "👑 AstraX VIP: ₹9/Day Pass | ₹99/Month All-Access", Toast.LENGTH_LONG).show());

        // Header Search (🔍)
        bindClick(R.id.btn_search, v -> openSearchDialog());

        // Header Menu (⋮)
        bindClick(R.id.btn_menu, v -> 
            Toast.makeText(this, "AstraX SuperApp v1.0 • Privacy Engine Active", Toast.LENGTH_SHORT).show());

        // Back to Home from inside Tools
        if (btnBackToHome != null) {
            btnBackToHome.setOnClickListener(v -> showHomeScreen());
        }

        // Hero Banner & View All
        bindClick(R.id.hero_tools_banner, v -> openCategory(
            "🪐 AstraX 165+ Master Hub",
            "AstraX ke sabhi 165 tools active hain. Kisi bhi category ya 🔍 Search se turant chalayein!"
        ));
        bindClick(R.id.btn_view_all, v -> openCategory(
            "🪐 All Categories",
            "• AI Studio (1 - 25)\n• Offline Tools (26 - 50)\n• Privacy Tools (51 - 70)\n• Social Savers (71 - 90)\n• Office & PDF (91 - 110)\n• Desi Life (111 - 130)"
        ));

        // 9 Category Boxes
        bindClick(R.id.card_home_ai, v -> openCategory(
            "🤖 AI Studio (1 – 25)",
            "1. AI Text Writer & Chat\n2. AI Image Generator\n3. AI Background Remover\n4. AI Voice & Speech Clone\n5. AI Code Explainer\n6. AI Essay & Homework Solver\n7. AI Photo Enhancer\n8. AI Face Swap\n...aur 17 AI tools!"
        ));

        bindClick(R.id.card_home_offline, v -> openCategory(
            "📶 Offline Tools (26 – 50)",
            "26. Flashlight Strobe & SOS\n27. Offline Unit Converter\n28. Compass & Leveler\n29. Scientific Calculator\n30. Offline Notepad Vault\n31. Stop Watch & Timer\n...aur 19 offline tools!"
        ));

        bindClick(R.id.card_home_privacy, v -> openCategory(
            "🎭 Privacy Tools (51 – 70)",
            "51. Screen Peep Blocker\n52. Intruder Selfie Catcher\n53. Fake Incoming Call\n54. App Locker\n55. Fake GPS Spoof Guide\n56. EXIF Photo Cleaner\n...aur 14 privacy tools!"
        ));

        bindClick(R.id.card_home_reels, v -> {
            if (bottomNav != null) bottomNav.setSelectedItemId(R.id.nav_reels);
        });

        bindClick(R.id.card_home_movies, v -> {
            if (bottomNav != null) bottomNav.setSelectedItemId(R.id.nav_movies);
        });

        bindClick(R.id.card_home_chat, v -> {
            if (bottomNav != null) bottomNav.setSelectedItemId(R.id.nav_chat);
        });

        bindClick(R.id.card_home_savers, v -> openCategory(
            "📥 Social Savers (71 – 90)",
            "71. Insta HD DP Viewer\n72. Status & Story Saver\n73. Direct WhatsApp Chat\n74. Viral Hashtags Generator\n75. Video to MP3 Extractor\n...aur 15 social tools!"
        ));

        bindClick(R.id.card_home_office, v -> openCategory(
            "📑 Office & PDF Tools (91 – 110)",
            "91. Images to PDF Converter\n92. PDF Compressor\n93. PDF Password Lock\n94. QR Code Scanner\n95. Digital Signature Maker\n...aur 15 office tools!"
        ));

        bindClick(R.id.card_home_desi, v -> openCategory(
            "₹ Desi Life & Utilities (111 – 130)",
            "111. Bijli Bill Calculator\n112. EMI & Byaj Ganit\n113. Gold Price & GST Calculator\n114. Vehicle RC & Challan Status\n115. Train PNR & Live Status\n...aur 15 desi tools!"
        ));

        // Bottom Navigation Tabs
        if (bottomNav != null) {
            bottomNav.setOnItemSelectedListener(item -> {
                int itemId = item.getItemId();
                if (itemId == R.id.nav_home) {
                    showHomeScreen();
                    return true;
                } else {
                    if (homeLayout != null) homeLayout.setVisibility(View.GONE);
                    if (layoutToolsHub != null) layoutToolsHub.setVisibility(View.GONE);
                    if (otherScreenTitle != null) {
                        otherScreenTitle.setVisibility(View.VISIBLE);
                        if (itemId == R.id.nav_reels) otherScreenTitle.setText("🎬 Reels Player");
                        else if (itemId == R.id.nav_movies) otherScreenTitle.setText("🍿 Movies & Cinema Hub");
                        else if (itemId == R.id.nav_chat) otherScreenTitle.setText("💬 Chat & Secret Vault");
                        else if (itemId == R.id.nav_profile) otherScreenTitle.setText("👤 VIP & Profile");
                    }
                    return true;
                }
            });
        }

        // Stealth Panic Mode
        if (fabPanic != null) {
            fabPanic.setOnClickListener(v -> {
                if (emergencyStudyLayer != null) emergencyStudyLayer.setVisibility(View.VISIBLE);
            });
        }
        if (btnCloseEmergency != null) {
            btnCloseEmergency.setOnClickListener(v -> {
                if (emergencyStudyLayer != null) emergencyStudyLayer.setVisibility(View.GONE);
            });
        }
    }

    private void bindClick(int viewId, View.OnClickListener listener) {
        View target = findViewById(viewId);
        if (target != null) {
            target.setOnClickListener(listener);
        }
    }

    private void openSearchDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("🔍 Search All 165+ Tools");

        android.widget.LinearLayout dialogLayout = new android.widget.LinearLayout(this);
        dialogLayout.setOrientation(android.widget.LinearLayout.VERTICAL);
        dialogLayout.setPadding(40, 20, 40, 10);

        final EditText searchInput = new EditText(this);
        searchInput.setHint("Type tool name (PDF, AI, Reels, GPS)...");
        dialogLayout.addView(searchInput);

        final ListView searchResultsList = new ListView(this);
        List<String> listData = new ArrayList<>(Arrays.asList(allToolsDatabase));
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listData);
        searchResultsList.setAdapter(adapter);
        dialogLayout.addView(searchResultsList);

        builder.setView(dialogLayout);
        builder.setNegativeButton("Cancel", (dialog, which) -> dialog.dismiss());

        AlertDialog dialog = builder.create();

        searchInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                adapter.getFilter().filter(s);
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        searchResultsList.setOnItemClickListener((parent, view, position, id) -> {
            String selectedTool = adapter.getItem(position);
            dialog.dismiss();

            if (selectedTool.contains("Reels") && bottomNav != null) {
                bottomNav.setSelectedItemId(R.id.nav_reels);
            } else if (selectedTool.contains("Movies") && bottomNav != null) {
                bottomNav.setSelectedItemId(R.id.nav_movies);
            } else if (selectedTool.contains("Chat") && bottomNav != null) {
                bottomNav.setSelectedItemId(R.id.nav_chat);
            } else {
                openCategory("⚡ Instant Tool: " + selectedTool,
                    "Selected: " + selectedTool + "\n\nStatus: 🟢 Ready to launch inside AstraX!");
            }
        });

        dialog.show();
    }

    private void openCategory(String title, String toolsList) {
        if (homeLayout != null) homeLayout.setVisibility(View.GONE);
        if (otherScreenTitle != null) otherScreenTitle.setVisibility(View.GONE);
        if (layoutToolsHub != null) layoutToolsHub.setVisibility(View.VISIBLE);

        if (toolsCategoryTitle != null) toolsCategoryTitle.setText(title);
        if (toolsListText != null) toolsListText.setText(toolsList);
    }

    private void showHomeScreen() {
        if (homeLayout != null) homeLayout.setVisibility(View.VISIBLE);
        if (layoutToolsHub != null) layoutToolsHub.setVisibility(View.GONE);
        if (otherScreenTitle != null) otherScreenTitle.setVisibility(View.GONE);
    }

    @Override
    public void onBackPressed() {
        if (emergencyStudyLayer != null && emergencyStudyLayer.getVisibility() == View.VISIBLE) {
            emergencyStudyLayer.setVisibility(View.GONE);
        } else if (layoutToolsHub != null && layoutToolsHub.getVisibility() == View.VISIBLE) {
            showHomeScreen();
        } else {
            super.onBackPressed();
        }
    }
                    }
            
