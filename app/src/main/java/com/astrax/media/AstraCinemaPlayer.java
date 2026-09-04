package com.astrax.media;

import android.content.Context;
import android.media.MediaPlayer;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.FrameLayout;

/**
 * AstraCinemaPlayer - simple wrapper around MediaPlayer to provide:
 * - double-tap left/right: -10s / +10s
 * - full-screen dark AMOLED themed controls (AstraX)
 *
 * This avoids rendering any third-party branding in UI.
 */
public class AstraCinemaPlayer extends FrameLayout implements SurfaceHolder.Callback {

    private MediaPlayer player;
    private SurfaceView surfaceView;
    private GestureDetector gestureDetector;
    private static final int SEEK_STEP_MS = 10_000;

    public AstraCinemaPlayer(Context ctx) {
        super(ctx);
        init(ctx);
    }

    private void init(Context ctx) {
        surfaceView = new SurfaceView(ctx);
        addView(surfaceView, LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        surfaceView.getHolder().addCallback(this);

        gestureDetector = new GestureDetector(ctx, new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onDoubleTap(MotionEvent e) {
                float x = e.getX();
                int w = getWidth();
                if (x < w * 0.4f) {
                    seekBy(-SEEK_STEP_MS);
                    showSeekAnimation(-10);
                } else if (x > w * 0.6f) {
                    seekBy(SEEK_STEP_MS);
                    showSeekAnimation(+10);
                } else {
                    // center double-tap: toggle play/pause
                    togglePlay();
                }
                return true;
            }
        });

        setBackgroundColor(0xFF0B0B12); // AstraX dark AMOLED
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        gestureDetector.onTouchEvent(event);
        return true;
    }

    private void showSeekAnimation(int seconds) {
        // TODO: implement circular neon animation overlay with AstraX color
    }

    private void seekBy(int deltaMs) {
        if (player != null && player.isPlaying()) {
            int pos = player.getCurrentPosition();
            int target = Math.max(0, pos + deltaMs);
            player.seekTo(target);
        }
    }

    private void togglePlay() {
        if (player == null) return;
        if (player.isPlaying()) player.pause();
        else player.start();
    }

    public void playUrl(String url) {
        release();
        player = new MediaPlayer();
        try {
            player.setDataSource(url);
            player.setOnPreparedListener(mp -> mp.start());
            SurfaceHolder sh = surfaceView.getHolder();
            player.setDisplay(sh);
            player.prepareAsync();
        } catch (Exception ex) {
            ex.printStackTrace();
            // TODO: show AstraX-branded error UI
        }
    }

    public void release() {
        if (player != null) {
            player.release();
            player = null;
        }
    }

    @Override public void surfaceCreated(SurfaceHolder holder) {}
    @Override public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {}
    @Override public void surfaceDestroyed(SurfaceHolder holder) { release(); }
}
