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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
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
    private RecyclerView rvSubfolderItems;
    private TextView toolActiveTitle;
    private TextView toolDescription;
    private EditText toolInputField;
    private Button btnRunTool;
    private TextView toolOutputBox;
    private View emergencyStudyLayer;

    private List<ToolModel> allTools = new ArrayList<>();

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
        toolActiveTitle = findViewById(R.id.tool_active_title);
        toolDescription = findViewById(R.id.tool_description);
        toolInputField = findViewById(R.id.tool_input_field);
        btnRunTool = findViewById(R.id.btn_run_tool);
        toolOutputBox = findViewById(R.id.tool_output_box);
        emergencyStudyLayer = findViewById(R.id.emergency_study_layer);

        // RecyclerView setup
        rvSubfolderItems = new RecyclerView(this);
        rvSubfolderItems.setLayoutManager(new LinearLayoutManager(this));
        LinearLayout container = findViewById(R.id.subfolder_items_container);
        if (container != null) {
            container.addView(rvSubfolderItems);
        }

        // Load Tools from JSON
        allTools = ToolsJsonLoader.load(this);

        // Header Actions
        findViewById(R.id.btn_vip_crown).setOnClickListener(v -> 
            Toast.makeText(this, "👑 AstraX VIP: ₹9/Day | ₹99/Month", Toast.LENGTH_SHORT).show());
        findViewById(R.id.btn_search).setOnClickListener(v -> openSearchDialog());
        findViewById(R.id.btn_menu).setOnClickListener(v -> 
            Toast.makeText(this, "AstraX JSON Engine Active", Toast.LENGTH_SHORT).show());

        // Back Navigation
        findViewById(R.id.btn_back_to_categories).setOnClickListener(v -> showHomeScreen());
        findViewById(R.id.btn_back_to_subfolders).setOnClickListener(v -> {
            viewToolWorkspace.setVisibility(View.GONE);
            viewSubfolders.setVisibility(View.VISIBLE);
        });

        // 9 Pure Folders
        findViewById(R.id.folder_ai).setOnClickListener(v -> openFolder("🤖 AI Studio", "AI"));
        findViewById(R.id.folder_offline).setOnClickListener(v -> openFolder("📶 Offline Tools", "Offline"));
        findViewById(R.id.folder_privacy).setOnClickListener(v -> openFolder("🎭 Privacy Shield", "Privacy"));
        findViewById(R.id.folder_savers).setOnClickListener(v -> openFolder("📥 Social Savers", "Savers"));
        findViewById(R.id.folder_office).setOnClickListener(v -> openFolder("📑 Office & PDF", "Office"));
        findViewById(R.id.folder_desi).setOnClickListener(v -> openFolder("₹ Desi Life", "Desi"));
        findViewById(R.id.folder_media).setOnClickListener(v -> openFolder("🎵 Media Studio", "Media"));
        findViewById(R.id.folder_system).setOnClickListener(v -> openFolder("⚡ System Boost", "Boost"));
        findViewById(R.id.folder_pro).setOnClickListener(v -> openFolder("🪐 Pro Utilities", "Pro"));

        findViewById(R.id.hero_tools_banner).setOnClickListener(v -> openFolder("🪐 All Tools", "ALL"));
        findViewById(R.id.btn_view_all).setOnClickListener(v -> openFolder("🪐 All Tools", "ALL"));

        // Bottom Navigation
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

                if (itemId == R.id.nav_reels) otherScreenTitle.setText("🎬 Reels Vertical Player");
                else if (itemId == R.id.nav_movies) otherScreenTitle.setText("🍿 Movies & Cinema Hub");
                else if (itemId == R.id.nav_chat) otherScreenTitle.setText("💬 Secret Chat & Vault");
                else if (itemId == R.id.nav_profile) otherScreenTitle.setText("👤 VIP & Profile");
                return true;
            }
        });

        // Panic Switch
        findViewById(R.id.fab_panic).setOnClickListener(v -> emergencyStudyLayer.setVisibility(View.VISIBLE));
        findViewById(R.id.btn_close_emergency).setOnClickListener(v -> emergencyStudyLayer.setVisibility(View.GONE));
    }

    private void openFolder(String title, String categoryKey) {
        viewHome.setVisibility(View.GONE);
        viewToolWorkspace.setVisibility(View.GONE);
        otherScreenTitle.setVisibility(View.GONE);
        viewSubfolders.setVisibility(View.VISIBLE);

        subfolderMainTitle.setText(title);

        List<ToolModel> filtered = new ArrayList<>();
        for (ToolModel t : allTools) {
            if ("ALL".equals(categoryKey) || categoryKey.equalsIgnoreCase(t.category)) {
                filtered.add(t);
            }
        }

        ToolAdapter adapter = new ToolAdapter(filtered, this::openToolWorkspace);
        rvSubfolderItems.setAdapter(adapter);
    }

    private void openToolWorkspace(ToolModel tool) {
        viewSubfolders.setVisibility(View.GONE);
        viewToolWorkspace.setVisibility(View.VISIBLE);

        toolActiveTitle.setText(tool.id + ". " + tool.title);
        toolDescription.setText(tool.desc);
        toolInputField.setText("");
        toolInputField.setHint(tool.hint != null ? tool.hint : "Enter query...");
        toolOutputBox.setText("Status: 🟢 Ready");

        btnRunTool.setOnClickListener(v -> {
            String input = toolInputField.getText().toString().trim();
            if (input.isEmpty()) {
                toolOutputBox.setText("⚠️ Please provide valid input!");
            } else {
                toolOutputBox.setText("⚡ Output Result:\n\n" + tool.output + "\n\nQuery: \"" + input + "\" processed.");
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
        builder.setTitle("🔍 Search All Tools");

        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setPadding(40, 20, 40, 10);

        final EditText input = new EditText(this);
        input.setHint("Type tool name (AI, PDF, Bill)...");
        layout.addView(input);

        final ListView listView = new ListView(this);
        List<String> names = new ArrayList<>();
        for (ToolModel t : allTools) {
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

        listView.setOnItemClickListener((p, v, pos, id) -> {
            String sel = adapter.getItem(pos);
            dialog.dismiss();
            for (ToolModel t : allTools) {
                if (sel.contains(t.title)) {
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
                                                   }
