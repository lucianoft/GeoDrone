package br.com.geodrone.ui.mensagem;

import android.util.Log;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.geodrone.R;
import br.com.geodrone.SessionGeooDrone;
import br.com.geodrone.model.Cliente;
import br.com.geodrone.model.Configuracao;
import br.com.geodrone.model.Dispositivo;
import br.com.geodrone.model.Usuario;
import br.com.geodrone.model.constantes.FlagDestinoMensagem;
import br.com.geodrone.oauth.APIClient;
import br.com.geodrone.oauth.ServiceGenerator;
import br.com.geodrone.oauth.dto.AccessToken;
import br.com.geodrone.resource.MensagemResource;
import br.com.geodrone.resource.MensagemResource;
import br.com.geodrone.service.ConfiguracaoService;
import br.com.geodrone.ui.base.BaseActivity;
import br.com.geodrone.ui.base.BasePresenter;
import br.com.geodrone.utils.Constantes;
import br.com.geodrone.utils.DateUtils;
import br.com.geodrone.utils.ErrorUtils;
import br.com.geodrone.utils.Messenger;
import br.com.geodrone.utils.NumberUtils;
import br.com.geodrone.utils.PreferencesUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by fernandes on 01/05/2018.
 */

public class MensagemPresenter extends BasePresenter<MensagemPresenter.View> {

    private static final String TAG = MensagemPresenter.class.getSimpleName() ;



    interface View {
        void onClickMensagem();
        void insert(String msg);
        void onMensagemSucesso(String msg);
        void onMensagemErro(String msg);
        void onMensagemErro(Messenger messenger);
        void onListMessage(List<MensagemResource> mensagemResources);
    }

    private BaseActivity activity;
    private Long idUsuario;

    ConfiguracaoService configuracaoService = null;

    public MensagemPresenter(BaseActivity activity, Long idUsuario) {
        this.activity = activity;
        this.idUsuario = idUsuario;
        this.configuracaoService = new ConfiguracaoService(activity);
    }

    public boolean validarSalvar(String msg) {
        boolean isOk = true;
        if (hasView()) {
            if (msg == null){
                view.onMensagemErro(activity.getString(R.string.msg_obr_nome));
                isOk = false;
            }


        }
        return isOk;
    }

    private MensagemResource criarMensagem(String msg) {
        Dispositivo dispositivo = SessionGeooDrone.getAttribute(SessionGeooDrone.CHAVE_DISPOSITIVO);

        NumberUtils numberUtils = new NumberUtils();
        MensagemResource mensagem = new MensagemResource();
        mensagem.setId(null);
        mensagem.setMensagem(msg);
        mensagem.setIdUsuario(idUsuario);
        mensagem.setFlagDestino(FlagDestinoMensagem.ADMINISTRADOR.value());
        mensagem.setDtRegistro(null);
        mensagem.setIdDispositivo(dispositivo.getId());

        return mensagem;

    }

    public void salvar(String msg){

        try{
            boolean isOk = validarSalvar(msg);

            if (isOk) {
                MensagemResource clienteResource = criarMensagem(msg);
                Configuracao configuracao = configuracaoService.getOneConfiguracao();
                String URL_BASE = configuracao != null ? configuracao.getUrl() : null;
                if (URL_BASE == null) {
                    URL_BASE = Constantes.API_LOGIN_URL;
                }
                AccessToken accessToken =  PreferencesUtils.getAccessToken(activity);

                APIClient client = ServiceGenerator.getInstance(URL_BASE).createServiceWithAuth(APIClient.class, accessToken);
                Call<MensagemResource> call = client.insert(clienteResource);
                final String finalURL_BASE = URL_BASE;
                call.enqueue(new Callback<MensagemResource>() {
                    @Override
                    public void onResponse(Call<MensagemResource> call, Response<MensagemResource> response) {
                        if (response.isSuccessful()) {
                            MensagemResource sincronizacaoResource = response.body();
                            view.onMensagemSucesso(activity.getString(R.string.msg_operacao_sucesso));
                        } else {
                            Messenger messenger = ErrorUtils.parseError(response, finalURL_BASE);
                            view.onMensagemErro(messenger);
                        }
                    }

                    @Override
                    public void onFailure(Call<MensagemResource> call, Throwable t) {
                        view.onMensagemErro(t.getMessage());
                        Log.e(TAG, t.toString(), t);
                    }
                });
            }
        }catch (Exception ex){
            view.onMensagemErro(ex.getMessage());
            Log.e(TAG, ex.toString(), ex);
        }
    }

    public void findMensagensByUsuario(Long idUsuario) {
        try{
            Configuracao configuracao = configuracaoService.getOneConfiguracao();
            String URL_BASE = configuracao != null ? configuracao.getUrl() : null;
            if (URL_BASE == null) {
                URL_BASE = Constantes.API_LOGIN_URL;
            }
            AccessToken accessToken =  PreferencesUtils.getAccessToken(activity);

            APIClient client = ServiceGenerator.getInstance(URL_BASE).createServiceWithAuth(APIClient.class, accessToken);
            Call<List<MensagemResource>> call = client.findAllMensagemsByUsuario(idUsuario);
            final String finalURL_BASE = URL_BASE;
            call.enqueue(new Callback<List<MensagemResource>>() {
                @Override
                public void onResponse(Call<List<MensagemResource>> call, Response<List<MensagemResource>> response) {
                    if (response.isSuccessful()) {
                        List<MensagemResource> mensagemResources = response.body();
                        view.onListMessage(mensagemResources);
                    } else {
                        Messenger messenger = ErrorUtils.parseError(response, finalURL_BASE);
                        view.onMensagemErro(messenger);
                    }
                }

                @Override
                public void onFailure(Call<List<MensagemResource>> call, Throwable t) {
                    view.onMensagemErro(t.getMessage());
                    Log.e(TAG, t.toString(), t);
                }
            });
        }catch (Exception ex){
            view.onMensagemErro(ex.getMessage());
            Log.e(TAG, ex.toString(), ex);
        }
    }
}
