package br.com.geodrone.ui.relatorioregistrochuva;

import android.util.Log;

import br.com.geodrone.R;
import br.com.geodrone.SessionGeooDrone;
import br.com.geodrone.dto.DownloadFile;
import br.com.geodrone.model.Cliente;
import br.com.geodrone.model.Configuracao;
import br.com.geodrone.oauth.APIClient;
import br.com.geodrone.oauth.ServiceGenerator;
import br.com.geodrone.oauth.dto.AccessToken;
import br.com.geodrone.service.ConfiguracaoService;
import br.com.geodrone.ui.RelatorioUtils;
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
 * Created by fernandes on 06/06/2018.
 */

public class RelatorioRegistroChuvaPresenter extends BasePresenter<RelatorioRegistroChuvaPresenter.View> {

    interface View {
        void onClickRelatorio();
        void onErrorDtInicio(String message);
        void onErrorDtFim(String message);

        void onError(String msg);
        void onError(Messenger messenger);
    }

    private RelatorioUtils relatorioUtils;
    private BaseRelatorioActivity activity;
    private ConfiguracaoService configuracaoService;

    public RelatorioRegistroChuvaPresenter(BaseRelatorioActivity activity){
        this.activity = activity;
        this.configuracaoService = new ConfiguracaoService(activity);
        this.relatorioUtils = new RelatorioUtils(activity);
    }

    private boolean validarGerarRelatorio(String dtInicio, String dtFim) {
        boolean isOk = true;
        if (hasView()) {
            DateUtils dateUtils = new DateUtils();
            if (dtInicio == null){
                isOk = false;
                view.onErrorDtInicio(activity.getString(R.string.msg_obr_dt_inicio));
            }
            if (dtFim == null){
                isOk = false;
                view.onErrorDtFim(activity.getString(R.string.msg_obr_dt_fim));
            }
            try {
                dtInicio = dateUtils.format(dateUtils.parse(dtInicio, "dd/MM/yyyy"));
            }catch (Exception ex){
                isOk = false;
                view.onErrorDtInicio(activity.getString(R.string.msg_inv_dt_inicio));
            }
            try {
                dtFim = dateUtils.format(dateUtils.parse(dtFim, "dd/MM/yyyy"));
            }catch (Exception ex){
                isOk = false;
                view.onErrorDtFim(activity.getString(R.string.msg_inv_dt_fim));
            }
        }
        return isOk;
    }

    public void gerarRelatorio(String dtInicio, String dtFim){
        try {

            if (!validarGerarRelatorio(dtInicio, dtFim)) return;

            DateUtils dateUtils = new DateUtils();
            dtInicio = dateUtils.format(dateUtils.parse(dtInicio, "dd/MM/yyyy"));
            dtFim = dateUtils.format(dateUtils.parse(dtFim, "dd/MM/yyyy"));

            final String fileName = "relatorioChuva" + System.currentTimeMillis() + ".pdf";

            Configuracao configuracao = configuracaoService.getOneConfiguracao();
            String URL_BASE = configuracao.getUrl();
            if (URL_BASE == null) {
                URL_BASE = Constantes.API_LOGIN_URL;
            }
            Cliente cliente = SessionGeooDrone.getAttribute(SessionGeooDrone.CHAVE_CLIENTE);
            AccessToken accessToken =  PreferencesUtils.getAccessToken(activity);

            APIClient client = ServiceGenerator.getInstance(URL_BASE).createServiceWithAuth(APIClient.class, accessToken);
            Call<ResponseBody> call = client.findRelatorioRegistroChuva(cliente.getId(), dtInicio, dtFim);
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

