package br.com.geodrone.activity.utils;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.util.Log;

/**
 * Created by fernandes on 02/04/2018.
 */

public class Gps implements LocationListener {

    private final Context context;
    private Location location;

    @SuppressLint("MissingPermission")
    public Gps(Context context){
        this.context = context;
        // Get the location manager
        LocationManager locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
        // Define the criteria how to select the locatioin provider -> use
        // default
        Criteria criteria = new Criteria();
        boolean isGPSEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        Log.v("isGPSEnabled", "=" + isGPSEnabled);

        // getting network status
        boolean isNetworkEnabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
        Log.v("isNetworkEnabled", "=" + isNetworkEnabled);

        if (isNetworkEnabled){
            this.location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
        }else {
            this.location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        }

    }

    public Location getLocation() {
        return this.location;
    }

    @Override
    public void onLocationChanged(Location location) {
        this.location = location;
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }
}
