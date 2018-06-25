package br.com.geodrone.ui.main;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import br.com.geodrone.R;
import br.com.geodrone.SessionGeooDrone;
import br.com.geodrone.activity.utils.Constantes;
import br.com.geodrone.dto.MenuMainDto;
import br.com.geodrone.model.Cliente;
import br.com.geodrone.model.Usuario;
import br.com.geodrone.ui.aceiteusuariogeoclima.AceiteUsuarioGeoclimaActivity;
import br.com.geodrone.ui.aceiteusuariogeomonitora.AceiteUsuarioGeomonitoraActivity;
import br.com.geodrone.ui.base.BaseActivity;
import br.com.geodrone.ui.cliente.consultar.ConsultarClienteActivity;
import br.com.geodrone.ui.forum.ForumGeodroneActivity;
import br.com.geodrone.ui.helper.ActivityHelper;
import br.com.geodrone.ui.logout.LogoutActivity;
import br.com.geodrone.ui.mensagem.usuarios.MensagemUsuariosActivity;
import br.com.geodrone.ui.monitoramento.MonitoramentoActivity;
import br.com.geodrone.ui.pontocoletachuva.PontoColetaChuvaActivity;
import br.com.geodrone.ui.previsaotempo.admcliente.PrevisaoTempoAdmClienteActivity;
import br.com.geodrone.ui.previsaotempo.admmicroregiao.PrevisaoTempoAdmMicroRegiaoActivity;
import br.com.geodrone.ui.previsaotempo.consulta.PrevisaoTempoConsultaActivity;
import br.com.geodrone.ui.registrochuva.RegistroPluviosidadeActivity;
import br.com.geodrone.ui.registrocondicaotempo.RegistroCondicoesTempoActivity;
import br.com.geodrone.ui.registrodefensivo.RegistroDefensivoActivity;
import br.com.geodrone.ui.registroimagem.RegistroImagemActivity;
import br.com.geodrone.ui.relatorioregistrochuva.RelatorioRegistroChuvaActivity;
import br.com.geodrone.ui.relatorioregistrodoenca.RelatorioRegistroDoencaActivity;
import br.com.geodrone.ui.relatorioregistropraga.RelatorioRegistroPragaActivity;
import br.com.geodrone.ui.rotatrabalhokml.RotaTrabalhoKmlActivity;
import br.com.geodrone.ui.sincronizacao.SincronizacaoActivity;
import br.com.geodrone.ui.talhao.ConsultaTalhaoActivity;
import br.com.geodrone.ui.usuario.consultar.ConsultarUsuarioActivity;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity
                          implements NavigationView.OnNavigationItemSelectedListener,
                                     MainPresenter.View
                          /*BottomNavigationView.OnNavigationItemSelectedListener*/ {

    public static final int REQ_LOCATION_PERMISSION_PONTO_PLUVIOSIDADE    = 1001;
    public static final int REQ_LOCATION_PERMISSION_REGISTRO_PLUVIOSIDADE = 1002;
    public static final int REQ_LOCATION_PERMISSION_MONITORAMENTO         = 1003;
    public static final int REQ_LOCATION_PERMISSION_FOTOS                 = 1004;
    public static final int REQ_LOCATION_PERMISSION_ROTA_KM               = 1005;
    public static final int REQ_PERMISSION_FORUM                          = 1006;

    private MainPresenter mainPresenter;
    private MainAdapter mainAdapter;

    @BindView(R.id.nav_view_main)NavigationView navigationView;
    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.drawer_layout) DrawerLayout drawer;
    //@BindView(R.id.recyclerViewMain)
    RecyclerView mRecyclerView;

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

        mainPresenter = new MainPresenter(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mainPresenter.takeView(this);
        //initAdapter();
        configurarUsuarioCliente();
        configurarPermissoes();
    }

    @Override
    public void onBackPressed() {
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

    private void configurarPermissoes(){
        Usuario usuario = SessionGeooDrone.getAttribute(SessionGeooDrone.CHAVE_USUARIO);
        Cliente cliente = SessionGeooDrone.getAttribute(SessionGeooDrone.CHAVE_CLIENTE);

        Menu menuNav = navigationView.getMenu();

        if (mainPresenter.isPerfilColetor(usuario)) {
            MenuItem menuItemMensagem = menuNav.findItem(R.id.nav_mensagem);
            menuItemMensagem.setEnabled(false);
        }

        if (mainPresenter.isPerfilColetor(usuario)) {
            MenuItem menuItemForum = menuNav.findItem(R.id.nav_forum);
            menuItemForum.setEnabled(false);
        }

        if (mainPresenter.isPerfilColetor(usuario)) {
            MenuItem menuItemTalhao = menuNav.findItem(R.id.menu_item_registro_talhao);
            menuItemTalhao.setEnabled(false);
        }

        if (!mainPresenter.isPerfilAdministrador(usuario)) {
            MenuItem menuItemAdmCliente = menuNav.findItem(R.id.menu_item_adm_cliente);
            menuItemAdmCliente.setEnabled(false);

            MenuItem menuItemAdmPrevisaoCliente = menuNav.findItem(R.id.menu_item_adm_previsao_cliente);
            menuItemAdmPrevisaoCliente.setEnabled(false);

            MenuItem menuItemAdmPrevisaoMicroRegiao = menuNav.findItem(R.id.menu_item_adm_previsao_cliente_microregiao);
            menuItemAdmPrevisaoMicroRegiao.setEnabled(false);

        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        this.menu = menu;
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        Usuario usuario = SessionGeooDrone.getAttribute(SessionGeooDrone.CHAVE_USUARIO);
        if (mainPresenter.isPerfilColetor(usuario)) {

            MenuItem menuItemAlterarCliente = menu.findItem(R.id.action_alterar_cliente);
            menuItemAlterarCliente.setEnabled(false);

            MenuItem menuItemUsuarios = menu.findItem(R.id.action_usuarios);
            menuItemUsuarios.setEnabled(false);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_configuracao) {
            return true;
        }else if (id == R.id.action_logout){
            finish();
            Intent i = new Intent(this,LogoutActivity.class);
            startActivity(i);
        }else if (id == R.id.action_alterar_senha){
            ActivityHelper activityHelper = new ActivityHelper();
            activityHelper.alterarSenha(this);
        }else if (id == R.id.action_alterar_cliente){
            ActivityHelper activityHelper = new ActivityHelper();
            activityHelper.alterarClienteSessao(this);
            configurarUsuarioCliente();
            configurarPermissoes();
        }else if (id == R.id.action_usuarios){
            Intent i = new Intent(this,ConsultarUsuarioActivity.class);
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
        if (id == R.id.menu_item_previsao_tempo){
            if (isAceiteGeoClima()) {
                Intent i = new Intent(this, PrevisaoTempoConsultaActivity.class);
                startActivity(i);
            }
        }else if (id == R.id.nav_registro_foto) {
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
                Intent intent = new Intent(this, MensagemUsuariosActivity.class);
                startActivity(intent);
            }
        } else if (id == R.id.nav_forum) {
            if (isAceiteGeoClima()) {
                getPermissionsForum();
            }
        }else if (id == R.id.menu_item_relatorio_registro_chuva){
            if (isAceiteGeoClima()) {
                Intent intent = new Intent(this, RelatorioRegistroChuvaActivity.class);
                startActivity(intent);
            }
        }

        //geomonitora
        else if (id == R.id.menu_item_registro_talhao){
            if (isAceiteGeomonitora()) {
                Intent intent = new Intent(this, ConsultaTalhaoActivity.class);
                startActivity(intent);
            }
        }else if (id == R.id.menu_item_monitoramento_campo) {
            if (isAceiteGeomonitora()) {
                getPermissionsGpsMonitoramento();
            }
        }else if (id == R.id.menu_item_defensivos_doses){
            if (isAceiteGeomonitora()){
                Intent intent = new Intent(this, RegistroDefensivoActivity.class);
                startActivity(intent);
            }
        }else if (id == R.id.menu_item_rota_trabalho){
            if (isAceiteGeomonitora()) {
                getPermissionsGpsRotaMonitoramentoKml();
            }
        }else if (id == R.id.menu_item_requisitar_arquivos_pragas){
            if (isAceiteGeomonitora()) {
                Intent intent = new Intent(this, RelatorioRegistroPragaActivity.class);
                startActivity(intent);
            }
        }else if (id == R.id.menu_item_requisitar_arquivos_doencas){
            if (isAceiteGeomonitora()) {
                Intent intent = new Intent(this, RelatorioRegistroDoencaActivity.class);
                startActivity(intent);
            }
        }/*else if (id == R.id.menu_item_relatorio_indice_pragas){
            if (isAceiteGeomonitora()) {
                Intent intent = new Intent(this, RelatorioIndicePragaActivity.class);
                startActivity(intent);
            }
        }*/

        //sincronizacao
        else if (id == R.id.nav_sincronizacao){
            Intent intent = new Intent(this, SincronizacaoActivity.class);
            Bundle b = new Bundle();
            b.putString(br.com.geodrone.activity.utils.Constantes.CHAVE_UI_ORIGEM, Constantes.ACTIVITY_MAIN); //Your id
            intent.putExtras(b); //Put your id to your next Intent
            startActivity(intent);
            finish();
        }

        else if (id == R.id.menu_item_adm_cliente) {
            Intent intent = new Intent(this, ConsultarClienteActivity.class);
            startActivity(intent);
        }else if (id == R.id.menu_item_adm_previsao_cliente) {
            Intent intent = new Intent(this, PrevisaoTempoAdmClienteActivity.class);
            startActivity(intent);
        }else if (id == R.id.menu_item_adm_previsao_cliente_microregiao) {
            Intent intent = new Intent(this, PrevisaoTempoAdmMicroRegiaoActivity.class);
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

    private void getPermissionsForum() {
        if (!hasPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                || !hasPermission(Manifest.permission.READ_EXTERNAL_STORAGE)) {
            requestPermissionsSafely(new String[]{Manifest.permission.CAMERA,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE,
                            Manifest.permission.READ_EXTERNAL_STORAGE},
                    REQ_PERMISSION_FORUM);
        }else {
            Intent i = new Intent(this,ForumGeodroneActivity.class);
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
        }else if (requestCode == REQ_PERMISSION_FORUM){
            if (grantResults.length > 1 && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                Intent i = new Intent(this, ForumGeodroneActivity.class);
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

    private void initAdapter() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mainAdapter = new MainAdapter(this);
        mRecyclerView.setAdapter(mainAdapter);
        mainAdapter.setData(getMenuMainDto());
    }
    private List<MenuMainDto> getMenuMainDto(){
        List<MenuMainDto> menuMainDtos = new ArrayList<>();
        menuMainDtos.add(new MenuMainDto(1, "Teste" , R.drawable.emoji_00a9));
        return  menuMainDtos;
    }

}