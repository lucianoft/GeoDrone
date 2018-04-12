package br.com.geodrone.ui.login;


import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import br.com.geodrone.BuildConfig;
import br.com.geodrone.R;
import br.com.geodrone.Session;
import br.com.geodrone.model.Cliente;
import br.com.geodrone.model.Dispositivo;
import br.com.geodrone.model.Usuario;
import br.com.geodrone.oauth.APIClient;
import br.com.geodrone.oauth.ServiceGenerator;
import br.com.geodrone.oauth.dto.AccessToken;
import br.com.geodrone.resource.SincronizacaoResource;
import br.com.geodrone.service.DispositivoService;
import br.com.geodrone.ui.base.BaseActivity;
import br.com.geodrone.ui.base.BasePresenter;
import br.com.geodrone.service.ClienteService;
import br.com.geodrone.service.UsuarioService;
import br.com.geodrone.utils.Constantes;
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
    }

    UsuarioService usuarioService = null;
    ClienteService clienteService = null;
    DispositivoService dispositivoService = null;

    private BaseActivity activity;

    public LoginPresenter(BaseActivity activity){
        this.activity = activity;
        this.usuarioService = new UsuarioService(activity);
        this.clienteService = new ClienteService(activity);
        this.dispositivoService = new DispositivoService(activity);
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
            login2(login, senha);
            this.activity.showLoading();
            if (validarLogin(login, senha)) {
                Usuario usuario = usuarioService.findByEmail(login);
                if (usuario != null) {
                    PreferencesUtils.putString(this.activity.getApplicationContext(), PreferencesUtils.CHAVE_EMAIL_USUARIO, login);
                    PreferencesUtils.putString(this.activity.getApplicationContext(), PreferencesUtils.CHAVE_SENHA_USUARIO, senha);
                    PreferencesUtils.putUsuario(this.activity.getApplicationContext(), usuario);
                    Cliente cliente = this.clienteService.findById(usuario.getIdCliente());
                    Dispositivo dispositivo = this.dispositivoService.findOne();
                    Session.setAttribute(PreferencesUtils.CHAVE_USUARIO, usuario);
                    Session.setAttribute(PreferencesUtils.CHAVE_CLIENTE, cliente);
                    Session.setAttribute(PreferencesUtils.CHAVE_DISPOSITIVO, dispositivo);
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


    private void login2(String email, String senha){
        final SharedPreferences prefs = this.activity.getSharedPreferences(
                BuildConfig.APPLICATION_ID, Context.MODE_PRIVATE);

        APIClient client = ServiceGenerator.createService(APIClient.class, Constantes.API_OAUTH_CLIENTID, Constantes.API_OAUTH_CLIENTSECRET);
        Call<AccessToken> call = client.getNewAccessToken(Constantes.API_OAUTH_CLIENTID,
                                                          "password",
                "admin", "admin");
        call.enqueue(new Callback<AccessToken>() {
            @Override
            public void onResponse(Call<AccessToken> call, Response<AccessToken> response) {
                int statusCode = response.code();
                if(statusCode == 200) {
                    AccessToken token = response.body();
                    prefs.edit().putBoolean("oauth.loggedin", true).apply();
                    prefs.edit().putString("oauth.accesstoken", token.getAccessToken()).apply();
                    prefs.edit().putString("oauth.refreshtoken", token.getRefreshToken()).apply();
                    prefs.edit().putString("oauth.tokentype", token.getTokenType()).apply();
                    sincronizacao(token);
                    // TODO Show the user they are logged in
                } else {
                    // TODO Handle errors on a failed response
                }
            }

            @Override
            public void onFailure(Call<AccessToken> call, Throwable t) {
                Log.e("erro", t.toString(), t);
            }
        });
    }

    private void sincronizacao(AccessToken accessToken){
        final SharedPreferences prefs = this.activity.getSharedPreferences(
                BuildConfig.APPLICATION_ID, Context.MODE_PRIVATE);

        try {
            APIClient client = ServiceGenerator.createServiceWithAuth(APIClient.class, accessToken);
            SincronizacaoResource sincronizacaoResource = new SincronizacaoResource();
            Call<SincronizacaoResource> call = client.getSincronizacao(sincronizacaoResource);
            call.enqueue(new Callback<SincronizacaoResource>() {
                @Override
                public void onResponse(Call<SincronizacaoResource> call, Response<SincronizacaoResource> response) {
                    int statusCode = response.code();
                    if(statusCode == 200) {
                        SincronizacaoResource sincronizacaoResource = response.body();
                        // TODO Show the user they are logged in
                    } else {
                        // TODO Handle errors on a failed response
                    }
                }

                @Override
                public void onFailure(Call<SincronizacaoResource> call, Throwable t) {
                    Log.e("erro", t.toString(), t);
                }
            });

        }catch (Exception ex){
            Log.e(TAG, ex.toString(), ex);

        }
    }
}