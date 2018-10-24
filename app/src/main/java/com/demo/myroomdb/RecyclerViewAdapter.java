package com.demo.myroomdb;

import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.demo.model.Product;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter
        .ProductViewHolder> {

    private List<Product> productList;

    public RecyclerViewAdapter(List<Product> productList) {
        this.productList = productList;
    }

    //must override below three methods when extending RecyclerView.Adapter
    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout
                .activity_list_product, viewGroup, false);
        ProductViewHolder productViewHolder = new ProductViewHolder(view);
        return productViewHolder;
    }

    @Override //this method is used to set the content of each item
    public void onBindViewHolder(@NonNull ProductViewHolder productViewHolder, int i) {
        Product p = productList.get(i);
        productViewHolder.product.setText(p.getName());
        productViewHolder.price.setText(String.valueOf(p.getPrice()));
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    public static class ProductViewHolder extends RecyclerView.ViewHolder{

        CardView cv;
        TextView product;
        TextView price;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            cv = itemView.findViewById(R.id.cardView);
            product = itemView.findViewById(R.id.product);
            price = itemView.findViewById(R.id.price);
        }
    }
}
