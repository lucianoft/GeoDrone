package br.com.geodrone.ui.mensagem.usuarios;

import android.util.Log;

import java.util.List;

import br.com.geodrone.SessionGeooDrone;
import br.com.geodrone.model.Cliente;
import br.com.geodrone.model.Configuracao;
import br.com.geodrone.oauth.APIClient;
import br.com.geodrone.oauth.ServiceGenerator;
import br.com.geodrone.oauth.dto.AccessToken;
import br.com.geodrone.resource.UsuarioMensagemResource;
import br.com.geodrone.service.ConfiguracaoService;
import br.com.geodrone.ui.base.BaseActivity;
import br.com.geodrone.ui.base.BasePresenter;
import br.com.geodrone.ui.mensagem.MensagemPresenter;
import br.com.geodrone.utils.Constantes;
import br.com.geodrone.utils.DateUtils;
import br.com.geodrone.utils.ErrorUtils;
import br.com.geodrone.utils.Messenger;
import br.com.geodrone.utils.PreferencesUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by fernandes on 29/05/2018.
 */

public class MensagemUsuariosPresenter extends BasePresenter<MensagemUsuariosPresenter.View> {

    private static final String TAG = MensagemUsuariosPresenter.class.getSimpleName() ;



    interface View {
        void onClickUsuario(UsuarioMensagemResource usuarioMensagemResource);
        void onSucessoFindUsuarios(List<UsuarioMensagemResource> usuarioMensagemResources);
        void onErroFindUsuarios(String msg);
        void onErroFindUsuarios(Messenger messenger);
    }

    private BaseActivity activity;

    ConfiguracaoService configuracaoService = null;

    public MensagemUsuariosPresenter(BaseActivity activity) {
        this.activity = activity;
        this.configuracaoService = new ConfiguracaoService(activity);
    }


    public void findUsuariosMensagem(){
        try {
            Configuracao configuracao = configuracaoService.getOneConfiguracao();
            String URL_BASE = configuracao.getUrl();
            if (URL_BASE == null) {
                URL_BASE = Constantes.API_LOGIN_URL;
            }
            Cliente cliente = SessionGeooDrone.getAttribute(SessionGeooDrone.CHAVE_CLIENTE);
            AccessToken accessToken =  PreferencesUtils.getAccessToken(activity);

            APIClient client = ServiceGenerator.getInstance(URL_BASE).createServiceWithAuth(APIClient.class, accessToken);
            Call<List<UsuarioMensagemResource>> call = client.findAllUsuariosMensagem();
            final String finalURL_BASE = URL_BASE;
            call.enqueue(new Callback<List<UsuarioMensagemResource>>() {
                @Override
                public void onResponse(Call<List<UsuarioMensagemResource>> call, Response<List<UsuarioMensagemResource>> response) {
                    if (response.isSuccessful()) {
                        List<UsuarioMensagemResource> responseBody = response.body();
                        view.onSucessoFindUsuarios(responseBody);
                    } else {
                        Messenger messenger = ErrorUtils.parseError(response, finalURL_BASE);
                        view.onErroFindUsuarios(messenger);
                    }
                }

                @Override
                public void onFailure(Call<List<UsuarioMensagemResource>> call, Throwable t) {
                    view.onErroFindUsuarios(t.getMessage());
                    Log.e(TAG, t.toString(), t);
                }
            });
        }catch (Exception ex){
            view.onErroFindUsuarios(ex.getMessage());
            Log.e(TAG, ex.toString(), ex);
        }
    }
}
