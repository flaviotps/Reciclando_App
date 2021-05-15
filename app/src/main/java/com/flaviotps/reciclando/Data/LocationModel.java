package com.flaviotps.reciclando.Data;


import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Location;

import com.flaviotps.reciclando.Adapter.LocationAdapter;
import com.flaviotps.reciclando.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

public class LocationModel {


    private String FormattedAddress;
    private String Name;
    private LatLng Location ;
    private String EmptyString = "";


    public String UniqueID;
    private float Distance;

    public LocationModel(String formattedAddress, String name, LatLng location, String uid) {
        FormattedAddress = formattedAddress;
        Name = name;
        Location = location;
        this.UniqueID = uid;
    }

    private static float Distance(LocationModel cl1, android.location.Location location) {

        float[] results = new float[1];
        android.location.Location.distanceBetween(cl1.Location.latitude, cl1.Location.longitude, location.getLatitude(), location.getLongitude(), results);
        return results[0];
    }

    private static List<LocationModel> OrderByDistance(Location location) {

        if (DataRequestManager.data.locationList == null || DataRequestManager.data.locationList.size() == 0) {
            return null;
        }

        List<LocationModel> placesInOrder = new ArrayList<>();
        float lastDistance;
        int LastIndex;

        do {

            lastDistance = Distance(DataRequestManager.data.locationList.get(0), location);
            LastIndex = 0;

            for (int i = 0; i < DataRequestManager.data.locationList.size(); i++) {

                DataRequestManager.data.locationList.get(i).setDistance(Distance(DataRequestManager.data.locationList.get(i), location));

                if (DataRequestManager.data.locationList.get(i).getDistance() < lastDistance) {
                    lastDistance = DataRequestManager.data.locationList.get(i).getDistance();
                    LastIndex = i;
                }
            }

            placesInOrder.add(DataRequestManager.data.locationList.get(LastIndex));
            DataRequestManager.data.locationList.remove(LastIndex);

        } while (DataRequestManager.data.locationList.size() != 0);

        return placesInOrder;
    }

    public static void CreateMap(Activity activity, GoogleMap googleMap, RecyclerView recyclerViewLocations, Location location) {


        if (DataRequestManager.data.locationList == null || DataRequestManager.data.locationList.size() == 0) {
            return;
        }

        DataRequestManager.data.locationList = OrderByDistance(location);


        for (int i = 0; i < DataRequestManager.data.locationList.size(); i++) {

            MarkerOptions options = new MarkerOptions();
            options.position(DataRequestManager.data.locationList.get(i).Location);
            options.title(DataRequestManager.data.locationList.get(i).getName());

            Bitmap bitmap = BitmapFactory.decodeResource(activity.getResources(), R.drawable.recycling_point);
            BitmapDescriptor bitmapDescriptor = BitmapDescriptorFactory.fromBitmap(Bitmap.createScaledBitmap(bitmap, 120, 120, false));
            options.icon(bitmapDescriptor);

            googleMap.addMarker(options);
        }

        recyclerViewLocations.setAdapter(new LocationAdapter(DataRequestManager.data.locationList, googleMap));

        if (DataRequestManager.data.locationList.size() != 0) {

            CameraPosition cameraPosition = new CameraPosition.Builder().target(DataRequestManager.data.locationList.get(0).Location).zoom(12).build();
            googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
        }


    }

    public String getFormattedAddress() {
        return FormattedAddress;
    }

    public void setFormattedAddress(String formattedAddress) {
        FormattedAddress = formattedAddress;
    }

    public String getName() {
        return (Name == null || Name.isEmpty()) ? EmptyString : Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public LatLng getLocation() {
        return Location;
    }

    public void setLocation(LatLng location) {
        Location = location;
    }

    public static void AddToLocationList(JSONObject response) throws JSONException {

        JSONArray mJsonArray = response.getJSONArray("results");

        for(int i=0;i<mJsonArray.length();i++){

            JSONObject mJsonObject = mJsonArray.getJSONObject(i);
            String uid = mJsonObject.getString("place_id");
            String name = mJsonObject.getString("name");
            String address = mJsonObject.getString("formatted_address");
            JSONObject mLocation = mJsonObject.getJSONObject("geometry").getJSONObject("location");
            LatLng mLatLng = new LatLng(mLocation.getDouble("lat"),mLocation.getDouble("lng"));
            LocationModel mLocationModel = new LocationModel(address, name, mLatLng, uid);


            boolean duplicated = false;


            for (int j = 0; j < DataRequestManager.data.locationList.size(); j++) {
                if (DataRequestManager.data.locationList.get(j).getUniqueID().equals(uid)) {
                    duplicated = true;
                }

            }

            if (!duplicated)
                DataRequestManager.data.locationList.add(mLocationModel);


        }


    }

    public String toString() {
        return FormattedAddress + "- " + Name + " - (" + Location.latitude + "," + Location.longitude + ")";
    }


    public String getShortAddress() {

        String[] s = getFormattedAddress().split(",");
        return (s[0] == null && s[1] != null) ? EmptyString : s[0] + "," + s[1];
    }

    public String getUniqueID() {
        return UniqueID;
    }

    public void setUniqueID(String uniqueID) {
        UniqueID = uniqueID;
    }

    public float getDistance() {
        return Distance / 1000;
    }

    public void setDistance(float distance) {
        Distance = distance;
    }

    public String getRegion() {

        String[] s = getFormattedAddress().split(",");
        return (s == null || s.length < 3 || s[2] == null) ? EmptyString : s[2].trim();

    }
}