package br.com.geodrone.ui.previsaotempoadmcliente;

import android.location.Location;
import android.util.Log;

import java.io.File;
import java.util.List;

import br.com.geodrone.R;
import br.com.geodrone.dto.ColetaPluviosidadeDto;
import br.com.geodrone.model.Configuracao;
import br.com.geodrone.oauth.APIClient;
import br.com.geodrone.oauth.ServiceGenerator;
import br.com.geodrone.oauth.dto.AccessToken;
import br.com.geodrone.resource.ClienteResource;
import br.com.geodrone.service.ClienteService;
import br.com.geodrone.service.ConfiguracaoService;
import br.com.geodrone.ui.base.BaseActivity;
import br.com.geodrone.ui.base.BasePresenter;
import br.com.geodrone.ui.pontocoletachuva.PontoColetaChuvaPresenter;
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
 * Created by fernandes on 13/06/2018.
 */

public class PrevisaoTempoAdmClientePresenter extends BasePresenter<PrevisaoTempoAdmClientePresenter.View> {

    private static  String TAG = PrevisaoTempoAdmClientePresenter.class.getName();

    interface View {
        void onListCliente(List<ClienteResource> clienteResources);
        void onEnvioArquivoSucesso(String message);
        void onError(String message);
        void onError(Messenger messenger);

        void onErrorFile(String string);
        void onErrorCliente(String string);
        void onErrorDtPrevisao(String string);
    }

    private BaseActivity activity;

    ClienteService clienteService = null;
    ConfiguracaoService configuracaoService = null;
    Configuracao configuracao = null;

    public PrevisaoTempoAdmClientePresenter(BaseActivity activity) {
        this.activity = activity;
        this.clienteService = new ClienteService(activity);
        this.configuracaoService = new ConfiguracaoService(activity);
        configuracao = configuracaoService.getOneConfiguracao();
    }

    public void findAllCliente() {
        try{
            String URL_BASE = configuracao != null ? configuracao.getUrl() : null;
            if (URL_BASE == null) {
                URL_BASE = Constantes.API_LOGIN_URL;
            }
            AccessToken accessToken =  PreferencesUtils.getAccessToken(activity);

            APIClient client = ServiceGenerator.getInstance(URL_BASE).createServiceWithAuth(APIClient.class, accessToken);
            Call<List<ClienteResource>> call = client.findAllCliente();
            final String finalURL_BASE = URL_BASE;
            call.enqueue(new Callback<List<ClienteResource>>() {
                @Override
                public void onResponse(Call<List<ClienteResource>> call, Response<List<ClienteResource>> response) {
                    if (response.isSuccessful()) {
                        List<ClienteResource> clienteResources = response.body();
                        view.onListCliente(clienteResources);
                    } else {
                        Messenger messenger = ErrorUtils.parseError(response, finalURL_BASE);
                        view.onError(messenger);
                    }
                }

                @Override
                public void onFailure(Call<List<ClienteResource>> call, Throwable t) {
                    view.onError(t.getMessage());
                    Log.e(TAG, t.toString(), t);
                }
            });
        }catch (Exception ex){
            view.onError(ex.getMessage());
            Log.e(TAG, ex.toString(), ex);
        }
    }


    private boolean validarEnvioArquivo(File file, ClienteResource clienteResource, String dtPrevisao) {
        boolean isOk = true;
        if (hasView()) {
            DateUtils dateUtils = new DateUtils();
            if (file == null){
                isOk = false;
                view.onErrorFile(activity.getString(R.string.msg_obr_dt_inicio));
            }
            if (clienteResource == null){
                isOk = false;
                view.onErrorCliente(activity.getString(R.string.msg_obr_dt_fim));
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
    public void enviarArquivo(File file, ClienteResource clienteResource, String dtPrevisao) {
        try{
            if (validarEnvioArquivo(file, clienteResource, dtPrevisao)) {
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
                RequestBody idCliente = RequestBody.create(MediaType.parse("text/plain"), clienteResource.getId().toString());
                RequestBody dtRegistro = RequestBody.create(MediaType.parse("text/plain"), dtPrevisao);

                Call<Void> fileUpload = client.uploadPrevisaoTempo(fileToUpload, nomeArquivo, idCliente, dtRegistro);
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
