package br.com.geodrone.ui.registrochuva;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

import br.com.geodrone.R;
import br.com.geodrone.dto.ColetaPluviosidadeDto;
import br.com.geodrone.ui.base.BaseMapFragmentActivity;
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

        if (!hasPermission(Manifest.permission.ACCESS_COARSE_LOCATION) ||
                !hasPermission(Manifest.permission.ACCESS_FINE_LOCATION)) {
            requestPermissionsSafely(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION,
                    Manifest.permission.ACCESS_FINE_LOCATION}, REQ_LOCATION_PERMISSION);
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
        checkLocation();
        carregarPontosColeta();
    }

    @Override
    public void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        registroPluviosidadePresenter.takeView(this);
        hideLoading();
    }

    private void carregarPontosColeta(){
        try {
            mMap.clear();
            List<ColetaPluviosidadeDto> coletaPluviosidadeDtos = registroPluviosidadePresenter.getPontosColeta();
            for (ColetaPluviosidadeDto pluviosidadeDiariaDto : coletaPluviosidadeDtos) {
                LatLng position = new LatLng(pluviosidadeDiariaDto.getLatitude(), pluviosidadeDiariaDto.getLongitude());

                float defaultMarker = pluviosidadeDiariaDto.isColetouDia() ? BitmapDescriptorFactory.HUE_AZURE : BitmapDescriptorFactory.HUE_RED;
                BitmapDescriptor icon = BitmapDescriptorFactory.defaultMarker(defaultMarker);

                MarkerOptions markerOptions = new MarkerOptions().position(position)
                        .title(pluviosidadeDiariaDto.getDescricao())
                        .snippet(pluviosidadeDiariaDto.getUltimaLeitura())
                        .icon(icon);
                Marker marker = mMap.addMarker(markerOptions);
                marker.showInfoWindow();
            }
            mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
                @Override
                public void onMapClick(LatLng point) {
                    showMessage(point.toString());
                }


            });
        }catch (Exception ex){
            onError(ex);
        }
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

    @Override
    public void onShowDialogInfPluviosidade(final ColetaPluviosidadeDto coletaPluviosidadeDto) {
        LayoutInflater li = getLayoutInflater();
        View view = li.inflate(R.layout.dialog_informar_pluviosidade, null);
        EditText editTextDescricaoPonto = view.findViewById(R.id.edit_text_ponto_coleta_chuva_registro_chuva);
        final EditText editTextObservacaoPonto = view.findViewById(R.id.edit_text_observacao_registro_chuva);
        final EditText editTextQtdePonto = view.findViewById(R.id.edit_text_qtde_registro_chuva);
        editTextDescricaoPonto.setText(coletaPluviosidadeDto.getDescricao());
        final AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle(coletaPluviosidadeDto.getUltimaLeitura());
        alert.setView(view);
        alert.setCancelable(false);
        alert.setNegativeButton(R.string.lb_cancelar, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        alert.setPositiveButton(R.string.lb_confirmar, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                registroPluviosidadePresenter.salvar(coletaPluviosidadeDto,
                        editTextObservacaoPonto.getText().toString(),
                        editTextQtdePonto.getText().toString());
                dialog.dismiss();
            }
        });
        AlertDialog dialog = alert.create();
        dialog.show();
    }

    @Override
    public Location getLocalizacaoAtual() {
        if (null != tracker) {
            return tracker.getLocation();
        }
        return null;
    }
}