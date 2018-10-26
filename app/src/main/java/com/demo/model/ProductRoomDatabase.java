package com.demo.model;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.support.annotation.NonNull;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

@Database(entities = {Product.class}, version = 1)
public abstract class ProductRoomDatabase extends RoomDatabase {

    //specify the DAO that database is going to work with
    public abstract ProductDao productDao();

    /*Make ProductRoomDatabase a singleton to prevent having multiple instances of the database
    opened at the same time.*/
    public static volatile ProductRoomDatabase INSTANCE;


    static ProductRoomDatabase getDatabase (final Context context){
        if (INSTANCE == null) {
            synchronized (ProductRoomDatabase.class){
                if (INSTANCE == null) {
                    //create a database
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            ProductRoomDatabase.class, "product_database").addCallback(new Callback() {
                        @Override
                        public void onCreate(@NonNull SupportSQLiteDatabase db) {
                            super.onCreate(db);
                            Executors.newSingleThreadExecutor().execute(new Runnable() {
                                @Override
                                public void run() {
                                    //prepopulate some sample data
                                    getDatabase(context).productDao().insertAll(Product.initialiseData());
                                }
                            });
                        }
                    })
                            .allowMainThreadQueries()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
