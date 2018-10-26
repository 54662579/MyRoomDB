package com.demo.model;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;

import java.util.List;

public class ProductViewModel extends AndroidViewModel {

    private ProductRepository productRepository;

    public ProductViewModel(@NonNull Application application) {
        super(application);
        productRepository = new ProductRepository(application);

    }

    public void insert(Product product){ productRepository.insert(product);}
    public void insertAll(Product... products){productRepository.insertAll(products);}
    public Product get(int id){return productRepository.get(id);}
    public List<Product> getAll() {return productRepository.getAll(); }
    public void update(Product product){productRepository.update(product);}
    public void delete(Product product){productRepository.delete(product);}

}
