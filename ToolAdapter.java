package com.astrax.app;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class ToolAdapter extends RecyclerView.Adapter<ToolAdapter.ToolViewHolder> {

    private final List<ToolModel> toolList;
    private final OnToolClickListener listener;

    public interface OnToolClickListener {
        void onToolClick(ToolModel tool);
    }

    public ToolAdapter(List<ToolModel> toolList, OnToolClickListener listener) {
        this.toolList = toolList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ToolViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tool_card, parent, false);
        return new ToolViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ToolViewHolder holder, int position) {
        ToolModel tool = toolList.get(position);
        holder.txtTitle.setText(tool.id + ". " + tool.title);
        holder.txtDesc.setText(tool.desc);
        holder.itemView.setOnClickListener(v -> listener.onToolClick(tool));
    }

    @Override
    public int getItemCount() {
        return toolList.size();
    }

    static class ToolViewHolder extends RecyclerView.ViewHolder {
        TextView txtTitle, txtDesc;

        public ToolViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTitle = itemView.findViewById(R.id.tool_item_title);
            txtDesc = itemView.findViewById(R.id.tool_item_desc);
        }
    }
}
