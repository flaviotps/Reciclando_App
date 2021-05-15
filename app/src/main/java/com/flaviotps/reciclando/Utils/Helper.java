package com.flaviotps.reciclando.Utils;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;

import com.google.android.gms.maps.MapView;

import androidx.core.app.ActivityCompat;

public class Helper {


    public  static  void PreLoadMap(final Context c){

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    MapView mv = new MapView(c);
                    mv.onCreate(null);
                    mv.onPause();
                    mv.onDestroy();
                }catch (Exception ignored){

                }
            }
        }).start();
    }


    public static Typeface getFont(Context context, String res) {
        Typeface mTypeFace;
        mTypeFace = Typeface.createFromAsset(context.getAssets(), res);
        return mTypeFace;

    }

    public static boolean CheckPermission(Activity activity) {
        if (ActivityCompat.checkSelfPermission(activity.getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(activity.getApplicationContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                activity.requestPermissions(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.INTERNET}
                        , Constants.PERMISSION_RESULT_CODE_OK);
            }
            return false;
        } else {

            return true;
        }
    }


    public static int CheckConnection(Activity activity) {


        ConnectivityManager cm = (ConnectivityManager) activity.getApplicationContext().getSystemService(
                Context.CONNECTIVITY_SERVICE);

        NetworkInfo wifiNetwork = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        if (wifiNetwork != null && wifiNetwork.isConnected()) {


            return Constants.CONNECTION_TYPE_WIFI;
        }

        NetworkInfo mobileNetwork = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        if (mobileNetwork != null && mobileNetwork.isConnected()) {
            return Constants.CONNECTION_TYPE_MOBILE;
        }

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        if (activeNetwork != null && activeNetwork.isConnected()) {
            return Constants.CONNECTION_TYPE_OTHER;
        }

        return Constants.CONNECTION_OFFLINE;
    }


}







