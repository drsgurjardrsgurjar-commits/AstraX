package com.astrax.core.data;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.ColumnInfo;

@Entity(tableName = "movies")
public class MediaEntities {
    @Entity(tableName = "movies")
    public static class Movie {
        @PrimaryKey(autoGenerate = true)
        public long id;

        @ColumnInfo(name = "title")
        public String title;

        @ColumnInfo(name = "stream_url")
        public String streamUrl;

        @ColumnInfo(name = "category")
        public String category;

        @ColumnInfo(name = "poster_url")
        public String posterUrl;

        @ColumnInfo(name = "quality")
        public String quality;

        // add timestamps, flags as needed
    }

    @Entity(tableName = "reels")
    public static class Reel {
        @PrimaryKey(autoGenerate = true)
        public long id;

        @ColumnInfo(name = "title")
        public String title;

        @ColumnInfo(name = "video_url")
        public String videoUrl;

        @ColumnInfo(name = "audio_name")
        public String audioName;
    }
}
