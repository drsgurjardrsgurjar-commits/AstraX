package com.astrax.app;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.widget.Button;

public class ToolAdapter extends RecyclerView.Adapter<ToolAdapter.ToolViewHolder> {
    
    private java.util.List<ToolModel> tools;
    private OnToolClickListener listener;

    public interface OnToolClickListener {
        void onToolClick(ToolModel tool);
    }

    public ToolAdapter(java.util.List<ToolModel> tools, OnToolClickListener listener) {
        this.tools = tools;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ToolViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Button button = new Button(parent.getContext());
        button.setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                100
        ));
        return new ToolViewHolder(button);
    }

    @Override
    public void onBindViewHolder(@NonNull ToolViewHolder holder, int position) {
        ToolModel tool = tools.get(position);
        holder.button.setText(tool.id + ". " + tool.title);
        holder.button.setOnClickListener(v -> listener.onToolClick(tool));
    }

    @Override
    public int getItemCount() {
        return tools.size();
    }

    static class ToolViewHolder extends RecyclerView.ViewHolder {
        Button button;

        public ToolViewHolder(@NonNull Button itemView) {
            super(itemView);
            this.button = itemView;
        }
    }
}
