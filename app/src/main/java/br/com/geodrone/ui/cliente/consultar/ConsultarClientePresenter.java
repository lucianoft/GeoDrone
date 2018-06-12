package br.com.geodrone.ui.cliente.consultar;

import android.util.Log;

import java.util.List;

import br.com.geodrone.model.Cliente;
import br.com.geodrone.model.Configuracao;
import br.com.geodrone.oauth.APIClient;
import br.com.geodrone.oauth.ServiceGenerator;
import br.com.geodrone.oauth.dto.AccessToken;
import br.com.geodrone.resource.ClienteResource;
import br.com.geodrone.resource.ClienteResource;
import br.com.geodrone.service.ClienteService;
import br.com.geodrone.service.ConfiguracaoService;
import br.com.geodrone.ui.base.BaseActivity;
import br.com.geodrone.ui.base.BasePresenter;
import br.com.geodrone.ui.cliente.cadastrar.CadastroClientePresenter;
import br.com.geodrone.utils.Constantes;
import br.com.geodrone.utils.ErrorUtils;
import br.com.geodrone.utils.Messenger;
import br.com.geodrone.utils.PreferencesUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by fernandes on 11/06/2018.
 */

public class ConsultarClientePresenter extends BasePresenter<ConsultarClientePresenter.View> {

    private static String TAG = CadastroClientePresenter.class.getName();

    interface View {

        void onClickCliente(ClienteResource clienteResource);

        void onClickAlterarStatusCliente(ClienteResource clienteResource);

        void onAlterarStatusSucesso(String message);

        void onError(String message);

        void onError(Messenger messenger);

        void onListCliente(List<ClienteResource> clienteResources);
    }

    private BaseActivity activity;

    ClienteService clienteService = null;
    ConfiguracaoService configuracaoService = null;

    public ConsultarClientePresenter(BaseActivity activity) {
        this.activity = activity;
        this.clienteService = new ClienteService(activity);
        this.configuracaoService = new ConfiguracaoService(activity);
    }


    public void findAllCliente() {
        try{
            Configuracao configuracao = configuracaoService.getOneConfiguracao();
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
}
