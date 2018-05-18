package br.com.geodrone.ui.reqpragas;

import android.util.Log;

import java.io.File;

import br.com.geodrone.R;
import br.com.geodrone.SessionGeooDrone;
import br.com.geodrone.model.Cliente;
import br.com.geodrone.model.Configuracao;
import br.com.geodrone.oauth.APIClient;
import br.com.geodrone.oauth.ServiceGenerator;
import br.com.geodrone.oauth.dto.AccessToken;
import br.com.geodrone.service.ConfiguracaoService;
import br.com.geodrone.ui.base.BaseActivity;
import br.com.geodrone.ui.base.BasePresenter;
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
 * Created by fernandes on 17/05/2018.
 */

public class RequisitarArquivoPragaPresenter extends BasePresenter<RequisitarArquivoPragaPresenter.View> {

    interface View {

        void onClickRelatorio();

        void onRelatorioSucesso(String message, File file);
        void onRelatorioError(String message);
        void onRelatorioError(Messenger messenger);

    }

    private BaseActivity activity;
    private ConfiguracaoService configuracaoService;

    public RequisitarArquivoPragaPresenter(BaseActivity activity){
        this.activity = activity;
        this.configuracaoService = new ConfiguracaoService(activity);
    }

    public void gerarRelatorio(String dtInicio, String dtFim){
        try {
            DateUtils dateUtils = new DateUtils();

            dtInicio = dateUtils.format(dateUtils.parse(dtInicio, "dd/MM/yyyy"));
            dtFim    = dateUtils.format(dateUtils.parse(dtFim, "dd/MM/yyyy"));

            Configuracao configuracao = configuracaoService.getOneConfiguracao();
            String URL_BASE = configuracao.getUrl();
            if (URL_BASE == null) {
                URL_BASE = Constantes.API_LOGIN_URL;
            }
            Cliente cliente = SessionGeooDrone.getAttribute(SessionGeooDrone.CHAVE_CLIENTE);
            AccessToken accessToken =  PreferencesUtils.getAccessToken(activity);

            APIClient client = ServiceGenerator.getInstance(URL_BASE).createServiceWithAuth(APIClient.class, accessToken);
            Call<ResponseBody> call = client.findRelatorioRegistroPraga(cliente.getId(), dtInicio, dtFim);
            final String finalURL_BASE = URL_BASE;
            call.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    if (response.isSuccessful()) {
                        ResponseBody file = response.body();
                        view.onRelatorioSucesso(activity.getString(R.string.msg_operacao_sucesso), null);
                    } else {
                        Messenger messenger = ErrorUtils.parseError(response, finalURL_BASE);
                        view.onRelatorioError(messenger);
                    }
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    view.onRelatorioError(t.getMessage());
                    Log.e(TAG, t.toString(), t);
                }
            });
        }catch (Exception ex){
            view.onRelatorioError(ex.getMessage());
            Log.e(TAG, ex.toString(), ex);
        }
    }
}
