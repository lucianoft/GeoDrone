package br.com.geodrone.ui.previsaotempoadmcliente;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
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
import br.com.geodrone.resource.ClienteResource;
import br.com.geodrone.resource.MicroRegiaoResource;
import br.com.geodrone.ui.base.BaseActivity;
import br.com.geodrone.ui.cliente.cadastrar.CadastroClienteActivity;
import br.com.geodrone.ui.cliente.consultar.ConsultarClientePresenter;
import br.com.geodrone.ui.helper.GenericProgress;
import br.com.geodrone.ui.login.LoginActivity;
import br.com.geodrone.utils.DateUtils;
import br.com.geodrone.utils.Messenger;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PrevisaoTempoAdmClienteActivity extends BaseActivity implements DatePickerDialog.OnDateSetListener,
                                                                             PrevisaoTempoAdmClientePresenter.View {

    private GenericProgress mProgress;
    private PrevisaoTempoAdmClientePresenter previsaoTempoAdmClientePresenter;
    private File file = null;

    @BindView(R.id.edit_text_fileName)
    EditText editTextFileName;

    @BindView(R.id.spinner_cliente_prev_tempo)
    Spinner spCliente;

    @BindView(R.id.edit_text_dt_previsao)
    EditText editTextDtPrevisao;


    List<ClienteResource> clienteResources = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_previsao_tempo_adm_cliente);
        ButterKnife.bind(this);

        mProgress = new GenericProgress(this);
        previsaoTempoAdmClientePresenter = new PrevisaoTempoAdmClientePresenter(this);
    }

    @Override
    public void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        previsaoTempoAdmClientePresenter.takeView(this);
        previsaoTempoAdmClientePresenter.findAllCliente();
    }

    private void initCompontes(){
        ArrayAdapter<ClienteResource> adapterMicroregiao = new ArrayAdapter<ClienteResource>(this, android.R.layout.simple_spinner_item, clienteResources);
        spCliente.setAdapter(adapterMicroregiao);
        adapterMicroregiao.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

    }

    @OnClick(R.id.image_view_arquivo_cliente)
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
    public void onListCliente(List<ClienteResource> clienteResources) {
        this.clienteResources = clienteResources;
        initCompontes();
        hideLoading();
    }

    @Override
    public void onEnvioArquivoSucesso(String message) {
        showMessage(message);
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
    public void onErrorCliente(String message) {
        hideLoading();
        showMessage(message);

    }

    @Override
    public void onErrorDtPrevisao(String message) {
        hideLoading();
        editTextDtPrevisao.setError(message);
    }

    @OnClick(R.id.button_enviar_file_cliente)
    public void onClickEnviarArqquivo() {
        showLoading();
        ClienteResource cliente = (ClienteResource) spCliente.getSelectedItem();
        previsaoTempoAdmClientePresenter.enviarArquivo(file, cliente, editTextDtPrevisao.getText().toString());
    }
}
