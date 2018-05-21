package br.com.geodrone.ui.sincronizacao;

import android.util.Log;

import java.util.Date;

import br.com.geodrone.R;
import br.com.geodrone.model.Configuracao;
import br.com.geodrone.model.Dispositivo;
import br.com.geodrone.oauth.APIClient;
import br.com.geodrone.oauth.ServiceGenerator;
import br.com.geodrone.oauth.dto.AccessToken;
import br.com.geodrone.resource.SincronizacaoAndroidResource;
import br.com.geodrone.resource.SincronizacaoAndroidResource;
import br.com.geodrone.resource.SincronizacaoFilter;
import br.com.geodrone.resource.SincronizacaoWebResource;
import br.com.geodrone.service.ConfiguracaoService;
import br.com.geodrone.service.DispositivoService;
import br.com.geodrone.service.SincronizacaoToAndroidService;
import br.com.geodrone.service.SincronizacaoToWebService;
import br.com.geodrone.ui.base.BaseActivity;
import br.com.geodrone.ui.base.BasePresenter;
import br.com.geodrone.utils.DateUtils;
import br.com.geodrone.utils.ErrorUtils;
import br.com.geodrone.utils.Messenger;
import br.com.geodrone.utils.PreferencesUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by fernandes on 14/04/2018.
 */
public class SincronizacaoPresenter extends BasePresenter<SincronizacaoPresenter.View> {

    private static String TAG = SincronizacaoPresenter.class.getName();

    interface View {
        void onAtualizacaoToAndroidSucesso(String msg);
        void onAtualizacaoToWebSucesso(String msg);
        void onAtualizacaoError(String msg);
        void onAtualizacaoError(Messenger messenger);
    }

    SincronizacaoToAndroidService sincronizacaoService = null;
    SincronizacaoToWebService sincronizacaoToWebService = null;
    ConfiguracaoService configuracaoService = null;
    DispositivoService dispositivoService = null;

    private BaseActivity activity;

    public SincronizacaoPresenter(BaseActivity activity){
        this.activity = activity;
        this.sincronizacaoService = new SincronizacaoToAndroidService(activity);
        this.sincronizacaoToWebService = new SincronizacaoToWebService(activity);
        this.configuracaoService = new ConfiguracaoService(activity);
        this.dispositivoService = new DispositivoService(activity);
    }

    public void getAtualizacoes(){
        try {
            AccessToken accessToken = PreferencesUtils.getAccessToken(this.activity);
            Configuracao configuracao = configuracaoService.getOneConfiguracao();
            Dispositivo dispositivo = this.dispositivoService.findOneDispositivo();
            final String URL_BASE = configuracao.getUrl();
            APIClient client = ServiceGenerator.getInstance(URL_BASE).createServiceWithAuth(APIClient.class, accessToken);
            DateUtils dateUtils = new DateUtils();
            Date dtSinc = dispositivo.getDtSincronizacaoErp() != null ?
                          dispositivo.getDtSincronizacaoErp() : dateUtils.createDate(01, 01, 1900);
            Call<SincronizacaoAndroidResource> call = client.getAtualizacoes(accessToken.getIdUsuario(),
                                                                    accessToken.getIdCliente(),
                                                                    dispositivo.getId(),
                                                                    dateUtils.format(dtSinc));
            call.enqueue(new Callback<SincronizacaoAndroidResource>() {
                @Override
                public void onResponse(Call<SincronizacaoAndroidResource> call, Response<SincronizacaoAndroidResource> response) {
                    int statusCode = response.code();
                    if(statusCode == 200) {
                        SincronizacaoAndroidResource sincronizacaoResource = response.body();
                        sincronizacaoService.sincronizar(sincronizacaoResource);
                        dispositivoService.atualizarDtSincAndroid();
                        view.onAtualizacaoToAndroidSucesso(activity.getString(R.string.msg_operacao_sucesso));
                    } else {

                        Messenger messenger = ErrorUtils.parseError(response, URL_BASE);
                        view.onAtualizacaoError(messenger);
                    }
                }
                @Override
                public void onFailure(Call<SincronizacaoAndroidResource> call, Throwable t) {
                    view.onAtualizacaoError(t.getMessage());
                    Log.e(TAG, t.toString(), t);
                }
            });

        }catch (Exception ex){
            view.onAtualizacaoError(ex.getMessage());
            Log.e(TAG, ex.toString(), ex);
        }
    }

    public void sincronizacaoToWeb(){
        try {
            AccessToken accessToken = PreferencesUtils.getAccessToken(this.activity);
            Configuracao configuracao = configuracaoService.getOneConfiguracao();
            Dispositivo dispositivo = this.dispositivoService.findOneDispositivo();
            final String URL_BASE = configuracao.getUrl();
            SincronizacaoWebResource sincronizacaoWebResource = sincronizacaoToWebService.sincronizarWeb();
            APIClient client = ServiceGenerator.getInstance(URL_BASE).createServiceWithAuth(APIClient.class, accessToken);
            Call<SincronizacaoWebResource> call = client.sincronizarWeb(sincronizacaoWebResource);
            call.enqueue(new Callback<SincronizacaoWebResource>() {
                @Override
                public void onResponse(Call<SincronizacaoWebResource> call, Response<SincronizacaoWebResource> response) {
                    int statusCode = response.code();
                    if(response.isSuccessful()) {
                        SincronizacaoWebResource sincronizacaoResource = response.body();
                        dispositivoService.atualizarDtSincWeb();
                        view.onAtualizacaoToWebSucesso(activity.getString(R.string.msg_operacao_sucesso));
                    } else {
                        Messenger messenger = ErrorUtils.parseError(response, URL_BASE);
                        view.onAtualizacaoError(messenger);
                    }
                }
                @Override
                public void onFailure(Call<SincronizacaoWebResource> call, Throwable t) {
                    view.onAtualizacaoError(t.getMessage());
                    Log.e(TAG, t.toString(), t);
                }
            });

        }catch (Exception ex){
            ex.printStackTrace();
            view.onAtualizacaoError(ex.getMessage());
            Log.e(TAG, ex.toString(), ex);
        }
    }
}
