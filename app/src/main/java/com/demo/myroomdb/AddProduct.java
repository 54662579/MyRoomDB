package com.demo.myroomdb;

import android.arch.lifecycle.ViewModelProviders;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.demo.model.Product;
import com.demo.model.ProductViewModel;

public class AddProduct extends AppCompatActivity {

    private ProductViewModel productViewModel;
    private EditText name, price;
    private Button saveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);
        name = findViewById(R.id.productName);
        price = findViewById(R.id.productPrice);
        saveButton = findViewById(R.id.saveButton);
        productViewModel = ViewModelProviders.of(this).get(ProductViewModel.class);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                Product product = new Product();
                product.setName(name.getText().toString());
                product.setPrice(Double.valueOf(price.getText().toString()));
                productViewModel.insert(product);
                Toast.makeText(getApplicationContext(), "Product saved", Toast.LENGTH_LONG).show();
                name.setText("");
                price.setText("");}
                catch (NullPointerException e){
                    e.printStackTrace();
                }
            }
        });

    }
}
