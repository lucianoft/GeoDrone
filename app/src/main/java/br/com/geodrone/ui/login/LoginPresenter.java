package br.com.geodrone.ui.login;


import android.util.Log;

import br.com.geodrone.R;
import br.com.geodrone.SessionGeooDrone;
import br.com.geodrone.crypt.Cryptography;
import br.com.geodrone.crypt.KeyUtils;
import br.com.geodrone.model.Cliente;
import br.com.geodrone.model.Configuracao;
import br.com.geodrone.model.Dispositivo;
import br.com.geodrone.model.Usuario;
import br.com.geodrone.oauth.APIClient;
import br.com.geodrone.oauth.ServiceGenerator;
import br.com.geodrone.oauth.dto.AccessToken;
import br.com.geodrone.resource.InstallerResource;
import br.com.geodrone.service.ClienteService;
import br.com.geodrone.service.ConfiguracaoService;
import br.com.geodrone.service.DispositivoService;
import br.com.geodrone.service.SincronizacaoToAndroidService;
import br.com.geodrone.service.UsuarioService;
import br.com.geodrone.ui.base.BaseActivity;
import br.com.geodrone.ui.base.BasePresenter;
import br.com.geodrone.utils.AndroidInformation;
import br.com.geodrone.utils.Constantes;
import br.com.geodrone.utils.ErrorUtils;
import br.com.geodrone.utils.Messenger;
import br.com.geodrone.utils.NetworkUtils;
import br.com.geodrone.utils.PreferencesUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by fernandes on 29/03/2018.
 */
public class LoginPresenter extends BasePresenter<LoginPresenter.View> {

    private static  String TAG = LoginPresenter.class.getName();

    interface View {

        void onClickLogin();

        void onSuccessoLogin(String message);

        void onErrorEmail(String message);

        void onErrorSenha(String message);

        void onDispositivoInstaladoSucesso(String message);
        void onDispositivoInstaladoError(String message);
        void onDispositivoInstaladoError(Messenger messenger);
    }

    UsuarioService usuarioService = null;
    ClienteService clienteService = null;
    DispositivoService dispositivoService = null;
    SincronizacaoToAndroidService sincronizacaoService = null;
    ConfiguracaoService configuracaoService = null;

    private BaseActivity activity;

    public LoginPresenter(BaseActivity activity){
        this.activity = activity;
        this.usuarioService = new UsuarioService(activity);
        this.clienteService = new ClienteService(activity);
        this.dispositivoService = new DispositivoService(activity);
        this.sincronizacaoService = new SincronizacaoToAndroidService(activity);
        configuracaoService = new ConfiguracaoService(activity);
        this.activity.hideLoading();
    }

    private boolean validarLogin(String login, String senha) {
        boolean isOk = true;
        if (hasView()) {
            if (login == null){
                isOk = false;
                view.onErrorEmail(activity.getString(R.string.msg_obr_email));
            }
            if (senha == null){
                isOk = false;
                view.onErrorSenha(activity.getString(R.string.msg_obr_senha));
            }
        }
        return isOk;
    }

    public void login(String login, String senha) {
        try {
            if (NetworkUtils.isNetworkConnected(activity)){
                Configuracao configuracao = configuracaoService.getOneConfiguracao();
                loginWeb(configuracao.getUrl(), login, senha);
                Cryptography cryptography = Cryptography.getInstance(Cryptography.CRYPTO_CIPHER, KeyUtils.SECRET_KEY);
                System.out.println(cryptography.encrypt(senha));
            }else{
                loginAndroid(login, senha);
            }
        }catch (Exception ex){
            this.activity.hideLoading();
            activity.onError(ex);
            Log.e(TAG, ex.toString(), ex);
        }
    }

    public void loginAndroid(String login, String senha) {
        try {
            this.activity.showLoading();
            if (validarLogin(login, senha)) {
                Usuario usuario = usuarioService.findByEmail(login);
                if (usuario != null) {

                    configurarCache(usuario, login, senha);
                    view.onSuccessoLogin(activity.getString(R.string.msg_obr_login_sucesso));
                } else {
                    activity.onError(activity.getString(R.string.msg_inv_login));
                }
            }
            this.activity.hideLoading();
        }catch (Exception ex){
            this.activity.hideLoading();
            activity.onError(ex);
            Log.e(TAG, ex.toString(), ex);
        }
    }

