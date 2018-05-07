package br.com.geodrone.ui.pontocoletachuva;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.location.Location;
import android.location.LocationManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

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
import br.com.geodrone.model.PontoColetaChuva;
import br.com.geodrone.model.constantes.FlagSimNao;
import br.com.geodrone.ui.base.BaseMapFragmentActivity;
import br.com.geodrone.ui.registrochuva.RegistroPluviosidadeActivity;
import br.com.geodrone.ui.registrochuva.RegistroPluviosidadePresenter;
import br.com.geodrone.utils.DateUtils;
import br.com.geodrone.utils.NumberUtils;
import butterknife.ButterKnife;

public class PontoColetaChuvaActivity extends BaseMapFragmentActivity implements GoogleMap.OnMarkerClickListener,
        GoogleMap.OnMapClickListener,
        GoogleMap.OnMapLongClickListener,
        PontoColetaChuvaPresenter.View  {

    private static final String TAG = PontoColetaChuvaActivity.class.getSimpleName();

    private PontoColetaChuvaPresenter pontoColetaChuvaPresenter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ponto_coleta_chuva);
        ButterKnife.bind(this);

        if (!hasPermission(Manifest.permission.ACCESS_COARSE_LOCATION) ||
                !hasPermission(Manifest.permission.ACCESS_FINE_LOCATION)) {
            requestPermissionsSafely(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION,
                    Manifest.permission.ACCESS_FINE_LOCATION}, REQ_LOCATION_PERMISSION);
        }
        pontoColetaChuvaPresenter = new PontoColetaChuvaPresenter(this);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        super.onMapReady(googleMap);
        checkLocation();
        mMap.setOnMarkerClickListener(this);
        mMap.setOnMapClickListener(this);
        mMap.setOnMapLongClickListener(this);
        carregarPontosColeta();
    }

    @Override
    public void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        pontoColetaChuvaPresenter.takeView(this);
        hideLoading();
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        showRegistroPluviosidade(marker);
        return true;
    }


    private void carregarPontosColeta(){
        try {
            mMap.clear();
            List<ColetaPluviosidadeDto> coletaPluviosidadeDtos = pontoColetaChuvaPresenter.getPontosColeta();
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
        }catch (Exception ex){
            Log.e(TAG, ex.toString(), ex);
            onError(ex);
        }
    }


    private void showRegistroPluviosidade(Marker marker) {
        LatLng latLng = marker.getPosition();
        Location temp = new Location(LocationManager.GPS_PROVIDER);
        temp.setLatitude(latLng.latitude);
        temp.setLongitude(latLng.longitude);
        ColetaPluviosidadeDto coletaPluviosidadeDto = pontoColetaChuvaPresenter.getPontosColetaDto(temp);
        if (coletaPluviosidadeDto != null){
            onShowDialogInfPluviosidade(coletaPluviosidadeDto);
        }else{
            onShowDialogInfPluviosidade(latLng);

        }
    }

    @Override
    public void onBackPressed() {
        finish();
        super.onBackPressed();
    }

    @Override
    public void onCadastroPontoColetaSucesso(String message) {

    }

    @Override
    public void onShowDialogInfPluviosidade(final ColetaPluviosidadeDto coletaPluviosidadeDto) {
        LayoutInflater li = getLayoutInflater();
        View view = li.inflate(R.layout.dialog_ponto_coleta_chuva, null);

        final EditText editTextDescricaoPonto = view.findViewById(R.id.edit_text_ponto_coleta_chuva_descricao);
        final EditText editTextLatitude = view.findViewById(R.id.edit_text_ponto_coleta_chuva_latitude);
        final EditText editTextLongitude = view.findViewById(R.id.edit_text_ponto_coleta_chuva_longitude);
        final Spinner spinnerAtivo = view.findViewById(R.id.spinner_ponto_coleta_chuva_ativo);
        spinnerAtivo.setAdapter(new ArrayAdapter<FlagSimNao>(this, android.R.layout.simple_list_item_1, FlagSimNao.values()));

        editTextDescricaoPonto.setText(coletaPluviosidadeDto.getDescricao());
        editTextLatitude.setText(coletaPluviosidadeDto.getLatitude().toString());
        editTextLongitude.setText(coletaPluviosidadeDto.getLongitude().toString());

        final AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle(R.string.title_dialog_cadastro_ponto_coleta_chuva);
        alert.setView(view);
        alert.setCancelable(false);
        alert.setNegativeButton(R.string.lb_cancelar, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        alert.setPositiveButton(R.string.lb_alterar, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                FlagSimNao flagSimNao = (FlagSimNao) spinnerAtivo.getSelectedItem();
                salvar(coletaPluviosidadeDto.getIdPontoColetaChuva(),
                        editTextDescricaoPonto.getText().toString(),
                        editTextLatitude.getText().toString(),
                        editTextLongitude.getText().toString(),
                        flagSimNao.value());
                dialog.dismiss();
            }
        });
        AlertDialog dialog = alert.create();
        dialog.show();
    }

    public void onShowDialogInfPluviosidade(LatLng latLng) {
        NumberUtils numberUtils = new NumberUtils();

        LayoutInflater li = getLayoutInflater();
        View view = li.inflate(R.layout.dialog_ponto_coleta_chuva, null);
        final EditText editTextDescricaoPonto = view.findViewById(R.id.edit_text_ponto_coleta_chuva_descricao);
        final EditText editTextLatitude = view.findViewById(R.id.edit_text_ponto_coleta_chuva_latitude);
        final EditText editTextLongitude = view.findViewById(R.id.edit_text_ponto_coleta_chuva_longitude);
        final Spinner spinnerAtivo = view.findViewById(R.id.spinner_ponto_coleta_chuva_ativo);
        spinnerAtivo.setAdapter(new ArrayAdapter<FlagSimNao>(this, android.R.layout.simple_list_item_1, FlagSimNao.values()));

        editTextDescricaoPonto.setText(null);
        editTextLatitude.setText(numberUtils.toString(latLng.latitude));
        editTextLongitude.setText(numberUtils.toString(latLng.longitude));

        final AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle(R.string.title_dialog_cadastro_ponto_coleta_chuva);
        alert.setView(view);
        alert.setCancelable(false);
        alert.setNegativeButton(R.string.lb_cancelar, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        alert.setPositiveButton(R.string.lb_inserir, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                FlagSimNao flagSimNao = (FlagSimNao) spinnerAtivo.getSelectedItem();
                salvar(null,
                    editTextDescricaoPonto.getText().toString(),
                    editTextLatitude.getText().toString(),
                    editTextLongitude.getText().toString(),
                    flagSimNao.value());
            dialog.dismiss();
            }
        });
        AlertDialog dialog = alert.create();
        dialog.show();
    }


    @Override
    public Location getLocalizacaoAtual() {
        return null;
    }

    @Override
    public void onChangeLocation(Location location) {
    }

    @Override
    public void onMapClick(LatLng latLng) {
        onShowDialogInfPluviosidade(latLng);
    }

    @Override
    public void onMapLongClick(LatLng latLng) {
        onShowDialogInfPluviosidade(latLng);
    }

    public void salvar(Long id,
                       String descricao,
                       String latitude,
                       String longitude,
                       Integer indAtivo){
        try {
            pontoColetaChuvaPresenter.salvar(id, descricao, latitude, longitude, indAtivo);
            carregarPontosColeta();
        }catch (Exception ex){
            Log.e(TAG, ex.toString(), ex);
            onError(ex);
        }
    }
}
