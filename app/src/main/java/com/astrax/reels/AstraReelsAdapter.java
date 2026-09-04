package com.astrax.reels;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.recyclerview.widget.RecyclerView;
import com.astrax.core.data.MediaEntities;
import com.astrax.R;
import java.util.List;

/**
 * AstraReelsAdapter - RecyclerView adapter for vertical reels.
 * Attach to a ViewPager2 or RecyclerView with SnapHelper for vertical swipe UX.
 */
public class AstraReelsAdapter extends RecyclerView.Adapter<AstraReelsAdapter.Holder> {

    private List<MediaEntities.Reel> items;
    public interface OnAction {
        void onPlay(MediaEntities.Reel reel);
        void onLike(MediaEntities.Reel reel);
    }
    private OnAction callback;

    public AstraReelsAdapter(List<MediaEntities.Reel> items, OnAction cb) {
        this.items = items;
        this.callback = cb;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_reel, parent, false);
        return new Holder(v);
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        MediaEntities.Reel r = items.get(position);
        // TODO: load poster/thumbnail with your image loader (AstraX-themed)
        holder.itemView.setOnClickListener(v -> callback.onPlay(r));
        // double-tap -> pulse like animation
        holder.itemView.setOnTouchListener((v, e) -> {
            // implement gesture detection for double-tap in host Activity if preferred
            return false;
        });
    }

    @Override public int getItemCount() { return items.size(); }

    static class Holder extends RecyclerView.ViewHolder {
        ImageView thumb;
        Holder(View v) {
            super(v);
            thumb = v.findViewById(R.id.reel_thumb);
        }
    }
}
