package com.astrax.core.data;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

/**
 * AstraX AppDatabase - stores movies, reels, and related metadata.
 * NOTE: Keep branding strings AstraX-only.
 */
@Database(entities = {MediaEntities.Movie.class, MediaEntities.Reel.class}, version = 1)
@TypeConverters({Converters.class})
public abstract class AppDatabase extends RoomDatabase {
    public abstract MediaDao mediaDao();

    // TODO: Provide singleton Room.databaseBuilder in application class.
    // TODO: Add migrations when schema changes.
}
