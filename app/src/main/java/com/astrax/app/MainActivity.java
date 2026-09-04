package com.astrax.app;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView grid = findViewById(R.id.quick_access_grid);
        grid.setLayoutManager(new GridLayoutManager(this, 3));

        List<ToolModel> tools = ToolsJsonLoader.load(this);
        ToolAdapter adapter = new ToolAdapter(this, tools, tool -> {
            // Simple click action: show toast with tool title
            Toast.makeText(this, "Open: " + tool.title, Toast.LENGTH_SHORT).show();
        });
        grid.setAdapter(adapter);
    }
}
