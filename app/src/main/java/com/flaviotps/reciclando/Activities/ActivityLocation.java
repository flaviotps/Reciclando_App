package com.flaviotps.reciclando.Activities;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.airbnb.lottie.LottieAnimationView;
import com.flaviotps.reciclando.Custom.CustomErrorDialog;
import com.flaviotps.reciclando.Data.DataRequestManager;
import com.flaviotps.reciclando.Data.LocationModel;
import com.flaviotps.reciclando.Interfaces.ICustomDialogClickListener;
import com.flaviotps.reciclando.Interfaces.LocationListener;
import com.flaviotps.reciclando.R;
import com.flaviotps.reciclando.Utils.Constants;
import com.flaviotps.reciclando.Utils.Helper;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


public class ActivityLocation extends AppCompatActivity implements View.OnClickListener, LocationListener, ICustomDialogClickListener {


    private MapView mMapView;
    private LocationManager mLocationManager;
    private GoogleMap mGoogleMap;
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private static ActivityLocation currentActivity;
    private LottieAnimationView lottieAnimationView;
    private CustomErrorDialog alert;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);

        currentActivity = this;

        lottieAnimationView = findViewById(R.id.animation_view_location);
        lottieAnimationView.playAnimation();

        mLocationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        mRecyclerView = findViewById(R.id.RecyclerViewLocation);

        mRecyclerView.setHasFixedSize(false);

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);

        StartMap(savedInstanceState);


    }


    @Override
    public void onResume() {
        super.onResume();

        if (mMapView != null) {
            mMapView.onResume();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (mMapView != null) {
            mMapView.onPause();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mMapView != null) {
            mMapView.onDestroy();
        }
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        if (mMapView != null) {
            mMapView.onLowMemory();
        }
    }

    @Override
    public void onClick(View view) {

    }


    private void StartMap(Bundle savedInstanceState) {
        mMapView = findViewById(R.id.map);
        mMapView.onCreate(savedInstanceState);
        mMapView.onResume();

        try {
            MapsInitializer.initialize(getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
        }

        mMapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap mMap) {
                mGoogleMap = mMap;


                if (Helper.CheckPermission(currentActivity)) {
                    Load();
                }



            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case Constants.PERMISSION_RESULT_CODE_OK: {

                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    //TODO: LOAD PLACES
                    Load();


                } else {
                    Log.i("gps =>", "PERMISSION DENIED");
                }
            }
        }
    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    private void Load() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                Location lastKnownLocation = mLocationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                DataRequestManager.getInstance(currentActivity).GetNearbyLocations("coleta:lixo:reciclagem:ecoponto", 20, lastKnownLocation,this);
                return;
            }

            try {
                Location lastKnownLocation = mLocationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                DataRequestManager.getInstance(currentActivity).GetNearbyLocations("coleta|lixo|reciclagem|ecoponto", 20, lastKnownLocation, this);
            }catch (Exception e){
                e.printStackTrace();
            }

        }
    }

    @Override
    public void onLoaded(Location location) {

        LocationModel.CreateMap(this, mGoogleMap, mRecyclerView, location);
        lottieAnimationView.setVisibility(View.GONE);

    }

    @Override
    public void onFail() {
        alert = new CustomErrorDialog(this);
        alert.showDialog(this, "Erro ao carregar os dados. \n Tente novamente mais tarde.");

    }


    @Override
    public void OnOK() {

    }

    @Override
    public void OnCancel() {

    }
}