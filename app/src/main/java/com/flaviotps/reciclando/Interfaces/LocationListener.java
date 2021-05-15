package com.flaviotps.reciclando.Interfaces;

import android.location.Location;

public interface LocationListener {
    void onLoaded(Location location);
    void onFail();
}
