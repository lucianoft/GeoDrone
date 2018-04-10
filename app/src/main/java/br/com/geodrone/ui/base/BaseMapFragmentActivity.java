package br.com.geodrone.ui.base;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Build;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import br.com.geodrone.Session;
import br.com.geodrone.activity.utils.GPSTracker;
import br.com.geodrone.ui.monitoramento.MonitoramentoActivity;
import br.com.geodrone.utils.PreferencesUtils;

/**
 * Created by fernandes on 01/04/2018.
 */

public abstract class BaseMapFragmentActivity extends BaseFragmentActivity implements OnMapReadyCallback{

    private static final String TAG = MonitoramentoActivity.class.getName();

    public GoogleMap mMap;
    public GPSTracker tracker;
    private static final int REQ_LOCATION_PERMISSION = 101;
    private static final int REQ_OPEN_SETTING = 102;

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
                Session.setAttribute(PreferencesUtils.CHAVE_LOCALIZACAO_ATUAL, location);
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

    public abstract void onChangeLocation(Location location);
}
