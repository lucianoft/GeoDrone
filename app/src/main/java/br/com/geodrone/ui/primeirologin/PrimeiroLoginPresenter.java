package br.com.geodrone.ui.primeirologin;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import java.io.IOException;

import br.com.geodrone.BuildConfig;
import br.com.geodrone.R;
import br.com.geodrone.SessionGeooDrone;
import br.com.geodrone.oauth.APIClient;
import br.com.geodrone.oauth.ServiceGenerator;
import br.com.geodrone.oauth.dto.AccessToken;
import br.com.geodrone.resource.InstallerResource;
import br.com.geodrone.service.ClienteService;
import br.com.geodrone.service.ConfiguracaoService;
import br.com.geodrone.service.DispositivoService;
import br.com.geodrone.service.SincronizacaoService;
import br.com.geodrone.service.UsuarioService;
import br.com.geodrone.ui.base.BaseActivity;
import br.com.geodrone.ui.base.BasePresenter;
import br.com.geodrone.ui.login.LoginPresenter;
import br.com.geodrone.utils.AndroidInformation;
import br.com.geodrone.utils.Constantes;
import br.com.geodrone.utils.PreferencesUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by fernandes on 14/04/2018.
 */

public class PrimeiroLoginPresenter extends BasePresenter<PrimeiroLoginPresenter.View> {

    private static  String TAG = LoginPresenter.class.getName();

    interface View {

        void onClickLogin();

        void onErrorEmail(String message);

        void onErrorSenha(String message);

        void onDispositivoInstaladoSucesso(String message);
        void onDispositivoInstaladoError(String message);

    }

    UsuarioService usuarioService = null;
    ClienteService clienteService = null;
    DispositivoService dispositivoService = null;
    SincronizacaoService sincronizacaoService = null;
    ConfiguracaoService configuracaoService = null;

    private BaseActivity activity;

    public PrimeiroLoginPresenter(BaseActivity activity){
        this.activity = activity;
        this.usuarioService = new UsuarioService(activity);
        this.clienteService = new ClienteService(activity);
        this.dispositivoService = new DispositivoService(activity);
        this.sincronizacaoService = new SincronizacaoService(activity);
        configuracaoService = new ConfiguracaoService(activity);
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

    public void login(final String url, final String email, final String senha){
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
                    atualizarUrlBase(url);
                    instalarDispositivo(url, accessToken);
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

    private void atualizarUrlBase(String url){
        configuracaoService.atualizarUrlBase(url);
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
}
