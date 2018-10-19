package com.demo.model;

import android.arch.persistence.db.SupportSQLiteOpenHelper;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.DatabaseConfiguration;
import android.arch.persistence.room.InvalidationTracker;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.support.annotation.NonNull;

@Database(entities = {Place.class}, version = 1)
public abstract class PlaceRoomDatabase extends RoomDatabase {

    //specify the DAO that database is going to work with
    public abstract PlaceDao placeDao();

    /*Make PlaceRoomDatabase a singleton to prevent having multiple instances of the database
    opened at the same time.*/
    public static volatile PlaceRoomDatabase INSTANCE;


    static PlaceRoomDatabase getDatabase (final Context context){
        if (INSTANCE == null) {
            synchronized (PlaceRoomDatabase.class){
                if (INSTANCE == null) {
                    //create a database
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            PlaceRoomDatabase.class, "place_database")
                            //.allowMainThreadQueries()
                            .build();
                }
            }
        }
        return INSTANCE;
    }


}
