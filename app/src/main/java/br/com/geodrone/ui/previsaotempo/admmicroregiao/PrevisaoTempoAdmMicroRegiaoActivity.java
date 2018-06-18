package br.com.geodrone.ui.previsaotempo.admmicroregiao;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import br.com.geodrone.R;
import br.com.geodrone.resource.MicroRegiaoResource;
import br.com.geodrone.ui.base.BaseActivity;
import br.com.geodrone.ui.helper.GenericProgress;
import br.com.geodrone.utils.DateUtils;
import br.com.geodrone.utils.Messenger;
import br.com.geodrone.utils.Util;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PrevisaoTempoAdmMicroRegiaoActivity extends BaseActivity implements DatePickerDialog.OnDateSetListener,
        PrevisaoTempoAdmMicroRegiaoPresenter.View {

    private GenericProgress mProgress;
    private PrevisaoTempoAdmMicroRegiaoPresenter previsaoTempoAdmMicroRegiaoPresenter;
    private File file = null;

    @BindView(R.id.edit_text_fileName)
    EditText editTextFileName;

    @BindView(R.id.spinner_microregiao_prev_tempo)
    Spinner spMicroRegiao;

    @BindView(R.id.edit_text_dt_previsao)
    EditText editTextDtPrevisao;


    List<MicroRegiaoResource> microRegiaoResources = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_previsao_tempo_adm_micro_regiao);
        ButterKnife.bind(this);

        if (!Util.verificaConexao(this)) {
            Util.initToast(this, getString(R.string.msg_sem_conexao_internet));
            finish();
        } else {

            mProgress = new GenericProgress(this);
            previsaoTempoAdmMicroRegiaoPresenter = new PrevisaoTempoAdmMicroRegiaoPresenter(this);
        }
    }

    @Override
    public void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        previsaoTempoAdmMicroRegiaoPresenter.takeView(this);
        previsaoTempoAdmMicroRegiaoPresenter.findAllMicroRegiao();
    }

    private void initCompontes(){
        ArrayAdapter<MicroRegiaoResource> adapterMicroregiao = new ArrayAdapter<MicroRegiaoResource>(this, android.R.layout.simple_spinner_item, microRegiaoResources);
        spMicroRegiao.setAdapter(adapterMicroregiao);
        adapterMicroregiao.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

    }

    @OnClick(R.id.image_view_arquivo_microregiao)
    public void onClickSelecionarArqquivo() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("file/*.pdf");
        startActivityForResult(intent,1);
    }


    @OnClick(R.id.edit_text_dt_previsao)
    public void createDatePickerDialogInicio() {
        createDatePicker();
    }

    private void createDatePicker() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        final DatePickerDialog datePickerDialog = new DatePickerDialog(this, R.style.ThemeOverlay_AppCompat_Dialog, this, year, month, day);
        datePickerDialog.show();
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar calendar = Calendar.getInstance();
        int ano = calendar.get(Calendar.YEAR);
        int mes = calendar.get(Calendar.MONTH)+1;
        int dia = calendar.get(Calendar.DAY_OF_MONTH);
        DateUtils dateUtils = new DateUtils();
        Date data = dateUtils.createDate(dayOfMonth, month, year);
        String dataStr = dateUtils.format(data, "dd/MM/yyyy");
        editTextDtPrevisao.setText(dataStr);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch(requestCode){
            case 1:
                if(resultCode==RESULT_OK){
                    String filePath = data.getData().getPath();
                    file = new File(filePath);
                    editTextFileName.setText(file.getName());
                }
                break;
        }
    }

    @Override
    public void showLoading() {
        mProgress.show();
    }

    @Override
    public void hideLoading() {
        mProgress.hide();
    }

    @Override
    public void onListMicroRegiao(List<MicroRegiaoResource> microregiaoResources) {
        this.microRegiaoResources = microregiaoResources;
        initCompontes();
        hideLoading();
    }

    @Override
    public void onEnvioArquivoSucesso(String message) {
        showMessage(message);
        clear();
        hideLoading();
    }

    @Override
    public void onError(Messenger messenger) {
        hideLoading();
        showMessenger(messenger);
    }

    @Override
    public void onErrorFile(String message) {
        hideLoading();
        editTextFileName.setError(message);
    }

    @Override
    public void onErrorMicroRegiao(String message) {
        hideLoading();
        showMessage(message);

    }

    @Override
    public void onErrorDtPrevisao(String message) {
        hideLoading();
        editTextDtPrevisao.setError(message);
    }

    @OnClick(R.id.button_enviar_file_microregiao)
    public void onClickEnviarArqquivo() {
        showLoading();
        MicroRegiaoResource microregiao = (MicroRegiaoResource) spMicroRegiao.getSelectedItem();
        previsaoTempoAdmMicroRegiaoPresenter.enviarArquivo(file, microregiao, editTextDtPrevisao.getText().toString());
    }

    public void clear(){
        editTextFileName.setText("");
        editTextDtPrevisao.setText("");
        file = null;
    }
}
