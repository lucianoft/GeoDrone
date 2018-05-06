package br.com.geodrone.ui.main;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
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


    @BindView(R.id.main_activity_coordinator_layout)
    CoordinatorLayout coordinatorLayout;

    //@BindView(R.id.navigationMain)
    //BottomNavigationView bottomNavigationView;
    @BindView(R.id.nav_view_main)NavigationView navigationView;
    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.drawer_layout) DrawerLayout drawer;


    /*@Nullable
    @BindView(R.id.textView_nome_usuario_main)
    */
     TextView nomeUsuario;

    /*@Nullable
    @BindView(R.id.textView_email_usuario_main) */TextView emailUsuario;


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
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
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
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_registro_foto) {
            getPermissionsCamera();
            Intent i = new Intent(this,RegistroImagemActivity.class);
            startActivity(i);

        } else if (id == R.id.nav_ponto_coleta_chuva){
            Intent i = new Intent(this,PontoColetaChuvaActivity.class);
            startActivity(i);
        } else if (id == R.id.nav_registro_condicoes_tempo){
            Intent i = new Intent(this,RegistroCondicoesTempoActivity.class);
            startActivity(i);
        } else if (id == R.id.nav_registo_chuva) {
            getPermissionsGpsRegistroPluviosidade();
        }  else if (id == R.id.menu_item_monitoramento_campo) {
            //getPermissionsGps();
            Intent i = new Intent(this,MonitoramentoActivity.class);
            startActivity(i);

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
                    1);
        }
    }

    private void getPermissionsGpsRegistroPluviosidade() {

        if (!hasPermission(Manifest.permission.ACCESS_COARSE_LOCATION)
                || !hasPermission(Manifest.permission.ACCESS_FINE_LOCATION)) {
            requestPermissions(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION,
                            Manifest.permission.ACCESS_FINE_LOCATION},
                    1);
        }else{
            Intent i = new Intent(this,RegistroPluviosidadeActivity.class);
            startActivity(i);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {

    }
}