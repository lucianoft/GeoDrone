package br.com.geodrone.ui.registrodefensivo;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import br.com.geodrone.R;
import br.com.geodrone.model.TipoDefensivo;
import br.com.geodrone.ui.base.BaseActivity;
import br.com.geodrone.ui.helper.GenericProgress;
import br.com.geodrone.utils.DateUtils;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemSelected;

public class RegistroDefensivoActivity extends BaseActivity implements  RegistroDefensivoPresenter.View, DatePickerDialog.OnDateSetListener{

    @BindView(R.id.spinner_tipo_defensivo)
    Spinner spiTipoDefensivo;
    @BindView(R.id.edit_text_dt_registro)
    EditText editTextDtRegistro;
    @BindView(R.id.edit_text_dose_defensivo) EditText editTextDose;

    private GenericProgress mProgress;
    private RegistroDefensivoPresenter registroDefensivoPresenter;
    private List<TipoDefensivo> tipoDefensivoList = new ArrayList<>();
    private TipoDefensivo tipoDefensivo = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_defensivo);
        ButterKnife.bind(this);

        //getSupportActionBar().setDisplayHomeAsUpEnabled(true); //Mostrar o botão
        //getSupportActionBar().setHomeButtonEnabled(true);      //Ativar o botão

        mProgress = new GenericProgress(this);
        registroDefensivoPresenter = new RegistroDefensivoPresenter(this);

        initComponentes();
    }    
    @Override
    public void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        registroDefensivoPresenter.takeView(this);
    }

    private void initComponentes() {
        tipoDefensivoList = registroDefensivoPresenter.findAllTipoDefensivo();
       
        ArrayAdapter<TipoDefensivo> myAdapter = new ArrayAdapter<TipoDefensivo>(this, android.R.layout.simple_spinner_item, tipoDefensivoList);
        spiTipoDefensivo.setAdapter(myAdapter);
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

    }

    @OnClick(R.id.edit_text_dt_registro)
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

    @OnItemSelected(R.id.spinner_tipo_defensivo)
    public void onChanceTipoDefensivo(int position) {
        tipoDefensivo = (TipoDefensivo) tipoDefensivoList.get(position);
    }

    @OnClick(R.id.btn_salvar_defensivo)
    public void salvar() {
        showLoading();
        registroDefensivoPresenter.salvar(tipoDefensivo, editTextDtRegistro.getText().toString(), editTextDose.getText().toString());
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
    public void onErrorQtde(String message) {
        editTextDose.setError(message);
        hideLoading();
    }

    @Override
    public void onErrorTipoDefensivo(String message) {
        showMessage(message);
        hideLoading();

    }

    @Override
    public void onErrorDtRegistro(String message) {
        editTextDtRegistro.setError(message);
        hideLoading();
    }


    @Override
    public void onRegitroDefensivoSucesso(String message) {
        showMessage(message);
        hideLoading();
        finish();
    }

    @Override
    public void onErrorRegitroDefensivo(String message) {
        hideLoading();

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

        editTextDtRegistro.setText(dataStr);
    }
}
