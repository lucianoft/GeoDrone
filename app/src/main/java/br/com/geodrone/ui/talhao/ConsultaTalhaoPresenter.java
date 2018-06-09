package br.com.geodrone.ui.talhao;

import android.util.Log;

import java.util.List;

import br.com.geodrone.R;
import br.com.geodrone.SessionGeooDrone;
import br.com.geodrone.model.Cliente;
import br.com.geodrone.model.Configuracao;
import br.com.geodrone.model.Dispositivo;
import br.com.geodrone.model.Talhao;
import br.com.geodrone.model.constantes.FlagDestinoMensagem;
import br.com.geodrone.oauth.APIClient;
import br.com.geodrone.oauth.ServiceGenerator;
import br.com.geodrone.oauth.dto.AccessToken;
import br.com.geodrone.resource.MensagemResource;
import br.com.geodrone.resource.TalhaoResource;
import br.com.geodrone.service.ConfiguracaoService;
import br.com.geodrone.service.TalhaoService;
import br.com.geodrone.ui.base.BaseActivity;
import br.com.geodrone.ui.base.BasePresenter;
import br.com.geodrone.utils.Constantes;
import br.com.geodrone.utils.ErrorUtils;
import br.com.geodrone.utils.Messenger;
import br.com.geodrone.utils.NumberUtils;
import br.com.geodrone.utils.PreferencesUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by fernandes on 06/06/2018.
 */

public class ConsultaTalhaoPresenter extends BasePresenter<ConsultaTalhaoPresenter.View> {

    private static final String TAG = ConsultaTalhaoPresenter.class.getSimpleName();

    interface View {
        void onClickTalhao(Talhao talhao);

        void onSucessoFindTalhao(List<Talhao> talhaos);
        void onErroFindTalhao(String msg);
        void onSucessoSalvar(String msg);
        void onErroSalvar(String msg);
        void onErroSalvar(Messenger messenger);
    }

    private BaseActivity activity;

    TalhaoService talhaoService = null;
    private ConfiguracaoService configuracaoService;

    public ConsultaTalhaoPresenter(BaseActivity activity) {
        this.activity = activity;
        this.talhaoService = new TalhaoService(activity);
        this.configuracaoService = new ConfiguracaoService(activity);
    }

    private List<Talhao> findAllByTalhao(Long idTalhao){
        return talhaoService.findAllByCliente(idTalhao);
    }

    public void findTalhao(){
        try {
            Cliente cliente = SessionGeooDrone.getAttribute(SessionGeooDrone.CHAVE_CLIENTE);

            view.onSucessoFindTalhao(findAllByTalhao(cliente.getId()));
        }catch (Exception ex){
            view.onErroFindTalhao(ex.toString());
        }
    }

    private TalhaoResource criarTalhaoResource(Talhao talhao, String codigo, String descricao, Integer indAtivo) {

        TalhaoResource talhaoResource = new TalhaoResource();
        talhaoResource.setIdTalhao(talhao != null ? talhao.getId() : null);
        talhaoResource.setCodigo(codigo);
        talhaoResource.setDescricao(descricao);
        talhaoResource.setIndAtivo(indAtivo);

        return talhaoResource;
    }

    public void salvarTalhao(TalhaoResource talhaoResource) {
        try {
            boolean isInsert = true;

            Talhao talhao = talhaoService.findById(talhaoResource.getIdTalhao());
            if (talhao == null) {
                talhao = new Talhao();
            }else{
                isInsert = false;
            }

            talhao.setCodigo(talhaoResource.getCodigo());
            talhao.setDescricao(talhaoResource.getDescricao());
            talhao.setIndAtivo(talhaoResource.getIndAtivo());
            if (isInsert) {
                talhaoService.insert(talhao);
            }else{
                talhaoService.update(talhao);
            }
            findTalhao();
            view.onSucessoSalvar(activity.getString(R.string.msg_operacao_sucesso));
        }catch (Exception ex){
            view.onErroSalvar(ex.toString());
        }
    }

    public void salvar(Talhao talhao, String codigo, String descricao, Integer indAtivo){

        try{
            boolean isOk = validarSalvar(codigo, descricao, indAtivo);

            if (isOk) {
                Cliente cliente = SessionGeooDrone.getAttribute(SessionGeooDrone.CHAVE_CLIENTE);

                final TalhaoResource talhaoResource = criarTalhaoResource(talhao, codigo, descricao, indAtivo);
                Configuracao configuracao = configuracaoService.getOneConfiguracao();
                String URL_BASE = configuracao != null ? configuracao.getUrl() : null;
                if (URL_BASE == null) {
                    URL_BASE = Constantes.API_LOGIN_URL;
                }
                AccessToken accessToken =  PreferencesUtils.getAccessToken(activity);

                APIClient client = ServiceGenerator.getInstance(URL_BASE).createServiceWithAuth(APIClient.class, accessToken);
                Call<TalhaoResource> call = client.insertTalhao(cliente.getId(), talhaoResource);
                final String finalURL_BASE = URL_BASE;
                call.enqueue(new Callback<TalhaoResource>() {
                    @Override
                    public void onResponse(Call<TalhaoResource> call, Response<TalhaoResource> response) {
                        if (response.isSuccessful()) {
                            TalhaoResource talhaoResource1 = response.body();
                            salvarTalhao(talhaoResource);
                        } else {
                            Messenger messenger = ErrorUtils.parseError(response, finalURL_BASE);
                            view.onErroSalvar(messenger);
                        }
                    }

                    @Override
                    public void onFailure(Call<TalhaoResource> call, Throwable t) {
                        view.onSucessoSalvar(t.getMessage());
                        Log.e(TAG, t.toString(), t);
                    }
                });
            }
        }catch (Exception ex){
            view.onSucessoSalvar(ex.getMessage());
            Log.e(TAG, ex.toString(), ex);
        }
    }

    public boolean validarSalvar(String codigo,
                                 String descricao,
                                 Integer indAtivo) {
        boolean isOk = true;
        if (hasView()) {
            if (codigo == null){
                view.onErroSalvar(activity.getString(R.string.msg_obr_codigo));
                isOk = false;
            }

            if (descricao == null){
                view.onErroSalvar(activity.getString(R.string.msg_obr_descricao));
                isOk = false;
            }

            if (indAtivo == null){
                view.onErroSalvar(activity.getString(R.string.msg_obr_ativo));
                isOk = false;
            }

        }
        return isOk;
    }



}    
