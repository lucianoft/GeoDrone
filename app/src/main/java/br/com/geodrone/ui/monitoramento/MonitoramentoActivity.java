package br.com.geodrone.ui.monitoramento;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.view.MenuItem;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;

import br.com.geodrone.R;
import br.com.geodrone.ui.base.BaseMapFragmentActivity;
import br.com.geodrone.ui.registrodoenca.RegistroDoencaActivity;
import br.com.geodrone.ui.registrodoenca.RegistroSemDoenca;
import br.com.geodrone.ui.registropraga.RegistroPragaActivity;
import br.com.geodrone.ui.registropraga.RegistroSemPraga;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MonitoramentoActivity extends BaseMapFragmentActivity implements BottomNavigationView.OnNavigationItemSelectedListener,
                                                                            MonitoramentoPresenter.View,
                                                                            RegistroSemDoenca.View,
                                                                            RegistroSemPraga.View{

    private static final String TAG = MonitoramentoActivity.class.getName();

    @BindView(R.id.bottom_navigation_monitoramento)
    BottomNavigationView bottomNavigationView ;

    AlertDialog alertDialog;

    private MonitoramentoPresenter monitoramentoPresenter;
    private RegistroSemDoenca registroSemDoenca;
    private RegistroSemPraga registroSemPraga;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monitoramento);
        ButterKnife.bind(this);

        monitoramentoPresenter = new MonitoramentoPresenter(this);
        registroSemDoenca = new RegistroSemDoenca(this);
        registroSemPraga = new RegistroSemPraga(this);

        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        if (!hasPermission(Manifest.permission.ACCESS_COARSE_LOCATION) ||
                !hasPermission(Manifest.permission.ACCESS_FINE_LOCATION)) {
            requestPermissionsSafely(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION,
                    Manifest.permission.ACCESS_FINE_LOCATION}, REQ_LOCATION_PERMISSION);
        }
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map_monitoramento);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        monitoramentoPresenter.takeView(this);
        registroSemDoenca.takeView(this);
        registroSemPraga.takeView(this);
        hideLoading();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        super.onMapReady(googleMap);
        checkLocation();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_item_registrar_praga:
                dialogConfirmPraga();
                break;
            case R.id.menu_item_registrar_doenca:
                dialogConfirmDoenca();
                break;
            default:
                break;
        }
        return true;
    }

    private void dialogConfirmDoenca() {
        //Cria o gerador do AlertDialog
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        //define o titulo
        builder.setTitle(R.string.title_dialog_confirmar_doenca);
        //define a mensagem
        builder.setMessage(R.string.lb_possui_registro_doenca);
        //define um botão como positivo
        builder.setPositiveButton(R.string.lb_sim, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface arg0, int arg1) {
                Intent i = new Intent(MonitoramentoActivity.this, RegistroDoencaActivity.class);
                i.putExtra("localizacao", tracker.getLocation());
                startActivity(i);
                alertDialog.dismiss();
            }
        });
        //define um botão como negativo.
        builder.setNegativeButton(R.string.lb_nao, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface arg0, int arg1) {
                registroSemDoenca.salvar(tracker.getLocation());
                alertDialog.dismiss();
            }
        });
        //cria o AlertDialog
        alertDialog = builder.create();
        //Exibe
        alertDialog.show();
    }


    private void dialogConfirmPraga() {
        //Cria o gerador do AlertDialog
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        //define o titulo
        builder.setTitle(R.string.title_dialog_confirmar_praga);
        //define a mensagem
        builder.setMessage(R.string.lb_possui_registro_praga);
        //define um botão como positivo
        builder.setPositiveButton(R.string.lb_sim, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface arg0, int arg1) {
                Intent i = new Intent(MonitoramentoActivity.this, RegistroPragaActivity.class);
                i.putExtra("localizacao", tracker.getLocation());
                startActivity(i);
            }
        });
        //define um botão como negativo.
        builder.setNegativeButton(R.string.lb_nao, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface arg0, int arg1) {
                registroSemPraga.salvar(tracker.getLocation());
                alertDialog.dismiss();
            }
        });
        //cria o AlertDialog
        alertDialog = builder.create();
        //Exibe
        alertDialog.show();
    }

    @Override
    public void onChangeLocation(Location location) {
        monitoramentoPresenter.onChangeLocation(location);
    }

    @Override
    public void onSemRegistroPragaSucesso(String message) {
        this.showMessage(message);
    }

    @Override
    public void onErrorSemRegistroRegitroPraga(String message) {
        this.showMessage(message);
    }

    @Override
    public void onSemRegistroDoencaSucesso(String message) {
        this.showMessage(message);
    }

    @Override
    public void onErrorSemRegistroRegitroDoenca(String message) {
        this.showMessage(message);
    }

    @Override
    protected void onDestroy() {
        monitoramentoPresenter.salvarTerminoRota(getMyLocation());
        super.onDestroy();
    }
}
