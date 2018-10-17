package com.demo.model;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface PlaceDao {

    @Insert  //insert place to database
    void insert(Place place);

    //get place by id
    @Query("SELECT * FROM PLACE where id = :rId")
    Place get(int rId);

    //update place
    @Update
    void update(Place place);

    //delete single place
    @Delete
    void delete(Place place);

    //delete all place
    @Query("DELETE FROM PLACE")
    void deleteAll();

    //get all data
    @Query("SELECT * FROM PLACE")
    LiveData<List<Place>> getAll();
}
