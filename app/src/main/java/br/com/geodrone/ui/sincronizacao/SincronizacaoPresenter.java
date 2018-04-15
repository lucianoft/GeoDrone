package br.com.geodrone.ui.sincronizacao;

import android.util.Log;

import br.com.geodrone.R;
import br.com.geodrone.SessionGeooDrone;
import br.com.geodrone.model.Configuracao;
import br.com.geodrone.oauth.APIClient;
import br.com.geodrone.oauth.ServiceGenerator;
import br.com.geodrone.oauth.dto.AccessToken;
import br.com.geodrone.resource.InstallerResource;
import br.com.geodrone.resource.SincronizacaoRetResource;
import br.com.geodrone.service.ConfiguracaoService;
import br.com.geodrone.service.SincronizacaoService;
import br.com.geodrone.ui.base.BaseActivity;
import br.com.geodrone.ui.base.BasePresenter;
import br.com.geodrone.utils.AndroidInformation;
import br.com.geodrone.utils.PreferencesUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by fernandes on 14/04/2018.
 */
public class SincronizacaoPresenter extends BasePresenter<SincronizacaoPresenter.View> {

    private static  String TAG = SincronizacaoPresenter.class.getName();

    interface View {
        void onAtualizacaoSucesso(String msg);
        void onAtualizacaoError(String msg);
    }

    SincronizacaoService sincronizacaoService = null;
    ConfiguracaoService configuracaoService = null;

    private BaseActivity activity;

    public SincronizacaoPresenter(BaseActivity activity){
        this.activity = activity;
        this.sincronizacaoService = new SincronizacaoService(activity);
        this.configuracaoService = new ConfiguracaoService(activity);
    }

    public void getAtualizacoes(){
        try {
            Configuracao configuracao = configuracaoService.getConfiguracao();
            String URL_BASE = configuracao.getUrl();
            AccessToken accessToken = PreferencesUtils.getAccessToken(this.activity);
            APIClient client = ServiceGenerator.getInstance(URL_BASE).createServiceWithAuth(APIClient.class, accessToken);
            Call<SincronizacaoRetResource> call = client.getAtualizacoes(accessToken.getIdUsuario(),
                                                                  accessToken.getIdCliente(),
                                                                  configuracao.getIdDispositivo(),
                                                                   "2000-01-01");
            call.enqueue(new Callback<SincronizacaoRetResource>() {
                @Override
                public void onResponse(Call<SincronizacaoRetResource> call, Response<SincronizacaoRetResource> response) {
                    int statusCode = response.code();
                    if(statusCode == 200) {
                        SincronizacaoRetResource sincronizacaoResource = response.body();
                        sincronizacaoService.sincronizar(sincronizacaoResource);
                        view.onAtualizacaoSucesso(activity.getString(R.string.msg_operacao_sucesso));
                    } else {
                        view.onAtualizacaoError("x");
                    }
                }
                @Override
                public void onFailure(Call<SincronizacaoRetResource> call, Throwable t) {
                    view.onAtualizacaoError(t.getMessage());
                    Log.e(TAG, t.toString(), t);
                }
            });

        }catch (Exception ex){
            view.onAtualizacaoError(ex.getMessage());
            Log.e(TAG, ex.toString(), ex);
        }
    }
}
