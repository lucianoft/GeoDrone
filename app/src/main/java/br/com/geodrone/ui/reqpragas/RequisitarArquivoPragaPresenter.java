package br.com.geodrone.ui.reqpragas;

import android.content.Intent;
import android.os.Environment;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

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
import br.com.geodrone.utils.Download;
import br.com.geodrone.utils.ErrorUtils;
import br.com.geodrone.utils.FileUtils;
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

    private int totalFileSize;
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
                        ResponseBody responseBody = response.body();
                        downloadFile(responseBody, "imagem.jpg");
                        //FileUtils.writeResponseBodyToDisk(responseBody, activity.getExternalFilesDir(null) + File.separator + "Future Studio Icon.png");
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


    private void downloadFile(ResponseBody body, String fileName)  {

        try {
            int count;
            byte data[] = new byte[1024 * 4];
            long fileSize = body.contentLength();
            InputStream bis = new BufferedInputStream(body.byteStream(), 1024 * 8);
            File outputFile = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), fileName);
            OutputStream output = new FileOutputStream(outputFile);
            long total = 0;
            long startTime = System.currentTimeMillis();
            int timeCount = 1;
            while ((count = bis.read(data)) != -1) {

                total += count;
                totalFileSize = (int) (fileSize / (Math.pow(1024, 2)));
                double current = Math.round(total / (Math.pow(1024, 2)));

                int progress = (int) ((total * 100) / fileSize);

                long currentTime = System.currentTimeMillis() - startTime;

                Download download = new Download();
                download.setTotalFileSize(totalFileSize);

                if (currentTime > 1000 * timeCount) {

                    download.setCurrentFileSize((int) current);
                    download.setProgress(progress);
                    sendNotification(download);
                    timeCount++;
                }

                output.write(data, 0, count);
            }
            onDownloadComplete();
            output.flush();
            output.close();
            bis.close();
        }catch (Exception ex){
            view.onRelatorioError(ex.toString());
        }
    }

    private void sendNotification(Download download){

     }

    private void sendIntent(Download download){

      /*  Intent intent = new Intent(RequisitarArquivoPragaActivity.MESSAGE_PROGRESS);
        intent.putExtra("download",download);
        LocalBroadcastManager.getInstance(RequisitarArquivoService.this).sendBroadcast(intent);*/
    }

    private void onDownloadComplete(){

        Download download = new Download();
        download.setProgress(100);
        sendIntent(download);


    }
}
