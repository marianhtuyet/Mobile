package com.example.finalproject.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.finalproject.R;
import com.example.finalproject.model.Product;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ItemHolder> {
    Context context;
    ArrayList<Product> productArr;

    public ProductAdapter(Context context, ArrayList<Product> productArr) {
        this.context = context;
        this.productArr = productArr;
    }

    @NonNull
    @Override
    public ItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_newproduct,null);
        ItemHolder itemHolder = new ItemHolder(v);
        return itemHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ItemHolder holder, int position) {
        Product product = productArr.get(position);
        holder.textViewName.setText(product.getName());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        holder.textViewPrice.setText("Giá: "+decimalFormat.format(product.getPrice())+"đ");
        Picasso.with(context).load(product.getImage()).into(holder.imageViewProduct);
    }

    @Override
    public int getItemCount() {
        return productArr.size();
    }

    public class ItemHolder extends RecyclerView.ViewHolder{
        public ImageView imageViewProduct;
        public TextView textViewName, textViewPrice;

        public ItemHolder(@NonNull View itemView) {
            super(itemView);
            imageViewProduct = (ImageView)itemView.findViewById(R.id.imageview_product);
            textViewName = (TextView) itemView.findViewById(R.id.textview_productname);
            textViewPrice = (TextView) itemView.findViewById(R.id.textview_productprice);
        }
    }
}
