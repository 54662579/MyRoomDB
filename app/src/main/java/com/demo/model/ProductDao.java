package com.demo.model;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface ProductDao {

    @Insert  //insert product to database
    void insert(Product product);

    @Insert
    void insertAll(Product... products);

    //get product by id
    @Query("SELECT * FROM PRODUCT where id = :rId")
    Product get(int rId);

    //update product
    @Update
    void update(Product product);

    //delete single product
    @Delete
    void delete(Product product);

    //delete all product
    @Query("DELETE FROM PRODUCT")
    void deleteAll();

    //get all data
    @Query("SELECT * FROM PRODUCT")
    List<Product> getAll();
}
