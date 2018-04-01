package br.com.geodrone.ui.registrochuva;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
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
import br.com.geodrone.model.PontoColetaChuva;
import br.com.geodrone.presenter.PontoColetaChuvaPresenter;
import br.com.geodrone.ui.base.BaseFragmentActivity;
import br.com.geodrone.ui.base.BaseMapFragmentActivity;
import br.com.geodrone.ui.main.MainActivity;
import br.com.geodrone.view.dialog.DialogInformarPluviosidade;
import butterknife.ButterKnife;

public class RegistroPluviosidadeActivity extends BaseMapFragmentActivity implements GoogleMap.OnMarkerClickListener,
                                                                              RegistroPluviosidadePresenter.View  {

    private static final String TAG = RegistroPluviosidadeActivity.class.getSimpleName();

    private RegistroPluviosidadePresenter registroPluviosidadePresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_pluviosidade);
        ButterKnife.bind(this);

        ActivityHelper activityHelper = new ActivityHelper();
        if (!activityHelper.checkPermissionsLocation(this)){
            return;
        }

        registroPluviosidadePresenter = new RegistroPluviosidadePresenter(this);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        super.onMapReady(googleMap);
        mMap.setOnMarkerClickListener(this);

        List<ColetaPluviosidadeDto> coletaPluviosidadeDtos = registroPluviosidadePresenter.getPontosColeta();
        for(ColetaPluviosidadeDto pluviosidadeDiariaDto : coletaPluviosidadeDtos) {
            LatLng position = new LatLng(pluviosidadeDiariaDto.getLatitude(), pluviosidadeDiariaDto.getLongitude());

            BitmapDescriptor icon = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE);
            Marker marker = mMap.addMarker(new MarkerOptions().position(position)
                                              .title(pluviosidadeDiariaDto.getDescricao())
                                              .snippet(pluviosidadeDiariaDto.getUltimaLeitura())
                                              .icon(icon));
            marker.showInfoWindow();
        }
        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener(){
            @Override
            public void onMapClick(LatLng point) {
            showMessage(point.toString());
            }


        });
    }

    @Override
    public void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        registroPluviosidadePresenter.takeView(this);
        hideLoading();
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        showRegistroPluviosidade(marker);
        return true;
    }

    private void showRegistroPluviosidade(Marker marker) {
        LatLng latLng = marker.getPosition();
        Location temp = new Location(LocationManager.GPS_PROVIDER);
        temp.setLatitude(latLng.latitude);
        temp.setLongitude(latLng.longitude);
        ColetaPluviosidadeDto coletaPluviosidadeDto = registroPluviosidadePresenter.getPontosColetaDto(temp);
        if (coletaPluviosidadeDto != null){
            onShowDialogInfPluviosidade(coletaPluviosidadeDto);
        }
    }

    @Override
    public void onBackPressed() {
        finish();
        super.onBackPressed();
    }

    @Override
    public void onRegitroChuvaSucesso(String message) {
        this.showMessage(message);
    }

    @Override
    public void onChangeLocation(Location location) {
        registroPluviosidadePresenter.onChangeLocation(location);

    }

    AlertDialog alerta;
    @Override
    public void onShowDialogInfPluviosidade(final ColetaPluviosidadeDto coletaPluviosidadeDto) {
        LayoutInflater li = getLayoutInflater();
        View view = li.inflate(R.layout.dialog_informar_pluviosidade, null);
        EditText editTextDescricaoPonto = view.findViewById(R.id.edit_text_ponto_coleta_chuva_registro_chuva);
        final EditText editTextObservacaoPonto = view.findViewById(R.id.edit_text_observacao_registro_chuva);
        final EditText editTextQtdePonto = view.findViewById(R.id.edit_text_qtde_registro_chuva);
        editTextDescricaoPonto.setText(coletaPluviosidadeDto.getDescricao());
        view.findViewById(R.id.btn_salvar_registro_chuva).setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                registroPluviosidadePresenter.salvar(coletaPluviosidadeDto,
                                                     editTextObservacaoPonto.getText().toString(),
                                                     editTextQtdePonto.getText().toString());
                alerta.dismiss();
            }
        });
        view.findViewById(R.id.btn_cancelar_registro_chuva).setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                alerta.dismiss();
            }
        });
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Titulo");
        builder.setView(view);
        alerta = builder.create();
        alerta.show();

    }

    @Override
    public Location getLocalizacaoAtual() {
        if (null != tracker) {
            return tracker.getLocation();
        }
        return null;
    }
}