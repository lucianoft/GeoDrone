package br.com.geodrone.ui;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Calendar;
import java.util.Date;

import br.com.geodrone.R;
import br.com.geodrone.SessionGeooDrone;
import br.com.geodrone.dto.DownloadFile;
import br.com.geodrone.model.Cliente;
import br.com.geodrone.model.Configuracao;
import br.com.geodrone.oauth.APIClient;
import br.com.geodrone.oauth.ServiceGenerator;
import br.com.geodrone.oauth.dto.AccessToken;
import br.com.geodrone.service.ConfiguracaoService;
import br.com.geodrone.ui.base.BaseActivity;
import br.com.geodrone.ui.base.BaseRelatorioActivity;
import br.com.geodrone.ui.helper.GenericProgress;
import br.com.geodrone.ui.helper.MessageUI;
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
 * Created by fernandes on 04/06/2018.
 */

public class RelatorioUtils {

    private BaseRelatorioActivity activity;

    public RelatorioUtils(BaseRelatorioActivity baseActivity) {
        this.activity = baseActivity;
    }

    private int totalFileSize;
    private Downloader downloader;

    public void iniciarDownload(DownloadFile downloadFile){
        Downloader downloader = new Downloader();
        downloader.execute(downloadFile);
    }

    private void downloadFile(final DownloadFile downloadFile)  {
        try {
            int count;
            byte data[] = new byte[1024 * 4];
            long fileSize = downloadFile.getResponseBody().contentLength();
            InputStream bis = new BufferedInputStream(downloadFile.getResponseBody().byteStream(), 1024 * 8);

            OutputStream output = null;
            File outputFile = null;
            if (Environment.getExternalStorageState() == null)
                output = activity.openFileOutput(downloadFile.getFileName(), Context.MODE_PRIVATE);
            else {
                outputFile = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), "/arquivos" + downloadFile.getFileName());
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
                    timeCount++;
                }

                output.write(data, 0, count);
            }
            output.flush();
            output.close();
            bis.close();
            activity.onRelatorioSucesso(activity.getString(R.string.msg_operacao_sucesso), outputFile);

        }catch (Exception ex){
            activity.onRelatorioError(ex.toString());
            ex.printStackTrace();
        }
    }

    private class Downloader extends AsyncTask<DownloadFile, Integer, Integer> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected Integer doInBackground(DownloadFile... params) {
            downloadFile(params[0]);
            return null;
        }
        @Override
        protected void onPostExecute(Integer result) {
            super.onPostExecute(result);

        }
    }


}
