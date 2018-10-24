package com.demo.model;

import android.app.Application;
import android.os.AsyncTask;

import java.util.List;

public class ProductRepository {

    //add member variables
    private ProductDao mProductDao;


    //create constructor that connects to database and initialise member variables
    public ProductRepository(Application application){
        ProductRoomDatabase db = ProductRoomDatabase.getDatabase(application);
        mProductDao = db.productDao();


    }

    //insert a Product, it has to be done in the background as to not block the main thread
    public void insert(Product product){
        mProductDao.insert(product);
    }


    public void insertAll(Product... products) {mProductDao.insertAll(products);}

    //populate the list of product
    public List<Product> getAll(){
        return mProductDao.getAll();
    }

    //get product by id
    public Product get(int id) {
        return mProductDao.get(id);
    }

    //update

    public void update(Product product){
        mProductDao.update(product);
    }

    //delete
    public void delete(Product product){
        mProductDao.delete(product);
    }

    public void deleteAll(){
        mProductDao.deleteAll();
    }
}
