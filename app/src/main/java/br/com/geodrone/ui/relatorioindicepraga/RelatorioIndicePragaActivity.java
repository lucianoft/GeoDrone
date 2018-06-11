package br.com.geodrone.ui.relatorioindicepraga;

import android.Manifest;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.EditText;

import java.util.Calendar;
import java.util.Date;

import br.com.geodrone.R;
import br.com.geodrone.ui.base.BaseRelatorioActivity;
import br.com.geodrone.ui.helper.GenericProgress;
import br.com.geodrone.utils.DateUtils;
import br.com.geodrone.utils.Messenger;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RelatorioIndicePragaActivity extends BaseRelatorioActivity
                                          implements  DatePickerDialog.OnDateSetListener,
                                                      RelatorioIndicePragaPresenter.View {

    public static final int FLAG_START_DATE = 0;
    public static final int FLAG_END_DATE = 1;

    @BindView(R.id.edit_text_dt_inicio)
    EditText editTextDtInicio;

    @BindView(R.id.edit_text_dt_fim)
    EditText editTextDtFim;

    private int flag = 0;

    private GenericProgress mProgress;
    private RelatorioIndicePragaPresenter relatorioIndicePragaPresenter;

    private int REQ_STORAGE_PERMISSION = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_relatorio_indice_praga);
        ButterKnife.bind(this);

        mProgress = new GenericProgress(this);
        relatorioIndicePragaPresenter = new RelatorioIndicePragaPresenter(this);
        if (!hasPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            requestPermissionsSafely(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQ_STORAGE_PERMISSION);
        }
    }

    @Override
    public void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        relatorioIndicePragaPresenter.takeView(this);
    }

    public void setFlag(int i) {
        flag = i;
    }

    @OnClick({R.id.text_input_layout_dt_inicio, R.id.edit_text_dt_inicio})
    public void createDatePickerDialogInicio() {
        setFlag(FLAG_START_DATE);
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

    @OnClick({R.id.text_input_layout_dt_fim, R.id.edit_text_dt_fim})
    public void createDatePickerDialogFim() {
        setFlag(FLAG_END_DATE);
        createDatePicker();
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

        if (flag == FLAG_START_DATE){
            editTextDtInicio.setText(dataStr);
        }else if (flag == FLAG_END_DATE){
            editTextDtFim.setText(dataStr);
        }
    }

    @OnClick(R.id.btn_req_relatorio_praga)
    @Override
    public void onClickRelatorio() {
        showLoading();
        relatorioIndicePragaPresenter.gerarRelatorio(editTextDtInicio.getText().toString(), editTextDtFim.getText().toString());
    }

    @Override
    public void onErrorDtInicio(String message) {
        hideLoading();
        editTextDtInicio.setError(message);
    }

    @Override
    public void onErrorDtFim(String message) {
        hideLoading();
        editTextDtFim.setError(message);
    }

    @Override
    public void onError(Messenger messenger) {
        hideLoading();
        showMessenger(messenger);
    }

    @Override
    public void showLoading() {
        mProgress.show();
    }

    @Override
    public void hideLoading() {
        mProgress.hide();
    }


}

