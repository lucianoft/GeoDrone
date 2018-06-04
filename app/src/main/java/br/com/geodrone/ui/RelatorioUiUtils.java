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
import br.com.geodrone.ui.helper.GenericProgress;
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

public class RelatorioUiUtils {

    private BaseActivity activity;
    private ConfiguracaoService configuracaoService;

    public RelatorioUiUtils(BaseActivity baseActivity) {
        this.activity = baseActivity;
        configuracaoService = new ConfiguracaoService(baseActivity);
    }

    private int totalFileSize;
    private Downloader downloader;

    public void relatorioPluviosidade() {
        LayoutInflater li = activity.getLayoutInflater();
        View view = li.inflate(R.layout.dialog_relatorio_registro_chuva, null);
        final EditText ediTextDtInicio = view.findViewById(R.id.edit_text_dt_inicio_req_praga);

        ediTextDtInicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                //To show current date in the datepicker
                Calendar mcurrentDate=Calendar.getInstance();
                int mYear = mcurrentDate.get(Calendar.YEAR);
                int mMonth = mcurrentDate.get(Calendar.MONTH);
                int mDay = mcurrentDate.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog mDatePicker=new DatePickerDialog(activity, new DatePickerDialog.OnDateSetListener() {
                    public void onDateSet(DatePicker datepicker, int year, int month, int dayOfMonth) {
                        Calendar calendar = Calendar.getInstance();
                        int ano = calendar.get(Calendar.YEAR);
                        int mes = calendar.get(Calendar.MONTH)+1;
                        int dia = calendar.get(Calendar.DAY_OF_MONTH);
                        DateUtils dateUtils = new DateUtils();
                        Date data = dateUtils.createDate(dayOfMonth, month, year);
                        String dataStr = dateUtils.format(data, "dd/MM/yyyy");
                        ediTextDtInicio.setText(dataStr);
                     }
                },mYear, mMonth, mDay);
                mDatePicker.setTitle("Select date");
                mDatePicker.show();  }
        });
        final EditText ediTextDtFim = view.findViewById(R.id.edit_text_dt_fim_req_praga);
        ediTextDtFim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar mcurrentDate=Calendar.getInstance();
                int mYear = mcurrentDate.get(Calendar.YEAR);
                int mMonth = mcurrentDate.get(Calendar.MONTH);
                int mDay = mcurrentDate.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog mDatePicker=new DatePickerDialog(activity, new DatePickerDialog.OnDateSetListener() {
                    public void onDateSet(DatePicker datepicker, int year, int month, int dayOfMonth) {
                        Calendar calendar = Calendar.getInstance();
                        int ano = calendar.get(Calendar.YEAR);
                        int mes = calendar.get(Calendar.MONTH)+1;
                        int dia = calendar.get(Calendar.DAY_OF_MONTH);
                        DateUtils dateUtils = new DateUtils();
                        Date data = dateUtils.createDate(dayOfMonth, month, year);
                        String dataStr = dateUtils.format(data, "dd/MM/yyyy");
                        ediTextDtFim.setText(dataStr);
                    }
                },mYear, mMonth, mDay);
                mDatePicker.setTitle("Select date");
                mDatePicker.show();  }
        });
        final AlertDialog.Builder alert = new AlertDialog.Builder(activity);
        alert.setTitle(activity.getString(R.string.lb_alterar_senha));
        alert.setView(view);
        alert.setCancelable(false);
        alert.setNegativeButton(R.string.lb_cancelar, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        final GenericProgress genericProgress = new GenericProgress(activity);;
        alert.setPositiveButton(R.string.lb_confirmar, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                gerarRelatorioRegistroChuva(ediTextDtInicio.getText().toString(), ediTextDtFim.getText().toString());
            }
        });
        AlertDialog dialog = alert.create();
        dialog.show();

    }

    public void gerarRelatorioRegistroChuva(String dtInicio, String dtFim){
        try {
            DateUtils dateUtils = new DateUtils();

            dtInicio = dateUtils.format(dateUtils.parse(dtInicio, "dd/MM/yyyy"));
            dtFim    = dateUtils.format(dateUtils.parse(dtFim, "dd/MM/yyyy"));
            final String fileName = "relatorioRegistroChuva" + System.currentTimeMillis() + ".pdf";

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

                        downloader = new Downloader();
                        downloader.execute(downloadFile);
                    } else{
                        Messenger messenger = ErrorUtils.parseError(response, finalURL_BASE);
                        activity.showMessenger(messenger);
                    }
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    activity.onError(t.getMessage());
                    Log.e(TAG, t.toString(), t);
                }
            });
        }catch (Exception ex){
            activity.onError(ex);
            Log.e(TAG, ex.toString(), ex);
        }
    }

    private void downloadFile(DownloadFile downloadFile)  {

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
            //view.onRelatorioSucesso(activity.getString(R.string.msg_operacao_sucesso), outputFile);

        }catch (Exception ex){
            //view.onRelatorioError(ex.toString());
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
