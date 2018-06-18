package br.com.geodrone.ui.previsaotempo.admmicroregiao;

import android.util.Log;

import java.io.File;
import java.util.List;

import br.com.geodrone.R;
import br.com.geodrone.model.Configuracao;
import br.com.geodrone.oauth.APIClient;
import br.com.geodrone.oauth.ServiceGenerator;
import br.com.geodrone.oauth.dto.AccessToken;
import br.com.geodrone.resource.MicroRegiaoResource;
import br.com.geodrone.service.ConfiguracaoService;
import br.com.geodrone.ui.base.BaseActivity;
import br.com.geodrone.ui.base.BasePresenter;
import br.com.geodrone.utils.Constantes;
import br.com.geodrone.utils.DateUtils;
import br.com.geodrone.utils.ErrorUtils;
import br.com.geodrone.utils.Messenger;
import br.com.geodrone.utils.PreferencesUtils;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by fernandes on 14/06/2018.
 */

public class PrevisaoTempoAdmMicroRegiaoPresenter extends BasePresenter<PrevisaoTempoAdmMicroRegiaoPresenter.View> {

    private static  String TAG = PrevisaoTempoAdmMicroRegiaoPresenter.class.getName();

    interface View {
        void onListMicroRegiao(List<MicroRegiaoResource> microRegiaoResources);
        void onEnvioArquivoSucesso(String message);
        void onError(String message);
        void onError(Messenger messenger);

        void onErrorFile(String string);
        void onErrorMicroRegiao(String string);
        void onErrorDtPrevisao(String string);
    }

    private BaseActivity activity;

    ConfiguracaoService configuracaoService = null;
    Configuracao configuracao = null;

    public PrevisaoTempoAdmMicroRegiaoPresenter(BaseActivity activity) {
        this.activity = activity;
        this.configuracaoService = new ConfiguracaoService(activity);
        configuracao = configuracaoService.getOneConfiguracao();
    }

    public void findAllMicroRegiao() {
        try{
            String URL_BASE = configuracao != null ? configuracao.getUrl() : null;
            if (URL_BASE == null) {
                URL_BASE = Constantes.API_LOGIN_URL;
            }
            AccessToken accessToken =  PreferencesUtils.getAccessToken(activity);

            APIClient client = ServiceGenerator.getInstance(URL_BASE).createServiceWithAuth(APIClient.class, accessToken);
            Call<List<MicroRegiaoResource>> call = client.findAllMicroRegiao();
            final String finalURL_BASE = URL_BASE;
            call.enqueue(new Callback<List<MicroRegiaoResource>>() {
                @Override
                public void onResponse(Call<List<MicroRegiaoResource>> call, Response<List<MicroRegiaoResource>> response) {
                    if (response.isSuccessful()) {
                        List<MicroRegiaoResource> microRegiaoResources = response.body();
                        view.onListMicroRegiao(microRegiaoResources);
                    } else {
                        Messenger messenger = ErrorUtils.parseError(response, finalURL_BASE);
                        view.onError(messenger);
                    }
                }

                @Override
                public void onFailure(Call<List<MicroRegiaoResource>> call, Throwable t) {
                    view.onError(t.getMessage());
                    Log.e(TAG, t.toString(), t);
                }
            });
        }catch (Exception ex){
            view.onError(ex.getMessage());
            Log.e(TAG, ex.toString(), ex);
        }
    }


    private boolean validarEnvioArquivo(File file, MicroRegiaoResource microRegiaoResource, String dtPrevisao) {
        boolean isOk = true;
        if (hasView()) {
            DateUtils dateUtils = new DateUtils();
            if (file == null){
                isOk = false;
                view.onErrorFile(activity.getString(R.string.msg_obr_dt_inicio));
            }
            if (microRegiaoResource == null){
                isOk = false;
                view.onErrorMicroRegiao(activity.getString(R.string.msg_obr_dt_fim));
            }
            try {
                dtPrevisao = dateUtils.format(dateUtils.parse(dtPrevisao, "dd/MM/yyyy"));
            }catch (Exception ex){
                isOk = false;
                view.onErrorDtPrevisao(activity.getString(R.string.msg_inv_dt_inicio));
            }

        }
        return isOk;
    }
    public void enviarArquivo(File file, MicroRegiaoResource microRegiaoResource, String dtPrevisao) {
        try{
            if (validarEnvioArquivo(file, microRegiaoResource, dtPrevisao)) {
                String URL_BASE = configuracao != null ? configuracao.getUrl() : null;
                if (URL_BASE == null) {
                    URL_BASE = Constantes.API_LOGIN_URL;
                }
                final String finalURL_BASE = URL_BASE;
                AccessToken accessToken = PreferencesUtils.getAccessToken(activity);

                APIClient client = ServiceGenerator.getInstance(URL_BASE).createServiceWithAuth(APIClient.class, accessToken);

                DateUtils dateUtils = new DateUtils();
                dtPrevisao = dateUtils.format(dateUtils.parse(dtPrevisao, "dd/MM/yyyy"));

                RequestBody mFile = RequestBody.create(MediaType.parse("application/pdf"), file);
                MultipartBody.Part fileToUpload = MultipartBody.Part.createFormData("file", file.getName(), mFile);
                RequestBody nomeArquivo = RequestBody.create(MediaType.parse("text/plain"), file.getName());
                RequestBody idMicroRegiao = RequestBody.create(MediaType.parse("text/plain"), microRegiaoResource.getId().toString());
                RequestBody dtPrevisao_ = RequestBody.create(MediaType.parse("text/plain"), dtPrevisao);

                Call<Void> fileUpload = client.uploadPrevisaoTempoMicroRegiao(fileToUpload, nomeArquivo, idMicroRegiao, dtPrevisao_);
                fileUpload.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if (response.isSuccessful()) {
                            view.onEnvioArquivoSucesso(activity.getString(R.string.msg_operacao_sucesso));
                        } else {
                            Messenger messenger = ErrorUtils.parseError(response, finalURL_BASE);
                            view.onError(messenger);
                        }
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Log.d(TAG, "Error " + t.getMessage());
                        view.onError(t.getMessage());
                    }
                });
            }
        }catch (Exception ex){
            view.onError(ex.getMessage());
            Log.e(TAG, ex.toString(), ex);
        }
    }

}
