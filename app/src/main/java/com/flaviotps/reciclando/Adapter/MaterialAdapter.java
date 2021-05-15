package com.flaviotps.reciclando.Adapter;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.flaviotps.reciclando.Activities.ActivityMaterialDetails;
import com.flaviotps.reciclando.Data.MaterialModel;
import com.flaviotps.reciclando.R;
import com.flaviotps.reciclando.Utils.Constants;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MaterialAdapter extends RecyclerView.Adapter<MaterialAdapter.MaterialViewHolder> {


    private List<MaterialModel> materialModels;
    private Activity activity;

    public MaterialAdapter(List<MaterialModel> materialModels, Activity activity) {
        this.materialModels = materialModels;
        this.activity = activity;
    }


    @NonNull
    @Override
    public MaterialViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mCustomView = LayoutInflater.from(parent.getContext()).inflate(R.layout.include_material, parent, false);

        MaterialViewHolder materialViewHolder = new MaterialViewHolder(mCustomView);

        return materialViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MaterialViewHolder holder, final int position) {


        Glide.with(activity).load(materialModels.get(position).Image).into(holder.Thumbnail);
        holder.Title.setText(materialModels.get(position).Name);
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(activity, ActivityMaterialDetails.class);
                intent.putExtra(Constants.BUNDLE_ARG_MATERIAL, materialModels.get(position));
                activity.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return materialModels.size();
    }

    public class MaterialViewHolder extends RecyclerView.ViewHolder {

        TextView Title;
        ImageView Thumbnail;
        View view;


        public MaterialViewHolder(@NonNull View itemView) {
            super(itemView);

            Title = itemView.findViewById(R.id.materialTitle);
            Thumbnail = itemView.findViewById(R.id.materialImage);
            view = itemView;


        }
    }
}
