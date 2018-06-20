package br.com.geodrone.ui.usuario.consultar;

import android.util.Log;

import java.util.List;

import br.com.geodrone.SessionGeooDrone;
import br.com.geodrone.model.Cliente;
import br.com.geodrone.model.Configuracao;
import br.com.geodrone.oauth.APIClient;
import br.com.geodrone.oauth.ServiceGenerator;
import br.com.geodrone.oauth.dto.AccessToken;
import br.com.geodrone.resource.MicroRegiaoResource;
import br.com.geodrone.resource.UsuarioResource;
import br.com.geodrone.service.ConfiguracaoService;
import br.com.geodrone.service.UsuarioService;
import br.com.geodrone.ui.base.BaseActivity;
import br.com.geodrone.ui.base.BasePresenter;
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

public class ConsultarUsuarioPresenter extends BasePresenter<ConsultarUsuarioPresenter.View> {

    private static String TAG = ConsultarUsuarioPresenter.class.getName();

    interface View {

        void onClickUsuario(UsuarioResource usuarioResource);

        void onAlterarStatusSucesso(String message);

        void onError(String message);

        void onError(Messenger messenger);

        void onListUsuario(List<UsuarioResource> usuarioResources);

        void onListMicroRegiao(List<MicroRegiaoResource> microRegiaoResources);
    }

    private BaseActivity activity;

    UsuarioService usuarioService = null;
    ConfiguracaoService configuracaoService = null;
    Configuracao configuracao = null;

    public ConsultarUsuarioPresenter(BaseActivity activity) {
        this.activity = activity;
        this.usuarioService = new UsuarioService(activity);
        this.configuracaoService = new ConfiguracaoService(activity);
        configuracao = configuracaoService.getOneConfiguracao();
    }


    public void findAllUsuario() {
        try{
            String URL_BASE = configuracao != null ? configuracao.getUrl() : null;
            if (URL_BASE == null) {
                URL_BASE = Constantes.API_LOGIN_URL;
            }
            AccessToken accessToken =  PreferencesUtils.getAccessToken(activity);

            Cliente cliente = SessionGeooDrone.getAttribute(SessionGeooDrone.CHAVE_CLIENTE);

            APIClient client = ServiceGenerator.getInstance(URL_BASE).createServiceWithAuth(APIClient.class, accessToken);
            Call<List<UsuarioResource>> call = client.findAllUsuarioResourceByCliente(cliente.getId());
            final String finalURL_BASE = URL_BASE;
            call.enqueue(new Callback<List<UsuarioResource>>() {
                @Override
                public void onResponse(Call<List<UsuarioResource>> call, Response<List<UsuarioResource>> response) {
                    if (response.isSuccessful()) {
                        List<UsuarioResource> usuarioResources = response.body();
                        view.onListUsuario(usuarioResources);
                    } else {
                        Messenger messenger = ErrorUtils.parseError(response, finalURL_BASE);
                        view.onError(messenger);
                    }
                }

                @Override
                public void onFailure(Call<List<UsuarioResource>> call, Throwable t) {
                    view.onError(t.getMessage());
                    Log.e(TAG, t.toString(), t);
                }
            });
        }catch (Exception ex){
            view.onError(ex.getMessage());
            Log.e(TAG, ex.toString(), ex);
        }
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
                        List<MicroRegiaoResource> usuarioResources = response.body();
                        view.onListMicroRegiao(usuarioResources);
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

    public void alterarUsuario(UsuarioResource usuarioResource, String telefone,  MicroRegiaoResource microRegiaoResource, String flagStatus) {
        try{
            String URL_BASE = configuracao != null ? configuracao.getUrl() : null;
            if (URL_BASE == null) {
                URL_BASE = Constantes.API_LOGIN_URL;
            }
            /*usuarioResource.setFlagStatus(flagStatus);
            usuarioResource.setIdMicroRegiao(microRegiaoResource != null ? microRegiaoResource.getId() : null);
            usuarioResource.setTelefone(telefone);
            AccessToken accessToken =  PreferencesUtils.getAccessToken(activity);

            APIClient client = ServiceGenerator.getInstance(URL_BASE).createServiceWithAuth(APIClient.class, accessToken);
            Call<UsuarioResource> call = client.alterarUsuario(usuarioResource.getId(), usuarioResource);
            final String finalURL_BASE = URL_BASE;
            call.enqueue(new Callback<UsuarioResource>() {
                @Override
                public void onResponse(Call<UsuarioResource> call, Response<UsuarioResource> response) {
                    if (response.isSuccessful()) {
                        view.onAlterarStatusSucesso(activity.getString(R.string.msg_operacao_sucesso));
                        findAllUsuario();
                    } else {
                        Messenger messenger = ErrorUtils.parseError(response, finalURL_BASE);
                        view.onError(messenger);
                    }
                }

                @Override
                public void onFailure(Call<UsuarioResource> call, Throwable t) {
                    view.onError(t.getMessage());
                    Log.e(TAG, t.toString(), t);
                }
            });*/
        }catch (Exception ex){
            view.onError(ex.getMessage());
            Log.e(TAG, ex.toString(), ex);
        }
    }
}
