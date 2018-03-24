package br.com.geodrone.activity;

import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.GeomagneticField;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
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
import br.com.geodrone.activity.utils.Constantes;
import br.com.geodrone.activity.utils.LocationUtils;
import br.com.geodrone.dto.ColetaPluviosidadeDto;
import br.com.geodrone.model.EstacaoPluviometrica;
import br.com.geodrone.presenter.EstacaoPluviometricaPresenter;
import butterknife.ButterKnife;

public class CadastroPluviosidadeActivity extends FragmentActivity implements OnMapReadyCallback, LocationListener {

    private static final String TAG = CadastroPluviosidadeActivity.class.getSimpleName();

    private GoogleMap mMap;
    private LocationManager locationManager;
    private List<ColetaPluviosidadeDto> coletaPluviosidadeDtos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_pluviosidade);
        ButterKnife.bind(this);

        ActivityHelper activityHelper = new ActivityHelper();
        if (!activityHelper.checkPermissionsLocation(this)){
            return;
        }

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        initMap();
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
                locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, Constantes.MIN_TIME_BW_UPDATES, Constantes.MIN_DISTANCE_CHANGE_FOR_UPDATES, this);
                location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
            }else{
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, Constantes.MIN_TIME_BW_UPDATES, Constantes.MIN_DISTANCE_CHANGE_FOR_UPDATES, this);
                location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            }
            if (location != null) {
                LatLng position = new LatLng(location.getLatitude(), location.getLongitude());
                //mMap.addMarker(new MarkerOptions().position(position).title("Posiçao atual"));
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(position, Constantes.ZOOM_MAP));
            }

            this.coletaPluviosidadeDtos = getPontosColeta();
            for(ColetaPluviosidadeDto pluviosidadeDiariaDto : this.coletaPluviosidadeDtos) {
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
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(locAtual, Constantes.ZOOM_MAP));


            ColetaPluviosidadeDto coletaPluviosidadeDto = LocationUtils.localLessDistance(location, this.coletaPluviosidadeDtos);
            Location locationMenor = LocationUtils.createNewLocation(coletaPluviosidadeDto.getLatitude(), coletaPluviosidadeDto.getLongitude());
            double distancia = LocationUtils.calculateDistance(location, locationMenor);
            Toast.makeText(CadastroPluviosidadeActivity.this, locAtual.toString() + "distancia: " + distancia, Toast.LENGTH_SHORT).show();

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

    private List<ColetaPluviosidadeDto> getPontosColeta(){
        EstacaoPluviometricaPresenter estacaoPluviometricaPresenter = new EstacaoPluviometricaPresenter(this);

        List<ColetaPluviosidadeDto> coletaPluviosidadeDtos = new ArrayList<>();
        List<EstacaoPluviometrica> estacaoPluviometricas = estacaoPluviometricaPresenter.findAllByCliente(1L);
        for (EstacaoPluviometrica estacaoPluviometrica : estacaoPluviometricas){

            ColetaPluviosidadeDto pluviosidadeDiariaDto = new ColetaPluviosidadeDto();
            pluviosidadeDiariaDto.setDescricao(estacaoPluviometrica.getDescricao());
            pluviosidadeDiariaDto.setLatitude(estacaoPluviometrica.getLatitude());
            pluviosidadeDiariaDto.setLongitude(estacaoPluviometrica.getLongitude());
            coletaPluviosidadeDtos.add(pluviosidadeDiariaDto);
        }
        return coletaPluviosidadeDtos;
    }
}