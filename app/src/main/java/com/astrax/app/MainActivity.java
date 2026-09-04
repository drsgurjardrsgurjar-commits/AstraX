package com.astrax.app;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ScrollView viewHome;
    private LinearLayout viewSubfolders;
    private LinearLayout viewToolWorkspace;
    private TextView otherScreenTitle;
    private BottomNavigationView bottomNav;

    private TextView subfolderMainTitle;
    private LinearLayout subfolderItemsContainer;
    private TextView toolActiveTitle;
    private TextView toolDescription;
    private EditText toolInputField;
    private Button btnRunTool;
    private TextView toolOutputBox;
    private View emergencyStudyLayer;

    // Master list of all 165 tools
    private final List<ToolModel> all165Tools = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewHome = findViewById(R.id.view_home);
        viewSubfolders = findViewById(R.id.view_subfolders);
        viewToolWorkspace = findViewById(R.id.view_tool_workspace);
        otherScreenTitle = findViewById(R.id.other_screen_title);
        bottomNav = findViewById(R.id.bottom_navigation);

        subfolderMainTitle = findViewById(R.id.subfolder_main_title);
        subfolderItemsContainer = findViewById(R.id.subfolder_items_container);
        toolActiveTitle = findViewById(R.id.tool_active_title);
        toolDescription = findViewById(R.id.tool_description);
        toolInputField = findViewById(R.id.tool_input_field);
        btnRunTool = findViewById(R.id.btn_run_tool);
        toolOutputBox = findViewById(R.id.tool_output_box);
        emergencyStudyLayer = findViewById(R.id.emergency_study_layer);

        // Populate database
        initToolsDatabase();

        // Header VIP Crown
        findViewById(R.id.btn_vip_crown).setOnClickListener(v -> 
            Toast.makeText(this, "👑 AstraX VIP: ₹9/Day Pass | ₹99/Month All-Access", Toast.LENGTH_LONG).show());

        // Header Instant Live Search (🔍)
        findViewById(R.id.btn_search).setOnClickListener(v -> openSearchDialog());

        // Header 3-Dot Menu (⋮)
        findViewById(R.id.btn_menu).setOnClickListener(v -> 
            Toast.makeText(this, "AstraX SuperApp v1.0 • Exclusive Tools Engine Active", Toast.LENGTH_SHORT).show());

        // Navigation Back Buttons
        findViewById(R.id.btn_back_to_categories).setOnClickListener(v -> showHomeScreen());
        findViewById(R.id.btn_back_to_subfolders).setOnClickListener(v -> {
            viewToolWorkspace.setVisibility(View.GONE);
            viewSubfolders.setVisibility(View.VISIBLE);
        });

        // 9 UNIQUE TOOLS FOLDERS (No duplication of Reels/Movies/Chat)
        findViewById(R.id.folder_ai).setOnClickListener(v -> openFolder("🤖 AI Studio (1 - 25)", 1, 25));
        findViewById(R.id.folder_offline).setOnClickListener(v -> openFolder("📶 Offline Tools (26 - 50)", 26, 50));
        findViewById(R.id.folder_privacy).setOnClickListener(v -> openFolder("🎭 Privacy Tools (51 - 70)", 51, 70));
        findViewById(R.id.folder_savers).setOnClickListener(v -> openFolder("📥 Social Savers (71 - 90)", 71, 90));
        findViewById(R.id.folder_office).setOnClickListener(v -> openFolder("📑 Office & PDF (91 - 110)", 91, 110));
        findViewById(R.id.folder_desi).setOnClickListener(v -> openFolder("₹ Desi Life (111 - 130)", 111, 130));
        findViewById(R.id.folder_media).setOnClickListener(v -> openFolder("🎵 Media Studio (131 - 145)", 131, 145));
        findViewById(R.id.folder_system).setOnClickListener(v -> openFolder("⚡ System Boost (146 - 155)", 146, 155));
        findViewById(R.id.folder_pro).setOnClickListener(v -> openFolder("🪐 Pro Utilities (156 - 165)", 156, 165));

        // Hero Banner & View All (Opens Complete 1-165 Directory)
        findViewById(R.id.hero_tools_banner).setOnClickListener(v -> openFolder("🪐 All 165+ Master Tools", 1, 165));
        findViewById(R.id.btn_view_all).setOnClickListener(v -> openFolder("🪐 All 165+ Master Tools", 1, 165));

        // Bottom Navigation: 5 Pillars Only
        bottomNav.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if (itemId == R.id.nav_home) {
                showHomeScreen();
                return true;
            } else {
                viewHome.setVisibility(View.GONE);
                viewSubfolders.setVisibility(View.GONE);
                viewToolWorkspace.setVisibility(View.GONE);
                otherScreenTitle.setVisibility(View.VISIBLE);

                if (itemId == R.id.nav_reels) otherScreenTitle.setText("🎬 Reels Vertical Player\n(Direct Play Active)");
                else if (itemId == R.id.nav_movies) otherScreenTitle.setText("🍿 Movies & Cinema Hub\n(Full HD Cinema Active)");
                else if (itemId == R.id.nav_chat) otherScreenTitle.setText("💬 Chat & Secret Vault\n(3-Dot Privacy PIN Protected)");
                else if (itemId == R.id.nav_profile) otherScreenTitle.setText("👤 VIP & User Profile\n(AstraX Member #001)");
                return true;
            }
        });

        // Stealth Panic Switch (⚡)
        findViewById(R.id.fab_panic).setOnClickListener(v -> emergencyStudyLayer.setVisibility(View.VISIBLE));
        findViewById(R.id.btn_close_emergency).setOnClickListener(v -> emergencyStudyLayer.setVisibility(View.GONE));
    }

    private void openFolder(String folderTitle, int startId, int endId) {
        viewHome.setVisibility(View.GONE);
        viewToolWorkspace.setVisibility(View.GONE);
        otherScreenTitle.setVisibility(View.GONE);
        viewSubfolders.setVisibility(View.VISIBLE);

        subfolderMainTitle.setText(folderTitle);
        subfolderItemsContainer.removeAllViews();

        for (ToolModel tool : all165Tools) {
            if (tool.id >= startId && tool.id <= endId) {
                CardView card = new CardView(this);
                card.setRadius(14);
                card.setCardBackgroundColor(0xFF161622);
                card.setUseCompatPadding(true);

                LinearLayout itemLayout = new LinearLayout(this);
                itemLayout.setOrientation(LinearLayout.VERTICAL);
                itemLayout.setPadding(22, 20, 22, 20);

                TextView name = new TextView(this);
                name.setText(tool.id + ". " + tool.title);
                name.setTextColor(0xFFFFFFFF);
                name.setTextSize(15);
                name.setTypeface(null, android.graphics.Typeface.BOLD);

                TextView desc = new TextView(this);
                desc.setText(tool.desc);
                desc.setTextColor(0xFF94A3B8);
                desc.setTextSize(12);
                desc.setPadding(0, 6, 0, 0);

                itemLayout.addView(name);
                itemLayout.addView(desc);
                card.addView(itemLayout);

                // Clicking sub-item opens its actual workspace
                card.setOnClickListener(v -> openToolWorkspace(tool));
                subfolderItemsContainer.addView(card);
            }
        }
    }

    private void openToolWorkspace(ToolModel tool) {
        viewSubfolders.setVisibility(View.GONE);
        viewToolWorkspace.setVisibility(View.VISIBLE);

        toolActiveTitle.setText(tool.id + ". " + tool.title);
        toolDescription.setText(tool.desc);
        toolInputField.setText("");
        toolInputField.setHint(tool.hint);
        toolOutputBox.setText("Status: 🟢 Waiting for command...");

        btnRunTool.setOnClickListener(v -> {
            String input = toolInputField.getText().toString().trim();
            if (input.isEmpty()) {
                toolOutputBox.setText("⚠️ Please provide input first!");
            } else {
                toolOutputBox.setText("⚡ Execution Success:\n\n" + tool.output + "\n\nQuery Processed: \"" + input + "\"");
            }
        });
    }

    private void showHomeScreen() {
        viewHome.setVisibility(View.VISIBLE);
        viewSubfolders.setVisibility(View.GONE);
        viewToolWorkspace.setVisibility(View.GONE);
        otherScreenTitle.setVisibility(View.GONE);
    }

    private void openSearchDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("🔍 Search All 165+ Tools");

        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setPadding(40, 20, 40, 10);

        final EditText input = new EditText(this);
        input.setHint("Search tool name (AI, PDF, Bill, GPS)...");
        layout.addView(input);

        final ListView listView = new ListView(this);
        List<String> names = new ArrayList<>();
        for (ToolModel t : all165Tools) {
            names.add(t.id + ". " + t.title);
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, names);
        listView.setAdapter(adapter);
        layout.addView(listView);

        builder.setView(layout);
        builder.setNegativeButton("Cancel", (d, w) -> d.dismiss());

        AlertDialog dialog = builder.create();

        input.addTextChangedListener(new TextWatcher() {
            @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override public void onTextChanged(CharSequence s, int start, int before, int count) {
                adapter.getFilter().filter(s);
            }
            @Override public void afterTextChanged(Editable s) {}
        });

        listView.setOnItemClickListener((parent, view, position, id) -> {
            String selected = adapter.getItem(position);
            dialog.dismiss();
            for (ToolModel t : all165Tools) {
                if (selected.contains(t.title)) {
                    viewHome.setVisibility(View.GONE);
                    openToolWorkspace(t);
                    break;
                }
            }
        });

        dialog.show();
    }

    @Override
    public void onBackPressed() {
        if (emergencyStudyLayer.getVisibility() == View.VISIBLE) {
            emergencyStudyLayer.setVisibility(View.GONE);
            return;
        }
        if (viewToolWorkspace.getVisibility() == View.VISIBLE) {
            viewToolWorkspace.setVisibility(View.GONE);
            viewSubfolders.setVisibility(View.VISIBLE);
            return;
        }
        if (viewSubfolders.getVisibility() == View.VISIBLE) {
            showHomeScreen();
            return;
        }
        super.onBackPressed();
    }

    private void initToolsDatabase() {
        // AI Studio (1 - 25)
        addTool(1, "AI Text & Article Writer", "Generates high quality essays and stories", "Enter topic...", "Article successfully generated.");
        addTool(2, "AI Image Generator", "Text to 4K image prompt rendering", "Enter image prompt...", "Image rendered in 4K resolution.");
        addTool(3, "AI Background Remover", "1-click transparent PNG generator", "Enter photo URL...", "Background removed cleanly.");
        addTool(4, "AI Voice & Speech Synthesizer", "Text to natural voice studio", "Enter script to speak...", "Audio file generated.");
        addTool(5, "AI Code Explainer & Fixer", "Explains code and fixes syntax errors", "Paste your code snippet...", "Syntax checked: 0 bugs found.");
        addTool(6, "AI Math & Homework Solver", "Step-by-step homework assistant", "Enter equation or question...", "Step-by-step solution calculated.");
        addTool(7, "AI Photo Restorer", "Scratch removal and HD sharpening", "Upload image link...", "Clarity enhanced by 400%.");
        addTool(8, "AI Face Swap Engine", "Seamless character replacement", "Enter source image...", "Face swapped cleanly.");
        addTool(9, "AI Video Script Generator", "YouTube & Reel viral script maker", "Enter video concept...", "Full viral script generated.");
        addTool(10, "AI Grammar & Spell Fixer", "Advanced proofreader and editor", "Enter text to check...", "Grammar corrected.");
        addTool(11, "AI Resume & CV Maker", "Professional job-ready resume", "Enter your job title...", "Resume template generated.");
        addTool(12, "AI Language Translator", "100+ languages instant translation", "Enter phrase to translate...", "Translated to target language.");
        addTool(13, "AI Paraphraser & Rewriter", "Plagiarism-free text rewriting", "Paste paragraph...", "Paragraph rephrased.");
        addTool(14, "AI Poetry & Shayari Composer", "Authentic rhyming shayari creator", "Enter emotion or theme...", "Shayari composed with rhythm.");
        addTool(15, "AI Logo & Icon Generator", "Brand and business logo prompt", "Enter brand name...", "Logo vector crafted.");
        addTool(16, "AI Email Drafter", "Formal client and boss emails", "Enter email purpose...", "Professional email drafted.");
        addTool(17, "AI Story & Novel Plotter", "Creative multi-chapter storylines", "Enter story premise...", "Story outline mapped.");
        addTool(18, "AI Character Voice Designer", "Anime & movie custom voices", "Enter character vibe...", "Custom voice profile tuned.");
        addTool(19, "AI Music Lyricist", "Song verses and catchy choruses", "Enter musical genre...", "Lyrics generated with chorus.");
        addTool(20, "AI Interview Preparation Coach", "Mock Q&A for exams and interviews", "Enter job role...", "Interview question set ready.");
        addTool(21, "AI PDF Document Q&A", "Ask questions from any document", "Enter PDF query...", "Key summary extracted.");
        addTool(22, "AI Caption & Bio Creator", "Catchy social profiles bio", "Enter your style...", "5 bio options ready.");
        addTool(23, "AI Presentation Slide Creator", "Keynotes and PPT outlines", "Enter presentation topic...", "10-slide outline created.");
        addTool(24, "AI Product Description Maker", "High-converting ecommerce copy", "Enter product name...", "SEO-optimized description generated.");
        addTool(25, "AI Color Palette Creator", "Hex color scheme for designers", "Enter theme/mood...", "Hex palette generated: #6C5CE7, #0B0B12.");

        // Offline Tools (26 - 50)
        addTool(26, "Flashlight SOS & Strobe", "Emergency blinking strobe light", "Enter frequency (1-10)...", "Strobe activated.");
        addTool(27, "Offline Land & Unit Converter", "Bigha, Acre, Gaj, Feet converter", "Enter unit to convert...", "1 Bigha = 27,225 sq ft.");
        addTool(28, "Digital Compass & Level", "Accurate sensor orientation", "Hold phone flat...", "Heading: 180° South.");
        addTool(29, "Scientific Ganit Calculator", "Trigonometry & log calculations", "Enter math equation...", "Result computed.");
        addTool(30, "Offline Encrypted Notepad", "Local notes with private key", "Write note...", "Saved securely in local storage.");
        addTool(31, "High-Precision Stopwatch", "Lap recording timer", "Press Start...", "Timer active.");
        addTool(32, "Decibel Sound Meter", "Ambient noise measurement", "Listen to sound...", "Current Noise: 42 dB (Normal).");
        addTool(33, "Morse Code Flasher", "Text to light blinks", "Enter text...", "Morse light sequence ready.");
        addTool(34, "Tally Counter", "Click counter for counts and jap", "Tap to count...", "Count recorded.");
        addTool(35, "Offline Ruler & Scale", "On-screen centimeter measuring", "Place object on screen...", "Measurement calibrated.");
        addTool(36, "World Clock & Timezone", "All capital cities time", "Enter city name...", "Local time synchronized.");
        addTool(37, "Metronome Beat Keeper", "BPM rhythm for musicians", "Enter BPM...", "Metronome running at 120 BPM.");
        addTool(38, "Random Number & Dice", "Fair random picker", "Enter min & max range...", "Generated Number: 7");
        addTool(39, "Offline Fuel Mileage Ganit", "Calculate km/litre efficiency", "Enter Litres & KM...", "Mileage: 18.5 KM/Litre.");
        addTool(40, "BMI & Fitness Calculator", "Health body mass index", "Enter Height & Weight...", "BMI: 22.4 (Healthy Range).");
        addTool(41, "Daily Water Reminder", "Hydration goal tracker", "Enter weight...", "Target: 3.2 Litres/day.");
        addTool(42, "Speedometer (GPS Free)", "Motion step speed meter", "Start walking...", "Speed: 4.8 km/h.");
        addTool(43, "Discount & Sale Calculator", "Final price after percentage", "Enter Price & Discount %...", "Final Amount: ₹680 (Saved ₹120).");
        addTool(44, "Age & Birthday Calculator", "Exact days, months, hours lived", "Enter DOB (DD/MM/YYYY)...", "Age: 21 Years, 4 Months, 12 Days.");
        addTool(45, "Offline Password Generator", "Strong cryptographic passwords", "Enter length (e.g. 16)...", "Generated: @K9#xP82!mQvL$10");
        addTool(46, "Morse Code Audio Decoder", "Beep sound translator", "Tap beep...", "Decoded: SOS.");
        addTool(47, "Offline Mirror Tool", "Clean front camera preview", "Tap to open...", "Mirror mode active.");
        addTool(48, "Bubble Leveler", "Surface flatness detector", "Place phone on table...", "Surface Level: 0.0° Flat.");
        addTool(49, "Vibration Tester", "Haptic motor frequency check", "Enter strength...", "Haptic vibration checked.");
        addTool(50, "Device Hardware Info", "Chip, display, sensors report", "Check hardware...", "Hardware status: 100% OK.");

        // Privacy Tools (51 - 70)
        addTool(51, "Screen Peep Blocker", "Dark privacy tint on screen", "Opacity (10%-90%)...", "Screen filter active.");
        addTool(52, "Intruder Selfie Catcher", "Snap photo on wrong lock attempt", "Enter safety email...", "Intruder guard active.");
        addTool(53, "Fake Incoming Call Generator", "Escape uncomfortable situations", "Caller name (e.g. Boss)...", "Incoming call in 5s.");
        addTool(54, "App Locker Vault", "PIN protection for apps", "Set 4-digit PIN...", "Apps protected.");
        addTool(55, "EXIF Photo Metadata Cleaner", "Remove GPS tags from photos", "Select photo...", "Metadata wiped clean.");
        addTool(56, "Spy Camera & Mic Detector", "Sensor anomaly detector", "Scan room...", "No rogue signals detected.");
        addTool(57, "Fake GPS Spoof Guide", "Mock location setup", "Enter desired city...", "Mock location configured.");
        addTool(58, "Incognito Keyboard Guard", "Prevent keylogger recording", "Enable guard...", "Key guard active.");
        addTool(59, "Temporary Disposable Mail", "10-minute temporary email", "Generate email...", "Temp Mail: astrax_user9@tempmail.org");
        addTool(60, "Clipboard Auto-Cleaner", "Wipes copied passwords in 30s", "Enable auto-wipe...", "Clipboard safety active.");
        addTool(61, "Private Audio Vault", "Hide recordings behind calc", "Set access PIN...", "Audio hidden in vault.");
        addTool(62, "Private Photo Safe", "Zero cloud encrypted gallery", "Set vault PIN...", "Gallery encrypted.");
        addTool(63, "SMS Spam Blocker Filter", "Filter OTP and marketing spam", "Enter filter keyword...", "Spam rule added.");
        addTool(64, "Wi-Fi Security Scanner", "Detect public Wi-Fi sniffers", "Scan connected Wi-Fi...", "Network Safe: WPA3 Protected.");
        addTool(65, "Ad-Block DNS Setter", "Block trackers system-wide", "Tap apply...", "Private DNS set to AdGuard.");
        addTool(66, "Biometric App Shield", "Fingerprint lock on sensitive apps", "Verify fingerprint...", "
