package com.app.roomdatabase.RoomDatabase;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.app.roomdatabase.Model.Product;

@Database(entities = {Product.class}, version = 1, exportSchema = false)
@TypeConverters(MyConvertor.class)
public abstract class AppDatabase extends RoomDatabase {

    public static AppDatabase roomDatabase = null;

    public static AppDatabase getRoomDatabase() {
        return roomDatabase;
    }

    public static AppDatabase getInstance(Context context) {

        if (roomDatabase == null) {
            synchronized (AppDatabase.class) {
                roomDatabase = Room.databaseBuilder(context, AppDatabase.class, "MY_DB").allowMainThreadQueries().build();
            }
        }
        return roomDatabase;
    }

    public abstract ProductDao getDao();
}