package br.com.geodrone.activity;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.util.Log;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.List;

import br.com.geodrone.R;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, LocationListener {

        private static final String TAG = MapsActivity.class.getSimpleName();


        private GoogleMap mMap;

        private LocationManager lm;
        private Location location;
        private double longitude = -25.429675;
        private double latitude = -49.271870;


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_maps);
            SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
            mapFragment.getMapAsync(this);

            lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            String locationProvider = LocationManager.GPS_PROVIDER;
// Or, use GPS location data:
// String locationProvider = LocationManager.GPS_PROVIDER;

            Log.d(TAG, "teste 1");

            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                    ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }
            Log.d(TAG, "teste 2");
            lm.requestLocationUpdates(locationProvider, 0, 0, this);
        }

        private void makeUseOfNewLocation(Location location) {
            Log.d(TAG, location.toString());
        }


        @Override
        public void onMapReady(GoogleMap googleMap) {
            mMap = googleMap;
            if (lm != null) {
                if (location != null) {
                    latitude = location.getLatitude();
                    longitude = location.getLongitude();

                }
            }

            mMap.addMarker(new MarkerOptions().position(new LatLng(-18.9202562,-48.2460435)).title("Casa"));
            mMap.addMarker(new MarkerOptions().position(new LatLng(-18.9165137,-48.2439621)).title("Parque Bacaheri"));

            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(-18.9202562,-48.2460435), 10));

        }

        @Override
        public void onLocationChanged(Location location) {
            if (location != null) {
                String strLocation =
                        DateFormat.getTimeInstance().format(location.getTime()) + "\n" +
                                "Latitude=" + location.getLatitude() + "\n" +
                                "Longitude=" + location.getLongitude();
                Log.d(TAG, strLocation);
            }
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
