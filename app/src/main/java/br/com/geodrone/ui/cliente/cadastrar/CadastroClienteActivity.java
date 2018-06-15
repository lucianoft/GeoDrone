package br.com.geodrone.ui.cliente.cadastrar;

import android.os.Bundle;
import android.widget.EditText;

import br.com.geodrone.R;
import br.com.geodrone.activity.utils.Mask;
import br.com.geodrone.ui.base.BaseActivity;
import br.com.geodrone.ui.helper.GenericProgress;
import br.com.geodrone.utils.Messenger;
import br.com.geodrone.utils.Util;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CadastroClienteActivity extends BaseActivity implements CadastroClientePresenter.View{

    @BindView(R.id.edit_text_nome_cliente) EditText editTextNome;
    @BindView(R.id.edit_text_cpf_cliente) EditText editTextCpf;
    @BindView(R.id.edit_text_email_cliente) EditText editTextEmail;
    @BindView(R.id.edit_text_segmento_cliente) EditText editTextSegmento;
    @BindView(R.id.edit_text_telefone_cliente) EditText editTextTelefone;
    @BindView(R.id.edit_text_celular_cliente) EditText editTextCelular;
    @BindView(R.id.edit_text_logradouro_cliente) EditText editTextLogradouro;
    @BindView(R.id.edit_text_bairro_cliente) EditText editTextBairro;
    @BindView(R.id.edit_text_localidade_cliente) EditText editTextLocalidade;
    @BindView(R.id.edit_text_numero_cliente) EditText editTextNumero;
    @BindView(R.id.edit_text_complemento_cliente) EditText editTextComplemento;
    @BindView(R.id.edit_text_cep_cliente) EditText editTextCep;

    private GenericProgress mProgress;
    private CadastroClientePresenter cadastroClientePresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_cliente);
        ButterKnife.bind(this);

        if (!Util.verificaConexao(this)) {
            Util.initToast(this, getString(R.string.msg_sem_conexao_internet));
            finish();
        } else {

            mProgress = new GenericProgress(this);
            cadastroClientePresenter = new CadastroClientePresenter(this);
            editTextTelefone.addTextChangedListener(Mask.insert("(##)#####-####", editTextTelefone));
            editTextCelular.addTextChangedListener(Mask.insert("(##)#####-####", editTextCelular));
            editTextCep.addTextChangedListener(Mask.insert("#####-###", editTextCep));
        }
    }

    @Override
    public void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        cadastroClientePresenter.takeView(this);
    }


    @OnClick(R.id.btn_cadastrar_cliente)
    @Override
    public void onClickLogin() {
        mProgress.show();
        cadastroClientePresenter.salvar(null,
                editTextNome.getText().toString(),
                editTextCpf.getText().toString(),
                editTextEmail.getText().toString(),
                editTextSegmento.getText().toString(),
                editTextTelefone.getText().toString(),
                editTextCelular.getText().toString(),
                editTextLogradouro.getText().toString(),
                editTextBairro.getText().toString(),
                editTextLocalidade.getText().toString(),
                editTextNumero.getText().toString(),
                editTextComplemento.getText().toString(),
                editTextCep.getText().toString());
    }

    @Override
    public void onCadastroSucesso(String message) {
        showMessage(message);
        hideLoading();
        finish();
        super.onBackPressed();
    }

    @Override
    public void onCadastroError(String message) {
        showMessage(message);
        hideLoading();
    }

    @Override
    public void onCadastroError(Messenger messenger) {
        showMessenger(messenger);
        mProgress.hide();

    }

    @Override
    public void onErrorNome(String message) {
        editTextNome.setError(message);
    }

    @Override
    public void onErrorTelefone(String message) {
        editTextTelefone.setError(message);
    }

    @Override
    public void onErrorEmail(String message) {
        editTextEmail.setError(message);
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
