package br.com.geodrone.activity;

import android.Manifest;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Build;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
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
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.List;

import br.com.geodrone.R;
import br.com.geodrone.activity.utils.ActivityHelper;
import br.com.geodrone.activity.utils.Constantes;
import br.com.geodrone.activity.utils.GPSTracker;
import br.com.geodrone.activity.utils.LocationUtils;
import br.com.geodrone.dto.ColetaPluviosidadeDto;
import br.com.geodrone.model.EstacaoPluviometrica;
import br.com.geodrone.presenter.EstacaoPluviometricaPresenter;
import br.com.geodrone.view.dialog.DialogInformarPluviosidade;
import butterknife.ButterKnife;

public class CadastroPluviosidadeActivity extends FragmentActivity implements OnMapReadyCallback , GoogleMap.OnMarkerClickListener{

    private static final String TAG = CadastroPluviosidadeActivity.class.getSimpleName();

    private GoogleMap mMap;
    private GPSTracker tracker;
    private static final int REQ_LOCATION_PERMISSION = 101;
    private static final int REQ_OPEN_SETTING = 102;
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
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }

        tracker = new GPSTracker(this, mMap) {
            @Override
            public void onLocationChanged(Location location) {

                super.onLocationChanged(location);
                updateLocation(location);
            }
        };
        mMap.setOnMarkerClickListener(this);

        this.coletaPluviosidadeDtos = getPontosColeta();
        for(ColetaPluviosidadeDto pluviosidadeDiariaDto : this.coletaPluviosidadeDtos) {
            LatLng position = new LatLng(pluviosidadeDiariaDto.getLatitude(), pluviosidadeDiariaDto.getLongitude());
            mMap.addMarker(new MarkerOptions().position(position).title(pluviosidadeDiariaDto.getDescricao()));

        }
        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener(){
            @Override
            public void onMapClick(LatLng point) {
                Toast.makeText(CadastroPluviosidadeActivity.this, point.toString(), Toast.LENGTH_LONG).show();

            }


        });

    }

    @Override
    public boolean onMarkerClick(Marker marker) {

        Toast.makeText(this,marker.getTitle(),Toast.LENGTH_LONG).show();

        new DialogInformarPluviosidade(this, "");
        return true;
    }

    private void setLocation() {
        if (!tracker.canGetLocation) {
            if (Build.VERSION.SDK_INT >= 23) {
                String[] PermissionsLocation = {android.Manifest.permission.ACCESS_COARSE_LOCATION, android.Manifest.permission.ACCESS_FINE_LOCATION};
                if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    requestPermissions(PermissionsLocation, REQ_LOCATION_PERMISSION);
                } else
                    showLocationAlert();
            } else
                showLocationAlert();
        } else setMyLocation();
    }

    private void setMyLocation() {
        if (tracker.getLatitude() != 0 && tracker.getLongitude() != 0) {
            LatLng myLocation = new LatLng(tracker.getLatitude(), tracker.getLongitude());
            mMap.clear();
            mMap.addMarker(new MarkerOptions().position(myLocation).title("You are at Here!!"));
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(myLocation, 14f));
        }
    }

    private AlertDialog locationAlertDialog;
    private void showLocationAlert() {
        if (null == locationAlertDialog)
            locationAlertDialog = new AlertDialog.Builder(this, Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP ? android.R.style.Theme_Material_Light_Dialog_Alert : -1)
                    .setCancelable(false)
                    .setMessage("This demo application would like to access your location")
                    .setPositiveButton("Settings", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                            Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                            startActivityForResult(intent, REQ_OPEN_SETTING);
                        }
                    }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    }).show();
        locationAlertDialog.show();
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQ_OPEN_SETTING) {
            if (null != tracker)
                tracker.getLocation();
            checkLocation();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        if (requestCode == REQ_LOCATION_PERMISSION) {
            if (grantResults.length > 1 && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                if (null != tracker)
                    tracker.getLocation();
                checkLocation();
            }
        }
    }

    private void checkLocation() {
        if (tracker.canGetLocation)
            setMyLocation();
        else
            showLocationAlert();
    }

    public void updateLocation(Location location) {
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