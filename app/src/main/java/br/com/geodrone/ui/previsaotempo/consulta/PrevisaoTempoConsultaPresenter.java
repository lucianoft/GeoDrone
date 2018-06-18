package br.com.geodrone.ui.previsaotempo.consulta;

import android.util.Log;

import java.util.List;

import br.com.geodrone.R;
import br.com.geodrone.SessionGeooDrone;
import br.com.geodrone.dto.DownloadFile;
import br.com.geodrone.model.Cliente;
import br.com.geodrone.model.Configuracao;
import br.com.geodrone.model.Usuario;
import br.com.geodrone.oauth.APIClient;
import br.com.geodrone.oauth.ServiceGenerator;
import br.com.geodrone.oauth.dto.AccessToken;
import br.com.geodrone.resource.PrevisaoTempoArqResource;
import br.com.geodrone.service.ConfiguracaoService;
import br.com.geodrone.ui.RelatorioUtils;
import br.com.geodrone.ui.base.BaseActivity;
import br.com.geodrone.ui.base.BasePresenter;
import br.com.geodrone.ui.base.BaseRelatorioActivity;
import br.com.geodrone.utils.Constantes;
import br.com.geodrone.utils.DateUtils;
import br.com.geodrone.utils.ErrorUtils;
import br.com.geodrone.utils.Messenger;
import br.com.geodrone.utils.PreferencesUtils;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.ContentValues.TAG;

/**
 * Created by fernandes on 16/06/2018.
 */

public class PrevisaoTempoConsultaPresenter extends BasePresenter<PrevisaoTempoConsultaPresenter.View> {

    private static  String TAG = PrevisaoTempoConsultaPresenter.class.getName();

    interface View {
        void onListPrevisaoTempo(List<PrevisaoTempoArqResource> microRegiaoResources);

        void onError(String message);

        void onError(Messenger messenger);

        void onClickPrevisaoTempoArqResource(PrevisaoTempoArqResource previsaoTempoArqResource);
    }

    private BaseActivity activity;
    private RelatorioUtils relatorioUtils;


    ConfiguracaoService configuracaoService = null;
    Configuracao configuracao = null;

    public PrevisaoTempoConsultaPresenter(BaseRelatorioActivity activity) {
        this.activity = activity;
        this.configuracaoService = new ConfiguracaoService(activity);
        configuracao = configuracaoService.getOneConfiguracao();
        this.relatorioUtils = new RelatorioUtils(activity);
    }

    public void findPrevisaoTempoCliente() {
        try{
            String URL_BASE = configuracao != null ? configuracao.getUrl() : null;
            if (URL_BASE == null) {
                URL_BASE = Constantes.API_LOGIN_URL;
            }
            AccessToken accessToken =  PreferencesUtils.getAccessToken(activity);

            Cliente cliente = SessionGeooDrone.getAttribute(SessionGeooDrone.CHAVE_CLIENTE);

            APIClient client = ServiceGenerator.getInstance(URL_BASE).createServiceWithAuth(APIClient.class, accessToken);
            Call<List<PrevisaoTempoArqResource>> call = client.findAllPrevisaoTempoByClienteResource(cliente.getId());
            final String finalURL_BASE = URL_BASE;
            call.enqueue(new Callback<List<PrevisaoTempoArqResource>>() {
                @Override
                public void onResponse(Call<List<PrevisaoTempoArqResource>> call, Response<List<PrevisaoTempoArqResource>> response) {
                    if (response.isSuccessful()) {
                        List<PrevisaoTempoArqResource> microRegiaoResources = response.body();
                        view.onListPrevisaoTempo(microRegiaoResources);
                    } else {
                        Messenger messenger = ErrorUtils.parseError(response, finalURL_BASE);
                        view.onError(messenger);
                    }
                }

                @Override
                public void onFailure(Call<List<PrevisaoTempoArqResource>> call, Throwable t) {
                    view.onError(t.getMessage());
                    Log.e(TAG, t.toString(), t);
                }
            });
        }catch (Exception ex){
            view.onError(ex.getMessage());
            Log.e(TAG, ex.toString(), ex);
        }
    }

    public void gerarRelatorio(PrevisaoTempoArqResource previsaoTempoArqResource){
        try {


            final String fileName = previsaoTempoArqResource.getNomeArquivo() ;

            Configuracao configuracao = configuracaoService.getOneConfiguracao();
            String URL_BASE = configuracao.getUrl();
            if (URL_BASE == null) {
                URL_BASE = Constantes.API_LOGIN_URL;
            }
            Cliente cliente = SessionGeooDrone.getAttribute(SessionGeooDrone.CHAVE_CLIENTE);
            AccessToken accessToken =  PreferencesUtils.getAccessToken(activity);

            APIClient client = ServiceGenerator.getInstance(URL_BASE).createServiceWithAuth(APIClient.class, accessToken);
            Call<ResponseBody> call = client.findRelatorioPrevisaoTempo(previsaoTempoArqResource.getId());
            final String finalURL_BASE = URL_BASE;
            call.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    if (response.isSuccessful()) {
                        ResponseBody responseBody = response.body();

                        DownloadFile downloadFile = new DownloadFile();
                        downloadFile.setResponseBody(responseBody);
                        downloadFile.setFileName(fileName);
                        relatorioUtils.iniciarDownload(downloadFile);
                    }else if (response.code() == 500) {
                        view.onError(activity.getString(R.string.msg_erro_500));

                    } else{
                        Messenger messenger = ErrorUtils.parseError(response, finalURL_BASE);
                        view.onError(messenger);
                    }
                }
                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    view.onError(t.getMessage());
                    Log.e(TAG, t.toString(), t);
                }
            });
        }catch (Exception ex){
            view.onError(ex.getMessage());
            Log.e(TAG, ex.toString(), ex);
        }
    }


   
}
