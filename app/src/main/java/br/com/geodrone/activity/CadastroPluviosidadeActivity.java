package br.com.geodrone.activity;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.GeomagneticField;
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
import com.google.maps.android.SphericalUtil;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.List;

import br.com.geodrone.R;
import br.com.geodrone.activity.utils.ActivityHelper;
import br.com.geodrone.dto.PluviosidadeDiariaDto;
import butterknife.ButterKnife;

public class CadastroPluviosidadeActivity extends FragmentActivity implements OnMapReadyCallback, LocationListener {

    private static final String TAG = CadastroPluviosidadeActivity.class.getSimpleName();

    private GoogleMap mMap;
    private LocationManager locationManager;

    // The minimum distance to change Updates in meters
    private static final long MIN_DISTANCE_CHANGE_FOR_UPDATES = 10; // 10 meters
    // The minimum time between updates in milliseconds
    private static final long MIN_TIME_BW_UPDATES = 1000 * 60 * 1; // 1 minute
    private static final int ZOOM_MAP = 17;


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
            // getting GPS status
            boolean isGPSEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
            // getting network status
            boolean isNetworkEnabled = false/*locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)*/;

            Location location = null;

            if (isNetworkEnabled) {
                locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, MIN_TIME_BW_UPDATES, MIN_DISTANCE_CHANGE_FOR_UPDATES, this);
                location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
            }else{
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, MIN_TIME_BW_UPDATES, MIN_DISTANCE_CHANGE_FOR_UPDATES, this);
                location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            }
            if (location != null) {
                LatLng position = new LatLng(location.getLatitude(), location.getLongitude());
                mMap.addMarker(new MarkerOptions().position(position).title("Posiçao atual"));
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(position, ZOOM_MAP));
            }

            List<PluviosidadeDiariaDto> s = getPontosColeta();
            for(PluviosidadeDiariaDto pluviosidadeDiariaDto : s) {
                LatLng position = new LatLng(pluviosidadeDiariaDto.getLatitude(), pluviosidadeDiariaDto.getLongitude());
                mMap.addMarker(new MarkerOptions().position(position).title(pluviosidadeDiariaDto.getDescricao()));

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
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(locAtual, ZOOM_MAP));

            GeomagneticField geoField = new GeomagneticField(Double.valueOf(location.getLatitude()).floatValue(), Double.valueOf(location.getLongitude()).floatValue(), Double.valueOf(location.getAltitude()).floatValue(), System.currentTimeMillis());
            float Declination = geoField.getDeclination();

            LatLng locDestino = new LatLng(new Double("-18.100"), new Double("-47.3344"));
            double distancia = SphericalUtil.computeDistanceBetween(locAtual, locDestino);
            Toast.makeText(CadastroPluviosidadeActivity.this, locAtual.toString() + " Distancia: " + distancia + " m", Toast.LENGTH_SHORT).show();

        }
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {
        Log.i(s, s);
    }

    @Override
    public void onProviderDisabled(String s) {

    }

    private List<PluviosidadeDiariaDto> getPontosColeta(){
        Double latitude = new Double("-18.100");
        Double longitude = new Double("-47.3344");
        List<PluviosidadeDiariaDto> pluviosidadeDiariaDtos = new ArrayList<>();
        for (int i = 0 ; i <= 50; i++){
            latitude = latitude + new Double("0.010");
            longitude = longitude + new Double("0.011");

            PluviosidadeDiariaDto pluviosidadeDiariaDto = new PluviosidadeDiariaDto();
            pluviosidadeDiariaDto.setDescricao("Posicao " + i);
            pluviosidadeDiariaDto.setLatitude(latitude);
            pluviosidadeDiariaDto.setLongitude(longitude);
            pluviosidadeDiariaDtos.add(pluviosidadeDiariaDto);
        }
        return pluviosidadeDiariaDtos;
    }
}