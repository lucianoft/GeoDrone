package br.com.geodrone.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import br.com.geodrone.R;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener /*BottomNavigationView.OnNavigationItemSelectedListener*/ {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    //@BindView(R.id.navigationMain)
    //BottomNavigationView bottomNavigationView;
    @BindView(R.id.nav_view_main)
    NavigationView navigationView;

    @BindView(R.id.drawer_layout) DrawerLayout drawer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
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

        //bottomNavigationView.setOnNavigationItemSelectedListener(this);
        navigationView.setNavigationItemSelectedListener(this);

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

    /*@Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_item1:
                Intent i = new Intent(this,CadImagemActivity.class);
                startActivity(i);
                break;
            case R.id.action_item2:
                i = new Intent(this,CadastroImagemActivity.class);
                startActivity(i);
                break;
            case R.id.action_item3:
                i = new Intent(this,CadastroPluviosidadeActivity.class);
                startActivity(i);
                break;
            default:
                break;
        }
        return true;
    }*/

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_registro_foto) {
            Intent i = new Intent(this,CadastroImagemActivity.class);
            startActivity(i);

        } else if (id == R.id.nav_registo_chuva) {
            Intent i = new Intent(this,CadastroPluviosidadeActivity.class);
            startActivity(i);
        } else if (id == R.id.nav_slideshow) {
            Intent i = new Intent(this,MonitoramentoActivity.class);
            startActivity(i);

        } else if (id == R.id.nav_manage) {
            Intent i = new Intent(this,RegistroPragasActivity.class);
            startActivity(i);
        } else if (id == R.id.menu_item_monitoramento_campo) {
            Intent i = new Intent(this,MonitoramentoActivity.class);
            startActivity(i);

        } else if (id == R.id.nav_mensagem) {
            Intent i = new Intent(this,MensagemActivity.class);
            startActivity(i);

        } else if (id == R.id.nav_forum) {
            Intent i = new Intent(this,ForunActivity.class);
            startActivity(i);

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}