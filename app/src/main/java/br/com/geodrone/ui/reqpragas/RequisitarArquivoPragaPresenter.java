package br.com.geodrone.ui.reqpragas;

import android.app.NotificationManager;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Environment;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationCompat.Builder;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
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

    private NotificationManager mNotifyManager;
    private Builder mBuilder;
    int id = 1;


    private Downloader downloader;
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
                        mNotifyManager = (NotificationManager) activity.getSystemService(Context.NOTIFICATION_SERVICE);
                        mBuilder = new NotificationCompat.Builder(activity, "download_id");
                        mBuilder.setContentTitle("Download")
                                .setContentText("Download in progress")
                                .setSmallIcon(R.drawable.ic_notifications_black_24dp);

                        downloader = new Downloader();
                        downloader.execute(responseBody);
                    }else if (response.code() == 500) {
                        view.onRelatorioError(activity.getString(R.string.msg_erro_500));

                    } else{
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

            OutputStream output = null;
            File outputFile = null;
           if (Environment.getExternalStorageState() == null) {
                //create new file directory object
                output = activity.openFileOutput(fileName, Context.MODE_PRIVATE);
           } else {
               outputFile = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), fileName);
              output = new FileOutputStream(outputFile);
            }
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
            output.flush();
            output.close();
            bis.close();
            view.onRelatorioSucesso(activity.getString(R.string.msg_operacao_sucesso), outputFile);

        }catch (Exception ex){
            view.onRelatorioError(ex.toString());
        }
    }

    private void sendNotification(Download download){

        sendIntent(download);
        mBuilder.setProgress(download.getTotalFileSize(),download.getProgress(),false);
        mBuilder.setContentText("Downloading file "+ download.getCurrentFileSize() +"/"+totalFileSize +" MB");
        mNotifyManager.notify(0, mBuilder.build());
        downloader.onProgressUpdate(download.getProgress());
    }

    private void sendIntent(Download download){

      /*  Intent intent = new Intent(RequisitarArquivoPragaActivity.MESSAGE_PROGRESS);
        intent.putExtra("download",download);
        LocalBroadcastManager.getInstance(RequisitarArquivoService.this).sendBroadcast(intent);*/
    }

    private class Downloader extends AsyncTask<ResponseBody, Integer, Integer> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            // Displays the progress bar for the first time.
            mBuilder.setProgress(100, 0, false);
            mNotifyManager.notify(id, mBuilder.build());
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            mBuilder.setProgress(100, values[0], false);
            mNotifyManager.notify(id, mBuilder.build());
            super.onProgressUpdate(values);
        }

        @Override
        protected Integer doInBackground(ResponseBody... params) {
            downloadFile(params[0],  "relatorioPraga.pdf");
            return null;
        }



        @Override
        protected void onPostExecute(Integer result) {
            super.onPostExecute(result);
            mBuilder.setContentText("Download completo");
            // Removes the progress bar
            mBuilder.setProgress(0, 0, false);
            mNotifyManager.notify(id, mBuilder.build());

        }
    }
}
