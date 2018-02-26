package br.com.geodrone.activity;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.text.DateFormat;

import br.com.geodrone.R;
import br.com.geodrone.activity.utils.ActivityHelper;
import butterknife.ButterKnife;

public class CadastroPluviosidadeActivity extends FragmentActivity implements OnMapReadyCallback, LocationListener {

    private static final String TAG = CadastroPluviosidadeActivity.class.getSimpleName();

    private GoogleMap mMap;
    private LocationManager locationManager;

    // The minimum distance to change Updates in meters
    private static final long MIN_DISTANCE_CHANGE_FOR_UPDATES = 10; // 10 meters
    // The minimum time between updates in milliseconds
    private static final long MIN_TIME_BW_UPDATES = 1000 * 60 * 1; // 1 minute

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_pluviosidade);
        ButterKnife.bind(this);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        ActivityHelper activityHelper = new ActivityHelper();
        if (activityHelper.checkPermissionsLocation(this)){
            initMap();
        };
    }

    private void initMap() {
        try{
            mMap.setMyLocationEnabled(true);
            mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
            mMap.getUiSettings().setCompassEnabled(true);
            mMap.getUiSettings().setZoomControlsEnabled(true);
            mMap.getUiSettings().setMyLocationButtonEnabled(true);//botal da localizacao atual
            mMap.getUiSettings().setMapToolbarEnabled(true);
            mMap.getUiSettings().setZoomGesturesEnabled(true);
            mMap.getUiSettings().setScrollGesturesEnabled(true);
            mMap.getUiSettings().setTiltGesturesEnabled(true);
            mMap.getUiSettings().setRotateGesturesEnabled(true);

            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, MIN_TIME_BW_UPDATES, MIN_DISTANCE_CHANGE_FOR_UPDATES, this);
            if (locationManager != null) {
                Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                if (location != null) {
                    LatLng position = new LatLng(location.getLatitude(), location.getLongitude());
                    mMap.addMarker(new MarkerOptions().position(position).title("Posiçao atual"));
                    mMap.moveCamera(CameraUpdateFactory.newLatLng(position));
                }
            }
            /*mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener(){
                @Override
                public void onMapClick(LatLng point) {
                    Toast.makeText(CadastroPluviosidadeActivity.this, point.toString(), Toast.LENGTH_LONG).show();

                }

            });*/
        }catch(SecurityException ex){
            Toast.makeText(this, ex.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case ActivityHelper.REQUEST_PERMISSION_LOCATION:
                if(grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED){
                    initMap();
                } else {
                    finish();
                }
                break;
        }
    }

    @Override
    public void onLocationChanged(Location location) {
        if (location != null) {
            String strLocation =
                    DateFormat.getTimeInstance().format(location.getTime()) + "\n" +
                            "Latitude=" + location.getLatitude() + "\n" +
                            "Longitude=" + location.getLongitude();
            Log.d(TAG, strLocation);

            double lat = location.getLatitude();
            double lng = location.getLongitude();
            LatLng locAtual = new LatLng(lat, lng);
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(locAtual, 15));
            mMap.animateCamera(CameraUpdateFactory.zoomTo(15));

            Toast.makeText(CadastroPluviosidadeActivity.this, locAtual.toString(), Toast.LENGTH_SHORT).show();

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