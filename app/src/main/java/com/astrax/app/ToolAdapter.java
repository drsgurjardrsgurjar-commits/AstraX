package com.astrax.app;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ToolAdapter extends RecyclerView.Adapter<ToolAdapter.ToolViewHolder> {

    private final List<ToolModel> tools;
    private final OnToolClickListener listener;
    private final Context ctx;

    public interface OnToolClickListener {
        void onToolClick(ToolModel tool);
    }

    public ToolAdapter(Context ctx, List<ToolModel> tools, OnToolClickListener listener) {
        this.tools = tools;
        this.listener = listener;
        this.ctx = ctx;
    }

    @NonNull
    @Override
    public ToolViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tool_card, parent, false);
        return new ToolViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ToolViewHolder holder, int position) {
        ToolModel tool = tools.get(position);
        holder.txtTitle.setText(tool.title);
        holder.txtDesc.setText(tool.desc == null ? "" : tool.desc);

        // Map category to colors and icon
        Map<String, Integer[]> gradMap = new HashMap<>();
        gradMap.put("AI", new Integer[]{ContextCompat.getColor(ctx, R.color.tile_ai_start), ContextCompat.getColor(ctx, R.color.tile_ai_end)});
        gradMap.put("Offline", new Integer[]{ContextCompat.getColor(ctx, R.color.tile_offline_start), ContextCompat.getColor(ctx, R.color.tile_offline_end)});
        gradMap.put("Privacy", new Integer[]{ContextCompat.getColor(ctx, R.color.tile_privacy_start), ContextCompat.getColor(ctx, R.color.tile_privacy_end)});
        gradMap.put("Savers", new Integer[]{ContextCompat.getColor(ctx, R.color.tile_savers_start), ContextCompat.getColor(ctx, R.color.tile_savers_end)});
        gradMap.put("Office", new Integer[]{ContextCompat.getColor(ctx, R.color.tile_office_start), ContextCompat.getColor(ctx, R.color.tile_office_end)});
        gradMap.put("Desi", new Integer[]{ContextCompat.getColor(ctx, R.color.tile_desi_start), ContextCompat.getColor(ctx, R.color.tile_desi_end)});
        gradMap.put("Boost", new Integer[]{ContextCompat.getColor(ctx, R.color.tile_boost_start), ContextCompat.getColor(ctx, R.color.tile_boost_end)});
        gradMap.put("Media", new Integer[]{ContextCompat.getColor(ctx, R.color.tile_media_start), ContextCompat.getColor(ctx, R.color.tile_media_end)});

        Integer[] colors = gradMap.getOrDefault(tool.category, new Integer[]{ContextCompat.getColor(ctx, R.color.surface_dark), ContextCompat.getColor(ctx, R.color.surface_dark_alt)});

        GradientDrawable gd = new GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM, new int[]{colors[0], colors[1]});
        gd.setCornerRadius(ctx.getResources().getDimension(R.dimen._14dp));
        holder.itemView.setBackground(gd);

        // icon mapping (emoji)
        String icon = "🔧";
        switch (tool.category) {
            case "AI": icon = "🤖"; break;
            case "Offline": icon = "📶"; break;
            case "Privacy": icon = "🎭"; break;
            case "Savers": icon = "📥"; break;
            case "Office": icon = "📑"; break;
            case "Desi": icon = "₹"; break;
            case "Boost": icon = "⚡"; break;
            case "Media": icon = "🎵"; break;
        }
        holder.txtIcon.setText(icon);

        holder.itemView.setOnClickListener(v -> {
            if (listener != null) listener.onToolClick(tool);
        });
    }

    @Override
    public int getItemCount() {
        return tools.size();
    }

    static class ToolViewHolder extends RecyclerView.ViewHolder {
        TextView txtIcon, txtTitle, txtDesc;

        public ToolViewHolder(@NonNull View itemView) {
            super(itemView);
            txtIcon = itemView.findViewById(R.id.tool_item_icon);
            txtTitle = itemView.findViewById(R.id.tool_item_title);
            txtDesc = itemView.findViewById(R.id.tool_item_desc);
        }
    }
}