    public void loginWeb(final String url, final String email, final String senha){
        APIClient client = ServiceGenerator.getInstance(url).createService(APIClient.class,
                Constantes.API_OAUTH_CLIENTID,
                Constantes.API_OAUTH_CLIENTSECRET);

        Call<AccessToken> call = client.getNewAccessToken(Constantes.API_OAUTH_CLIENTID,
                Constantes.API_OAUTH_GRANT_TYPE,
                email, senha);
        call.enqueue(new Callback<AccessToken>() {
            @Override
            public void onResponse(Call<AccessToken> call, Response<AccessToken> response) {
                int statusCode = response.code();
                if(statusCode == 200) {
                    PreferencesUtils.putString(activity.getApplicationContext(), PreferencesUtils.CHAVE_EMAIL_USUARIO, email);
                    PreferencesUtils.putString(activity.getApplicationContext(), PreferencesUtils.CHAVE_SENHA_USUARIO, senha);

                    AccessToken accessToken = response.body();
                    PreferencesUtils.putAccessToken(activity, accessToken);
                    if (dispositivoService.isPrimeiroLogin()){
                        instalarDispositivo(url, accessToken);
                    }else{
                        configurarCache(accessToken);
                        view.onDispositivoInstaladoSucesso(activity.getString(R.string.msg_operacao_sucesso));
                    }
                } else {
                    activity.onError(activity.getString(R.string.msg_inv_login));
                }
            }
            @Override
            public void onFailure(Call<AccessToken> call, Throwable t) {
                Log.e("erro", t.toString(), t);
                activity.onError(t.getMessage());

            }
        });
    }

    public void instalarDispositivo(final String URL_BASE, AccessToken accessToken){
        try {
            APIClient client = ServiceGenerator.getInstance(URL_BASE).createServiceWithAuth(APIClient.class, accessToken);
            Call<InstallerResource> call = client.instalarAplicativo(accessToken.getIdUsuario(), accessToken.getIdCliente(), AndroidInformation.getDeviceInformations(activity));
            call.enqueue(new Callback<InstallerResource>() {
                @Override
                public void onResponse(Call<InstallerResource> call, Response<InstallerResource> response) {
                    int statusCode = response.code();
                    if(response.isSuccessful()) {
                        InstallerResource sincronizacaoResource = response.body();
                        sincronizacaoService.instalarAplicativo(URL_BASE, sincronizacaoResource);
                        view.onDispositivoInstaladoSucesso(activity.getString(R.string.msg_operacao_sucesso));
                    } else {
                        Messenger messenger = ErrorUtils.parseError(response, URL_BASE);
                        view.onDispositivoInstaladoError(messenger);
                        view.onDispositivoInstaladoError("x");
                    }
                }
                @Override
                public void onFailure(Call<InstallerResource> call, Throwable t) {
                    view.onDispositivoInstaladoError(t.getMessage());
                    Log.e(TAG, t.toString(), t);
                }
            });

        }catch (Exception ex){
            view.onDispositivoInstaladoError(ex.getMessage());
            Log.e(TAG, ex.toString(), ex);
        }
    }

    private void configurarCache(Usuario usuario, String login, String senha){
        PreferencesUtils.putString(this.activity.getApplicationContext(), PreferencesUtils.CHAVE_EMAIL_USUARIO, login);
        PreferencesUtils.putString(this.activity.getApplicationContext(), PreferencesUtils.CHAVE_SENHA_USUARIO, senha);
        PreferencesUtils.putUsuario(this.activity.getApplicationContext(), usuario);
        Cliente cliente = this.clienteService.findById(usuario.getIdCliente());
        Dispositivo dispositivo = this.dispositivoService.findOneDispositivo();
        SessionGeooDrone.setAttribute(SessionGeooDrone.CHAVE_USUARIO, usuario);
        SessionGeooDrone.setAttribute(SessionGeooDrone.CHAVE_CLIENTE, cliente);
        SessionGeooDrone.setAttribute(SessionGeooDrone.CHAVE_DISPOSITIVO, dispositivo);

    }

    private void configurarCache(AccessToken accessToken){
        Cliente cliente = this.clienteService.findById(accessToken.getIdCliente());
        Dispositivo dispositivo = this.dispositivoService.findOneDispositivo();
        Usuario usuario = this.usuarioService.findById(accessToken.getIdUsuario());
        SessionGeooDrone.setAttribute(SessionGeooDrone.CHAVE_USUARIO, usuario);
        SessionGeooDrone.setAttribute(SessionGeooDrone.CHAVE_CLIENTE, cliente);
        SessionGeooDrone.setAttribute(SessionGeooDrone.CHAVE_DISPOSITIVO, dispositivo);

    }
}