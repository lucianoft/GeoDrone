package br.com.geodrone.ui.monitoramento;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Build;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.MenuItem;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import br.com.geodrone.R;
import br.com.geodrone.ui.base.BaseFragmentActivity;
import br.com.geodrone.ui.registrochuva.RegistroPluviosidadeActivity;
import br.com.geodrone.activity.utils.ActivityHelper;
import br.com.geodrone.activity.utils.GPSTracker;
import br.com.geodrone.ui.registrodoenca.RegistroDoencaActivity;
import br.com.geodrone.ui.registropraga.RegistroPragaActivity;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MonitoramentoActivity extends BaseFragmentActivity implements OnMapReadyCallback,
                                                                            BottomNavigationView.OnNavigationItemSelectedListener,
        MonitoramentoPresenter.View{

    private GoogleMap mMap;
    private GPSTracker tracker;
    private static final int REQ_LOCATION_PERMISSION = 101;
    private static final int REQ_OPEN_SETTING = 102;
    @BindView(R.id.bottom_navigation_monitoramento)
    BottomNavigationView bottomNavigationView ;

    private MonitoramentoPresenter monitoramentoPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monitoramento);
        ButterKnife.bind(this);

        monitoramentoPresenter = new MonitoramentoPresenter(this);

        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        ActivityHelper activityHelper = new ActivityHelper();
        if (!activityHelper.checkPermissionsLocation(this)) {
            return;
        }
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map_monitoramento);
        mapFragment.getMapAsync(this);
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
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
                onChangeLocation(location);
            }
        };

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


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_item_registrar_praga:
                Intent i = new Intent(this, RegistroPragaActivity.class);
                i.putExtra("localizacao", tracker.getLocation());
                startActivity(i);
                break;
            case R.id.menu_item_sem_registrar_praga:
                i = new Intent(this, RegistroPragaActivity.class);
                i.putExtra("localizacao", tracker.getLocation());
                startActivity(i);
                break;
            case R.id.menu_item_registrar_doenca:
                i = new Intent(this, RegistroDoencaActivity.class);
                i.putExtra("localizacao", tracker.getLocation());
                startActivity(i);

                break;
            case R.id.menu_item_sem_registrar_doenca:
                i = new Intent(this, RegistroDoencaActivity.class);
                i.putExtra("localizacao", tracker.getLocation());
                startActivity(i);

                break;
            default:
                break;
        }
        return true;
    }


    @Override
    public void onChangeLocation(Location location) {
        monitoramentoPresenter.onChangeLocation(location);
    }
}
