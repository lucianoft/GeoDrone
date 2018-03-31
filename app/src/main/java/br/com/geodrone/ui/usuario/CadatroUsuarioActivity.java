package br.com.geodrone.ui.usuario;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

import br.com.geodrone.R;
import br.com.geodrone.activity.utils.Mask;
import br.com.geodrone.ui.base.BaseActivity;
import br.com.geodrone.ui.login.LoginPresenter;
import butterknife.BindView;
import butterknife.ButterKnife;

public class CadatroUsuarioActivity extends BaseActivity implements UsuarioPresenter.View{

    private UsuarioPresenter usuarioPresenter;

    @BindView(R.id.input_nome_usuario) EditText editTextNome;
    @BindView(R.id.input_sobrenome_usuario) EditText editTextSobreNome;
    @BindView(R.id.input_telefone_usuario) EditText editTextTelefone;
    @BindView(R.id.input_email_usuario) EditText editTextEmail;
    @BindView(R.id.input_senha_usuario) EditText editTextSenha;
    @BindView(R.id.input_confirmar_senha_usuario) EditText editTextConfirmarSenha;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadatro_usuario);
        ButterKnife.bind(this);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //Mostrar o botão
        getSupportActionBar().setHomeButtonEnabled(true);      //Ativar o botão

        usuarioPresenter = new UsuarioPresenter(this);
        editTextTelefone.addTextChangedListener(Mask.insert("(##)#####-####", editTextTelefone));

    }
    @Override
    public void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        usuarioPresenter.takeView(this);
        hideLoading();
    }


    @Override
    public void onClickLogin() {
        usuarioPresenter.salvar(editTextNome.getText().toString(),
                editTextSobreNome.getText().toString(),
                editTextTelefone.getText().toString(),
                editTextEmail.getText().toString(),
                editTextSenha.getText().toString(),
                editTextConfirmarSenha.getText().toString());
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public void onCadastroSucesso(String message) {
        hideLoading();
        finish();
        onBackPressed();
    }

    @Override
    public void onErrorNome(String message) {
        editTextNome.setError(message);
    }

    @Override
    public void onErrorSobrenome(String message) {
        editTextSobreNome.setError(message);
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
    public void onErrorSenha(String message) {
        editTextSobreNome.setError(message);
    }

    @Override
    public void onErrorConfirmSenha(String message) {
        editTextConfirmarSenha.setError(message);
    }
}
