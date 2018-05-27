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
import android.view.View;
import android.widget.TextView;

import br.com.geodrone.R;
import br.com.geodrone.SessionGeooDrone;
import br.com.geodrone.activity.utils.Constantes;
import br.com.geodrone.model.Cliente;
import br.com.geodrone.model.Usuario;
import br.com.geodrone.ui.aceiteusuariogeoclima.AceiteUsuarioGeoclimaActivity;
import br.com.geodrone.ui.aceiteusuariogeomonitora.AceiteUsuarioGeomonitoraActivity;
import br.com.geodrone.ui.base.BaseActivity;
import br.com.geodrone.ui.forum.ForumGeodroneActivity;
import br.com.geodrone.ui.forum.ForumActivity;
import br.com.geodrone.ui.logout.LogoutActivity;
import br.com.geodrone.ui.mensagem.MensagemActivity;
import br.com.geodrone.ui.pontocoletachuva.PontoColetaChuvaActivity;
import br.com.geodrone.ui.registrocondicaotempo.RegistroCondicoesTempoActivity;
import br.com.geodrone.ui.registroimagem.RegistroImagemActivity;
import br.com.geodrone.ui.registrochuva.RegistroPluviosidadeActivity;
import br.com.geodrone.ui.monitoramento.MonitoramentoActivity;
import br.com.geodrone.ui.reqpragas.RequisitarArquivoPragaActivity;
import br.com.geodrone.ui.rotatrabalhokml.RotaTrabalhoKmlActivity;
import br.com.geodrone.ui.sincronizacao.SincronizacaoActivity;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity
                          implements NavigationView.OnNavigationItemSelectedListener /*BottomNavigationView.OnNavigationItemSelectedListener*/ {

    public static final int REQ_LOCATION_PERMISSION_PONTO_PLUVIOSIDADE    = 1001;
    public static final int REQ_LOCATION_PERMISSION_REGISTRO_PLUVIOSIDADE = 1002;
    public static final int REQ_LOCATION_PERMISSION_MONITORAMENTO         = 1003;
    public static final int REQ_LOCATION_PERMISSION_FOTOS                 = 1004;
    public static final int REQ_LOCATION_PERMISSION_ROTA_KM               = 1005;

    @BindView(R.id.main_activity_coordinator_layout)
    CoordinatorLayout coordinatorLayout;

    @BindView(R.id.nav_view_main)NavigationView navigationView;
    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.drawer_layout) DrawerLayout drawer;

    private Integer nSair =0;
    private Menu menu;

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
        configurarUsuarioCliente();

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

    private void configurarUsuarioCliente(){
        Usuario usuario = SessionGeooDrone.getAttribute(SessionGeooDrone.CHAVE_USUARIO);
        Cliente cliente = SessionGeooDrone.getAttribute(SessionGeooDrone.CHAVE_CLIENTE);

        View header = navigationView.getHeaderView(0);
        TextView textViewEmailUsuario = (TextView) header.findViewById(R.id.text_view_email_usuario);
        textViewEmailUsuario.setText(usuario.getEmail());

        TextView textViewNomeCliente = (TextView) header.findViewById(R.id.text_view_nome_cliente);
        textViewNomeCliente.setText(cliente.getNomeRazaoSocial());

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        this.menu = menu;
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

        //geoclima
        if (id == R.id.nav_registro_foto) {
            if (isAceiteGeoClima()) {
                getPermissionsCamera();
            }
        } else if (id == R.id.nav_ponto_coleta_chuva){
            if (isAceiteGeoClima()) {
                getPermissionsGpsPontoPluviosidade();
            }
        } else if (id == R.id.nav_registro_condicoes_tempo){
            if (isAceiteGeoClima()) {
                Intent i = new Intent(this, RegistroCondicoesTempoActivity.class);
                startActivity(i);
            }
        } else if (id == R.id.nav_registo_chuva) {
            if (isAceiteGeoClima()) {
                getPermissionsGpsRegistroPluviosidade();
            }
        }else if (id == R.id.nav_mensagem) {
            if (isAceiteGeoClima()) {
                Intent intent = new Intent(this, MensagemActivity.class);
                startActivity(intent);
            }
        } else if (id == R.id.nav_forum) {
            if (isAceiteGeoClima()) {
                Intent intent = new Intent(this, ForumGeodroneActivity.class);
                startActivity(intent);
            }
        }

        //geomonitora
        else if (id == R.id.menu_item_monitoramento_campo) {
            if (isAceiteGeomonitora()) {
                getPermissionsGpsMonitoramento();
            }
        } else if (id == R.id.menu_item_requisitar_arquivos_pragas){
            if (isAceiteGeomonitora()) {
                Intent intent = new Intent(this, RequisitarArquivoPragaActivity.class);
                startActivity(intent);
            }
        }  else if (id == R.id.menu_item_rota_trabalho){
            if (isAceiteGeomonitora()) {
                getPermissionsGpsRotaMonitoramentoKml();
            }
        }

        //sincronizacao
        else if (id == R.id.nav_sincronizacao){
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
                    -1);
        }
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

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void getPermissionsGpsRotaMonitoramentoKml() {

        if (!hasPermission(Manifest.permission.ACCESS_COARSE_LOCATION)
                || !hasPermission(Manifest.permission.ACCESS_FINE_LOCATION)) {
            requestPermissions(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION,
                            Manifest.permission.ACCESS_FINE_LOCATION},
                    -1);
        }
        if (!hasPermission(Manifest.permission.ACCESS_COARSE_LOCATION)
                || !hasPermission(Manifest.permission.ACCESS_FINE_LOCATION)) {
            requestPermissions(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION,
                            Manifest.permission.ACCESS_FINE_LOCATION},
                    REQ_LOCATION_PERMISSION_ROTA_KM);
        }else{
            Intent i = new Intent(this,RotaTrabalhoKmlActivity.class);
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
        }else if (requestCode == REQ_LOCATION_PERMISSION_ROTA_KM) {
            if (grantResults.length > 1 && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                Intent i = new Intent(this,RotaTrabalhoKmlActivity.class);
                startActivity(i);
            }
        }
    }

    private boolean isAceiteGeomonitora(){
        Usuario usuario = SessionGeooDrone.getAttribute(SessionGeooDrone.CHAVE_USUARIO);
        if (usuario.getIndAceiteGeomonitora() != null && usuario.getIndAceiteGeomonitora().intValue() == 1){
            return true;
        }else{
            Intent i = new Intent(this,AceiteUsuarioGeomonitoraActivity.class);
            startActivity(i);
        }
        return false;
    }

    private boolean isAceiteGeoClima(){
        Usuario usuario = SessionGeooDrone.getAttribute(SessionGeooDrone.CHAVE_USUARIO);
        if (usuario.getIndAceiteGeoClima() != null && usuario.getIndAceiteGeoClima().intValue() == 1){
            return true;
        }else{
            Intent i = new Intent(this, AceiteUsuarioGeoclimaActivity.class);
            startActivity(i);
        }
        return false;
    }

}