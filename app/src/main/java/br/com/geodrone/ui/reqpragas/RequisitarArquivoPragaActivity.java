package br.com.geodrone.ui.reqpragas;

import android.app.DatePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.EditText;

import java.io.File;
import java.util.Calendar;
import java.util.Date;

import br.com.geodrone.R;
import br.com.geodrone.ui.base.BaseActivity;
import br.com.geodrone.ui.helper.GenericProgress;
import br.com.geodrone.utils.DateUtils;
import br.com.geodrone.utils.Messenger;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RequisitarArquivoPragaActivity extends BaseActivity implements DatePickerDialog.OnDateSetListener, RequisitarArquivoPragaPresenter.View{

    public static final int FLAG_START_DATE = 0;
    public static final int FLAG_END_DATE = 1;

    @BindView(R.id.edit_text_dt_inicio_req_praga)
    EditText editTextDtInicio;

    @BindView(R.id.edit_text_dt_fim_req_praga)
    EditText editTextDtFim;

    private int flag = 0;

    private GenericProgress mProgress;
    private RequisitarArquivoPragaPresenter requisitarArquivoPragaPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_requisitar_arquivo_praga);
        ButterKnife.bind(this);

        mProgress = new GenericProgress(this);
        requisitarArquivoPragaPresenter = new RequisitarArquivoPragaPresenter(this);
    }

    @Override
    public void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        requisitarArquivoPragaPresenter.takeView(this);
    }

    public void setFlag(int i) {
        flag = i;
    }

   @OnClick({R.id.text_input_layout_dt_inicio_req_praga, R.id.edit_text_dt_inicio_req_praga})
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

    @OnClick({R.id.text_input_layout_dt_fim_req_praga, R.id.edit_text_dt_fim_req_praga})
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
        requisitarArquivoPragaPresenter.gerarRelatorio(editTextDtInicio.getText().toString(), editTextDtFim.getText().toString());
    }

    @Override
    public void onRelatorioSucesso(String message, File file) {

    }

    @Override
    public void onRelatorioError(String message) {

    }

    @Override
    public void onRelatorioError(Messenger messenger) {

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
