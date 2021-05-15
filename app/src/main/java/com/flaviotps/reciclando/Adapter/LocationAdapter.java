package com.flaviotps.reciclando.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.flaviotps.reciclando.Data.LocationModel;
import com.flaviotps.reciclando.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.CameraPosition;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class LocationAdapter extends RecyclerView.Adapter<LocationAdapter.LocationViewHolder> {


    private List<LocationModel> mLocationModels;
    private GoogleMap mMap;

    public LocationAdapter(List<LocationModel> mLocationModels, GoogleMap mMap) {

        this.mLocationModels = mLocationModels;
        this.mMap = mMap;
    }

    @NonNull
    @Override
    public LocationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mCustomView = LayoutInflater.from(parent.getContext()).inflate(R.layout.include_collect_location, parent, false);

        LocationViewHolder mLocationViewHolder = new LocationViewHolder(mCustomView);

        return mLocationViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull LocationViewHolder holder, final int position) {


        holder.Name.setText(mLocationModels.get(position).getName());
        holder.Address.setText(mLocationModels.get(position).getShortAddress());
        holder.Region.setText(mLocationModels.get(position).getRegion());
        holder.Id.setText(String.valueOf(position));
        holder.Distance.setText(String.format("%.01f", mLocationModels.get(position).getDistance()) + "km");

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                CameraPosition cameraPosition = new CameraPosition.Builder().target(mLocationModels.get(position).getLocation()).zoom(17).build();
                mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
            }
        });

    }

    @Override
    public int getItemCount() {
        return mLocationModels.size();
    }

    public class LocationViewHolder extends RecyclerView.ViewHolder {

        TextView Name;
        TextView Address;
        TextView Region;
        TextView Id;
        TextView Distance;


        public LocationViewHolder(@NonNull View itemView) {
            super(itemView);

            Name = itemView.findViewById(R.id.txtLocationName);
            Address = itemView.findViewById(R.id.txtLocationAddress);
            Region = itemView.findViewById(R.id.txtLocationRegion);
            Id = itemView.findViewById(R.id.txtId);
            Distance = itemView.findViewById(R.id.txtLocationDistance);


        }
    }
}
