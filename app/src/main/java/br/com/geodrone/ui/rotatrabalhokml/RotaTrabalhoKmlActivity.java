package br.com.geodrone.ui.rotatrabalhokml;

import android.Manifest;
import android.location.Location;
import android.os.AsyncTask;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.maps.android.kml.KmlContainer;
import com.google.maps.android.kml.KmlLayer;
import com.google.maps.android.kml.KmlPlacemark;
import com.google.maps.android.kml.KmlPolygon;

import org.xmlpull.v1.XmlPullParserException;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.geodrone.R;
import br.com.geodrone.dto.LocationPointDto;
import br.com.geodrone.dto.LocationRotaDto;
import br.com.geodrone.ui.base.BaseMapFragmentActivity;
import br.com.geodrone.utils.DateUtils;
import br.com.geodrone.utils.FileUtils;
import br.com.geodrone.utils.KmlUtils;
import br.com.geodrone.utils.Messenger;
import butterknife.ButterKnife;

public class RotaTrabalhoKmlActivity extends BaseMapFragmentActivity implements RotaTrabalhoKmlPresenter.View{

    private KmlLayer kmlLayer;

    private RotaTrabalhoKmlPresenter rotaTrabalhoKmlPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rota_trabalho_kml);
        ButterKnife.bind(this);
        if (!hasPermission(Manifest.permission.ACCESS_COARSE_LOCATION) ||
                !hasPermission(Manifest.permission.ACCESS_FINE_LOCATION)) {
            requestPermissionsSafely(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION,
                    Manifest.permission.ACCESS_FINE_LOCATION}, REQ_LOCATION_PERMISSION);
        }
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map_rota_trabalho_kml);
        mapFragment.getMapAsync(this);

        rotaTrabalhoKmlPresenter = new RotaTrabalhoKmlPresenter(this);
    }
    @Override
    public void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        rotaTrabalhoKmlPresenter.takeView(this);
        hideLoading();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        super.onMapReady(googleMap);
        checkLocation();
        //retrieveFileFromUrl();
        rotaTrabalhoKmlPresenter.configLocationRota(new DateUtils().now());
    }

    @Override
    public void onChangeLocation(Location location) {

    }

    @Override
    public void onRotaTrabalhoKmlSucesso(LocationRotaDto locationRotaDto) {
        try{
            String bytes = KmlUtils.getLocationRotaDto(locationRotaDto);
            KmlLayer kmlLayer = new KmlLayer(mMap, FileUtils.getInputStream(bytes), getApplicationContext());
            kmlLayer.addLayerToMap();
        } catch (IOException e) {
            onError(e);
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            onError(e);
            e.printStackTrace();
        }
    }

    @Override
    public void onRotaTrabalhoKmlErro(String message) {

    }

    @Override
    public void onRotaTrabalhoKmlErro(Messenger messenger) {

    }
}