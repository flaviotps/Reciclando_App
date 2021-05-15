package com.flaviotps.reciclando.Adapter;

import android.app.Activity;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.flaviotps.reciclando.Data.ProductModel;
import com.flaviotps.reciclando.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {


    private List<ProductModel> products;
    private Activity activity;

    public ProductAdapter(List<ProductModel> products, Activity activity) {
        this.products = products;
        this.activity = activity;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View mCustomView = LayoutInflater.from(parent.getContext()).inflate(R.layout.include_product, parent, false);

        ProductAdapter.ProductViewHolder productViewHolder = new ProductAdapter.ProductViewHolder(mCustomView);

        return productViewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        ProductModel p = products.get(position);
        if (p.Title != null && p.Title.length() > 0) {
            holder.Title.setText(products.get(position).Title);
        }
        if (p.Desc != null && p.Desc.length() > 0) {
            holder.Description.setText(Html.fromHtml( "Onde descartar: <i>"+products.get(position).Desc+"</i>"));
        }
        if (p.Synonymous != null && p.Synonymous.length() > 0) {
            holder.Synonymous.setText(Html.fromHtml("Sinônimos: <i>" + p.Synonymous+"</i>"));
        }
    }

    @Override
    public int getItemCount() {
        return products.size();
    }


    public class ProductViewHolder extends RecyclerView.ViewHolder {

        TextView Title;
        TextView Description;
        TextView Synonymous;


        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);

            Title = itemView.findViewById(R.id.ProductTitle);
            Synonymous = itemView.findViewById(R.id.ProductSynonymous);
            Description = itemView.findViewById(R.id.ProductDesc);


        }
    }
}
