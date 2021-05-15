package com.flaviotps.reciclando.Data;

import android.app.Activity;
import android.content.Intent;
import android.location.Location;
import android.os.Handler;
import android.util.Log;

import com.airbnb.lottie.LottieAnimationView;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.flaviotps.reciclando.Activities.ActivityMain;
import com.flaviotps.reciclando.Interfaces.LocationListener;
import com.flaviotps.reciclando.R;
import com.flaviotps.reciclando.Utils.Constants;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONException;
import org.json.JSONObject;

import androidx.annotation.NonNull;

public class DataRequestManager {

    private RequestQueue queue;
    public static DataWrapper data;
    private Activity activity;
    private static DataRequestManager Instance;
    public FirebaseDatabase firebaseDatabase;
    public int RequestCounter = 0;


    public DataRequestManager(Activity activity) {
        this.activity = activity;
        this.queue = Volley.newRequestQueue(activity);
        Instance = this;


        firebaseDatabase = FirebaseDatabase.getInstance();
        firebaseDatabase.setPersistenceEnabled(true);
        data = new DataWrapper();

    }

    public static DataRequestManager getInstance(Activity mActivity) {

        if (Instance == null) {
            return new DataRequestManager(mActivity);
        }
        return Instance;
    }


    public void getData() {

        data = new DataWrapper();
        DatabaseReference myRef = firebaseDatabase.getReference(Constants.REF_APP);
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                int c = 0;
                for (DataSnapshot child : dataSnapshot.getChildren()) {

                    switch (child.getKey()) {
                        case Constants.REF_GRAPH:

                            for (DataSnapshot chart : child.getChildren()) {
                                data.graphList.add(chart.getValue(ChartModel.class));
                            }
                            break;

                        case Constants.REF_VIDEO:

                            for (DataSnapshot video : child.getChildren()) {
                                data.videoList.add(video.getValue(VideoModel.class));
                            }
                            break;

                        case Constants.REF_PRODUCT:

                            for (DataSnapshot product : child.getChildren()) {
                                data.productList.add(product.getValue(ProductModel.class));
                            }
                            break;


                        case Constants.REF_DISCARD:
                            for (DataSnapshot discard : child.getChildren()) {
                                data.discardList.add(discard.getValue(DiscardModel.class));
                            }
                            break;

                        case Constants.REF_MATERIAL:
                            for (DataSnapshot material : child.getChildren()) {
                                MaterialModel m = material.getValue(MaterialModel.class);
                                Log.i("CONTENT", m.Content);
                                data.materialList.add(m);
                            }
                            break;

                        case Constants.REF_PAGE:

                            for (DataSnapshot page : child.getChildren()) {
                                PageModel p = page.getValue(PageModel.class);
                                data.pageList.add(p);
                            }
                            break;
                    }

                }


                // LINK GRAFICOS -> PAGINAS
                for (ChartModel chart : data.graphList) {
                    chart.setTabPage(PageModel.getPageByCod(chart.Page, data.pageList));
                }

                // LINK VIDEOS -> PAGINAS
                for (VideoModel video : data.videoList) {
                    video.setTabPage(PageModel.getPageByCod(video.Page, data.pageList));
                }

                // LINK PRODUTOS -> DESCARTE
                for (ProductModel product : data.productList) {
                    product.Desc = DiscardModel.getDiscard(product.Discard, data.discardList);
                }


                LottieAnimationView lottieAnimationView = activity.findViewById(R.id.animation_view);
                lottieAnimationView.playAnimation();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(activity.getApplicationContext(), ActivityMain.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                        activity.startActivity(intent);
                    }
                },lottieAnimationView.getDuration());





            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }


    public void GetNearbyLocations(String query, int radius, final Location mPosition, final LocationListener locationListener) {


            String RequestURL = Constants.GOOGLE_PLACES_URL + query + "&location=" + mPosition.getLatitude() + "," + mPosition.getLongitude() + "&radius=" + radius + "&key=" + Constants.GOOGLE_PLACES_SERVER_KEY;

            if (RequestURL.isEmpty()) {
                return;
            }

            RequestCounter++;

            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, RequestURL, null,
                    new Response.Listener<JSONObject>() {

                        @Override
                        public void onResponse(JSONObject response) {

                            try {
                                RequestCounter--;
                                LocationModel.AddToLocationList(response);
                                Log.i("RESPONSE =>", response.toString());
                                locationListener.onLoaded(mPosition);
                            } catch (JSONException e) {
                                locationListener.onFail();
                                e.printStackTrace();
                            }

                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    locationListener.onFail();
                    Log.i("ERROR", error.toString());
                }
            });

            queue.add(jsonObjectRequest);
        }


    }







