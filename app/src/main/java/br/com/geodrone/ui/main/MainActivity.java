package br.com.geodrone.ui.main;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import br.com.geodrone.R;
import br.com.geodrone.activity.utils.Constantes;
import br.com.geodrone.ui.base.BaseActivity;
import br.com.geodrone.ui.logout.LogoutActivity;
import br.com.geodrone.ui.pontocoletachuva.PontoColetaChuvaActivity;
import br.com.geodrone.ui.registrocondicaotempo.RegistroCondicoesTempoActivity;
import br.com.geodrone.ui.registroimagem.RegistroImagemActivity;
import br.com.geodrone.ui.registrochuva.RegistroPluviosidadeActivity;
import br.com.geodrone.activity.ForunActivity;
import br.com.geodrone.ui.mensagem.MensagemActivity;
import br.com.geodrone.ui.monitoramento.MonitoramentoActivity;
import br.com.geodrone.ui.sincronizacao.SincronizacaoActivity;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener /*BottomNavigationView.OnNavigationItemSelectedListener*/ {

    public static final int REQ_LOCATION_PERMISSION_PONTO_PLUVIOSIDADE    = 1001;
    public static final int REQ_LOCATION_PERMISSION_REGISTRO_PLUVIOSIDADE = 1002;
    public static final int REQ_LOCATION_PERMISSION_MONITORAMENTO         = 1003;
    public static final int REQ_LOCATION_PERMISSION_FOTOS                 = 1004;

    @BindView(R.id.main_activity_coordinator_layout)
    CoordinatorLayout coordinatorLayout;

    @BindView(R.id.nav_view_main)NavigationView navigationView;
    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.drawer_layout) DrawerLayout drawer;

    private Integer nSair =0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //Mostrar o botão
        getSupportActionBar().setHomeButtonEnabled(true);      //Ativar o botão

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);

    }


    @Override
    public void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

      }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        nSair++;
        if(nSair<2) {
            if (drawer.isDrawerOpen(GravityCompat.START)) {
                drawer.closeDrawer(GravityCompat.START);
            }
           showMessage(getString(R.string.lbl_pressionarparasair));
        }
        else{
            if (drawer.isDrawerOpen(GravityCompat.START)) {
                drawer.closeDrawer(GravityCompat.START);
            } else {
                finishAffinity();
            }
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }else if (id == R.id.action_logout){
            Intent i = new Intent(this,LogoutActivity.class);
            startActivity(i);
        }

        return super.onOptionsItemSelected(item);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_registro_foto) {
            getPermissionsCamera();
        } else if (id == R.id.nav_ponto_coleta_chuva){
            getPermissionsGpsPontoPluviosidade();
        } else if (id == R.id.nav_registro_condicoes_tempo){
            Intent i = new Intent(this,RegistroCondicoesTempoActivity.class);
            startActivity(i);
        } else if (id == R.id.nav_registo_chuva) {
            getPermissionsGpsRegistroPluviosidade();
        }  else if (id == R.id.menu_item_monitoramento_campo) {
            getPermissionsGpsMonitoramento();
        } else if (id == R.id.nav_mensagem) {
            Intent i = new Intent(this,MensagemActivity.class);
            startActivity(i);

        } else if (id == R.id.nav_forum) {
            Intent i = new Intent(this,ForunActivity.class);
            startActivity(i);

        } else if (id == R.id.nav_sincronizacao){
            Intent intent = new Intent(this, SincronizacaoActivity.class);
            Bundle b = new Bundle();
            b.putString(br.com.geodrone.activity.utils.Constantes.CHAVE_UI_ORIGEM, Constantes.ACTIVITY_MAIN); //Your id
            intent.putExtras(b); //Put your id to your next Intent
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    private void getPermissionsCamera() {

        if (!hasPermission(Manifest.permission.CAMERA)
                || !hasPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                || !hasPermission(Manifest.permission.READ_EXTERNAL_STORAGE)) {
            requestPermissionsSafely(new String[]{Manifest.permission.CAMERA,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE,
                            Manifest.permission.READ_EXTERNAL_STORAGE},
                    REQ_LOCATION_PERMISSION_FOTOS);
        }else {
            Intent i = new Intent(this,RegistroImagemActivity.class);
            startActivity(i);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void getPermissionsGpsRegistroPluviosidade() {

        if (!hasPermission(Manifest.permission.ACCESS_COARSE_LOCATION)
                || !hasPermission(Manifest.permission.ACCESS_FINE_LOCATION)) {
            requestPermissions(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION,
                            Manifest.permission.ACCESS_FINE_LOCATION},
                    REQ_LOCATION_PERMISSION_PONTO_PLUVIOSIDADE);
        }else{
            Intent i = new Intent(this,RegistroPluviosidadeActivity.class);
            startActivity(i);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void getPermissionsGpsPontoPluviosidade() {

        if (!hasPermission(Manifest.permission.ACCESS_COARSE_LOCATION)
                || !hasPermission(Manifest.permission.ACCESS_FINE_LOCATION)) {
            requestPermissions(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION,
                            Manifest.permission.ACCESS_FINE_LOCATION},
                    REQ_LOCATION_PERMISSION_PONTO_PLUVIOSIDADE);
        }else{
            Intent i = new Intent(this,PontoColetaChuvaActivity.class);
            startActivity(i);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void getPermissionsGpsMonitoramento() {

        if (!hasPermission(Manifest.permission.ACCESS_COARSE_LOCATION)
                || !hasPermission(Manifest.permission.ACCESS_FINE_LOCATION)) {
            requestPermissions(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION,
                            Manifest.permission.ACCESS_FINE_LOCATION},
                    REQ_LOCATION_PERMISSION_MONITORAMENTO);
        }else{
            Intent i = new Intent(this,MonitoramentoActivity.class);
            startActivity(i);
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        if (requestCode == REQ_LOCATION_PERMISSION_PONTO_PLUVIOSIDADE) {
            if (grantResults.length > 1 && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                Intent i = new Intent(this,PontoColetaChuvaActivity.class);
                startActivity(i);
            }
        }else if (requestCode == REQ_LOCATION_PERMISSION_REGISTRO_PLUVIOSIDADE) {
            if (grantResults.length > 1 && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                Intent i = new Intent(this,RegistroCondicoesTempoActivity.class);
                startActivity(i);
            }
        }else if (requestCode == REQ_LOCATION_PERMISSION_MONITORAMENTO) {
            if (grantResults.length > 1 && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                Intent i = new Intent(this,MonitoramentoActivity.class);
                startActivity(i);
            }
        }else if (requestCode == REQ_LOCATION_PERMISSION_FOTOS) {
            if (grantResults.length > 1 && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                Intent i = new Intent(this,RegistroImagemActivity.class);
                startActivity(i);
            }
        }
    }

}