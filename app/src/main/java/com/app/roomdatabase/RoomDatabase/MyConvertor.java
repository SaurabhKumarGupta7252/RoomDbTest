package com.app.roomdatabase.RoomDatabase;

import androidx.room.TypeConverter;

import com.app.roomdatabase.Model.Rating;
import com.google.gson.Gson;

public class MyConvertor {

    @TypeConverter
    public String RatingToJson(Rating rating) {

        return new Gson().toJson(rating);
    }

    @TypeConverter
    public Rating jsonToRating(String string) {

        return new Gson().fromJson(string, Rating.class);
    }

}
